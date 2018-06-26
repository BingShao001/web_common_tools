package com.netcommon.event;

import java.util.Vector;

public class MyEventSource {
    private int value;
    private Vector<Listener> listenerVector = new Vector<>();

    /**
     * 添加监听器
     * @param listener
     */
    public void addListener(Listener listener){
        listenerVector.add(listener);
    }
    public void setValue(int value){
        this.value = value;
        MyEvent myEvent = new MyEvent(value);
        for (Listener listener : listenerVector){
            listener.eventDataChange(myEvent);
        }
    }
}
