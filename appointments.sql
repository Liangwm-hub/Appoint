
create database appointments;

-- -------------
-- 学生 user表
-- -------------

CREATE TABLE user (
    sno INT PRIMARY KEY COMMENT '学号',
    name VARCHAR(20) NOT NULL COMMENT '名字',
    password VARCHAR(50) NOT NULL COMMENT '密码',
    sex ENUM('男', '女') COMMENT '性别',
    majorClass VARCHAR(20) COMMENT '专业班级',
    phone VARCHAR(11) COMMENT '电话',
    status ENUM('审核中', '注册成功', '注册失败', '冻结中') COMMENT '账号状态'
);

insert user values(1001,'阿翔','12345','男','网络工程2班','12345678901','注册成功');

SELECT 
    *
FROM
    user;



-- ----------------
-- 导师 teacher表
-- ----------------

CREATE TABLE teacher (
    id INT COMMENT '教师编号',
    name VARCHAR(20) NOT NULL COMMENT '名字',
    password VARCHAR(50) NOT NULL COMMENT '密码',
    sex ENUM('男', '女') COMMENT '性别',
    college VARCHAR(20) COMMENT '学院',
    phone VARCHAR(11) COMMENT '电话',
    scope INT COMMENT '预约范围'
);

insert teacher values(9001,'老王','12345','男','计算机学院','12345678991',30);

SELECT 
    *
FROM
    teacher;


-- ------------------------
-- 导师发布预约 release表
-- ------------------------

CREATE TABLE `release` (
    id INT COMMENT '教师编号',
    name VARCHAR(20) NOT NULL COMMENT '名字',
    sex ENUM('男', '女') COMMENT '性别',
    college VARCHAR(20) COMMENT '学院',
    phone VARCHAR(11) COMMENT '电话',
    appoint_time DATETIME COMMENT '见面时间',
    place VARCHAR(20) COMMENT '见面地点',
    scope INT COMMENT '预约范围'
);


insert `release` values(9001,'老王','男','计算机学院','12345678991','2020-5-1 10:00','工一301',30);

SELECT 
    *
FROM
    `release`;

SELECT 
    *
FROM
    `release`
WHERE
    appoint_time BETWEEN NOW() AND DATE_ADD(CURDATE(), INTERVAL scope DAY);


SELECT 
    *
FROM
    `release`
WHERE
    appoint_time <= DATE_ADD(CURDATE(), INTERVAL 30 DAY)
        AND appoint_time >= NOW();

SELECT 
    *
FROM
    `release`
LIMIT 0 , 5;

UPDATE teacher t1,
    `release` t2 
SET 
    t1.scope = 10,
    t2.scope = 10
WHERE
    t1.id = 9002 AND t2.id = 9002;


-- ------------------------
-- 学生预约导师 appoint表
-- ------------------------

CREATE TABLE appoint (
    num INT PRIMARY KEY AUTO_INCREMENT COMMENT '预约编号',
    teacher_id INT COMMENT '导师编号',
    teacher_name VARCHAR(20) NOT NULL COMMENT '导师名字',
    user_sno INT COMMENT '学生学号',
    user_name VARCHAR(20) NOT NULL COMMENT '学生名字',
    appoint_time DATETIME COMMENT '见面时间',
    place VARCHAR(20) COMMENT '见面地点',
    request_time DATETIME COMMENT '请求时间',
    teacher_college VARCHAR(20) COMMENT '导师学院',
    teacher_phone VARCHAR(20) COMMENT '导师电话',
    status enum('审核中','预约成功','预约失败') COMMENT '预约状态',
    picture VARCHAR(50) COMMENT '图片名'
);

insert appoint values(1,9001,'老王',1001,'阿翔','2020-04-12 10:00:00','工一301',now(),'计算机学院','12345678991','审核中','阿.jpg');

SELECT 
    *
FROM
    appoint;
    
    
SELECT 
    *
FROM
    appoint
WHERE
    user_sno = 1001
        AND DATE_FORMAT(request_time, '%Y-%m-%d') >= DATE_SUB(CURDATE(), INTERVAL 30 DAY)
ORDER BY appoint_time DESC
LIMIT 1 , 5;
SELECT 
    COUNT(*)
FROM
    appoint
WHERE
    status = '审核中';


-- ------------------
-- 管理员 manager表
-- ------------------

CREATE TABLE manager (
    manager_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '管理员编号',
    password VARCHAR(20) COMMENT '密码'
);

insert into manager values(8001,'12345');

SELECT 
    *
FROM
    manager;



-- ------------------------
-- 通知 notice表
-- ------------------------

CREATE TABLE notice (
    notice_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '通知的编号',
    notice_title VARCHAR(20) COMMENT '标题',
    notice_content TEXT COMMENT '内容',
    notice_file VARCHAR(50) COMMENT '文件名'
);

insert into notice values(1,'abc','明天就开学',null);

SELECT 
    *
FROM
    notice;



-- -----------------------
-- 聊天列表 
-- -----------------------

CREATE TABLE chat_room (
    chat_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '聊天的编号',
    teacher_id INT COMMENT '导师的编号',
    teacher_name VARCHAR(20) NOT NULL COMMENT '导师名字',
    user_sno INT COMMENT '学生学号',
    user_name VARCHAR(20) NOT NULL COMMENT '学生名字'
);

insert into chat_room values(2,9001,'老梁',1001,'阿兰');
select * from chat_room;

-- ----------------------
-- 聊天信息
-- ----------------------

CREATE TABLE chat_message (
    message_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '信息编号',
    chat_id INT COMMENT '聊天编号',
    message_content VARCHAR(100) COMMENT '信息内容',
    message_type enum('文字','图片'),
    teacher_name VARCHAR(20) NOT NULL COMMENT '导师名字',
    user_name VARCHAR(20) NOT NULL COMMENT '学生名字',
    sender_identity ENUM('导师', '学生') COMMENT '发送人的身份',
    create_time DATETIME COMMENT '发送的时间'
);

select * from chat_message;
delete from chat_message;

insert into chat_message values(19,15,'2020050722033091829.jpg','图片','老王','阿翔','导师',now());
insert into chat_message values(2,3,'同学你好啊','老王','阿翔','导师',now());

SELECT COUNT(*) FROM `chat_message` WHERE `message_id`>'1';

drop table chat_message;
