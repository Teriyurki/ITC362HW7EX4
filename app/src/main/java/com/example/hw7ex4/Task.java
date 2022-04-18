package com.example.hw7ex4;

public class Task {

    private int id;
    private String task;
    private String deadline;

    public Task (int newId, String newTask, String newDeadline){
        setID(newId);
        setTask(newTask);
        setDeadline(newDeadline);
    }

    private void setDeadline(String newDeadline) {
        deadline = newDeadline;
    }


    private void setTask(String newTask) {
        task = newTask;
    }

    private void setID(int newId) {

        id = newId;
    }
    public int getId() {
        return id;
    }

    public String getTask() {

        return task;
    }

    public String getDeadline() {
        return deadline;
    }


    public String toString() {

        return id + ":" + task + ":" + deadline;
    }
}
