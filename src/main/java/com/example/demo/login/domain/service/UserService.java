package com.example.demo.login.domain.service;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    @Qualifier("UserDaoJdbcImpl4") // どのBeanを使用するかを指定
    UserDao dao;

    /**
     * リポジトリクラスのinsertOneメソッドを呼び出している
     *
     * @param user
     * @return insertが成功したかの判定結果
     */
    public boolean insert(User user) {

        // insert実行
        int rowNumber = dao.insertOne(user);

        boolean result = false;

        if (rowNumber > 0) {
            result = true; // insert成功
        }

        return result;
    }

    /**
     * カウント用のメソッド
     *
     * @return
     */
    public int count() {

        return dao.count();
    }

    /**
     * 全件取得用
     */
    public List<User> selectMany() {

        return dao.selectMany();
    }

    /**
     * 1件取得用
     */
    public User selectOne(String useId) {
        return dao.selectOne(useId);
    }

    public boolean updateOne(User user) {

        int rowNumber = dao.updateOne(user);

        boolean result = false;

        if (rowNumber > 0) {
            result = true;
        }

        return result;
    }

    public boolean deleteOne(String userId) {

        int rowNumber = dao.deleteOne(userId);

        boolean result = false;

        if (rowNumber > 0) {
            result = true;
        }

        return result;
    }
}
