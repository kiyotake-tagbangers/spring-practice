package com.example.demo.login.controller;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @GetMapping("/home")
    public String getHome(Model model){

        // <div th:include="__${contents}__"></div> で使用する
        model.addAttribute("contents", "login/home :: home_contents");

        return "login/homeLayout";
    }

    /**
     * ユーザ一覧画面のGET
     * @param model
     * @return
     */
    @GetMapping("/userList")
    public String getUserList(Model model){

        // コンテンツ部分にユーザ一覧を表示するための文字列を登録
        model.addAttribute("contents", "login/userList :: userList_contents");

        // ユーザの一覧の生成
        List<User> userList = userService.selectMany();

        // Modelにユーザリストを登録
        model.addAttribute("userList", userList);

        // データ件数を取得
        int count = userService.count();
        model.addAttribute("userListCount", count);

        return "login/homeLayout";
    }

    /**
     * ログアウト用
     * @return
     */
    @GetMapping("/logout")
    public String getLogout(){

        return "redirect:/login";
    }

    /**
     * ユーザ一覧のCSV出力用
     */
    @GetMapping("/userList/csv")
    public String getUserListCsv(Model model){
        // TODO:
        return getUserList(model);
    }

}
