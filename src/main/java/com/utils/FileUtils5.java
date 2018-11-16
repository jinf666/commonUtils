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
 *         缺点：<br>
 *         1.线程不安全<br>
 */
public class FileUtils5 {

    private static FileUtils5 instance;

    private FileUtils5() {
    }

    public static FileUtils5 getInstance() {
        if (instance == null) {
            synchronized (FileUtils5.class) {
                instance = new FileUtils5();
            }
        }
        return instance;
    }
}
