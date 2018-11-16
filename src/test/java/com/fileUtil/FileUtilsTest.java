package com.fileUtil;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.utils.MyFileUtils;


public class FileUtilsTest {

    @Test
    public void test() {
        MyFileUtils fileUtils = MyFileUtils.getInstance();
        File file = new File("C:\\Users\\Administrator\\Desktop\\json文件\\2.json");
        try {
            System.out.println(fileUtils.searchPosition(file, "CRCCRE-XN-ZQSY-ZTJBJXMBN35M-KF.XS.10-2018-004"));
            fileUtils.splitFile(file, 10000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
