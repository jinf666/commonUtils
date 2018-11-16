package com.utils;

/**
 * 
 * @author jinf
 *
 *         饿汉式单利模式<br>
 *         <br>
 *         优点：<br>
 *         1.写法简单<br>
 *         2.类装载时就完成实例化<br>
 *         3.避免线程同步问题 <br>
 *         缺点：<br>
 *         1.装载静态代码块时即完成实例化，没有达到懒加载的效果<br>
 *         2.若至始至终没有用到这个实例，则造成内容浪费
 */
public class FileUtils2 {

    private static FileUtils2 instance;

    static {
        instance = new FileUtils2();
    }

    private FileUtils2() {
    }

    public static FileUtils2 getInstance() {
        return instance;
    }
}
