package com.example.demo.login.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * コントローラクラスのログ出力用のアスペクト
 */
@Aspect // AOPのクラスにつけるアノテーション
@Component // DIコンテナにBean定義するためつける(AOPとはセットでつけることが多い)
public class LogAspect {

    // JoinPoint(処理を実行するタイミング)の指定
    // メソッドが実行される前に、AOPの処理(Advice)を行う
    // execution(<戻り値><パッケージ名>.<クラス名>.<メソッド名>(<引数>)
    // .. で任意の(0以上の)引数を表す
    // @Before("execution(* com.example.demo.login.controller.LoginController.getLogin(..))")
    @Before("execution(* *..*.*Controller.*(..))") // コントローラクラスの全てのメソッドを対象
    public void startLog(JoinPoint jp){
        System.out.println("メソッド開始: " + jp.getSignature());
    }

    // メソッドが実行された後に、AOPの処理(Advice)を行う
    // @After("execution(* com.example.demo.login.controller.LoginController.getLogin(..))")
    @After("execution(* *..*.*Controller.*(..))") // コントローラクラスの全てのメソッドを対象
    public void endLog(JoinPoint jp){
        System.out.println("メソッド終了: " + jp.getSignature());
    }
}
