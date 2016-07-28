package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.SocketException;
import org.apache.commons.net.ftp.*;
import java.util.Scanner;

import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPClient;



public class FTPClientClass {

    private static FTPClient ftpClient = new FTPClient();
    private static String localPath;
    private static String userName;
    private static String Password;
    private static String host;
    private static Integer port;

    /* Username and password is hardcoded , should have a method to create/ delete account
    and allow multiple user login */
    public FTPClientClass() {

        this.localPath = "/Users/revathy/Desktop/ClientFolder/java-ebook.pdf";
        this.userName = "demo-user";
        this.Password = "demo-user";

    }

    /* Help needed to add Command Line Interface */
    public static void main(String[] args) {

        FTPClientClass ftpObj = new FTPClientClass();
        try{
            ftpObj.getConnection();
            ftpObj.loginUser(userName, Password);
            ftpObj.listFilesLocal();
            ftpObj.listFilesRemote();
            ftpObj.listDirectoriesLocal();
            ftpObj.listDirectoriesRemote();
            ftpObj.downloadSingle();
            ftpObj.uploadSingle();
            ftpObj.deleteFileRemote();

        }

        catch(ConnectException ex)

        {
            System.out.println("conn ex " + ex);
        }

        catch(IOException ex)

        {
            System.out.println("IO Exception");
            ex.printStackTrace();

        }

    }


    //Get connection info from User

    void getConnection()throws ConnectException, SocketException, IOException {
        FTPClient ftpClient = new FTPClient();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the host name to connect \n");
        final String host;
        this.host= scanner.next();
        System.out.println(this.host);
        System.out.println("Enter the host name to connect \n");
        final Integer port;
        this.port= scanner.nextInt();
        System.out.println(this.port);



        //
        //boolean login = ftpClient.login(userName, Password);

    }

    //Save connection info
    void saveConnection() throws IOException {

    }

    //To Login user to the Remote Server
    private void loginUser(String userName, String Password) throws IOException, ConnectException {
        ftpClient.connect(host,port);
        System.out.println(host + " " + port);
        System.out.println("Entering login");
        boolean login = ftpClient.login(userName, Password);
        String[] reply = ftpClient.getReplyStrings();
        System.out.println(reply);
        System.out.println("Is the connection a success ? :" + login);
        System.out.println("After connecting to server");
    }

    //Listing files in Remote Server
    private void listFilesRemote() throws IOException {

        FTPFile[] files = ftpClient.listFiles();
        for (FTPFile file : files)

        {
            System.out.println("**Listing Files ON SERVER**");
            System.out.println(file.getName());
            System.out.println();
        }
    }

    //Listing directories in Remote Server
    private void listDirectoriesRemote() throws IOException {
        FTPFile[] directories = ftpClient.listDirectories();

        for ( FTPFile directory : directories)

        {
            System.out.println("**Listing Directories ON SERVER**");
            System.out.println(directory.getName());
            System.out.println();
        }
    }

    //Listing files in Local Machine
    private void listFilesLocal() throws IOException {
        FTPFile[] Lfiles = ftpClient.listFiles();
        for ( FTPFile file : Lfiles)

        {
            System.out.println("**Listing Files ON CLIENT **");
            System.out.println(file.getName());
            System.out.println();
        }
    }

    //Listing directories in Local Server
    private void listDirectoriesLocal() throws IOException {
        FTPFile[] Ldirectories = ftpClient.listDirectories();

        for (FTPFile directory : Ldirectories)

        {
            System.out.println("**Listing Directories ON CLIENT**");
            System.out.println(directory.getName());
            System.out.println();
        }
    }

    //Download single file from server
    private void downloadSingle() throws IOException {

        FileOutputStream fos = new FileOutputStream(localPath);
        ftpClient.retrieveFile("java-ebook.pdf", fos);
        fos.close();
    }

    //download multiple files from server


    // To delete a file  from remote server
    private void deleteFileRemote() throws IOException{
        String fileToDelete = "test.txt"; // This should change according to your file structure
        boolean isDeleted = ftpClient.deleteFile(fileToDelete);
        if(isDeleted)

        {
            System.out.println("The file was deleted successfully.");
        }

        else

        {
            System.out.println("Could not delete the  file, it may not exist.");
        }
    }

    // To upload a single file to remote server from local
    private void uploadSingle() throws IOException {
        File firstLocalFile = new File("/Users/revathy/Desktop/ClientFolder/UploadedFile.txt");
        // Change the path above according to your file directory and also put a file down
        String firstRemoteFile = "UploadedFile.txt";
        InputStream inputStream = new FileInputStream(firstLocalFile);

        System.out.println("Start uploading first file");
        boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
        inputStream.close();
        if (done)

        {
            System.out.println("The first file is uploaded successfully.");
        }
    }


}







