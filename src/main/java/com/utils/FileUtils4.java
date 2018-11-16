package com.utils;

/**
 * 
 * @author jinf
 *
 *         懒汉式单利模式<br>
 *         <br>
 *         优点：<br>
 *         1.写法简单<br>
 *         2.起到了懒汉加载模式<br>
 *         3.线程安全<br>
 *         缺点：<br>
 *         1.效率太低了，每个线程在想获得类的实例时候，执行getInstance()方法都要进行同步。而其实这个方法只执行一次实例化代码就够了， <br>
 *         后面的想获得该类实例，直接return就行了。<br>
 */
public class FileUtils4 {

    private static FileUtils4 instance;

    private FileUtils4() {
    }

    public static synchronized FileUtils4 getInstance() {
        if (instance == null) {
            instance = new FileUtils4();
        }
        return instance;
    }
}
