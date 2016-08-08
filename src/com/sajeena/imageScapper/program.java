/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sajeena.imageScapper;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Admin
 */
public class program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            String link = "http://www.howtogeek.com/howto/20578/desktop-fun-dragons-wallpaper-collection/";
            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            BufferedReader reader= new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder content=new StringBuilder();
            String line="";
            while((line=reader.readLine())!=null){
                content.append(line);
            }
            reader.close();
            String regex="<a href=\"(.*?)\" target=\"(.*?)\"><img style=\"(.*?)\" src=\"(.*?)\" border=\"0\" alt=\"(.*?)\" width=\"(.*?)\" height=\"(.*?)\"/></a></p>";
            Pattern pattern=Pattern.compile(regex);
            Matcher matcher=pattern.matcher(content.toString());
            
              while(matcher.find())
        {
           
            String imgLink= matcher.group(4);
            URL imgUrl=new URL(imgLink);
            URLConnection conn1=imgUrl.openConnection();
            InputStream is = conn1.getInputStream();
         
           String[] tokens=imgLink.split("/");
           String path="C:/Users/Admin/Desktop/output/";
           FileOutputStream os = new FileOutputStream(path+tokens[tokens.length-1]);
            byte[] data=new byte[1024];
                int i=0;
                while((i=is.read(data))!=-1)
                {
                    os.write(data, 0, i);
                }
                os.close();
                is.close();
        }
       

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
}
