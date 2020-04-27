package com.example.demo.login.domain.repository.jdbc;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoJdbcImpl implements UserDao {

    // Springが用意しているため、すでにBean定義がされている
    @Autowired
    JdbcTemplate jdbc;

    // Userテーブルの件数を取得
    @Override
    public int count() throws DataAccessException {
        // TODO:
        return 0;
    }

    // Userテーブルにデータを1件insert
    @Override
    public int insertOne(User user) throws DataAccessException {
        int rowNumber = jdbc.update("INSERT INTO m_user(user_id," +
                        " password," +
                        " user_name," +
                        " birthday," +
                        " age," +
                        " marriage," +
                        " role)" +
                        " VALUES(?, ?, ?, ?, ?, ?, ?)"
                , user.getUserId()
                , user.getPassword()
                , user.getUserName()
                , user.getBirthday()
                , user.getAge()
                , user.isMarriage()
                , user.getRole());

        return rowNumber;
    }

    // Userテーブルのデータを1件取得
    @Override
    public User selectOne(String userId) throws DataAccessException {
        // TODO:
        return null;
    }

    // Userテーブルの全データを取得
    @Override
    public List<User> selectMany() throws DataAccessException {
        // TODO:
        return null;
    }

    // Userテーブルを1件更新
    @Override
    public int updateOne(User user) throws DataAccessException {
        // TODO:
        return 0;
    }

    // Userテーブルを1件削除
    @Override
    public int deleteOne(String userId) throws DataAccessException {
        // TODO:
        return 0;
    }

    // SQL取得結果をサーバにCSVで保存する
    @Override
    public void userCsvOut() throws DataAccessException {
        // TODO:
    }

}
