package main_package;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.SocketException;
import java.util.Scanner;

//import com.sun.tools.doclets.internal.toolkit.util.SourceToHTMLConverter;
import org.apache.commons.net.ftp.*;
//import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPReply;


public class FTPClientClass {
//    this.localPath = "/Users/revathy/Desktop/ClientFolder/java-ebook.pdf";
//    this.userName = "demo-user";
//    this.Password = "demo-user";
    private static String localPath;
    private static String userName;
    private static String Password;
    private static String host;
    private static Integer port;
	FTPClient ftpClient = new FTPClient();
	
	   //Get connection info from User
    void getConnection() throws ConnectException, SocketException, IOException {
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
    }
	
	public void connectServer() {
		try {
			ftpClient.connect("ftp.drivehq.com");
			ftpClient.login("prajyoth", "bq6ItBRI");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("problem connecting to the server");
			e.printStackTrace();
		}
	}
	
	public void listRemoteFiles() {
        //Listing files in Remote Server
        FTPFile[] files;
		try {
			files = ftpClient.listFiles();
	        for (FTPFile file : files)
	        {
	            System.out.println("**Listing Files ON SERVER**");
	            System.out.println(file.getName());
	            System.out.println();
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

    //Listing directories in Remote Server
    public void listDirectoriesRemote() throws IOException {
        FTPFile[] directories = ftpClient.listDirectories();
        for ( FTPFile directory : directories) {
            System.out.println("**Listing Directories ON SERVER**");
            System.out.println(directory.getName());
            System.out.println();
        }
    }
	
	
	//Listing files in Local Machine
    public void listFilesLocal() throws IOException {
        FTPFile[] Lfiles = ftpClient.listFiles();
        for ( FTPFile file : Lfiles) {
            System.out.println("**Listing Files ON CLIENT **");
            System.out.println(file.getName());
            System.out.println();
        }
    }
    
    //Listing directories in Local Server
    public void listDirectoriesLocal() throws IOException {
        FTPFile[] Ldirectories = ftpClient.listDirectories();
        for (FTPFile directory : Ldirectories){
            System.out.println("**Listing Directories ON CLIENT**");
            System.out.println(directory.getName());
            System.out.println();
        }
    }
    
    //Download single file from server
    public void downloadSingle() throws IOException {
        FileOutputStream fos = new FileOutputStream(localPath);
        ftpClient.retrieveFile("java-ebook.pdf", fos);
        fos.close();
    }
    
    //download multiple files from server
    
    // To delete a file  from remote server
    public void deleteFileRemote() throws IOException{
        String fileToDelete = "test.txt"; // This should change according to your file structure
        boolean isDeleted = ftpClient.deleteFile(fileToDelete);
        if(isDeleted) {
            System.out.println("The file was deleted successfully.");
        }
        else {
            System.out.println("Could not delete the  file, it may not exist.");
        }
    }
    
    // To upload a single file to remote server from local
    public void uploadSingle() throws IOException {
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
    
}
