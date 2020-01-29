package com.example.cdsscheduleapp_legacy;

public class ToDoItem {

    private String toDo;


    public ToDoItem(String toDo) {
        this.toDo = toDo;
    }

    public String getToDo() {
        return toDo;
    }

    public void setToDo(String toDo) {
        this.toDo = toDo;
    }

    @Override
    public String toString() {
        return "ToDoItem{" +
                "toDo='" + toDo + '\'' +
                '}';
    }
}