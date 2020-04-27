package com.example.demo.login.domain.service;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDao dao;

    /**
     * リポジトリクラスのinsertOneメソッドを呼び出している
     * @param user
     * @return insertが成功したかの判定結果
     */
    public boolean insert(User user){

        // insert実行
        int rowNumber = dao.insertOne(user);

        boolean result = false;

        if(rowNumber > 0) {
            result = true; // insert成功
        }

        return result;
    }
}
