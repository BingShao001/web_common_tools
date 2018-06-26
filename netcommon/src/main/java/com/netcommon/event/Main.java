package com.netcommon.event;

public class Main {
    public static void main(String[] args){
     MyEventSource myEventSource = new MyEventSource();
     MyListener listener = new MyListener();
     MyListener listener2 = new MyListener();
     myEventSource.addListener(listener);
     myEventSource.addListener(listener2);
     myEventSource.setValue(10);
     myEventSource.setValue(15);
    }
}
