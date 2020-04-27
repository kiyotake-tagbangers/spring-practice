package com.example.demo.login.domain.model;

import lombok.Data;

import java.util.Date;

/**
 * DBから取得した値を、コントローラクラスやサービスクラスなどの間でやり取りするためのクラス
 * すなわち、ユーザテーブルのカラムをフィールドに持つためのクラス
 */
@Data
public class User {

    private String userId;
    private String password;
    private String userName;
    private Date birthday;
    private int age;
    private boolean marriage; // 結婚ステータス
    private String role; // ロール

}
