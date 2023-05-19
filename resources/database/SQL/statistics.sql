-- Get average move duration
WITH move_times AS (SELECT end_time - LAG(end_time) OVER (ORDER BY end_time) AS move_time
                    FROM move
                    WHERE game_id = '%s')
SELECT extract('seconds' from AVG(move_time)) || ' seconds' AS avg_move_time
FROM move_times
WHERE move_time IS NOT NULL;


-- Get the values for the chart ( seconds/move)
WITH move_times AS (SELECT end_time - LAG(end_time) OVER (ORDER BY end_time) AS move_time
                    FROM move
                    WHERE game_id = '%s'
                    ORDER BY move_id)
SELECT extract('minutes' from move_time) * 60 + extract('seconds' from move_time)
FROM move_times
WHERE move_times IS NOT NULL;


-- Get the most profitable rounds ( the ones where the player didn't gain any points
SELECT round.round_number, move_number
FROM (SELECT round_id, points, move_number, ROW_NUMBER() OVER (ORDER BY round_id,move_number) AS row_num
      FROM move
      where game_id = '%s') t1
         join round on round.round_id = t1.round_id
WHERE points = (SELECT points
                FROM (SELECT points, ROW_NUMBER() OVER (ORDER BY round_id,t1.move_number) AS row_num
                      FROM move) t2
                WHERE t1.row_num = t2.row_num + 1);


-- Get the moves and the score of the player after that move, the values are latter used in the OutliersCaluculator class
select move_id, points
from move
where game_id = '%s'
order by end_time;