package com.example.demo.login.domain.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class SignupForm {

    private String userId;
    private String password;
    private String userName;

    // データバインドは画面の入力項目とオブジェクトのフィールドのマッピングを行うこと
    // 画面から渡された文字列を日付型に変換
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date birthday;
    private int age;
    private boolean marriage;
}
