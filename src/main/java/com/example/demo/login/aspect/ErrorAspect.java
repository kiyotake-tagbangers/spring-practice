package com.example.demo.login.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Aspect
@Component // DIコンテナにBean定義するためにつける
public class ErrorAspect {

    // JoinPoint(処理を実行するポイント)を
    // AfterThrowing に指定することで、
    // メソッドが異常終了した場合のみ処理(Advice)を行う
    @AfterThrowing(value = "execution(* *..*(..))" + " && (bean(*Controller) || bean(*Service) || bean(*Repository))", throwing = "ex")
    public void throwingNull(DataAccessException ex) {

        System.out.println("=====================================");
        System.out.println("DataAccessException が発生しました。 : " + ex);
        System.out.println("=====================================");
    }
}
