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
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
//import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPReply;



public class FTPClientClass {

    public static void main(String[] args) {
	    System.out.println("hi");
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect("demo.wftpserver.com", 21);
            boolean login = ftpClient.login("demo-user", "demo-user");
            String[] reply = ftpClient.getReplyStrings();
            System.out.println(reply);


            System.out.println("login is :" + login);
            //ftpClient.disconnect();
            System.out.println("yes");
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
