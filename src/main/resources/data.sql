INSERT INTO employee (employee_id, employee_name, age)
VALUES(1, '山田太郎', 30);

INSERT INTO m_user (user_id, password, user_name, birthday, age, marriage, role)
VALUES('admin@xxx.co.jp', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '管理太郎', '1990-01-01', 28, false, 'ROLE_ADMIN');

INSERT INTO m_user (user_id, password, user_name, birthday, age, marriage, role)
VALUES('general@xxx.co.jp', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '一般太郎', '1980-01-01', 40, false, 'ROLE_GENERAL');
