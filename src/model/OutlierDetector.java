package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OutlierDetector {

    public static List<Double> getOutliers(List<Double> data) {
        List<Double> outliers = new ArrayList<>();
        Collections.sort(data);

        double median;
        int size = data.size();
        if (size % 2 == 0) {
            median = (data.get(size / 2 - 1) + data.get(size / 2)) / 2.0;
        } else {
            median = data.get(size / 2);
        }

        double q1;
        int q1Index = size / 4;
        if (size % 4 == 0) {
            q1 = (data.get(q1Index - 1) + data.get(q1Index)) / 2.0;
        } else {
            q1 = data.get(q1Index);
        }

        double q3;
        int q3Index = size * 3 / 4;
        if (size % 4 == 0) {
            q3 = (data.get(q3Index - 1) + data.get(q3Index)) / 2.0;
        } else {
            q3 = data.get(q3Index);
        }

        double iqr = q3 - q1;

        for (Double d : data) {
            if (d < median - 1.5 * iqr || d > median + 1.5 * iqr) {
                outliers.add(d);
            }
        }
        List<Double> output = outliers;
        return output;
    }

}

