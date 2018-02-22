package com.nowui.cloud.util;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.FileChannel;

import javax.imageio.ImageIO;

import com.nowui.cloud.constant.Constant;
import org.springframework.util.FileCopyUtils;

/**
 * 文件业务实现
 *
 * @author ZhongYongQiang
 *
 * 2018-01-01
 */
public class FileUtil {

    public static String webRootPath;

    public static String uploadPath;

    static {
        try {
            webRootPath = FileUtil.class.getResource("/").toURI().getPath();
            uploadPath = webRootPath + "/" + Constant.UPLOAD;
            createPath(uploadPath);
        } catch (Exception var1) {
            throw new RuntimeException(var1);
        }
    }

    /**
     * 创建文件路径
     * 
     * @param path 路径
     * @return true 创建成功  false 创建失败 
     */
    public static boolean createPath(String path) {
        File file = new File(path);

        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();

            return true;
        }

        return false;
    }

    /**
     * 调整图片大小
     * 
     * @param image 图片文件
     * @param suffix 图片后缀
     * @param path 调整后图片地址
     * @param width 图片宽度
     */
    public static void resizeImage(File image, String suffix, String path, int width) {
        try {
            BufferedImage bufferedImage = ImageIO.read(image);

            int originalWidth = bufferedImage.getWidth();
            int originalHeight = bufferedImage.getHeight();

            int newWidth = 0;
            int newHeight = 0;


            if (originalWidth > width && width > 0) {
//                if (originalWidth > originalHeight) {
                newWidth = width;

                double scale = (double) originalWidth / (double) newWidth;
                newHeight = (int) (originalHeight / scale);
//                } else {
//                    newHeight = width;
//
//                    double scale = (double) originalHeight / (double) newHeight;
//                    newWidth = (int) (originalWidth / scale);
//                }
            } else {
                newWidth = originalWidth;
                newHeight = originalHeight;
            }

            String pngSuffix = "png";
            String gifSuffix = "gif";
            if ((suffix.trim().toLowerCase().endsWith(pngSuffix) || suffix.trim().toLowerCase().endsWith(gifSuffix))) {
                BufferedImage to = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
                Graphics2D g2d = to.createGraphics();
                to = g2d.getDeviceConfiguration().createCompatibleImage(newWidth, newHeight, Transparency.TRANSLUCENT);
                g2d.dispose();

                g2d = to.createGraphics();
                Image from = bufferedImage.getScaledInstance(newWidth, newHeight, Image.SCALE_AREA_AVERAGING);
                g2d.drawImage(from, 0, 0, null);
                g2d.dispose();

                ImageIO.write(to, suffix, new File(path));
            } else {
                BufferedImage newImage = new BufferedImage(newWidth, newHeight, bufferedImage.getType());
                Graphics g = newImage.getGraphics();
                g.drawImage(bufferedImage, 0, 0, newWidth, newHeight, null);
                g.dispose();
                ImageIO.write(newImage, suffix, new File(path));
            }
        } catch (IOException e) {
            throw new RuntimeException("IOException: " + e.toString());
        }

    }

    /**
     * 复制文件
     * 
     * @param source 源文件
     * @param dest 目标文件
     */
    public static void copy(File source, File dest) {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } catch (IOException e) {
            throw new RuntimeException("IOException: " + e.toString());
        } finally {
            try {
                inputChannel.close();
                outputChannel.close();
            } catch (IOException e) {
                throw new RuntimeException("IOException: " + e.toString());
            }
        }
    }

    /**
     * 写入生成文件
     * 
     * @param content 文件内容
     * @param fileName 文件名称
     */
    public static void writeFile(String content, String fileName) {
        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(new File(fileName)), "UTF-8"));
            writer.write(content.toCharArray());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 读取缓冲区输入流字节
     * 
     * @param bufin 带缓冲区输入流
     * @return byte[] 字节数组
     * @throws IOException
     */
    public static byte[] readBytes(BufferedInputStream bufin) throws IOException {
        int buffSize = 1024;
        ByteArrayOutputStream out = new ByteArrayOutputStream(buffSize);

        byte[] temp = new byte[buffSize];
        int size = 0;
        while ((size = bufin.read(temp)) != -1) {
            out.write(temp, 0, size);
        }
        bufin.close();

        byte[] content = out.toByteArray();
        return content;
    }
    
    /**
     * 读取输入流到文件
     * 
     * @param source 源输入流
     * @param destination 目标文件
     * @throws IOException
     */
    public static void copyInputStreamToFile(InputStream source, File destination) throws IOException {
        FileOutputStream output = openOutputStream(destination, false);
        FileCopyUtils.copy(source, output);
    }
    
    /**
     * 打开文件输入流
     * 
     * @param file 文件
     * @param append 是否字节写入文件结束处而不是开始处
     * @return
     * @throws IOException
     */
    public static FileOutputStream openOutputStream(File file, boolean append) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }
            if (file.canWrite() == false) {
                throw new IOException("File '" + file + "' cannot be written to");
            }
        } else {
            File parent = file.getParentFile();
            if (parent != null) {
                if (!parent.mkdirs() && !parent.isDirectory()) {
                    throw new IOException("Directory '" + parent + "' could not be created");
                }
            }
        }
        return new FileOutputStream(file, append);
    }
    
    /**
     * 根据文件url获取文件输入流
     * 
     * @param url 下载文件
     * @throws Exception
     */
    private static InputStream getInputStream(String url) throws Exception {
        
        URL urlGet = new URL(url);
        
        HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
        // 必须是get方式请求
        http.setRequestMethod("GET"); 
        http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        http.setDoOutput(true);
        http.setDoInput(true);
        
        // 连接超时30秒
        System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
        // 读取超时30秒
        System.setProperty("sun.net.client.defaultReadTimeout", "30000"); 
        
        http.connect();
        
        // 获取文件转化为byte流
        return http.getInputStream();
    }

    /**
     * 下载文件到本地
     * 
     * @param sourceUrl 文件url
     * @param destinationUrl 文件名称
     * @throws Exception
     */
    public static void saveFileToDisk(String sourceUrl, String destinationUrl) throws Exception {
        InputStream inputStream = getInputStream(sourceUrl);
        byte[] data = new byte[1024];
        int len = 0;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(destinationUrl);
            while ((len = inputStream.read(data)) != -1) {
                fileOutputStream.write(data, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}