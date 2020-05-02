package com.example.demo;

import com.example.demo.login.domain.repository.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

// RunWith はテストをどのクラスで実行するかを指定
// SpringRunner はSpring用のJUnitを使えるクラス
// SpringBootTest をつけると、SpringBootを起動してからテストを始める
// これをつけないと、Beanが作られていないため、コントローラやサービスクラスが動かない
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserDaoTest {

    @Autowired
    @Qualifier("UserDaoJdbcImpl")
    UserDao dao;

    @Test
    public void countTest1(){

        // staticインポートしているので(import static org.junit.Assert.*;)
        // Assert.assertEquals() ではなく、assertEquals() でメソッドを実行できる
        // コード記述量を減らしたり、可読性を上げるために
        assertEquals(dao.count(), 2);
    }

    // @Sql アノテーションを使用すると、そのSQLを実行した後の状態でテストされる(そのメソッド内のみで有効)
    @Test
    @Sql("/testdata.sql")
    public void countTest2(){
        assertEquals(dao.count(), 3);
    }
}
