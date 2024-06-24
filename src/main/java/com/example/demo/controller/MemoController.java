package com.example.demo.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Memo;
import com.example.demo.form.EditMemoForm;
import com.example.demo.form.NewMemoForm;
import com.example.demo.repository.MemoRepository;
import com.example.demo.service.MemoRegistrationService;
import com.example.demo.userDetails.LoginDetailsImpl;

import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("memo")
public class MemoController {
    @Autowired
  MemoRepository memoRepository;

   @Autowired
    private MemoRegistrationService memoRegistrationService;

  
  //日記一覧情報の取得
  @GetMapping("/summary")
  public String summary(@AuthenticationPrincipal LoginDetailsImpl loginDetails,Model model, NewMemoForm newMemoForm) {
    String username = loginDetails.getUsername();
		Long userId = loginDetails.getLogin().getId();
    model.addAttribute("newMemoForm", new NewMemoForm());
		
		// モデルに情報を追加
		model.addAttribute("username", username);
        model.addAttribute("userId", userId);
        List<Memo> memos = memoRepository.findByLoginId(userId); // ログインユーザーに関連するメモのみ取得
        model.addAttribute("memos", memos);
    return "summary";
  }

  //指定されたidの日記を削除する
  @PostMapping("/delete/{id}")
  public String delete(@PathVariable("id") Long id) {
      memoRepository.deleteById(id);
      return "redirect:/memo/summary";
  }


  //@Validを@Validatedに変更しても問題なく動作します
  //日記の新規登録
  @PostMapping(value = "/add")
    public String add(@AuthenticationPrincipal LoginDetailsImpl loginDetails, Model model,
            @Valid NewMemoForm newMemoForm, BindingResult bindingResult) {
        Long userId = loginDetails.getLogin().getId();
        
        if (bindingResult.hasErrors()) {
            List<Memo> memos = memoRepository.findByLoginId(userId); // ログインユーザーに関連するメモのみ取得
        model.addAttribute("memos", memos);
        model.addAttribute("username", loginDetails.getUsername());
        model.addAttribute("userId", userId);
        // model.addAttribute("newMemoForm", newMemoForm);
        return "summary";
        }

        // 新しいメモを登録する
        memoRegistrationService.MemoRegistration(newMemoForm.getMemo_content(), userId);

        return "redirect:/memo/summary";
    }

    //編集画面を表示する
  @GetMapping("/edit/{id}")
  String edit(Model model, EditMemoForm editmemoForm) {
    Memo memo = memoRepository.findById(editmemoForm.getId()).get();
    model.addAttribute("editMemoForm", memo);
    return "edit";
  }

  //日記を更新する
@PostMapping("/update/{id}")
String update(@PathVariable("id") Long id,  @Valid EditMemoForm editmemoForm, BindingResult bindingResult,Model model) {
  if (bindingResult.hasErrors()) {
    model.addAttribute("editMemoForm", editmemoForm);
        // バリデーションエラーがある場合は、編集画面に戻る
        return "edit";
    }
    // 更新されたメモを保存する
    Memo updatedMemo = new Memo(editmemoForm.getId(), editmemoForm.getMemo_content(), LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
    memoRegistrationService.updateMemo(id, updatedMemo);
    return "redirect:/memo/summary";
}

}
