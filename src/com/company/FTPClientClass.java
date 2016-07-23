package com.company;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;


import com.sun.tools.doclets.internal.toolkit.util.SourceToHTMLConverter;
import org.apache.commons.net.ftp.*;
//import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPReply;



public class FTPClientClass {

    public static void main(String[] args) {
	    System.out.println("Before connection");
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect("demo.wftpserver.com", 21);
            boolean login = ftpClient.login("demo-user", "demo-user");
            String[] reply = ftpClient.getReplyStrings();
            System.out.println(reply);
            System.out.println("Is the connection a success ? :" + login);
            //ftpClient.disconnect();
            System.out.println("After connecting to server");

            //List all the files in the server
            FTPFile[] files = ftpClient.listFiles();
            for (FTPFile file : files)
            {
                System.out.println(file.getName());
            }




        }
        catch(ConnectException ex){
            System.out.println("conn ex " + ex);
        }
        catch(IOException ex){
            System.out.println("Error logging in");
            ex.printStackTrace();

        }




    }
}
