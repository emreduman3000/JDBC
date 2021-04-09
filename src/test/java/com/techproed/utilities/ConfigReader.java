package com.techproed.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader
{
    //bu classın amacı configuration.proğperties file'daki dataları okumaktır.
    private static Properties properties;

    static {
        String path = "configuration.properties";

        try {
            FileInputStream file = new FileInputStream(path);//dosya actık
            properties = new Properties();//Properties classından object create ettik
            properties.load(file);//properties'i bu dosyaya yüklüyoruz
        } catch (Exception e) {
            System.out.println("The file was not found!");
            e.printStackTrace();
        }
    }

    //key - value ilişkisini cagırmak içir kullanırız
   public static String getProperty(String key)
    {
       return properties.getProperty(key);
    }


}
