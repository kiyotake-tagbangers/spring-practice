package com.example.demo.login.domain.repository;

import com.example.demo.login.domain.model.User;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * リポジトリクラスのインターフェース
 */
public interface UserDao {

    // DataAccessException はSpringJDBC,MyBatisを使用した時のデータベース操作で例外が発生した場合に投げられる

    // Userテーブルの件数を取得
    public int count() throws DataAccessException;

    // Userテーブルにデータを1件insert
    public int insertOne(User user) throws DataAccessException;

    // Userテーブルのデータを1件取得
    public User selectOne(String userId) throws DataAccessException;

    // Userテーブルの全データを取得
    public List<User> selectMany() throws DataAccessException;

    // Userテーブルを1件更新
    public int updateOne(User user) throws DataAccessException;

    // Userテーブルを1件削除
    public int deleteOne(String userId) throws DataAccessException;

    // SQL取得結果をサーバにCSVで保存する
    public void userCsvOut() throws DataAccessException;

}
