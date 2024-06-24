package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Login;
import com.example.demo.entity.Memo;
// import com.example.demo.form.EditMemoForm;
import com.example.demo.repository.MemoRepository;
import jakarta.persistence.EntityNotFoundException;


// import jakarta.persistence.EntityNotFoundException;

@Service
public class MemoRegistrationService {
    @Autowired
	private MemoRepository memoRepository;
    
    public List<Memo> getMemosByLoginId(Long loginId) {
        // ログインIDに関連付けられたToDoリストを取得するための処理を記述
        return memoRepository.findByLoginId(loginId);
    }
	
	public void MemoRegistration(String memo_content,Long loginId) {
        // ToDoエンティティを作成
    Memo memo = new Memo();
    memo.setMemo_content(memo_content);
    // ログインユーザーのIDを設定
    Login login = new Login();
    login.setId(loginId);
    memo.setLogin(login);
   // 日本時間での現在の日時を取得
    LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Tokyo"));
    
    // 秒までの精度に設定（ミリ秒は切り捨て）
    memo.setRegistration(now.truncatedTo(ChronoUnit.SECONDS));
        memoRepository.save(memo);
    }

    public Memo updateMemo(Long id, Memo updatedMemo) {
        // 更新対象のメモアイテムを取得
        Memo existingMemo = memoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Memo not found with id: " + id));
        // 更新内容を設定
        existingMemo.setMemo_content(updatedMemo.getMemo_content());
        // 更新を保存
        return memoRepository.save(existingMemo);
    }
    
}
