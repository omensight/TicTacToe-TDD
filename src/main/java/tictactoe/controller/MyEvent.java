package tictactoe.controller;

import java.beans.PropertyChangeEvent;

public class MyEvent extends PropertyChangeEvent {

    public MyEvent(Object source, String propertyName, Object oldValue, Object newValue) {
        super(source, propertyName, oldValue, newValue);
    }
}