package com.example.demo.observer;

import java.util.ArrayList;
import java.util.List;

public class MsgBean {

    private List<Observer> list = new ArrayList<Observer>();
    private String Message;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
        notifyAllObservers();
    }

    public void attach(Observer observer) {
        this.list.add(observer);
    }

    public void notifyAllObservers() {
        for (Observer observer : list) {
            observer.update();
        }
    }


}
