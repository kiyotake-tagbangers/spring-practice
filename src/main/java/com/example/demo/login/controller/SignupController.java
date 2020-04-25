package com.example.demo.login.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import com.example.demo.login.domain.model.GroupOrder;
import com.example.demo.login.domain.model.SignupForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {

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
        System.out.println(form);
        return "redirect:/login"; // login.html
    }

}