package com.store.util;

import com.store.dto.ImageHolder;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
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

    public static String generateThumbNail(ImageHolder thumbnail, String targetPath) {
        String realFileName = getRandomFileName();
        String extension = getFileExtension(thumbnail.getImageName());
        makeDirPath(targetPath);
        String relativePath = targetPath + realFileName + extension;
        logger.debug("Relative path is: " + relativePath);
        File dest = new File(PathUtil.getImgBasePath() + relativePath);
        logger.debug("Complete path is: " + PathUtil.getImgBasePath() + relativePath);
        try {
            Thumbnails.of(thumbnail.getImage()).size(200, 200).watermark(Positions.BOTTOM_RIGHT,
                    ImageIO.read(new File(basePath+"/watermark.jpg")), 0.25f).
                    outputQuality(0.8f).toFile(dest);
        } catch (IOException e) {
            logger.error(e.toString());
            throw new RuntimeException("Error in generateNormalImage(): "+e.toString());
        }
        return relativePath;
    }

    public static String generateNormalImage(ImageHolder thumbnail, String targetPath) {
        String realFileName = getRandomFileName();
        String extension = getFileExtension(thumbnail.getImageName());
        makeDirPath(targetPath);
        String relativePath = targetPath + realFileName + extension;
        logger.debug("Relative path is: " + relativePath);
        File dest = new File(PathUtil.getImgBasePath() + relativePath);
        logger.debug("Complete path is: " + PathUtil.getImgBasePath() + relativePath);
        try {
            Thumbnails.of(thumbnail.getImage()).size(337, 640).watermark(Positions.BOTTOM_RIGHT,
                    ImageIO.read(new File(basePath+"/watermark.jpg")), 0.25f).
                    outputQuality(0.9f).toFile(dest);
        } catch (IOException e) {
            logger.error(e.toString());
            throw new RuntimeException("Error in generateNormalImage(): "+e.toString());
        }
        return relativePath;
    }

    public static String generateHeadImg(MultipartFile thumbnail, String targetAddr) {
        String realFileName = getRandomFileName();
        String extension = getFileExtension(thumbnail);
        makeDirPath(targetAddr);
        String relativePath = targetAddr + realFileName + extension;
        File dest = new File(PathUtil.getImgBasePath() + relativePath);
        try {
            Thumbnails.of(thumbnail.getInputStream()).size(400, 300).outputQuality(0.9f).toFile(dest);
        } catch (IOException e) {
            throw new RuntimeException("error in generateHeadImg()：" + e.toString());
        }
        return relativePath;
    }

    public static String generateShopCategoryImg(MultipartFile thumbnail, String targetAddr) {
        String realFileName = getRandomFileName();
        String extension = getFileExtension(thumbnail);
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        try {
            Thumbnails.of(thumbnail.getInputStream()).size(50, 50).outputQuality(0.9f).toFile(dest);
        } catch (IOException e) {
            throw new RuntimeException("error in generateShopCategoryImg()：" + e.toString());
        }
        return relativeAddr;
    }

    private static String getFileExtension(String fileName) {
        int index = fileName.lastIndexOf(".");
        return fileName.substring(index);
    }

    private static String getFileExtension(MultipartFile thumbnail) {
        String originalFileName = thumbnail.getOriginalFilename();
        return originalFileName.substring(originalFileName.lastIndexOf("."));
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

    public static MultipartFile path2MultipartFile(String filePath) throws IOException {
        File file = new File(filePath);
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain",
                IOUtils.toByteArray(input));
        return multipartFile;
    }

    public static void main(String[] args) throws IOException {
//        Thumbnails.of(new File("F:\\QQ\\873253190\\FileRecv\\test1.jpg")).
//                size(400, 300).outputQuality(0.9f).toFile(dest);

        System.out.println(System.getProperty("file.separator"));

//        Thumbnails.of(new File("F:\\QQ\\873253190\\FileRecv\\test1.jpg")).
//                size(200, 200).watermark(Positions.BOTTOM_RIGHT,
//                ImageIO.read(new File(basePath+"/watermark.jpg")), 0.25f).
//                outputQuality(0.8f).toFile("F:\\QQ\\873253190\\FileRecv\\test1-new.jpg");
    }
}
