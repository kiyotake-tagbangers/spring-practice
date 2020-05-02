package com.example.demo;

import com.example.demo.login.domain.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// https://github.com/mockito/mockito/issues/1606
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//public class HomeControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    // モックとして使用するBeanにつける
//    @MockBean
//    public UserService service;
//
//    // @WithMockUserアノテーションを使うとログイン後にしか表示できない画面をテストできる
//    // @WithMockUser(username="kiyota", roles={"ROLE_ADMIN"}
//    @Test
//    @WithMockUser
//    public void userListCount() throws Exception {
//
//        // 任意の値を戻り値にしている
//        when(service.count()).thenReturn(10);
//
//        // ユーザ一覧画面の表示内容が切り替わっていることを確認
//        mockMvc.perform(get("/userList"))
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("合計：10件")));
//    }
//}
