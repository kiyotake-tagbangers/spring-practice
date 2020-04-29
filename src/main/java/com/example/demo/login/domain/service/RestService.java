package com.example.demo.login.domain.service;

import com.example.demo.login.domain.model.User;

import java.util.List;

public interface RestService {

    // 1件登録
    public boolean insert(User user);

    // 1件検索
    public User selectOne(String userId);

    // 全件検索
    public List<User> selectMany();

    // 1件更新
    public boolean updateOne(User user);

    // 1件削除
    public boolean deleteOne(String userId);
}
