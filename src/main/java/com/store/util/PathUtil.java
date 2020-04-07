package com.store.util;

public class PathUtil {
    private static String separator = System.getProperty("file.separator");

    public static String getImgBasePath() {
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {
            basePath="G:/OverallWorkspace/online-store-proj";
        } else {
            basePath="/home/online-store-proj";
        }
        basePath = basePath.replace("/", separator);
        return basePath;
    }

    public static String getShopImgPath(long shopId) {
        String imagePath = "/upload/images/item/shop" + shopId + "/";
        return imagePath.replace("/", separator);
    }
}
