package com.store.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ImageUtil {

    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmms");
    private static final Random r = new Random();
    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    public static File transferCommonMultiPartFileToFile(CommonsMultipartFile cFile) {
        File newFile = new File(cFile.getOriginalFilename());
        try {
            cFile.transferTo(newFile);
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return newFile;
    }

    public static String generateThumbNail(InputStream thumbnailInputStream, String fileName, String targetPath) {
        String realFileName = getRandomFileName();
        String extension = getFileExtension(fileName);
        makeDirPath(targetPath);
        String relativePath = targetPath + realFileName + extension;
        logger.debug("Relative path is: " + relativePath);
        File dest = new File(PathUtil.getImgBasePath() + relativePath);
        logger.debug("Complete path is: " + PathUtil.getImgBasePath() + relativePath);
        try {
            Thumbnails.of(thumbnailInputStream).size(200, 200).watermark(Positions.BOTTOM_RIGHT,
                    ImageIO.read(new File(basePath+"/watermark.jpg")), 0.25f).
                    outputQuality(0.8f).toFile(dest);
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return relativePath;
    }

    private static String getFileExtension(String fileName) {
        int index = fileName.lastIndexOf(".");
        return fileName.substring(index);
    }

    /**
     * Create folders according to targetPath
     * @param targetPath
     */
    private static void makeDirPath(String targetPath) {
        String realFileParentPath = PathUtil.getImgBasePath() + targetPath;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
    }

    /**
     *
     * @return a String comprised of current + 5-digit random number
     */
    public static String getRandomFileName() {
        String curTimeStr = dateFormat.format(new Date());
        int rand = r.nextInt(89999) + 10000;
        return curTimeStr + rand;
    }

    /**
     * Delete old image file and its directory before updating
     * @param storePath
     */
    public static void deleteFileOrPath(String storePath) {
        File file = new File(PathUtil.getImgBasePath() + storePath);
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File f : files) {
                    f.delete();
                }
            }
            file.delete();
        }
    }

    public static void main(String[] args) throws IOException {
        Thumbnails.of(new File("F:\\QQ\\873253190\\FileRecv\\test1.jpg")).
                size(200, 200).watermark(Positions.BOTTOM_RIGHT,
                ImageIO.read(new File(basePath+"/watermark.jpg")), 0.25f).
                outputQuality(0.8f).toFile("F:\\QQ\\873253190\\FileRecv\\test1-new.jpg");
    }
}
