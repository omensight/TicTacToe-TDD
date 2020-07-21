package tictactoe.controller;

public class MyEvent {
    private Object source;
    private String propertyName;
    private Object oldValue;
    private Object newValue;

    public MyEvent(Object source, String propertyName, Object oldValue, Object newValue) {
        this.source = source;
        this.propertyName = propertyName;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public Object getSource() {
        return source;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }
}
