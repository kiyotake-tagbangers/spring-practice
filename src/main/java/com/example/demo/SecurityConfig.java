package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

// セキュリティの設定用のクラス
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // リポジトリクラスなどで使用するため、パスワードエンコーダのBeanを定義
    @Bean
    public PasswordEncoder passwordEncoder() {

        // パスワードを暗号化、復号するインターフェース PasswordEncoder を実装したもの
        return new BCryptPasswordEncoder();
    }

    // SQLを実行できるようにする(BeanはSpringが用意している)
    @Autowired
    private DataSource dataSource;

    // ユーザIDとパスワードを取得
    private static final String USER_SQL = "SELECT"
            + " user_id,"
            + " password,"
            + " true"
            + " FROM"
            + " m_user"
            + " WHERE"
            + " user_id = ?";

    // ユーザのロールを取得
    private static final String ROLE_SQL = "SELECT"
            + " user_id,"
            + " role"
            + " FROM"
            + " m_user"
            + " WHERE"
            + " user_id = ?";

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
                .antMatchers("/admin").hasAuthority("ROLE_ADMIN") // 認可を設定
                .anyRequest().authenticated(); // それ以外はログインしないと見れない

        // ログイン処理
        http
                .formLogin()
                .loginProcessingUrl("/login") // ログイン処理のパス(htmlのフォームの action="/login" の部分と一致させる
                // 以下にメソッドチャーンで条件を追加する
                .loginPage("/login") // ログインページの指定
                .failureUrl("/login") // ログイン失敗時の遷移先
                .usernameParameter("userId") // ログインページのユーザID
                .passwordParameter("password") // ログインページのパスワード
                .defaultSuccessUrl("/home", true); // ログイン成功後の遷移先

        // ログアウト処理
        http
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // GETメソッドでリクエストをおくる
                .logoutUrl("/logout") // POSTメソッドでログアウトする場合の設定
                .logoutSuccessUrl("/login");

        // CSRF対策を無効(一時的)
        // http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // ログイン処理時のユーザ情報をDBから取得する
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(USER_SQL)
                .authoritiesByUsernameQuery(ROLE_SQL)
                .passwordEncoder(passwordEncoder()); // パスワードの復号
    }
}
