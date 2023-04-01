package org.example.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ToDo {
    private int id;

    @NotEmpty(message = "topic can't be empty")
    @Size(message = "size should be no more than 100 characters")
    private String task;

    @NotEmpty(message = "Fill discription of task")
    @Size(message = "no more than 500char")
    private String disc;

    @NotEmpty(message = "it would better if you'll fill stage")
    private String stage;

    public ToDo() {};

    public ToDo(int id, String task, String disc, String stage) {
        this.disc = disc;
        this.id = id;
        this.stage = stage;
        this.task = task;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
