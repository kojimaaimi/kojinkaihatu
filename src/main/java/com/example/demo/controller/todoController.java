package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.example.demo.entity.Todo;
import com.example.demo.repository.TodoRepository;
import com.example.demo.form.TodoForm;
import com.example.demo.service.TodoRegistrationService;
import com.example.demo.userDetails.LoginDetailsImpl;

import jakarta.validation.Valid;



@Controller
@RequestMapping
public class todoController {
    
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoRegistrationService todoRegistrationService;

    @GetMapping("/todo")
    public String showTodoList(@AuthenticationPrincipal LoginDetailsImpl loginDetails, Model model,TodoForm TodoForm) {
        String username = loginDetails.getUsername();
		Long userId = loginDetails.getLogin().getId();
        model.addAttribute("TodoForm", new TodoForm());
		
		// モデルに情報を追加
		model.addAttribute("username", username);
		model.addAttribute("userId", userId);
        List<Todo> todos = todoRegistrationService.getAllTodos(userId);
        model.addAttribute("todos", todos);
    
        return "todoindex"; // ビュー名を返す
    }
    

  
    @PostMapping(value="/add")
    public String add( @Valid TodoForm todoForm, BindingResult bindingResult,Model model, @AuthenticationPrincipal LoginDetailsImpl loginDetails) {
    Long userId = loginDetails.getLogin().getId();
     if (bindingResult.hasErrors()) {
            List<Todo> todos = todoRepository.findByLoginId(userId); // ログインユーザーに関連するメモのみ取得
        model.addAttribute("todos", todos);
        model.addAttribute("username", loginDetails.getUsername());
        model.addAttribute("userId", userId);
        model.addAttribute("todoForm", todoForm);
        return "todoindex";
        }
        todoRegistrationService.todoRegistration(todoForm.getTitle(), userId);
    return "redirect:/todo";
}

    @PostMapping(value="/update/{id}")
    public String update(@AuthenticationPrincipal LoginDetailsImpl loginDetails,@PathVariable("id") Long id,@Valid TodoForm todoForm,BindingResult bindingResult,Model model) {
        // Long userId = loginDetails.getLogin().getId();
        // if (bindingResult.hasErrors()) {
        //        List<Todo> todos = todoRepository.findByLoginId(userId); 
        //    model.addAttribute("todos", todos);
        //    model.addAttribute("username", loginDetails.getUsername());
        //    model.addAttribute("userId", userId);
        //    model.addAttribute("todoForm", todoForm);
        //    return "todoindex";
        //    }
    // 更新するToDoエンティティを作成
    Todo updatedTodo = new Todo();
    updatedTodo.setId(id);
    updatedTodo.setTitle(todoForm.getTitle());
    updatedTodo.setDone(todoForm.isDone());
    
    // IDと更新するToDoエンティティを渡して更新処理を実行
    todoRegistrationService.updateTodo(id, updatedTodo);

    return "redirect:/todo";
    }

    @GetMapping(value="/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
    // 削除処理を実行
    todoRepository.deleteById(id);
    return "redirect:/todo";
}
@GetMapping("/todoindex")
    public String todoIndex(@AuthenticationPrincipal LoginDetailsImpl loginDetails,Model model) {
        Long userId = loginDetails.getLogin().getId();
        List<Todo> todos = todoRegistrationService.getAllTodos(userId);
        model.addAttribute("todos", todos);
        return "todoindex";
    }

}


