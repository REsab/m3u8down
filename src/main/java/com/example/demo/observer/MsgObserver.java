package com.example.demo.observer;

public class MsgObserver extends Observer {

    public MsgObserver(MsgBean msgBean) {
        this.msgBean = msgBean;
        this.msgBean.attach(this);
    }

    @Override
    public void update() {

        msgBean.getMessage();
    }

}
