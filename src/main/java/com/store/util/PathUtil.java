package com.store.util;

public class PathUtil {
    private static String separator = System.getProperty("file.separator");

    public static String getImgBasePath() {
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {
            basePath="G:/OverallWorkspace/online-store-proj/image/";
        } else {
            basePath="/home/online-store-proj/image/";
        }
        basePath = basePath.replace("/", separator);
        return basePath;
    }

    public static String getShopImgPath(long shopId) {
        String imagePath = "upload/item/shop" + shopId + "/";
        return imagePath.replace("/", separator);
    }
}
