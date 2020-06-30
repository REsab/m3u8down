package com.example.demo.observer;

public abstract class Observer {
    protected MsgBean msgBean;

    public abstract void update();
}
