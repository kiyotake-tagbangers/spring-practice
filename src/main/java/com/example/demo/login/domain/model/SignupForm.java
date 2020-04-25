package com.example.demo.login.domain.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class SignupForm {

    @NotBlank(message = "{require_check}")
    @Email(message = "{email_check}")
    private String userId;

    @NotBlank(message = "{require_check}")
    @Length(min = 4, max = 100, message = "{length_check}")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "{pattern_check}")
    private String password;

    @NotBlank(message = "{require_check}")
    private String userName;

    // データバインドは画面の入力項目とオブジェクトのフィールドのマッピングを行うこと
    // 画面から渡された文字列を日付型に変換
    @NotNull(message = "{require_check}")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date birthday;

    @Min(value = 20, message = "{min_check}")
    @Max(value = 100, message = "{max_check}")
    private int age;

    @AssertFalse(message = "{false_check}")
    private boolean marriage;

}
