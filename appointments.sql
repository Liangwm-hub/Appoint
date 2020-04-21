
create database appointments;

#user表
CREATE TABLE user (
    sno INT PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    password VARCHAR(50) NOT NULL,
    sex ENUM('男', '女'),
    majorClass VARCHAR(20),
    phone VARCHAR(11)
);
drop table user;

insert user values(1001,'阿翔','12345','男','网络工程2班','12345678901');
insert user values(1002,'阿红','12345',null,null,null);

select * from user;




#teacher表
CREATE TABLE teacher (
    id INT,
    name VARCHAR(20) NOT NULL,
    password VARCHAR(50) NOT NULL,
    sex ENUM('男', '女'),
    college VARCHAR(20),
    phone VARCHAR(11)
);
drop table teacher;

insert teacher values(9001,'老王','12345','男','计算机学院','12345678991');
insert teacher values(9002,'老于','12345','男','计算机学院','12345678992');

select * from teacher;




#release表
CREATE TABLE `release` (
    id INT,
    name VARCHAR(20) NOT NULL,
    sex ENUM('男', '女'),
    college VARCHAR(20),
    phone VARCHAR(11),
    appoint_time DATETIME,
    place VARCHAR(20)
);

insert `release` values(9001,'老王','男','计算机学院','12345678991','2020-4-12 10:00','工一301');

select * from `release`;
select * from `release` where id=9001 and appoint_time='2020-4-12 10:00' and place='工一301';

select * from `release` limit 1,5 ;

#appoint表
CREATE TABLE appoint (
    teacher_id INT,
    teacher_name varchar(20) not null,
    user_sno INT,
    user_name varchar(20) not null,
    appoint_time DATETIME,
    place varchar(20),
    request_time DATETIME,
    teacher_college varchar(20),
    teacher_phone varchar(20),
    status VARCHAR(10)
);

drop table appoint;

insert appoint values(9001,'老王',1001,'阿翔','2020-04-12 10:00:00','工一301',now(),'预约成功');
insert appoint values(9001,'老王',1001,'阿翔','2020-4-28','工一405',now(),'审核中');
insert appoint values(9001,'老王',1001,'阿翔','2020-4-1','工一405','2020-3-22','计算机学院','12345678991','已过期');

delete from appoint where teacher_id=9002;

select * from appoint;
select * from appoint  where user_sno=1001 and DATE_FORMAT(request_time,'%Y-%m-%d')>=DATE_SUB(curdate(),interval 30 day) order by appoint_time desc limit 1,5 ;

SET SQL_SAFE_UPDATES = 0;