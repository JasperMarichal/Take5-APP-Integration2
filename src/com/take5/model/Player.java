package com.take5.model;

public abstract class Player{
    public String name;
    Hand hand;

    abstract void chooseCard();
    abstract void placeCard();

}
