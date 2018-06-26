package com.netcommon.event;

public class MyListener implements Listener {

    @Override
    public void eventDataChange(MyEvent event) {
        System.out.println("event data change : "+event.getValue());
    }
}
