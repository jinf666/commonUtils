package com.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 * 
 * @author jinf
 *
 *         静态内部类<br>
 *         这种方式跟饿汉式方式采用的机制类似，但又有不同。两者都是采用了类装载的机制来保证初始化实例时只有一个线程<br>
 *         而静态内部类方式在Singleton类被装载时并不会立即实例化，而是在需要实例化时，调用getInstance方法，<br>
 *         才会装载SingletonInstance类，从而完成Singleton的实例化。<br>
 *         优点：<br>
 *         1.线程安全<br>
 *         2.延迟加载<br>
 *         3.效率较高<br>
 */
public class MyFileUtils {

    private MyFileUtils() {
    }

    private static class FileUtilsInstance {
        private static final MyFileUtils INSTANCE = new MyFileUtils();
    }

    public static MyFileUtils getInstance() {
        return FileUtilsInstance.INSTANCE;
    }

    public int searchPosition(File file, String search) throws IOException {
        String content = FileUtils.readFileToString(file, "UTF-8");
        return content.indexOf(search);
    }

    /**
     * 拆分文件
     * 
     * @param file      需要拆分的文件
     * @param charCount 拆分后每个文件含有字符数
     * @throws IOException
     */
    public void splitFile(File file, int charCount) throws IOException {
        String content = FileUtils.readFileToString(file, "UTF-8");
        int fileCharCount = content.length();
        int offset = 0;
        int max;
        String fileName = file.getName();
        int extentsionIndex = fileName.lastIndexOf(".");
        String extension = fileName.substring(extentsionIndex);
        fileName = fileName.substring(0, extentsionIndex);
        FileWriter writer = null;
        for (int i = 0; i < (fileCharCount / charCount); i++) {
            offset = i * charCount;
            max = (max = offset + charCount) > fileCharCount ? fileCharCount : max;
            File tmpFile = new File(file.getParent() + File.separator + "新建文件夹" + File.separator + fileName + "_" + i
                    + "." + extension);
            if (!tmpFile.getParentFile().exists()) {
                tmpFile.getParentFile().mkdirs();
            }
            writer = new FileWriter(tmpFile);
            writer.write(content.substring(offset, max));
        }
        if (writer != null) {
            writer.close();
        }
    }

    /**
     * 在文件中搜索特定的字符串，需要完全匹配，包含情况不包括
     * 
     * @param file   被搜索的文件
     * @param search 要搜索的字符串
     * @return 对应字符串所在的json段
     * @throws IOException
     */
    public String search(File file, String search) throws IOException {
        FileReader reader = new FileReader(file);
        // 当前读取到的字符
        int ch = 0;
        // 读取到json字符串的位置
        int jsonIndex = 0;
        // 匹配到字符串的第几个字符
        int index = 0;
        // 缓存合同编号所在json字符串
        StringBuffer sBuf = new StringBuffer();
        // 缓存合同编号所在json字符串
        StringBuffer jsonBuf = new StringBuffer();
        /*
         * 缓存从json开始到合同编号的完全匹配
         */
        while ((ch = reader.read()) != -1) {
            char c = (char) ch;

            if (c == '{') {
                jsonIndex = 0;
                jsonBuf.setLength(0);
                jsonIndex++;
            }
            if (jsonIndex > 0) {
                jsonBuf.append(c);
            }

            /* 合同编号开始匹配开始 */
            if (c == search.charAt(index)) {
                sBuf.append(c);
                index++;
                /*
                 * 判断是否已经合同编号是否完全匹配 如果不是，说明是包含关系，需要继续查找
                 */
                if (index == search.length()) {
                    c = (char) (reader.read());
                    if (jsonIndex > 0) {
                        jsonBuf.append(c);
                    }
                    if (c == '"') {
                        break;
                    } else {
                        index = 0;
                        sBuf.setLength(0);
                    }
                }
            } else {
                index = 0;
                sBuf.setLength(0);
            }
        }
        // 判断是否全部都没有找到匹配的字段
        if (index != 0) {
            /*
             * 缓存从合同编号完全匹配到json结束
             */
            if (search.equals(sBuf.toString())) {
                while ((ch = reader.read()) != -1) {
                    char c = (char) ch;
                    jsonBuf.append(c);
                    if (c == '}') {
                        break;
                    }
                }
            }
        } else {
            jsonBuf.setLength(0);
        }
        if (reader != null) {
            reader.close();
        }
        return jsonBuf.toString();
    }
}
