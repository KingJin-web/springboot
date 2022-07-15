package com.king.id_maker.service;

import com.king.id_maker.util.ZkHelper;
import lombok.SneakyThrows;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author: King
 * @project: springboot
 * @date: 2022年07月15日 01:50
 * @content 创建分布式ID
 */

public class IdMaker {

    private ZooKeeper client = null;
    private String server;//记录服务器的地址
    //记录父节点的路径
    private final String root;
    //节点的名称
    private final String nodeName;
    //标识当前服务是否正在运行
    private volatile boolean running = false;
    //创建线程池
    private ExecutorService cleanExector = null;

    //删除节点的级别
    public enum RemoveMethod {
        NONE, IMMEDIATELY, DELAY
    }

    public IdMaker(String root, String nodeName) {
        this.root = root;
        this.nodeName = nodeName;
    }

    //启动服务
    public void start() throws Exception {
        if (running) {
            throw new Exception("server has stated...");
        }
        running = true;
        init();
    }

    //停止服务
    public void stop() throws Exception {
        if (!running) {
            throw new Exception("server has stopped...");
        }
        running = false;
        freeResource();
    }

    //初始化服务资源
    private void init() {
        ZkHelper zh = new ZkHelper();
        try {
            client = zh.connect();
            //newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
            cleanExector = Executors.newFixedThreadPool(10);
            //如果服务器中的父节点不存在
            if (client.exists(root, null) == null) {
                String r = client.create(root, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (IOException | InterruptedException | KeeperException e) {
            e.printStackTrace();
        }

    }

    //释放服务资源
    private void freeResource() {
        //释放线程池
        //在调用shutdown()方法之后，ExecutorService不会立即关闭，但是它不再接收新的任务，
        //直到当前所有线程执行完成才会关闭，所有在shutdown()执行之前提交的任务都会被执行。
        cleanExector.shutdown();
        try {
            //awaitTermination当等待超过设定时间时，会监测ExecutorService是否已经关闭，若关闭则返回true，否则返回false。
            cleanExector.awaitTermination(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            cleanExector = null;
        }

        if (client != null) {
            try {
                client.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            client = null;
        }
    }

    //检测当前服务是否正在运行
    private void checkRunning() throws Exception {
        if (!running) {
            throw new Exception("请先调用start");
        }
    }

    //从顺序节点中提取我们要的ID值
    //  /NameService/IdGen/ID000000000001  ourPath:/NameService/IdGen/ID
    private String ExtractId(String str) {
        // 000000000001
        int index = str.lastIndexOf(nodeName);
        System.out.println("index:" + index);
        if (index >= 0) {
            //000000000001 + 2
            index += nodeName.length();
            return index <= str.length() ? str.substring(index) : "";
        }
        return str;
    }

    //生成ID
    public String generateId(RemoveMethod removeMethod) throws Exception {
        checkRunning();
        //构造顺序节点的完整路径  /NameService/IdGen /          ID
        //concat 连接函数
        final String fullNodePath = root.concat("/").concat(nodeName);
        //创建持久化顺序节点   PERSISTENT_SEQUENTIAL/ 临时有序
        ///NameService/IdGen/ID000000000001
        String ourPath = client.create(fullNodePath, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        //避免zookeeper的顺序节点暴增,直接删除掉刚创建的顺序节点
        if (removeMethod.equals(RemoveMethod.IMMEDIATELY)) {//立即删除
            client.delete(ourPath, client.exists(ourPath, null).getVersion());
        } else if (removeMethod.equals(RemoveMethod.DELAY)) {//延迟删除
            //execute 这个方法接收一个Runnable实例，并且异步的执行
            cleanExector.execute(new Runnable() {//用线程池执行删除,让generateId()方法尽快返回
                @SneakyThrows
                @Override
                public void run() {
                    client.delete(ourPath, client.exists(ourPath, null).getVersion());
                }
            });
        }
        //  /NameService/IdGen/ID000000000001  ourPath:/NameService/IdGen/ID
        return ExtractId(ourPath);
    }

}
