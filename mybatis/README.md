@[TOC]

> 这篇文章是初探mybatis的总结
> 借助 MyBatis-Spring-Boot-Starter 来实现零xml使用mybatis

> 参考文章
> [http://mybatis.org/spring/zh/index.html](http://mybatis.org/spring/zh/index.html)
> [http://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/](http://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)

# 前言
众所周知，MyBatis的核心有两大组件：SqlSessionFactory 和 Mapper 接口。前者表示数据库链接，后者表示SQL映射。当我们基于Spring使用MyBatis的时候，也要保证在Spring环境中能存在着两大组件。

MyBatis-Spring-Boot-Starter 将会完成以下功能:

1、`Autodetect an existing DataSource`
自动发现存在的DataSource

2、`Will create and register an instance of a SqlSessionFactory passing that DataSource as an input using the SqlSessionFactoryBean`
利用SqlSessionFactoryBean创建并注册SqlSessionFactory

3、`Will create and register an instance of a SqlSessionTemplate got out of the SqlSessionFactory`
创建并注册SqlSessionTemplate

4、`Auto-scan your mappers, link them to the SqlSessionTemplate and register them to Spring context so they can be injected into your beans`
自动扫描Mappers，并注册到Spring上下文环境方便程序的注入使用

mybatis-spring-boot-starter就是参照Spring Boot的设计思想，化繁为简，以简单注解的方式让用户快速上手。



下面我们简单的创建一个springboot项目，让他跑起来：
# 使用SpringBoot整合mybatis
## 依赖配置
### pom.xml
```xml
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.2.0</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
```

### application.yml

```yml
mybatis:
  configuration:
    map-underscore-to-camel-case: true
    # 该配置就是将带有下划线的表字段映射为驼峰格式的实体类属性
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/mybits?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8
    username: root
    password: aaaa
```
## 建表语句和数据

```sql
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `sex` char(6) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `pwd` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

INSERT INTO `user` VALUES (2, 'java', '✔', 'aaaa', 'teat@163.com');
INSERT INTO `user` VALUES (3, '张三', '男', 'a', '67676@qq.com');
INSERT INTO `user` VALUES (4, '李四', '男', 'a', '7676776@qq.com');
INSERT INTO `user` VALUES (5, '王五', '女', 'a', '7575@qq.com');
INSERT INTO `user` VALUES (6, '赵六', '女', 'a', '123@qq.com');
INSERT INTO `user` VALUES (7, '测试', '男', 'aaaaa', '1234@qq.com');
```
## 实体类

```java
public class User {

    private Integer id;
    private String name;
    private String sex;
    private String pwd;
    private String email;

    public User() { }

    public User(Integer id, String name, String sex, String pwd, String email) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.pwd = pwd;
        this.email = email;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getSex() { return sex; }

    public void setSex(String sex) { this.sex = sex; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPwd() { return pwd; }

    public void setPwd(String pwd) { this.pwd = pwd; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}

```

## 创建Mapper

```java
@Mapper
public interface UserMapper {
    @Select("select * from user")
    List<User> selectAll();

    @Select("select * from user where id = #{id}")
    User selectById(@Param("id") int id);

    @Select("select * from user where name = #{name}")
    Map<String, Object> selectByName1(@Param("name") String name);

    @Select("select * from user where name = #{name}")
    User selectByName2(@Param("name") String name);

//    @Select("select * from user where name = #{name} and pwd = #{name}")
//    User selectByNameAndPwd(@Param("name") String name, @Param("pwd") String pwd);

    @Select("select * from user where name = #{name} and pwd = #{pwd}")
    User selectByNameAndPwd(String name, String pwd);

    @Delete("delete from user where id = #{id}")
    boolean deleteById(int id);

    @Insert("insert into user values (null,#{name},#{sex},#{pwd},#{email})")
    boolean insertUser(String name, String sex, String pwd, String email);

    @Update("update user set name =  #{name} where id = #{id}")
    boolean updateById(String name, int id);
}

```
## 创建server层

```java
@Service
public class UserBiz {
    @Resource
    private UserMapper um;

    public User selectById(int id) {
        return um.selectById(id);
    }

    public List<User> queryAll() {
        return um.selectAll();
    }

    public Map<String, Object> queryByName1(String name) {
        return um.selectByName1(name);
    }

    public User queryByName2(String name) {
        return um.selectByName2(name);
    }

    public User queryByNameAndPwd(String name, String pwd) {
        return um.selectByNameAndPwd(name, pwd);
    }

    public boolean delete(int id) {
        return um.deleteById(id);
    }

    public boolean add(User user) {
        return um.insertUser(user.getName(), user.getSex(), user.getPwd(), user.getEmail());
    }

    public boolean change(String name, int id) {
        return um.updateById(name, id);
    }
}
```

## 测试代码

```java
@SpringBootTest
public class UserBizTest {

    @Autowired
    UserBiz userBiz;

    @Test
    public void selectByAccountAndPwd() {
        System.out.println(userBiz.selectById(2));
    }

    @Test
    public void queryAll() {
        System.out.println(userBiz.queryAll());
    }

    @Test
    public void queryByName() {
        long t1 = new Date().getTime();
        Map<String, Object> maps = userBiz.queryByName1("张三");
        long t2 = new Date().getTime();

        System.out.println(t2 - t1);
        System.out.println(maps);

        t1 = new Date().getTime();
        User user = userBiz.queryByName2("张三");
        t2 = new Date().getTime();
        System.out.println(t2 - t1);
        System.out.println(user);
    }

    @Test
    public void login() {
        User user = userBiz.queryByNameAndPwd("张三", "a");
        System.out.println(user);
        user = userBiz.queryByNameAndPwd("张三", "aa");
        System.out.println(user);
    }

    @Test
    public void delete() {
        Assert.isTrue(userBiz.delete(1));
    }

    @Test
    public void add() {
        User user = new User(null, "Test", "男", "aaaaa", "1234@qq.com");
        Assert.isTrue(userBiz.add(user));
    }
    @Test
    public void change() {
        Assert.isTrue(userBiz.change("测试",7),"修改失败");
    }
}
```

上面的代码就演示了使用mybatis对数据库的增删改查操作

# MyBatis 中 #{} 和 ${} 的区别

> [https://www.cnblogs.com/dato/p/7027949.html](https://www.cnblogs.com/dato/p/7027949.html)

## 1、在MyBatis 的映射配置文件中，动态传递参数有两种方式：
（1）#{} 占位符
（2）${} 拼接符


## 2、#{} 和 ${} 的区别
1. #{} 为参数占位符 ?，即sql 预编译
${} 为字符串替换，即 sql 拼接

2. #{}：动态解析 -> 预编译 -> 执行
${}：动态解析 -> 编译 -> 执行

3. #{} 的变量替换是在DBMS 中
${} 的变量替换是在 DBMS 外

4. 变量替换后，#{} 对应的变量自动加上单引号 ''
   变量替换后，${} 对应的变量不会加上单引号 ''

5. #{} 能防止sql 注入
${} 不能防止sql 注入

## 3、演示
#### Mapper 层
```java
    /**
     * 使用 #{} 占位符
     *
     * @param id
     * @return
     */
    @Select("select * from user where id > #{id}")
    List<User> select1(String id);

    /**
     * 使用  ${} 拼接符
     *
     * @param id
     * @return
     */
    @Select("select * from user where id > ${id}")
    List<User> select2(String id);
```

#### server层

```java
    public List<User> test1(String id){
        return um.select1(id);
    }
    public List<User> test2(String id){
        return um.select2(id);
    }
```
#### 测试类

```java
 @Test
    public void test1(){
        printList(userBiz.test1("1"));
        System.out.println();
        printList(userBiz.test1("1 and sex = '男'"));
    }
    @Test
    public void test2(){
        printList(userBiz.test2("1"));
        System.out.println();
        printList(userBiz.test2("1 and sex = '男'"));
    }

    public void printList(List list){
        for(Object o: list){
            System.out.println(o);
        }
    }
```

#### 查询结果对比
1. 使用#{}
   ![在这里插入图片描述](https://img-blog.csdnimg.cn/f6e9476d31a946d3956f89c03142cd2a.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ0NzM3MDk0,size_16,color_FFFFFF,t_70)
2. 使用${}
   ![在这里插入图片描述](https://img-blog.csdnimg.cn/47329e1c86c74785b96c2f90ff0e4fae.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ0NzM3MDk0,size_16,color_FFFFFF,t_70)
# 项目源码
百度云
链接：[https://pan.baidu.com/s/1gn72dGCxoorhBKFebDOvLQ](https://pan.baidu.com/s/1gn72dGCxoorhBKFebDOvLQ)
提取码：g44z 

