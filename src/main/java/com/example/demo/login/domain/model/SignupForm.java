package com.example.demo.login.domain.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class SignupForm {

    @NotBlank(groups = ValidGroup1.class,message = "{require_check}")
    @Email(groups = ValidGroup2.class,message = "{email_check}")
    private String userId;

    @NotBlank(groups = ValidGroup1.class,message = "{require_check}")
    @Length(min = 4, max = 100, groups = ValidGroup2.class,message = "{length_check}")
    @Pattern(regexp = "^[a-zA-Z0-9]+$",groups = ValidGroup3.class,message = "{pattern_check}")
    private String password;

    @NotBlank(groups = ValidGroup1.class, message = "{require_check}")
    private String userName;

    // データバインドは画面の入力項目とオブジェクトのフィールドのマッピングを行うこと
    // 画面から渡された文字列を日付型に変換
    @NotNull(groups = ValidGroup1.class, message = "{require_check}")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date birthday;

    @Min(groups = ValidGroup2.class,value = 20, message = "{min_check}")
    @Max(groups = ValidGroup2.class,value = 100, message = "{max_check}")
    private int age;

    @AssertFalse(groups = ValidGroup2.class,message = "{false_check}")
    private boolean marriage;

}
