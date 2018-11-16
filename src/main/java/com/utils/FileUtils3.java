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
 *         1.多线程环境下不可以用<br>
 *         2.线程在if判断处卡主，容易造成多线程
 */
public class FileUtils3 {

    private static FileUtils3 instance;

    private FileUtils3() {
    }

    public static FileUtils3 getInstance() {
        if (instance == null) {
            instance = new FileUtils3();
        }
        return instance;
    }
}
