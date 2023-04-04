package com.example.mainactivity;

public class TodoList {
    int _id;
    String name;
    public Boolean isUrgent;

    public TodoList(int _id, String name, Boolean isUrgent) {
        this._id = _id;
        this.name = name;
        this.isUrgent = isUrgent;
    }

    public TodoList() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getUrgent() {
        return isUrgent;
    }

    public void setUrgent(Boolean urgent) {
        isUrgent = urgent;
    }

    public int getID() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }
}
