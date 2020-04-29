package com.example.demo.login.domain.repository.jdbc;

import com.example.demo.login.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

// BeanPropertyRowMapperはRowMapper自体を用意しなくて済む
@Repository("UserDaoJdbcImpl3")
public class UserDaoJdbcImpl3 extends UserDaoJdbcImpl {

    @Autowired
    private JdbcTemplate jdbc;

    /**
     * ユーザ1件取得
     * @param userId
     * @return
     */
    @Override
    public User selectOne(String userId){

        String sql = "SELECT * FROM m_user WHERE user_id = ?";

        // BeanPropertyRowMapperはDBから取得したカラム名と
        // 同一のフィールド名がクラス名にあれば、自動でマッピングする
        // カラム名はスネークケース(user_id)、フィールド名はキャメルケース(userId)にする必要がある
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);

        return jdbc.queryForObject(sql, rowMapper, userId);
    }

    /**
     * ユーザ全件取得
     * @return
     */
    @Override
    public List<User> selectMany(){

        String sql = "SELECT * FROM m_user";

        RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);

        return jdbc.query(sql,rowMapper);
    }

}
