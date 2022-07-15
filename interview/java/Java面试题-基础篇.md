## 基础篇
### JDK和JRE有什么区别？

- JRE：Java Runtime Environment（ java 运行时环境）。即java程序的运行时环境，包含了 java 虚拟机，java基础类库。
- JDK：Java Development Kit（ java 开发工具包）。即java语言编写的程序所需的开发工具包。JDK 包含了 JRE，同时还包括 java 源码的编译器 javac、监控工具 jconsole、分析工具 jvisualvm等。

### ==和equals的区别是什么?
-  == 是关系运算符，equals() 是方法，结果都返回布尔值
- Object 的 == 和 equals() 比较的都是地址，作用相同

== 作用：
- 基本类型，比较值是否相等
- 引用类型，比较内存地址值是否相等
- 不能比较没有父子关系的两个对象

equals()方法的作用：

- JDK 中的类一般已经重写了 equals()，比较的是内容
- 自定义类如果没有重写 equals()，将调用父类（默认 Object 类）的 equals() 方法，Object 的 equals() 比较使用了 this == obj
- 可以按照需求逻辑，重写对象的 equals() 方法（重写 equals 方法，一般须重写 hashCode 方法）

### 基本类型和包装类对象使用 == 和 equals进行比较的结果？
**1、值不同，使用 == 和 equals() 比较都返回 false**
**2、值相同**
使用 == 比较：
基本类型 － 基本类型、基本类型 － 包装对象返回 true
包装对象 － 包装对象，非同一个对象（对象的内存地址不同）返回 false；对象的内存地址相同返回 true，如下值等于 100 的两个 Integer 对象（原因是 JVM 缓存部分基本类型常用的包装类对象，如 Integer -128 ~ 127 是被缓存的）
```java
 Integer i1 = 100;
 Integer i2 = 100;
 Integer i3 = 200;
 Integer i4 = 200;
         
 System.out.println(i1==i2); //打印true
 System.out.println(i3==i4); //打印false
```
使用 equals() 比较
包装对象－基本类型返回 true
包装对象－包装对象返回 true

### 什么是装箱？什么是拆箱？装箱和拆箱的执行过程？常见问题？
**1、什么是装箱？什么是拆箱？**

装箱：基本类型转变为包装器类型的过程。
拆箱：包装器类型转变为基本类型的过程。

```java
//JDK1.5之前是不支持自动装箱和自动拆箱的，定义Integer对象，必须
Integer i = new Integer(8);
 
//JDK1.5开始，提供了自动装箱的功能，定义Integer对象可以这样
Integer i = 8;
 
int n = i;//自动拆箱
```
**2、装箱和拆箱的执行过程？**

- 装箱是通过调用包装器类的 valueOf 方法实现的
- 拆箱是通过调用包装器类的 xxxValue 方法实现的，xxx代表对应的基本数据类型。
- 如int装箱的时候自动调用Integer的valueOf(int)方法；Integer拆箱的时候自动调用Integer的intValue方法。

**3、常见问题？**
- 整型的包装类 valueOf 方法返回对象时，在常用的取值范围内，会返回缓存对象。
- 浮点型的包装类 valueOf 方法返回新的对象。
- 布尔型的包装类 valueOf 方法 Boolean类的静态常量 TRUE | FALSE。
### hashCode()相同，equals()也一定为true吗？
首先，答案肯定是不一定。同时反过来 equals() 为true，hashCode() 也不一定相同。

类的 hashCode() 方法和 equals() 方法都可以重写，返回的值完全在于自己定义。
hashCode() 返回该对象的哈希码值；equals() 返回两个对象是否相等。


**关于 hashCode() 和 equals() 是方法是有一些 常规协定：**

- 1、两个对象用 equals() 比较返回true，那么两个对象的hashCode()方法必须返回相同的结果。

- 2、两个对象用 equals() 比较返回false，不要求hashCode()方法也一定返回不同的值，但是最好返回不同值，以提高哈希表性能。

- 3、重写 equals() 方法，必须重写 hashCode() 方法，以保证 equals() 方法相等时两个对象 hashcode() 返回相同的值。

就像打人是你的能力，但打伤了就违法了。重写 equals 和 hashCode 方法返回是否为 true 是你的能力，但你不按照上述协议进行控制，在用到对象 hash 和 equals 逻辑判断相等时会出现意外情况，如 HashMap 的 key 是否相等。
### final在java中的作用
final 语义是不可改变的。

- 被 final 修饰的类，不能够被继承
- 被 final 修饰的成员变量必须要初始化，赋初值后不能再重新赋值(可以调用对象方法修改属性值)。对基本类型来说是其值不可变；对引用变量来说其引用不可变，即不能再指向其他的对象
- 被 final 修饰的方法不能重写

### final finally finalize()区别
- final 表示最终的、不可改变的。用于修饰类、方法和变量。final 修饰的类不能被继承；final 方法也同样只能使用，不能重写，但能够重载；final 修饰的成员变量必须在声明时给定初值或者在构造方法内设置初始值，只能读取，不可修改；final 修饰的局部变量必须在声明时给定初值；final 修饰的变量是非基本类型，对象的引用地址不能变，但对象的属性值可以改变
- finally 异常处理的一部分，它只能用在 try/catch 语句中，表示希望 finally 语句块中的代码最后一定被执行（存在一些情况导致 finally 语句块不会被执行，如 jvm 结束）
- finalize() 是在 java.lang.Object 里定义的，Object 的 finalize() 方法什么都不做，对象被回收时 finalize() 方法会被调用。Java 技术允许使用 finalize() 方法在垃圾收集器将对象从内存中清除出去之前做必要清理工作，在垃圾收集器删除对象之前被调用的。一般情况下，此方法由JVM调用。特殊情况下，可重写 finalize() 方法，当对象被回收的时候释放一些资源，须调用 super.finalize() 。
### finally语句块一定执行吗？
答案是不一定。存在很多特殊情况导致 finally 语句块不执行。如：

- 直接返回未执行到 try-finally 语句块
- 抛出异常未执行到 try-finally 语句块
- 系统退出未执行到 finally 语句块
  等...

### final与static的区别
- 都可以修饰类、方法、成员变量。
- 都不能用于修饰构造方法。
- static 可以修饰类的代码块，final 不可以。
- static 不可以修饰方法内的局部变量，final 可以。

**static：**
- static 修饰表示静态或全局，被修饰的属性和方法属于类，可以用类名.静态属性 / 方法名 访问
- static 修饰的代码块表示静态代码块，当 Java 虚拟机（JVM）加载类时，就会执行该代码块,只会被执行一次
- static 修饰的属性，也就是类变量，是在类加载时被创建并进行初始化，只会被创建一次
- static 修饰的变量可以重新赋值
- static 方法中不能用 this 和 super 关键字
- static 方法必须被实现，而不能是抽象的abstract
- static 方法不能被重写


**final：**

- final 修饰表示常量、一旦创建不可改变
- final 标记的成员变量必须在声明的同时赋值，或在该类的构造方法中赋值，不可以重新赋值
- final 方法不能被子类重写
- final 类不能被继承，没有子类，final 类中的方法默认是 final 的

### return与finally的执行顺序对返回值的影响
对于 try 和 finally 至少一个语句块包含 return 语句的情况：
- finally 语句块会执行
- finally 没有 return，finally 对 return 变量的重新赋值修改无效
- try 和 finally 都包含 return，return 值会以 finally 语句块 return 值为准

### String对象中的replace和replaceAll的区别？
- replace方法：支持字符和字符串的替换。
- replaceAll方法：基于正则表达式的字符串替换。
  例子

```java
String str = "Hello Java. Java is a language.";
System.out.println(str.replace("Java.", "c++"));//打印 Hello c++ Java is a language.
System.out.println(str.replaceAll("Java.", "c++"));//打印 Hello c++ c++is a language.
```

### Math.round(-1.5) 等于多少？
运行结果： -1

JDK 中的 java.lang.Math 类

- ceil() ：向上取整，返回小数所在两整数间的较大值，返回类型是 double，如 -1.5 返回 -1.0
- floor() ：向下取整，返回小数所在两整数间的较小值，返回类型是 double，如 -1.5 返回 -2.0
- round() ：朝正无穷大方向返回参数最接近的整数，可以换算为 参数 + 0.5 向下取整，返回值是 int 或 long，如 -1.5 返回 -1

### String属于基础的数据类型吗？
不属于。

Java 中 8 种基础的数据类型：byte、short、char、int、long、float、double、boolean

但是 String 类型却是最常用到的引用类型。
### java中操作字符串都有哪些类？它们之间有什么区别？
Java 中，常用的对字符串操作的类有 String、StringBuffer、StringBuilder

- String : final 修饰，String 类的方法都是返回 new String。即对 String 对象的任何改变都不影响到原对象，对字符串的修改操作都会生成新的对象。
- StringBuffer : 对字符串的操作的方法都加了synchronized，保证线程安全。
- StringBuilder : 不保证线程安全，在方法体内需要进行字符串的修改操作，可以 new StringBuilder 对象，调用 StringBuilder 对象的 append()、replace()、delete() 等方法修改字符串。

### 如何将字符串反转？
### String类的常用方法有哪些？
- equals：字符串是否相同
- equalsIgnoreCase：忽略大小写后字符串是否相同
- compareTo：根据字符串中每个字符的Unicode编码进行比较
- compareToIgnoreCase：根据字符串中每个字符的Unicode编码进行忽略大小写比较
- indexOf：目标字符或字符串在源字符串中位置下标
- lastIndexOf：目标字符或字符串在源字符串中最后一次出现的位置下标
- valueOf：其他类型转字符串
- charAt：获取指定下标位置的字符
- codePointAt：指定下标的字符的Unicode编码
- concat：追加字符串到当前字符串
- isEmpty：字符串长度是否为0
- contains：是否包含目标字符串
- startsWith：是否以目标字符串开头
- endsWith：是否以目标字符串结束
- format：格式化字符串
- getBytes：获取字符串的字节数组
- getChars：获取字符串的指定长度字符数组
- toCharArray：获取字符串的字符数组
- join：以某字符串，连接某字符串数组
- length：字符串字符数
- matches：字符串是否匹配正则表达式
- replace：字符串替换
- replaceAll：带正则字符串替换
- replaceFirst：替换第一个出现的目标字符串
- split：以某正则表达式分割字符串
- substring：截取字符串
- toLowerCase：字符串转小写
- toUpperCase：字符串转大写
- trim：去字符串首尾空格

### 普通类和抽象类有哪些区别？
- 抽象类不能被实例化
- 抽象类可以有抽象方法，抽象方法只需申明，无需实现
- 含有抽象方法的类必须申明为抽象类
- 抽象类的子类必须实现抽象类中所有抽象方法，否则这个子类也是抽象类
- 抽象方法不能被声明为静态
- 抽象方法不能用 private 修饰
- 抽象方法不能用 final 修饰

### 抽象类必须要有抽象方法吗？
不一定。如

```java
public abstract class TestAbstractClass {
	public static void notAbstractMethod() {
		System.out.println("I am not a abstract method.");
	}
}
```

### 抽象类能使用final修饰吗？
不能，抽象类是被用于继承的，final修饰代表不可修改、不可继承的。


### 接口和抽象类有什么区别？
- 抽象类可以有构造方法；接口中不能有构造方法。
- 抽象类中可以有普通成员变量；接口中没有普通成员变量。
- 抽象类中可以包含非抽象普通方法；JDK1.8 以前接口中的所有方法默认都是抽象的，JDK1.8 开始方法可以有 default 实现和 static 方法。
- 抽象类中的抽象方法的访问权限可以是 public、protected 和 default；接口中的抽象方法只能是 public 类型的，并且默认即为 public abstract 类型。
- 抽象类中可以包含静态方法；JDK1.8 前接口中不能包含静态方法，JDK1.8 及以后可以包含已实现的静态方法。
- 抽象类和接口中都可以包含静态成员变量，抽象类中的静态成员变量可以是任意访问权限；接口中变量默认且只能是 public static final 类型。
- 一个类可以实现多个接口，用逗号隔开，但只能继承一个抽象类。
- 接口不可以实现接口，但可以继承接口，并且可以继承多个接口，用逗号隔开。
### Java访问修饰符有哪些？权限的区别？
Java 语言中有四种权限访问控制符，能够控制类中成员变量和方法的可见性。

- public
  被 public 修饰的成员变量和方法可以在任何类中都能被访问到。
  被 public 修饰的类，在一个 java 源文件中只能有一个类被声明为 public ，而且一旦有一个类为 public ，那这个 java 源文件的文件名就必须要和这个被 public 所修饰的类的类名相同，否则编译不能通过。

- protected
  被 protected 修饰的成员会被位于同一 package 中的所有类访问到，也能被该类的所有子类继承下来。

- friendly
  默认，缺省的。在成员的前面不写访问修饰符的时候，默认就是友好的。
  同一package中的所有类都能访问。
  被 friendly 所修饰的成员只能被该类所在同一个 package 中的子类所继承下来。


- private
  私有的。只能在当前类中被访问到。

![在这里插入图片描述](https://img-blog.csdnimg.cn/96622b3bc9524d9497ed6c983bb2f225.png)

### Java中的 << >> >>> 是什么？
- << 表示左移，不分正负数，低位补0
- &gt;> 表示右移，如果该数为正，则高位补0，若为负数，则高位补1
- &gt;>>  表示无符号右移，也叫逻辑右移，即若该数为正，则高位补0，而若该数为负数，则右移后高位同样补0

**简单理解：**

<<1  相当于乘以2
&gt;> 1  相当于除以2
&gt;>> 不考虑高位的正负号，正数的 >>> 等同于 >>


**PS：位移操作涉及二进制、原码、补码、反码相关，可参考：**

www.cnblogs.com/chuijingjing/p/9405598.html
www.cnblogs.com/hanhuo/p/6341111.html

### javap的作用是什么？
javap 是 Java class文件分解器，可以反编译，也可以查看 java 编译器生成的字节码等。

### throw和throws的区别？
**throw：**
- 表示方法内抛出某种异常对象(只能是一个)
- 用于程序员自行产生并抛出异常
- 位于方法体内部，可以作为单独语句使用
- 如果异常对象是非 RuntimeException 则需要在方法申明时加上该异常的抛出，即需要加上 throws 语句 或者 在方法体内 try catch 处理该异常，否则编译报错
- 执行到 throw 语句则后面的语句块不再执行

**throws：**
- 方法的定义上使用 throws 表示这个方法可能抛出某些异常(可以有多个)
- 用于声明在该方法内抛出了异常
- 必须跟在方法参数列表的后面，不能单独使用
- 需要由方法的调用者进行异常处理
  try-catch-finally中哪个部分可以省略？

### 常见的异常类有哪些？
异常非常多，Throwable 是异常的根类。

Throwable 包含子类 错误-Error 和 异常-Exception 。

Exception 又分为 一般异常和运行时异常 RuntimeException。

运行时异常不需要代码显式捕获处理。



下面是常见异常类及其父子关系：

```java
Throwable
|　　├ Error  

|　　│ ├ IOError

|　　│ ├ LinkageError

|　　│ ├ ReflectionError

|　　│ ├ ThreadDeath

|　　│ └ VirtualMachineError

|　　│ 

|　　├ Exception  

|　　│　├ CloneNotSupportedException

|　　│　├ DataFormatException

|　　│　├ InterruptedException

|　　│　├ IOException

|　　│　├ ReflectiveOperationException

|　　│　├ RuntimeException 

|　　│　　├ ArithmeticException

|　　│　　├ ClassCastException

|　　│　　├ ConcurrentModificationException

|　　│　　├ IllegalArgumentException

|　　│　　├ IndexOutOfBoundsException

|　　│　　├ NoSuchElementException

|　　│　　├ NullPointerException

|　　│　　└ SecurityException

|　　│　└  SQLException
```

什么是JAVA内部类？
### nio中的Files类常用方法有哪些？
- isExecutable：文件是否可以执行
- isSameFile：是否同一个文件或目录
- isReadable：是否可读
- isDirectory：是否为目录
- isHidden：是否隐藏
- isWritable：是否可写
- isRegularFile：是否为普通文件
- getPosixFilePermissions：获取POSIX文件权限，windows系统调用此方法会报错
- setPosixFilePermissions：设置POSIX文件权限
- getOwner：获取文件所属人
- setOwner：设置文件所属人
- createFile：创建文件
- newInputStream：打开新的输入流
- newOutputStream：打开新的输出流
- createDirectory：创建目录，当父目录不存在会报错
- createDirectories：创建目录，当父目录不存在会自动创建
- createTempFile：创建临时文件
- newBufferedReader：打开或创建一个带缓存的字符输入流
- probeContentType：探测文件的内容类型
- list：目录中的文件、文件夹列表
- find：查找文件
- size：文件字节数
- copy：文件复制
- lines：读出文件中的所有行
- move：移动文件位置
- exists：文件是否存在
- walk：遍历所有目录和文件
- write：向一个文件写入字节
- delete：删除文件
- getFileStore：返回文件存储区
- newByteChannel：打开或创建文件，返回一个字节通道来访问文件
- readAllLines：从一个文件读取所有行字符串
- setAttribute：设置文件属性的值
- getAttribute：获取文件属性的值
- newBufferedWriter：打开或创建一个带缓存的字符输出流
- readAllBytes：从一个文件中读取所有字节
- createTempDirectory：在特殊的目录中创建临时目录
- deleteIfExists：如果文件存在删除文件
- notExists：判断文件不存在
- getLastModifiedTime：获取文件最后修改时间属性
- setLastModifiedTime：更新文件最后修改时间属性
- newDirectoryStream：打开目录，返回可迭代该目录下的目录流
- walkFileTree：遍历文件树，可用来递归删除文件等操作
### 什么是反射？有什么作用？
**Java 反射，就是在运行状态中**

- 获取任意类的名称、package 信息、所有属性、方法、注解、类型、类加载器、modifiers（public、static）、父类、现实接口等
- 获取任意对象的属性，并且能改变对象的属性
- 调用任意对象的方法
- 判断任意一个对象所属的类
- 实例化任意一个类的对象

**Java 的动态就体现在反射。**
通过反射我们可以实现动态装配，降低代码的耦合度；动态代理等。反射的过度使用会严重消耗系统资源。
JDK 中 java.lang.Class 类，就是为了实现反射提供的核心类之一。
一个 jvm 中一种 Class 只会被加载一次。
### 动态代理是什么？应用场景？
**`动态代理：在运行时，创建目标类，可以调用和扩展目标类的方法。`**
Java 中实现动态的方式：

- JDK 中的动态代理
- Java类库 CGLib
  应用场景：

- 统计每个 api 的请求耗时
- 统一的日志输出
- 校验被调用的 api 是否已经登录和权限鉴定
- Spring的 AOP 功能模块就是采用动态代理的机制来实现切面编程
### 怎么实现动态代理？
- JDK 动态代理
- CGLib 动态代理
- 使用 Spring aop 模块完成动态代理功能
### 什么是java序列化？什么情况下需要序列化？
- 序列化：将 Java 对象转换成字节流的过程。
- 反序列化：将字节流转换成 Java 对象的过程。

**注意事项：**
- 某个类可以被序列化，则其子类也可以被序列化
- 对象中的某个属性是对象类型，需要序列化也必须实现 Serializable 接口
- 声明为 static 和 transient 的成员变量，不能被序列化。static 成员变量是描述类级别的属性，transient 表示临时数据
- 反序列化读取序列化对象的顺序要保持一致


当 Java 对象需要在网络上传输 或者 持久化存储到文件中时，就需要对 Java 对象进行序列化处理。

序列化的实现：类实现 Serializable 接口，这个接口没有需要实现的方法。实现 Serializable 接口是为了告诉 jvm 这个类的对象可以被序列化。

### 什么场景要对象克隆？
- 方法需要 return 引用类型，但又不希望自己持有引用类型的对象被修改。
- 程序之间方法的调用时参数的传递。有些场景为了保证引用类型的参数不被其他方法修改，可以使用克隆后的值作为参数传递。

### 深拷贝和浅拷贝区别是什么？
复制一个 Java 对象


- 浅拷贝：复制基本类型的属性；引用类型的属性复制，复制栈中的变量 和 变量指向堆内存中的对象的指针，不复制堆内存中的对象。
  ![在这里插入图片描述](https://img-blog.csdnimg.cn/c002e370d7fd4165ab6bb40e8d8947fc.png)
- 深拷贝：复制基本类型的属性；引用类型的属性复制，复制栈中的变量 和 变量指向堆内存中的对象的指针和堆内存中的对象。
  ![在这里插入图片描述](https://img-blog.csdnimg.cn/f2db1d5141e44eddbb6e67d0440d1e5a.png)


### 如何实现对象克隆与深拷贝？
1、实现 Cloneable 接口，重写 clone() 方法。
2、不实现 Cloneable 接口，会报 CloneNotSupportedException 异常。
3、Object 的 clone() 方法是浅拷贝，即如果类中属性有自定义引用类型，只拷贝引用，不拷贝引用指向的对象。



可以使用下面的两种方法，完成 Person 对象的深拷贝。

方法1、对象的属性的Class 也实现 Cloneable 接口，在克隆对象时也手动克隆属性。
方法2、结合序列化(JDK java.io.Serializable 接口、JSON格式、XML格式等)，完成深拷贝
结合 java.io.Serializable 接口，完成深拷贝

### Java跨平台运行的原理
1. .java 源文件要先编译成与操作系统无关的 .class 字节码文件，然后字节码文件再通过 Java 虚拟机解释成机器码运行。
2. .class 字节码文件面向虚拟机，不面向任何具体操作系统。
3. 不同平台的虚拟机是不同的，但它们给 JDK 提供了相同的接口。
4. Java 的跨平台依赖于不同系统的 Java 虚拟机。

### Java的安全性体现在哪里？
1. 使用引用取代了指针，指针的功能强大，但是也容易造成错误，如数组越界问题。

2. 拥有一套异常处理机制，使用关键字 throw、throws、try、catch、finally
3. 强制类型转换需要符合一定规则
4. 字节码传输使用了加密机制
5. 运行环境提供保障机制：字节码校验器->类装载器->运行时内存布局->文件访问限制
6. 不用程序员显示控制内存释放，JVM 有垃圾回收机制

### Java针对不同的应用场景提供了哪些版本？
- J2SE：Standard Edition(标准版) ，包含 Java 语言的核心类。如IO、JDBC、工具类、网络编程相关类等。从JDK 5.0开始，改名为Java SE。
- J2EE：Enterprise Edition(企业版)，包含 J2SE 中的类和企业级应用开发的类。如web相关的servlet类、JSP、xml生成与解析的类等。从JDK 5.0开始，改名为Java EE。
- J2ME：Micro Edition(微型版)，包含 J2SE 中的部分类，新添加了一些专有类。一般用设备的嵌入式开发，如手机、机顶盒等。从JDK 5.0开始，改名为Java ME。

### 什么是JVM？什么是JDK？什么是JRE？
**JVM**
1. Java Virtual Machine（Java虚拟机）的缩写
2. 实现跨平台的最核心的部分
3. .class 文件会在 JVM 上执行，JVM 会解释给操作系统执行
4. 有自己的指令集，解释自己的指令集到 CPU 指令集和系统资源的调用
5. JVM 只关注被编译的 .class 文件，不关心 .java 源文件

**JDK**
-  Java Development Kit（Java 开发工具包）的缩写。用于 java 程序的开发，提供给程序员使用
- 使用 Java 语言编程都需要在计算机上安装一个 JDK
- JDK 的安装目录 5 个文件夹、一个 src 类库源码压缩包和一些说明文件
    * bin：各种命令工具， java 源码的编译器 javac、监控工具 jconsole、分析工具 jvisualvm 等
    * include：与 JVM 交互C语言用的头文件
    * lib：类库
    * jre：Java 运行环境
    * db：安装 Java DB 的路径
    * src.zip：Java 所有核心类库的源代码
    * jdk1.8 新加了 javafx-src.zip 文件，存放 JavaFX 脚本，JavaFX 是一种声明式、静态类型编程语言
    * src.zip：Java 所有核心类库的源代码
    * jdk1.8 新加了 javafx-src.zip 文件，存放 JavaFX 脚本，JavaFX 是一种声明式、静态类型编程语言

**JVM**
-  Java Runtime Environment（Java运行环境）的缩写
- 包含 JVM 标准实现及 Java 核心类库，这些是运行 Java 程序的必要组件
- 是 Java 程序的运行环境，并不是一个开发环境，没有包含任何开发工具（如编译器和调试器）
- 是运行基于 Java 语言编写的程序所不可缺少的运行环境，通过它，Java 程序才能正常运行

### JDK、JRE、JVM之间的关系是什么样的？
- JDK 是 JAVA 程序开发时用的开发工具包，包含 Java 运行环境 JRE
- JDK、JRE 内部都包含 JAVA虚拟机 JVM
- JVM 包含 Java 应用程序的类的解释器和类加载器等

### Java语言有哪些注释的方式？
- 单行注释
- 多行注释，不允许嵌套
- 文档注释，常用于类和方法的注释
-
### Java中有几种基本数据类型？它们分别占多大字节？
- byte：1个字节，8位
- short：2个字节，16位
- int：4个字节，32位
- long：8个字节，64位
- float：4个字节，32位
- double：8个字节，64位
- boolean：官方文档未明确定义，依赖于 JVM 厂商的具体实现。逻辑上理解是占用 1位，但是实际中会考虑计算机高效存储因素
- char：2个字节，16位

**详细说明可以参考：**
https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html

i++和++i的作用和区别
### &和&&的作用和区别
**&**
- 逻辑与，& 两边的表达式都会进行运算
- 整数的位运算符

**&&**
- 短路与，&& 左边的表达式结果为 false 时，&& 右边的表达式不参与计算

### |和||的作用和区别
**|**
- 逻辑或，| 两边的表达式都会进行运算
- 整数的或运算符

**||**
- 短路或，|| 左边的表达式结果为 true 时，|| 右边的表达式不参与计算


### Java中基本类型的转换规则
等级低到高:

- byte、short、int、long、float、double
- char、int、long、float、double

自动转换：运算过程中，低级可以自动向高级转换

强制转换：高级需要强制转换为低级，可能会丢失精度

规则：

- = 右边先自动转换成表达式中最高级的数据类型，再进行运算。整型经过运算会自动转化最低 int 级别，如两个 char 类型的相加，得到的是一个 int 类型的数值。
- = 左边数据类型级别 大于 右边数据类型级别，右边会自动升级
- = 左边数据类型级别 小于 右边数据类型级别，需要强制转换右边数据类型
- char 与 short，char 与 byte 之间需要强转，因为 char 是无符号类型

### if-else if-else与switch的区别
### while和do-while的区别
- while 先判断后执行，第一次判断为 false，循环体一次都不执行
- do-while 先执行后判断，最少执行1次

### break语句的作用
- 结束当前循环并退出当前循环体
- 结束 switch 语句
### continue语句的作用
- 结束本次循环，循环体后续的语句不执行
- 继续进行循环条件的判断，进行下一次循环体语句的执行

### Java中数组有什么特征？
- 在内存中申请一块连续的空间
- 数组下标从 0 开始
- 每个数组元素都有默认值，基本类型的默认值为 0、0.0、false，引用类型的默认值为 null
- 数组的类型只能是一个，且固定，在申明时确定
- 数组的长度一经确定，无法改变，即定长。要改变长度，只能重新申明一个
### 可变参数的作用和特点是什么？
**作用：**
在不确定参数的个数时，可以使用可变参数。



**语法：**
参数类型...

**特点：**

- 每个方法最多只有一个可变参数
- 可变参数必须是方法的最后一个参数
- 可变参数可以设置为任意类型：引用类型，基本类型
- 参数的个数可以是 0 个、1 个或多个
- 可变参数也可以传入数组
- 无法仅通过改变 可变参数的类型，来重载方法
- 通过对 class 文件反编译可以发现，可变参数被编译器处理成了数组
### 类和对象的关系
- 类是对象的抽象；对象是类的具体实例
- 类是抽象的，不占用内存；对象是具体的，占用存储空间
- 类是一个定义包括在一类对象中的方法和变量的模板
### 说一说你的对面向过程和面向对象的理解
- 软件开发思想，先有面向过程，后有面向对象
- 在大型软件系统中，面向过程的做法不足，从而推出了面向对象
  都是解决实际问题的思维方式
- 两者相辅相成，宏观上面向对象把握复杂事物的关系；微观上面向过程去处理
- 面向过程以实现功能的函数开发为主；面向对象要首先抽象出类、属性及其方法，然后通过实例化类、执行方法来完成功能
- 面向过程是封装的是功能；面向对象封装的是数据和功能
- 面向对象具有继承性和多态性；面向过程则没有

### 方法重载和重写是什么？有什么区别？
**`重写：`** 在子类中将父类的成员方法的名称保留，重新编写成员方法的实现内容，更改方法的访问权限，修改返回类型的为父类返回类型的子类。

- 规则：
    * 重写发生在子类继承父类
    * 参数列表必须完全与被重写方法的相同
    * 重写父类方法时，修改方法的权限只能从小范围到大范围
    * 返回类型与被重写方法的返回类型可以不相同，但是必须是父类返回值的子类(JDK1.5 及更早版本返回类型要一样，JDK1.7 及更高版本可以不同)
    * 访问权限不能比父类中被重写的方法的访问权限更低。如：父类的方法被声明为 public，那么子类中重写该方法不能声明为 protected
    * 重写方法不能抛出新的检查异常和比被重写方法申明更宽泛的异常(即只能抛出父类方法抛出异常的子类)
    * 声明为 final 的方法不能被重写
    * 声明为 static 的方法不能被重写
    * 声明为 private 的方法不能被重写



**`重载：`** 一个类中允许同时存在一个以上的同名方法，这些方法的参数个数或者类型不同

- 重载条件：
    * 方法名相同
    * 参数类型不同 或 参数个数不同 或 参数顺序不同

- 规则：
    * 被重载的方法参数列表(个数或类型)不一样
    * 被重载的方法可以修改返回类型
    * 被重载的方法可以修改访问修饰符
    * 被重载的方法可以修改异常抛出
    * 方法能够在同一个类中或者在一个子类中被重载
    * 无法以返回值类型作为重载函数的区分标准



重载和重写的区别：

- 作用范围：重写的作用范围是父类和子类之间；重载是发生在一个类里面
- 参数列表：重载必须不同；重写不能修改
- 返回类型：重载可修改；重写方法返回相同类型或子类
- 抛出异常：重载可修改；重写可减少或删除，一定不能抛出新的或者更广的异常
- 访问权限：重载可修改；重写一定不能做更严格的限制
### this和super关键字的作用
**this：**

- 对象内部指代自身的引用
- 解决成员变量和局部变量同名问题
- 可以调用成员变量
- 不能调用局部变量
- 可以调用成员方法
- 在普通方法中可以省略 this
- 在静态方法当中不允许出现 this 关键字


**super：**

- 代表对当前对象的直接父类对象的引用
- 可以调用父类的非 private 成员变量和方法
- super(); 可以调用父类的构造方法，只限构造方法中使用，且必须是第一条语句
### static关键字的作用是什么？
- static 可以修饰变量、方法、代码块和内部类
- static 变量是这个类所有，由该类创建的所有对象共享同一个 static 属性
- 可以通过创建的对象名.属性名 和 类名.属性名两种方式访问
- static 变量在内存中只有一份
- static 修饰的变量只能是类的成员变量
- static 方法可以通过对象名.方法名和类名.方法名两种方式来访问
- static 代码块在类被第一次加载时执行静态代码块，且只被执行一次，主要作用是实现 static 属性的初始化
- static 内部类属于整个外部类，而不属于外部类的每个对象，只可以访问外部类的静态变量和方法
### abstract关键字的作用是什么？
- 可以修饰类和方法
- 不能修饰属性和构造方法
- abstract 修饰的类是抽象类，需要被继承
- abstract 修饰的方法是抽象方法，需要子类被重写
### java.lang.Object的常用方法？
- public final native Class<?> getClass(); 获取类结构信息
- public native int hashCode() 获取哈希码
- public boolean equals(Object) 默认比较对象的地址值是否相等，子类可以重写比较规则
- protected native Object clone() throws CloneNotSupportedException 用于对象克隆
- public String toString() 把对象转变成字符串
- public final native void notify() 多线程中唤醒功能
- public final native void notifyAll() 多线程中唤醒所有等待线程的功能
- public final void wait() throws InterruptedException 让持有对象锁的线程进入等待
- public final native void wait(long timeout) throws InterruptedException 让持有对象锁的线程进入等待，设置超时毫秒数时间
- public final void wait(long timeout, int nanos) throws InterruptedException 让持有对象锁的线程进入等待，设置超时纳秒数时间
- protected void finalize() throws Throwable 垃圾回收前执行的方法

### 子类构造方法的执行过程是什么样的？
- 如果子类的构造方法中没有通过 super 显式调用父类的有参构造方法，也没有通过 this 显式调用自身的其他构造方法，则系统会默认先调用父类的无参构造方法。这种情况下，写不写 super(); 语句，效果是一样的
- 如果子类的构造方法中通过 super 显式调用父类的有参构造方法，将执行父类相应的构造方法，不执行父类无参构造方法
- 如果子类的构造方法中通过 this 显式调用自身的其他构造方法，将执行类中相应的构造方法
- 如果存在多级继承关系，在创建一个子类对象时，以上规则会多次向更高一级父类应用，一直到执行顶级父类 Object 类的无参构造方法为止
### 什么是Java的多态？
- 实现多态的三个条件
    * 继承的存在。继承是多态的基础，没有继承就没有多态
    * 子类重写父类的方法，JVM 会调用子类重写后的方法
    * 父类引用变量指向子类对象


- 向上转型：将一个父类的引用指向一个子类对象，自动进行类型转换。
    * 通过父类引用变量调用的方法是子类覆盖或继承父类的方法，而不是父类的方法。
    * 通过父类引用变量无法调用子类特有的方法。


- 向下转型：将一个指向子类对象的引用赋给一个子类的引用，必须进行强制类型转换。
    *  向下转型必须转换为父类引用指向的真实子类类型，不是任意的强制转换，否则会出现 ClassCastException
    * 向下转型时可以结合使用 instanceof 运算符进行判断
### instanceof关键字的作用是什么？
instanceof 运算符是用来在运行时判断对象是否是指定类及其父类的一个实例。
比较的是对象，不能比较基本类型
### 什么是Java的垃圾回收机制？
**垃圾回收机制，简称 GC**

- Java 语言不需要程序员直接控制内存回收，由 JVM 在后台自动回收不再使用的内存
- 提高编程效率
- 保护程序的完整性
- JVM 需要跟踪程序中有用的对象，确定哪些是无用的，影响性能


**特点**

- 回收 JVM 堆内存里的对象空间，不负责回收栈内存数据
- 无法处理一些操作系统资源的释放，如数据库连接、输入流输出流、Socket 连接
- 垃圾回收发生具有不可预知性，程序无法精确控制垃圾回收机制执行
- 可以将对象的引用变量设置为 null，垃圾回收机制可以在下次执行时回收该对象。
- JVM 有多种垃圾回收 实现算法，表现各异
- 垃圾回收机制回收任何对象之前，会先调用对象的 finalize() 方法
- 可以通过 System.gc() 或 Runtime.getRuntime().gc() 通知系统进行垃圾回收，会有一些效果，但系统是否进行垃圾回收依然不确定
- 不要主动调用对象的 finalize() 方法，应该交给垃圾回收机制调用

### 什么是包装类？为什么要有包装类？基本类型与包装类如何转换？
**Java 中有 8 个基本类型，分别对应的包装类如下**

```java
byte -- Byte
boolean -- Boolean
short -- Short
char -- Character
int -- Integer
long -- Long
float -- Float
**加粗样式**double -- Double
```



**为什么要有包装类**

- 基本数据类型方便、简单、高效，但泛型不支持、集合元素不支持
- 不符合面向对象思维
- 包装类提供很多方法，方便使用，如 Integer 类 toHexString(int i)、parseInt(String s) 方法等等


**基本数据类型和包装类之间的转换**

- 包装类-->基本数据类型：包装类对象.xxxValue()
- 基本数据类型-->包装类：new 包装类(基本类型值)
- JDK1.5 开始提供了自动装箱（autoboxing）和自动拆箱（autounboxing）功能, 实现了包装类和基本数据类型之间的自动转换
  包装类可以实现基本类型和字符串之间的转换，字符串转基本类型：parseXXX(String s)；基本类型转字符串：String.valueOf(基本类型)
### 基本类型和包装类的区别？
- 基本类型只有值，而包装类型则具有与它们的值不同的同一性（即值相同但不是同一个对象）
- 包装类型比基本类型多了一个非功能值：null
- 基本类型通常比包装类型更节省时间和空间，速度更快
- 但有些情况包装类型的使用会更合理：
    * 泛型不支持基本类型，作为集合中的元素、键和值直接使用包装类（否则会发生基本类型的自动装箱消耗性能）。如：只能写 ArrayList<Integer&gt;，不能写 List<int&gt;
    * 在进行反射方法的调用时
      补充：两者之间的转换存在自动装/拆箱，可以提一下。

### java.sql.Date和java.util.Date的区别
- `java.sql.Date` 是 `java.util.Date` 的子类
- `java.util.Date` 是 JDK 中的日期类，精确到时、分、秒、毫秒
- `java.sql.Date` 与数据库 Date 相对应的一个类型，只有日期部分，时分秒都会设置为 0，如：2019-10-23 00:00:00
- 要从数据库时间字段取 时、分、秒、毫秒数据，可以使用`java.sql.Timestamp`


### 说说你对面向对象的理解

- 对 Java 语言来说，一切皆是对象。

- 对象有以下特点：

    * 对象具有属性和行为
    * 对象具有变化的状态
    * 对象具有唯一性
    * 对象都是某个类别的实例
    * 一切皆为对象，真实世界中的所有事物都可以视为对象

- 面向对象的特性：

    * 抽象性：抽象是将一类对象的共同特征总结出来构造类的过程，包括数据抽象和行为抽象两方面。
    * 继承性：指子类拥有父类的全部特征和行为，这是类之间的一种关系。Java 只支持单继承。
    * 封装性：封装是将代码及其处理的数据绑定在一起的一种编程机制，该机制保证了程序和数据都不受外部干扰且不被误用。封装的目的在于保护信息。
    * 多态性：多态性体现在父类的属性和方法被子类继承后或接口被实现类实现后，可以具有不同的属性或表现方式。


#### 内存泄漏和内存溢出的区别
- 内存溢出(out of memory)：指程序在申请内存时，没有足够的内存空间供其使用，出现 out of memory。
- 内存泄露(memory leak)：指程序在申请内存后，无法释放已申请的内存空间，内存泄露堆积会导致内存被占光。
- memory leak 最终会导致 out of memory。


### 不通过构造方法能创建对象吗？

### 匿名内部类可以继承类或实现接口吗？为什么？
- 匿名内部类本质上是对父类方法的重写 或 接口的方法的实现
- 从语法角度看，匿名内部类创建处是无法使用关键字继承类 或 实现接口


原因：

- 匿名内部类没有名字，所以它没有构造函数。因为没有构造函数，所以它必须通过父类的构造函数来实例化。即匿名内部类完全把创建对象的任务交给了父类去完成。
- 匿名内部类里创建新的方法没有太大意义，新方法无法被调用。
- 匿名内部类一般是用来覆盖父类的方法。
- 匿名内部类没有名字，所以无法进行向下的强制类型转换，只能持有匿名内部类对象引用的变量类型的直接或间接父类。

### 什么是多态？如何实现？有什么好处？
**多态：**
同一个接口，使用不同的实例而执行不同操作。同一个行为具有多个不同表现形式或形态的能力。



**实现多态有三个条件：**
- 继承
- 子类重写父类的方法
- 父类引用变量指向子类对象
  实现多态的技术称为：动态绑定(dynamic binding)，是指在执行期间判断所引用对象的实际类型，根据其实际的类型调用其相应的方法。

Java 中使用父类的引用变量调用子类重写的方法，即可实现多态。

**好处：**

- 消除类型之间的耦合关系
- 可替换性(substitutability)
- 可扩充性(extensibility)
- 接口性(interface-ability)
- 灵活性(flexibility)
- 简化性(simplicity)

### Math.random()的返回值是多少？

a pseudorandom double greater than or equal to 0.0 and less than 1.0.

### 同步代码块和同步方法有什么区别？
- 同步方法就是在方法前加关键字 synchronized；同步代码块则是在方法内部使用 synchronized
- 加锁对象相同的话，同步方法锁的范围大于等于同步方法块。一般加锁范围越大，性能越差
- 同步方法如果是 static 方法，等同于同步方法块加锁在该 Class 对象上

### 静态内部类和非静态内部类有什么区别？
- 静态内部类不需要有指向外部类的引用；非静态内部类需要持有对外部类的引用
- 静态内部类可以有静态方法、属性；非静态内部类则不能有静态方法、属性
- 静态内部类只能访问外部类的静态成员，不能访问外部类的非静态成员；非 静态内部类能够访问外部类的静态和非静态成员
- 静态内部类不依赖于外部类的实例，直接实例化内部类对象；非静态内部类通过外部类的对象实例生成内部类对象


### 下列运算符合法的是()

### 接口可否继承接口？抽象类是否可实现接口？抽象类是否可继承实体类？
都可以


### 可序列化对象为什么要定义serialversionUID值?
- SerialVersionUid 是为了序列化对象版本控制，告诉 JVM 各版本反序列化时是否兼容
- 如果在新版本中这个值修改了，新版本就不兼容旧版本，反序列化时会抛出InvalidClassException异常
- 仅增加了一个属性，希望向下兼容，老版本的数据都保留，就不用修改
- 删除了一个属性，或更改了类的继承关系，就不能不兼容旧数据，这时应该手动更新 SerialVersionUid

### 十进制100转换成八进制是多少？

```java
100 =  1*(8*8) + 4*(8) + 4*(1)

八进制：144

Java中八进制数必须以0开头，0144
```

### Class类的getDeclaredFields()与getFields()方法的区别？
- getDeclaredFields(): 获取所有本类自己声明的属性, 不能获取父类和实现的接口中的属性
- getFields(): 只能获取所有 public 声明的属性, 包括获取父类和实现的接口中的属性

### final修饰变量，是引用不能变？还是引用的对象不能变？
- final 修饰基本类型变量，值不能改变
- final 修饰引用类型变量，栈内存中的引用不能改变，所指向的堆内存中的对象的属性值可能可以改变


### Java属于编译型还是解释型语言？
计算机不能直接理解高级语言，只能理解和运行机器语言。必须要把高级语言翻译成机器语言，计算机才能运行高级语言所编写的程序。
翻译的方式有两种，一个是编译，一个是解释。

用编译型语言写的程序执行之前，需要一个专门的编译过程，通过编译系统把高级语言翻译成机器语言，把源高级程序编译成为机器语言文件，以后直接运行而不需要再编译了，所以一般编译型语言的程序执行效率高。

解释型语言在运行的时候才解释成机器语言，每个语句都是执行时才翻译。每执行一次就要翻译一次，效率较低。



Java 是一种兼具编译和解释特性的语言，.java 文件会被编译成与平台无关的 .class 文件，但是 .class 字节码文件无法被计算机直接，仍然需要 JVM 进行翻译成机器语言。
所以严格意义上来说，Java 是一种解释型语言。

### 如果有两个类A、B（注意不是接口），如何编写C类同时使用这两个类的功能？
让A、B成为父子类，C继承子类即可。

### 构造方法是否可以被重载？重写？
- 构造方法可以被重载

- 构造方法不可以被重写


### 基本类型byte表示的数值范围是多少？
-128 至 127


### 日期类型如何格式化？字符串如何转日期？
使用Date

```java
//日期格式为字符串
DateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
String s = sdf.format(new Date());
 

//字符串转日期
DateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
String s = "2019-10-31 22:53:10";
Date date = sdf.parse(s);
```



### 静态与非静态成员变量区别？
- 生命周期不同：非静态成员变量随着对象的创建而存在；静态成员变量随着类的加载而存在
- 调用方式不同：非静态成员变量用 对象名.变量名 调用；静态成员变量用 类名.变量名，JDK1.7 以后也能用对象名.变量名调用
- 别名不同：非静态成员变量也称为实例变量；静态变量称为类变量
- 数据存储位置不同：成员变量数据存储在堆内存的对象中，对象的特有数据；JDK1.6 静态变量数据存储在方法区(共享数据区)的静态区，对象的共享数据，JDK1.7 静态变量移到堆中存储


### 二进制数，小数点向右移一位，值会发生什么变化？
相当于乘以 2

如，1.1 = 1 * 2^0 + 1 * 2^-1 = 1.5

小数点向右移 1 位为 11， 1 * 2^1 + 1 * 2^0 = 3


### switch能否作用在byte、long、String上？
- 早期 JDK，switch(expr)，expr 可以是 byte、short、char、int
- JDK 1.5 开始，引入了枚举(enum)，expr 也可以是枚举
- JDK 1.7 开始，expr 还可以是字符串(String)
- 长整型(long)是不可以的

### 在Java 中，如何跳出当前的多重嵌套循环？
使用标签标注循环，使用 break 标签即可。

```java
 A:for (int i = 0; i <10; i++) {
            for (int j = 0; j <10; j++) {
                System.out.println(j);
                if (j == 5) {
                    break A;
                }
            }
            
        }
```


### 为什么不能根据返回类型来区分方法重载？
同时方法的重载只是要求两同三不同
- 在同一个类中
- 相同的方法名称
- 参数列表中的参数类型、个数、顺序不同
- 跟权限修饰符和返回值类型无关

如果可以根据返回值类型来区分方法重载，那在仅仅调用方法不获取返回值的使用场景，JVM 就不知道调用的是哪个返回值的方法了。
### Inner Class和Static Nested Class的区别？
**Inner Class：内部类**

- 内部类就是在一个类的内部定义的类
- 内部类中不能定义静态成员
- 内部类可以直接访问外部类中的成员变量
- 内部类可以定义在外部类的方法外面，也可以定义在外部类的方法体中
- 在方法体外面定义的内部类的访问类型可以是 public, protected , 默认的，private 等 4 种类型
- 方法内部定义的内部类前面不能有访问类型修饰符，可以使用 final 或 abstract 修饰符
- 创建内部类的实例对象时，一定要先创建外部类的实例对象，然后用这个外部类的实例对象去创建内部类的实例对象
- 内部类里还包含匿名内部类，即直接对类或接口的方法进行实现，不用单独去定义内部类

```java
//内部类的创建语法
Outer outer = new Outer();
Outer.Inner inner = outer.new Innner();
```

**Static Nested Class：静态嵌套类**

- 不依赖于外部类的实例对象
- 不能直接访问外部类的非 static 成员变量
- 可以直接引用外部类的static的成员变量，不需要加上外部类的名字
- 在静态方法中定义的内部类也是Static Nested Class

```java
//静态内部类创建语法
Outter.Inner inner = new Outter.Inner();
```

### abstract方法是否可是static的？native的？synchronized的?
都不能

- 抽象方法需要子类重写，而静态的方法是无法被重写的
- 本地方法是由本地动态库实现的方法，而抽象方法是没有实现的
- 抽象方法没有方法体；synchronized 方法，需要有具体的方法体，相互矛盾

### 静态方法能直接调用非静态方法吗？
不能

- 静态方法只能访问静态成员
- 调用静态方法时可能对象并没有被初始化，此时非静态变量还未初始化
- 非静态方法的调用和非静态成员变量的访问要先创建对象

### 内部类可以引用它的外部类的成员吗？有什么限制？
- 内部类对象可以访问创建它的外部类对象的成员，包括私有成员
- 访问外部类的局部变量，此时局部变量必须使用 final 修饰

### 说说字符串与基本数据之间的转换
字符串转基本数据

- 基本数据类型的包装类中的 parseXXX(String)可以字符串转基本类型
- valueOf(String) 可以字符串转基本类型的包装类
  基本数据转字符串
- 基本数据类型与空字符串 "" 用 + 连接即可获得基本类型的字符串
- 调用 String 类中的 valueOf(…) 方法返回相应字符串


### 反射主要实现类有哪些？
在JDK中，主要由以下类来实现 Java 反射机制，除了 Class 类，一般位于 java.lang.reflect 包中

- java.lang.Class ：一个类
- java.lang.reflect.Field ：类的成员变量(属性)
- java.lang.reflect.Method ：类的成员方法
- java.lang.reflect.Constructor ：类的构造方法
- java.lang.reflect.Array ：提供了静态方法动态创建数组，访问数组的元素

### Class类的作用是什么？如何获取Class对象？
- Class 类是 Java 反射机制的起源和入口，用于获取与类相关的各种信息，提供了获取类信息的相关方法。
- Class 类存放类的结构信息，能够通过 Class 对象的方法取出相应信息：类的名字、属性、方法、构造方法、父类、接口和注解等信息

```java
对象名.getClass()    
对象名.getSuperClass()
Class.forName("oracle.jdbc.driver.OracleDriver");
类名.class
Class c2 = Student.class;
Class c2 = int.class
包装类.TYPE
Class c2 = Boolean.TYPE;
Class.getPrimitiveClass()
(Class<Boolean>)Class.getPrimitiveClass("boolean");
```

### 面向对象设计原则有哪些？
- 单一职责原则 SRP
- 开闭原则 OCP
- 里氏替代原则 LSP
- 依赖注入原则 DIP
- 接口分离原则 ISP
- 迪米特原则 LOD
- 组合/聚合复用原则 CARP

其他原则可以看作是开闭原则的实现手段或方法，开闭原则是理想状态

### 反射的使用场景、作用及优缺点?
**使用场景**

- 在编译时无法知道该对象或类可能属于哪些类，程序在运行时获取对象和类的信息


**作用**

- 通过反射可以使程序代码访问装载到 JVM 中的类的内部信息，获取已装载类的属性信息、方法信息


**优点**

提高了 Java 程序的灵活性和扩展性，降低耦合性，提高自适应能力。
允许程序创建和控制任何类的对象，无需提前硬编码目标类
应用很广，测试工具、框架都用到了反射


**缺点**

- 性能问题：反射是一种解释操作，远慢于直接代码。因此反射机制主要用在对灵活性和扩展性要求很高的系统框架上,普通程序不建议使用
- 模糊程序内部逻辑：反射绕过了源代码，无法再源代码中看到程序的逻辑，会带来维护问题
- 增大了复杂性：反射代码比同等功能的直接代码更复杂


### String、StringBuilder、StringBuffer的区别?

**相同点：**

- 都可以储存和操作字符串
- 都使用 final 修饰，不能被继承
- 提供的 API 相似


**区别：**

- String 是只读字符串，String 对象内容是不能被改变的
- StringBuffer 和 StringBuilder 的字符串对象可以对字符串内容进行修改，在修改后的内存地址不会发生改变
- StringBuilder 线程不安全；StringBuffer 线程安全


方法体内没有对字符串的并发操作，且存在大量字符串拼接操作，建议使用 StringBuilder，效率较高。


### 为什么String类被设计用final修饰？
String 类是最常用的类之一，为了效率，禁止被继承和重写
为了安全。String 类中有很多调用底层的本地方法，调用了操作系统的 API，如果方法可以重写，可能被植入恶意代码，破坏程序。Java 的安全性也体现在这里。

### String s = new String("xyz");创建几个String对象？
两个或一个

- 第一次调用 new String("xyz"); 时，会在堆内存中创建一个字符串对象，同时在字符串常量池中创建一个对象 "xyz"
- 第二次调用 new String("xyz"); 时，只会在堆内存中创建一个字符串对象，指向之前在字符串常量池中创建的 "xyz"

### String s="a"+"b"+"c"+"d";创建了几个对象？
1个

Java 编译器对字符串常量直接相加的表达式进行优化，不等到运行期去进行加法运算，在编译时就去掉了加号，直接将其编译成一个这些常量相连的结果。

所以 "a"+"b"+"c"+"d" 相当于直接定义一个 "abcd" 的字符串。

#### 对比一下Java和JavaScriprt
JavaScript 与 Java 是两个公司开发的不同的两个产品。

- Java 是 Sun 公司推出的面向对象的编程语言，现在多用于于互联网服务端开发，前身是 Oak
- JavaScript 是 Netscape 公司推出的，为了扩展 Netscape 浏览器功能而开发的一种可以嵌入Web 页面中运行的基于对象和事件驱动的解释性语言，前身是 LiveScript
  区别：

- 面向对象和基于对象：Java是一种面向对象的语言；JavaScript 是一种基于对象（Object-Based）和事件驱动（Event-Driven）的编程语言，提供了丰富的内部对象供开发者使用
- 编译和解释：Java 的源代码在执行之前，必须经过编译；JavaScript 是一种解释型编程语言，其源代码不需经过编译，由浏览器直接解释执行
- 静态与动态语言：Java 是静态语言(编译时变量的数据类型即可确定的语言)；JavaScript 是动态语言(运行时确定数据类型的语言)
- 强类型变量和类型弱变量：Java 采用强类型变量检查，所有变量在编译之前必须声明类型；JavaScript 中变量声明，采用弱类型，即变量在使用前不需作声明类型，解释器在运行时检查其数据类型

### 什么是assert？
**assert：断言**
- 一种常用的调试方式，很多开发语言中都支持这种机制
- 通常在开发和测试时开启
- 可以用来保证程序最基本、关键的正确性
- 为了提高性能，发布版的程序通常关闭断言
- 断言是一个包含布尔表达式的语句，在执行这个语句时假定该表达式为 true；如果表达式计算为 false ，会报告一个 AssertionError
- 断言在默认情况下是禁用的，要在编译时启用断言，需使用source 1.4 标记，如 javac -source 1.4 TestAssert.java
- 要在运行时启用断言，需加参数 -ea 或 -enableassertions
- 要在运行时选择禁用断言，需加参数 -da 或 -disableassertions
- 要在系统类中启用或禁用断言，需加参数 -esa 或 -dsa


Java 中断言有两种语法形式：

```java
assert 表达式1;
assert 表达式1 : 错误表达式 ;
```

表达式1 是一个布尔值

错误表达式可以得出一个值，用于生成显示调试信息的字符串消息

### 类的实例化方法调用顺序
类加载器实例化时进行的操作步骤：

加载 -> 连接 -> 初始化

代码书写顺序加载父类静态变量和父类静态代码块
代码书写顺序加载子类静态变量和子类静态代码块
父类非静态变量（父类实例成员变量）
父类非静态代码块
父类构造函数
子类非静态变量（子类实例成员变量）
子类非静态代码块
子类构造函数

### JDK8中Stream接口的常用方法
Stream 接口中的方法分为中间操作和终端操作，具体如下。

中间操作：

filter：过滤元素
map：映射，将元素转换成其他形式或提取信息
flatMap：扁平化流映射
limit：截断流，使其元素不超过给定数量
skip：跳过指定数量的元素
sorted：排序
distinct：去重

终端操作：

anyMatch：检查流中是否有一个元素能匹配给定的谓词
allMatch：检查谓词是否匹配所有元素
noneMatch：检查是否没有任何元素与给定的谓词匹配
findAny：返回当前流中的任意元素（用于并行的场景）
findFirst：查找第一个元素
collect：把流转换成其他形式，如集合 List、Map、Integer
forEach：消费流中的每个元素并对其应用 Lambda，返回 void
reduce：归约，如：求和、最大值、最小值
count：返回流中元素的个数

### 说说反射在你实际开发中的使用
反射使用的不当，对性能影响比较大，一般项目中直接使用较少。

反射主要用于底层的框架中，Spring 中就大量使用了反射，比如：

- 用 IoC 来注入和组装 bean
- 动态代理、面向切面、bean 对象中的方法替换与增强，也使用了反射
- 定义的注解，也是通过反射查找


### 什么是泛型？为什么要使用泛型？
**泛型：**

- "参数化类型"，将类型由具体的类型参数化，把类型也定义成参数形式（称之为类型形参），然后在使用/调用时传入具体的类型（类型实参）。
- 是 JDK 5 中引入的一个新特性，提供了编译时类型安全监测机制，该机制允许程序员在编译时监测非法的类型。
- 泛型的本质是把参数的类型参数化，也就是所操作的数据类型被指定为一个参数，这种参数类型可以用在类、接口和方法中。

**为什么要用泛型？**

- 使用泛型编写的程序代码，要比使用 Object 变量再进行强制类型转换的代码，具有更好的安全性和可读性。
- 多种数据类型执行相同的代码使用泛型可以复用代码。

比如集合类使用泛型，取出和操作元素时无需进行类型转换，避免出现 java.lang.ClassCastException 异常

### 有没有使用JDK1.8 中的日期与时间API?
为什么 JDK 1.8 之前的时间与日期 API 不好用？

1. java.util.Date 是从 JDK 1.0 开始提供，易用性差
   默认是中欧时区(Central Europe Time)
   起始年份是 1900 年
   起始月份从 0 开始
   对象创建之后可修改

2. JDK 1.1 废弃了 Date 中很多方法，新增了并建议使用 java.util.Calendar 类
   相比 Date 去掉了年份从 1900 年开始月份依然从 0 开始选用 Date 或 Calendar，让人更困扰

3. DateFormat 格式化时间，线程不安全


为了解决 JDK 中时间与日期较难使用的问题，JDK 1.8 开始，吸收了 Joda-Time 很多功能，新增 java.time 包，加了新特性：
- 区分适合人阅读的和适合机器计算的时间与日期类
- 日期、时间及对比相关的对象创建完均不可修改
- 可并发解析与格式化日期与时间
- 支持设置不同的时区与历法

常用 api

1、 获取当前日期

```java
LocalDate.now()
```



2、创建日期

```java
LocalDate date = LocalDate.of(2020, 9, 21)
```

3、获取年份

```java
date.getYear()

//通过 TemporalField 接口的实现枚举类 ChronoField.YEAR 获取年份
date.get(ChronoField.YEAR)
```



4、获取月份

```java
date.getMonth().getValue()

//通过 TemporalField 接口的实现枚举类 ChronoField.MONTH_OF_YEAR 获取月份
date.get(ChronoField.MONTH_OF_YEAR)
```



5、获取日

```java
date.getDayOfMonth()

//通过 TemporalField 接口的实现枚举类 ChronoField.DAY_OF_MONTH 获取日
date.get(ChronoField.DAY_OF_MONTH)
```



6、获取周几

```java
date.getDayOfWeek()
```

7、获取当前月多少天

```java
date.lengthOfMonth()
```



8、获取当前年是否为闰年

```java
date.isLeapYear()
```



9、当前时间

```java
LocalTime nowTime = LocalTime.now()
```



10、创建时间

```java
LocalTime.of(23, 59, 59)
```



11、获取时

```java
nowTime.getHour()
```



12、获取分

```java
nowTime.getMinute()
```



13、获取秒

```java
nowTime.getSecond()
```



14、获取毫秒

```java
nowTime.getLong(ChronoField.MILLI_OF_SECOND)
```



15、获取纳秒

```java
nowTime.getNano()
```



16、创建日期时间对象

```java
LocalDateTime.of(2020, 9, 21, 1, 2, 3);
LocalDateTime.of(date, nowTime);
```



17、获取当前日期时间对象

```java
LocalDateTime.now()
```



18、通过 LocalDate 创建日期时间对象

```java
date.atTime(1, 2, 3)
```



19、通过 LocalTime 创建日期时间对象

```java
nowTime.atDate(date)
```



20、通过 LocalDateTime 获取 LocalDate 对象

```java
LocalDateTime.now().toLocalDate()
```



21、通过 LocalDateTime 获取 LocalTime 对象

```java
LocalDateTime.now().toLocalTime()
```



22、解析日期字符串

```java
LocalDate.parse("2020-09-21")
```



23、解析时间字符串

```java
LocalTime.parse("01:02:03")
```



24、解析日期时间字符串

```java
LocalDateTime.parse("2020-09-21T01:02:03", DateTimeFormatter.ISO_LOCAL_DATE_TIME)
```



25、方便时间建模、机器处理的时间处理类 Instant，起始时间 1970-01-01 00:00:00

```java
//起始时间 + 3 秒
Instant.ofEpochSecond(3)
//起始时间 + 3 秒 + 100 万纳秒
Instant.ofEpochSecond(3, 1_000_000_000)
//起始时间 + 3 秒 - 100 万纳秒
Instant.ofEpochSecond(3, -1_000_000_000))
//距离 1970-01-01 00:00:00 毫秒数
Instant.now().toEpochMilli()
```



26、Duration：LocalTime、LocalDateTime、Intant 的时间差处理

```java
Duration.between(LocalTime.parse("01:02:03"), LocalTime.parse("02:03:04"))
Duration.between(LocalDateTime.parse("2020-09-21T01:02:03"), LocalDateTime.parse("2020-09-22T02:03:04"))
Duration.between(Instant.ofEpochMilli(1600623455080L), Instant.now())
```



27、日期时间，前、后、相等比较

```java
//2020-09-21 在 2020-09-18 前？
LocalDate.parse("2020-09-21").isBefore(LocalDate.parse("2020-09-18"))
//01:02:03 在 02:03:04 后？
LocalTime.parse("01:02:03").isAfter(LocalTime.parse("02:03:04"))
```



28、修改日期、时间对象，返回副本

```java
//修改日期返回副本
LocalDate.now().withYear(2019).withMonth(9).withDayOfMonth(9)
LocalDate date4Cal = LocalDate.now();
//加一周
date4Cal.plusWeeks(1)
//减两个月
date4Cal.minusMonths(2)
//减三年
date4Cal.minusYears(3)
```



29、格式化

```java
//格式化当前日期
LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)
//指定格式，格式化当前日期
LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
指定格式，格式化当前日期时间
//格式化当前日期时间
LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd  HH:mm:ss"))
```



30、解析

```java
//日期解析
LocalDate.parse("2020-09-20")
//指定格式，日期解析
LocalDate.parse("2020/09/20", DateTimeFormatter.ofPattern("yyyy/MM/dd"))
//指定格式，日期时间解析
LocalDateTime.parse("2020/09/20 01:01:03", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"))
```



31、时区

```java
//上海时区
ZoneId shanghaiZone = ZoneId.of("Asia/Shanghai");
//设置日期为上海时区
LocalDate.now().atStartOfDay(shanghaiZone)
//设置日期时间为上海时区
LocalDateTime.now().atZone(shanghaiZone)
//设置 Instant 为上海时区
Instant.now().atZone(shanghaiZone)
```



32、子午线时间差

```java
//时间差减 1 小时
ZoneOffset offset = ZoneOffset.of("-01:00");
//设置时间差
OffsetDateTime.of(LocalDateTime.now(), offset)
```

