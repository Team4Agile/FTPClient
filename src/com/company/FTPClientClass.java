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
        String LocalPath = "/Users/revathy/Desktop/ClientFolder/java-ebook.pdf";
        //String Source =
        try {
            ftpClient.connect("127.0.0.1", 21);
            boolean login = ftpClient.login("demo-user", "demo-user");
            String[] reply = ftpClient.getReplyStrings();
            System.out.println(reply);
            System.out.println("Is the connection a success ? :" + login);
            //ftpClient.disconnect();
            System.out.println("After connecting to server");

            //Listing files in Remote Server
            FTPFile[] files = ftpClient.listFiles();
            for (FTPFile file : files)
            {
                System.out.println("**Listing Files ON SERVER**");
                System.out.println(file.getName());
                System.out.println();
            }
            //Listing directories in Remote Server

            FTPFile[] directories = ftpClient.listDirectories();
            for (FTPFile directory : directories)
            {
                System.out.println("**Listing Directories ON SERVER**");
                System.out.println(directory.getName());
                System.out.println();
            }

            //Listing files in Local Machine
            FTPFile[] Lfiles = ftpClient.listFiles();
            for (FTPFile file : Lfiles)
            {
                System.out.println("**Listing Files ON CLIENT **");
                System.out.println(file.getName());
                System.out.println();
            }

            //Listing directories in Local Server
            FTPFile[] Ldirectories = ftpClient.listDirectories();
            for (FTPFile directory : directories)
            {
                System.out.println("**Listing Directories ON CLIENT**");
                System.out.println(directory.getName());
                System.out.println();
            }

            //download single file from server
            FileOutputStream fos = new FileOutputStream(LocalPath);
            ftpClient.retrieveFile("java-ebook.pdf",fos);

            fos.close();

            //download multiple files from server



            // To delete a file  from remote server
            String fileToDelete = "test.txt"; // This should change according to your file structure
            boolean isDeleted = ftpClient.deleteFile(fileToDelete);
            if (isDeleted) {
                System.out.println("The file was deleted successfully.");
            } else {
                System.out.println("Could not delete the  file, it may not exist.");
            }

            // To upload a single file to remote server from local
            File firstLocalFile = new File("/Users/revathy/Desktop/ClientFolder/UploadedFile.txt");
            // Change the path above according to your file directory and also put a file down
            String firstRemoteFile = "UploadedFile.txt";
            InputStream inputStream = new FileInputStream(firstLocalFile);

            System.out.println("Start uploading first file");
            boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
            inputStream.close();
            if (done) {
                System.out.println("The first file is uploaded successfully.");
            }




        }



        catch(ConnectException ex){
            System.out.println("conn ex " + ex);
        }
        catch(IOException ex){
            System.out.println("IO Exception");
            ex.printStackTrace();

        }




    }
}
