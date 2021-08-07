insert into user
values (null, '蔡徐坤', '123', '', '');

insert into user
values (null, '张三', '男', 'a', '67676@qq.com'),
       (null, '李四', '男', 'a', '7676776@qq.com'),
       (null, '李四', '女', 'a', '7575@qq.com'),
       (null, '蔡徐坤', '女', 'a', '123@qq.com');


delete
from user
where id = 1;
update user
set name = " "
where id = "";