package com.example.demo.dto;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class TodoDTO {

    @NotBlank(message = "Makhasch tache tkon khawya")
    @Size(min = 4, message = "Kteb chi haja feha ktar mn 4 les characteres.")
    private String task;
    private boolean finished;

    private String id;

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
