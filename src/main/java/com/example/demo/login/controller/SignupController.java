package com.example.demo.login.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import com.example.demo.login.domain.model.GroupOrder;
import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {

    @Autowired
    private UserService userService;

    private Map<String, String> radioMarriage;

    /**
     * ラジオボタン初期化メソッド
     *
     * @return
     */
    private Map<String, String> initRadioMarriage() {

        Map<String, String> radio = new LinkedHashMap<>();

        radio.put("既婚", "true");
        radio.put("未婚", "false");

        return radio;
    }

    // @ModelAttributeは自動でModelクラスに登録してくれる(以下と同じ)
    // (model.addAttribute("SignupForm", form);
    @GetMapping("/signup")
    public String getSignUp(@ModelAttribute SignupForm form, Model model) {

        radioMarriage = initRadioMarriage();

        model.addAttribute("radioMarriage", radioMarriage);

        return "login/signup";
    }

    // BindingResultでデータバインドの結果を受け取る
    // validationの結果はBindingResultクラスに入る
    @PostMapping("/signup")
    public String postSignUp(@ModelAttribute @Validated(GroupOrder.class) SignupForm form, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()){

            // GETリクエストのメソッドを呼び出してユーザ登録画面に戻る
            return getSignUp(form, model);
        }

        // formの中身を確認のため出力
        System.out.println(form);

        User user = new User();

        user.setUserId(form.getUserId());
        user.setPassword(form.getPassword());
        user.setUserName(form.getUserName());
        user.setBirthday(form.getBirthday());
        user.setAge(form.getAge());
        user.setMarriage(form.isMarriage());
        user.setRole("ROLE_GENERAL");

        // ユーザ登録処理
        boolean result = userService.insert(user);

        if (result == true) {
            System.out.println("insert成功");
        } else {
            System.out.println("insert失敗");
        }

        return "redirect:/login";
    }

}