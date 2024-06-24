package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Login;
import com.example.demo.entity.Todo;
import com.example.demo.repository.TodoRepository;
import jakarta.persistence.EntityNotFoundException;



@Service
public class TodoRegistrationService {
	
	@Autowired
	private TodoRepository todoRepository;

    public List<Todo> getTodosByLoginId(Long loginId) {
        // ログインIDに関連付けられたToDoリストを取得するための処理を記述
        return todoRepository.findByLoginId(loginId);
    }
	
	public void todoRegistration(String title,Long loginId) {
        // ToDoエンティティを作成
    Todo todo = new Todo();
    todo.setTitle(title);
    // ログインユーザーのIDを設定
    Login login = new Login();
    login.setId(loginId);
    todo.setLogin(login);
        todoRepository.save(todo);
    }

	public Todo updateTodo(Long id, Todo updatedTodo) {
        // 更新対象のToDoアイテムを取得
        Todo existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Todo not found with id: " + id));
        // 更新内容を設定
        existingTodo.setTitle(updatedTodo.getTitle());
        existingTodo.setDone(updatedTodo.isDone());
        // 更新を保存
        return todoRepository.save(existingTodo);
    }

	public void deleteTodo(Long id) {
		todoRepository.deleteById(id);
	}
	public List<Todo> getAllTodos(Long loginId) {
        // ログインユーザーのIDを使用してToDoリストを取得
        return todoRepository.findByLoginId(loginId);
    }
}
