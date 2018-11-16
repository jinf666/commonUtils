package com.utils;

/**
 * 
 * @author jinf
 *
 *         Double-Check<br>
 *         <br>
 *         优点：<br>
 *         1.线程安全<br>
 *         2.延迟加载<br>
 *         3.效率较高<br>
 *         缺点：<br>
 *         1.线程不安全<br>
 */
public class FileUtils6 {

    private static volatile FileUtils6 instance;

    private FileUtils6() {
    }

    public static FileUtils6 getInstance() {
        if (instance == null) {
            synchronized (FileUtils6.class) {
                if (instance == null) {
                    instance = new FileUtils6();
                }
            }
        }
        return instance;
    }
}
