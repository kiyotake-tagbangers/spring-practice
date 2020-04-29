package com.example.demo.login.domain.service;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Transactional
@Service
public class UserService {

    @Autowired
    // どのBeanを使用するかを指定
    @Qualifier("UserDaoJdbcImpl")
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

    /**
     * ユーザ一覧をCSV出力する
     *
     * @throws DataAccessException
     */
    public void userCsvOut() throws DataAccessException {

        // リポジトリクラスのCSV出力メソッドを呼び出しているだけ
        dao.userCsvOut();
    }

    /**
     * 引数に指定したファイルをサーバから取得
     *
     * @param fileName ファイル名
     * @return ファイルの中身をbyte型の配列にして返す
     * @throws IOException
     */
    public byte[] getFile(String fileName) throws IOException {

        // ファイルシステムの取得
        FileSystem fs = FileSystems.getDefault();

        // ファイル取得
        Path p = fs.getPath(fileName);

        // ファイルをbyte配列に変換
        byte[] bytes = Files.readAllBytes(p);

        return bytes;
    }
}
