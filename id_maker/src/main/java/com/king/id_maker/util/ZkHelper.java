package com.king.id_maker.util;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author: King
 * @project: springboot
 * @date: 2022年07月15日 01:49
 * @content 创建一个新的帮助类ZookeeperCnnection, 并添加一个方法connect, connect
 * 方法创建一个Zookeeper对象,连接到Zookeeper集合,然后返回对象.这里CountDownLatch
 * 用于停止(等待)主进程,直到客户端与Zookeeper集合连接.
 * Zookeeper集合通过监视器回调来回复连接状态,一旦客户端与Zookeeper集合连接,监视器回调就会被调用.
 * 并且监视器回调函数调用CountDownLatch的countDown方法来释放锁,在主进程中await.
 */
public class ZkHelper {
    //    private static String connectString = "node1:2181,node2:2181,node3:2181";
    private static String connectString = "king1:2181,king2:2182,king3:2183";
    private static int sessionTimeout = 2000;
    private static ZooKeeper zkClient = null;   //声明zookeeper实例以连接zookeeper中的集合
    //CountDownLatch用于停止(等待)主进程,直到客户端与Zookeeper集合连接.这是一个信号.
    //阻塞.
    final CountDownLatch connectedSignal = new CountDownLatch(1);

    //连接Zookeeper集合的方法
    public ZooKeeper connect() throws IOException, InterruptedException {
        System.out.println("zk客户端初始化中...");

        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                //收到事件通知后的回调函数(用户的业务逻辑)
                System.out.println("事件信息:" + event.getType() + "--" + event.getPath() + "--" + event.getState());   //一开始连接时,这个回调函数也会调用,但没有事件类
                if (event.getState() == Event.KeeperState.SyncConnected) {  //只有回调的状态是
                    //连接上时,才激活这个判断.
                    System.out.println("zk客户端建立与服务器的连接");
                    //Decrements the count of the latch,releasing all waiting
                    //threads if the count reaches zero.
                    connectedSignal.countDown();    //只有连接建立了,再释放这把锁,这样主进程才可以继续运行
                }
            }
        });
        connectedSignal.await();    //在主进程中await,阻塞     不为0,就阻塞.
        System.out.println("客户端主进程运行完");
        return zkClient;
    }

    public void close() throws InterruptedException {
        zkClient.close();
    }

    public String getConnectString() {
        return connectString;
    }

}
