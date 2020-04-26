package com.example.demo.login.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
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
    // @Before("execution(* *..*.*Controller.*(..))") // コントローラクラスの全てのメソッドを対象
    // public void startLog(JoinPoint jp){
    //    System.out.println("メソッド開始: " + jp.getSignature());
    // }

    // メソッドが実行された後に、AOPの処理(Advice)を行う
    // @After("execution(* com.example.demo.login.controller.LoginController.getLogin(..))")
    // @After("execution(* *..*.*Controller.*(..))") // コントローラクラスの全てのメソッドを対象
    // public void endLog(JoinPoint jp){
    //    System.out.println("メソッド終了: " + jp.getSignature());
    // }

    // JoinPointにAroundを指定
    // メソッドの実行前後で任意の処理をできる
    @Around("execution(* *..*.*Controller.*(..))")
    public Object startLog(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("メソッド開始: " + jp.getSignature());

        // アノテーションをつけたメソッドの中でAOP対象クラスをメソッドを直接実行する
        // そのため、returnに実行結果の戻り値を指定する
        try {
            Object result = jp.proceed();
            System.out.println("メソッド終了: " + jp.getSignature());
            return result;
        } catch (Exception e) {
            System.out.println("メソッド異常終了: " + jp.getSignature());
            e.printStackTrace();
            throw e;
        }
    }
}
