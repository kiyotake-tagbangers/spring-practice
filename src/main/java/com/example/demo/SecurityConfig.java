package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// セキュリティの設定用のクラス
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {

        // 静的リソースを除外
        web.ignoring().antMatchers("/webjars/**", "/css/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 直リンクの禁止
        // http.authorizeRequests()にメソッドチェーンでリンク禁止先の条件を追加
        http
                .authorizeRequests()
                .antMatchers("/webjars/**").permitAll() // webjarsへアクセス許可
                .antMatchers("/css/**").permitAll() // cssへアクセス許可
                .antMatchers("/login").permitAll() // ログインページは直リンクを許可
                .anyRequest().authenticated(); // それ以外はログインしないと見れない

        // TODO: 有効にする
        // CSRF対策を無効(一時的)
        http.csrf().disable();
    }
}
