package com.example.demo.form;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class NewMemoForm {
    
  @NotNull
  @Size(min=1, max=150)
  private String memo_content;

  private Long id;
  
  public String getMemo_content(){
    return memo_content;
  }

  public void setMemo_content(String memo_content){
    this.memo_content=memo_content;
  }
  public Long getId(){
    return id;
}

public void setId(Long id){
    this.id=id;
}

}
