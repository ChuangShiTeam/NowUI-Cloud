package com.nowui.cloud.util;

import java.io.File;

public class FileUtil {

    public static Boolean createPath(String path) {
        File file = new File(path);

        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();

            return true;
        }

        return false;
    }
}
