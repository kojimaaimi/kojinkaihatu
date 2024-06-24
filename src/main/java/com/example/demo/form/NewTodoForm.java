package com.example.demo.form;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class NewTodoForm {

    @NotNull
    @Size(min=1, max=30)
    private String title;
 
    private Long id;

    private boolean done;


    
    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

    public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}
}
