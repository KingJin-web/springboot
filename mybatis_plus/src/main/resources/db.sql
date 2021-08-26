insert into user
values (null, '蔡徐坤', '123', '', '');

insert into user
values (null, '张三', '男', 'a', '67676@qq.com'),
       (null, '李四', '男', 'a', '7676776@qq.com'),
       (null, '李四', '女', 'a', '7575@qq.com'),
       (null, '蔡徐坤', '女', 'a', '123@qq.com');


DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`    int(0) UNSIGNED                                 NOT NULL AUTO_INCREMENT,
    `name`  varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
    `sex`   char(6) CHARACTER SET utf8 COLLATE utf8_bin     NULL DEFAULT NULL,
    `pwd`   varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
    `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 13
  CHARACTER SET = utf8
  COLLATE = utf8_bin
  ROW_FORMAT = Dynamic;
