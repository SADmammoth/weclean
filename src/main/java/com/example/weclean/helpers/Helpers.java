package com.example.weclean.helpers;

import org.springframework.beans.factory.annotation.Value;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.nio.file.FileSystems;

public class Helpers {

    public static Object[] unionArrays(Object[]... arrays)
    {
        int maxSize = 0;
        int counter = 0;

        for(Object[] array : arrays) maxSize += array.length;
        Object[] accumulator = new Object[maxSize];

        for(Object[] array : arrays)
            for(Object i : array)
                if(!isDuplicated(accumulator, counter, i))
                    accumulator[counter++] = i;

        Object[] result = new Object[counter];
        for(int i = 0; i < counter; i++) result[i] = accumulator[i];

        return result;
    }

    public static boolean isDuplicated(Object[] array, int counter, Object value)
    {
        for(int i = 0; i < counter; i++) if(array[i].equals(value)) return true;
        return false;
    }

    public static String getAbsolutePath(String relativePath){
      return FileSystems.getDefault().getPath(relativePath).normalize().toAbsolutePath().toString();
    }

    public static String getPublicPath(String relativePath){
        String path = "http://localhost:8080/";
        return path + relativePath;
    }

    public static BufferedImage getImageThumb(BufferedImage image, String format){
        int width = image.getWidth() / 3;
        int height = image.getHeight() / 3;

        BufferedImage resized = new BufferedImage(width, height, format.equals("jpg") || format.equals("jpeg") ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(image.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
        g2d.dispose();
        return resized;
    }
}
