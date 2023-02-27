package com.take5.model;

public abstract class Player{
    public String name;
    PlayingTable table;
    Hand hand;

    abstract void chooseCard();
    abstract void placeCard();

}
