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
import java.nio.channels.FileChannel;

import javax.imageio.ImageIO;

import org.springframework.util.FileCopyUtils;

/**
 * @author ZhongYongQiang
 */
public class FileUtil {

    public static boolean createPath(String path) {
        File file = new File(path);

        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();

            return true;
        }

        return false;
    }

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
    
    public static void copyInputStreamToFile(InputStream source, File destination) throws IOException {
        FileOutputStream output = openOutputStream(destination, false);
        FileCopyUtils.copy(source, output);
    }
    
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
    
}