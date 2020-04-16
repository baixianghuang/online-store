package com.store.util;

public class PathUtil {
    private static String separator = System.getProperty("file.separator");

    public static String getImgBasePath() {
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {
            basePath="G:/OverallWorkspace/online-store-proj";
        } else {
            basePath="/home/bai/online_store";
        }
        basePath = basePath.replace("/", separator);
        return basePath;
    }

    public static String getShopImgPath(long shopId) {
        String imagePath = "/upload/images/item/shop" + shopId + "/";
        return imagePath.replace("/", separator);
    }

    public static String getHeadLineImagePath() {
        String imagePath = "/upload/images/item/headline/";
        return imagePath.replace("/", separator);
    }

    public static String getShopCategoryImagePath() {
        String imagePath = "/upload/images/item/shopcategory/";
        return imagePath.replace("/", separator);
    }
}
