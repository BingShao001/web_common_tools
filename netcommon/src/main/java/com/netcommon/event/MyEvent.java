package com.netcommon.event;

public class MyEvent<T> {
    private T value;

    public MyEvent(T value){
        this.value = value;
    }
    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
