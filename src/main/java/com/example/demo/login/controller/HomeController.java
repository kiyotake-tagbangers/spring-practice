package com.example.demo.login.controller;

import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    // 結婚ステータス用のラジオボタン用変数
    private Map<String, String> radioMarriage;

    private Map<String, String> initRadioMarriage() {

        Map<String, String> radio = new LinkedHashMap<>();

        radio.put("既婚", "true");
        radio.put("未婚", "false");

        return radio;
    }

    @GetMapping("/home")
    public String getHome(Model model) {

        // <div th:include="__${contents}__"></div> で使用する
        model.addAttribute("contents", "login/home :: home_contents");

        return "login/homeLayout";
    }

    /**
     * ユーザ一覧画面のGET用
     *
     * @param model
     * @return
     */
    @GetMapping("/userList")
    public String getUserList(Model model) {

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
     * ユーザ詳細のGET用
     *
     * @param form
     * @param model
     * @param userId
     * @return
     */
    @GetMapping("/userDetail/{id:.+}")
    public String getUserDetail(@ModelAttribute SignupForm form, Model model, @PathVariable("id") String userId) {

        System.out.println("userId = " + userId);

        model.addAttribute("contents", "login/userDetail :: userDetail_contents");

        radioMarriage = initRadioMarriage();

        model.addAttribute("radioMarriage", radioMarriage);

        if (userId != null && userId.length() > 0) {

            User user = userService.selectOne(userId);

            form.setUserId(user.getUserId());
            form.setUserName(user.getUserName());
            form.setBirthday(user.getBirthday());
            form.setAge(user.getAge());
            form.setMarriage(user.isMarriage());

            model.addAttribute("signupForm", form);
        }

        return "login/homeLayout";
    }

    /**
     * ユーザの更新POST用
     *
     * @param form
     * @param model
     * @return
     */
    // value = ボタン名 と params属性でどのメソッドを実行するか判定
    @PostMapping(value = "/userDetail", params = "update")
    public String postUserDetailUpdate(@ModelAttribute SignupForm form, Model model) {
        System.out.println("更新ボタンの処理");

        User user = new User();

        user.setUserId(form.getUserId());
        user.setPassword(form.getPassword());
        user.setUserName(form.getUserName());
        user.setBirthday(form.getBirthday());
        user.setAge(form.getAge());
        user.setMarriage(form.isMarriage());

        // DBで例外が発生しても画面を表示する
        try {
            boolean result = userService.updateOne(user);

            if (result == true) {
                model.addAttribute("result", "更新成功");
            } else {
                model.addAttribute("result", "更新失敗");
            }
        } catch (DataAccessException e) {

            model.addAttribute("result", "更新失敗(トランザクションテスト)");
        }

        // ユーザ一覧画面を表示
        return getUserList(model);
    }

    /**
     * ユーザ削除POST用
     *
     * @param form
     * @param model
     * @return
     */
    @PostMapping(value = "/userDetail", params = "delete")
    public String postUserDetailDelete(@ModelAttribute SignupForm form, Model model) {

        System.out.println("削除ボタンの処理");

        boolean result = userService.deleteOne(form.getUserId());

        if (result == true) {
            model.addAttribute("result", "削除成功");
        } else {
            model.addAttribute("result", "削除失敗");
        }

        // ユーザ一覧画面を表示
        return getUserList(model);
    }

    /**
     * ログアウト用
     *
     * @return
     */
    @PostMapping("/logout")
    public String getLogout() {

        return "redirect:/login";
    }

    /**
     * ユーザ一覧のCSV出力用
     */
    @GetMapping("/userList/csv")
    // メソッドの戻り値を、ResponseEntity型にすると
    // htmlでなく<>で指定したbyte型の配列を呼び出し元に返却できる
    public ResponseEntity<byte[]> getUserListCsv(Model model) {

        // CSV出力
        userService.userCsvOut();

        byte[] bytes = null;

        try {

            bytes = userService.getFile("sample.csv");

        } catch (IOException e) {
            e.printStackTrace();
        }

        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "text/csv; charset=UTF-8");
        header.setContentDispositionFormData("filename", "sample.csv");

        return new ResponseEntity<>(bytes, header, HttpStatus.OK);
    }

}
