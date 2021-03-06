package com.example.demo.login.controller;

import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// REST用のコントローラクラスには、 @RestController をつける
// 各メソッドの戻り値に、html以外を指定できるようになる
@RestController
public class UserRestController {

    @Autowired
    RestService service;

    /**
     * ユーザ全件取得
     *
     * @return
     */
    @GetMapping("/rest/get")
    public List<User> getUserMany() {

        return service.selectMany();
    }

    @GetMapping("/rest/get/{id:.+}")
    public User getUserOne(@PathVariable("id") String userId) {

        return service.selectOne(userId);
    }

    @PostMapping("/rest/insert")
    public String postUserOne(@RequestBody User user) {

        boolean result = service.insert(user);

        String str = "";

        if (result == true) {
            str = "{\"result\":\"ok\"}";
        } else {
            str = "{\"result\":\"error\"}";
        }

        // 登録
        // curl -X POST -H "Content-Type:application/json" -d "{\"userId\": \"post-test@co.jp\",\"password\": \"pass\",\"userName\": \"tamura\",\"birthday\": \"1990-01-01\",\"age\": \"30\",\"marriage\": \"false\",\"role\": \"ROLE_ADMIN\"}" http://localhost:8080/rest/insert

        // 確認
        // curl http://localhost:8080/rest/get/post-test@co.jp

        return str;
    }

    @PutMapping("/rest/update")
    public String putUserOne(@RequestBody User user){

        boolean result = service.updateOne(user);

        String str = "";

        if (result == true) {
            str = "{\"result\":\"ok\"}";
        } else {
            str = "{\"result\":\"error\"}";
        }

        // 事前確認
        // curl http://localhost:8080/rest/get/admin@xxx.co.jp

        // PUT処理(userNameを変更)
        // curl http://localhost:8080/rest/update -X PUT -H "Content-Type: application/json" -d "{\"userId\":\"admin@xxx.co.jp\", \"password\":\"pass\", \"userName\":\"Kanri Taro\", \"birthday\":\"1990-01-01\", \"age\":\"28\", \"marriage\":\"false\", \"role\":\"ROLE_ADMIN\"}"

        // 事後確認
        // curl http://localhost:8080/rest/get/admin@xxx.co.jp

        return str;
    }

    @DeleteMapping("/rest/delete/{id:.+}")
    public String deleteUserOne(@PathVariable("id") String userId){

        boolean result = service.deleteOne(userId);

        String str = "";

        if (result == true) {
            str = "{\"result\":\"ok\"}";
        } else {
            str = "{\"result\":\"error\"}";
        }

        // DELETE処理
        // $ curl http://localhost:8080/rest/delete/admin@xxx.co.jp -X DELETE

        return str;
    }
}
