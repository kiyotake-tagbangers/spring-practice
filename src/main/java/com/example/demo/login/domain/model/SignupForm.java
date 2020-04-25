package com.example.demo.login.domain.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class SignupForm {

    @NotBlank
    @Email
    private String userId;

    @NotBlank
    @Length(min = 4, max = 100)
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String password;

    @NotBlank
    private String userName;

    // データバインドは画面の入力項目とオブジェクトのフィールドのマッピングを行うこと
    // 画面から渡された文字列を日付型に変換
    @NotNull
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date birthday;

    @Min(20)
    @Max(100)
    private int age;

    @AssertFalse
    private boolean marriage;

}
