// import statements
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.Arrays;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

// Create a class Authentication
public class AuthenticationSystem {
    // main() method
    public static void main(String[] args) throws IOException
    {
         // Call loginScreen() method
         loginScreen();
    }   
    // loginScreen() method for user login
    public static void loginScreen()
    {
         // Declare variables
         String genPwd = "";               
         int flag = 0,attempts=3;        
         // Create an object 'br' for BufferedReader class to accept data from
         // user and read data from a file
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));    
         System.out.println("\nLogin");        
         try {
             do{                
                 attempts--;
                 System.out.println("Enter Username");
                 String uName = br.readLine();
                 System.out.println("Enter Password");
                 String pwd = br.readLine();       
                 // Create the object 'md' for the MessageDigest class to convert
                 // password in md5
                String original = pwd;  //Replace "password" with the actual password inputted by the user
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(original.getBytes());
		byte[] digest = md.digest();
                StringBuffer sb = new StringBuffer();
		for (byte b : digest) {
			sb.append(String.format("%02x", b & 0xff));
		}
                 genPwd = sb.toString();
                 //System.out.println("Password entered by you:" + genPwd);
                                
                 // Open credentials.txt file
                 BufferedReader bin = new BufferedReader(new FileReader("credentials.txt")); 
                 String currentLine; 
                 // Check the username and password from the file
                 // Read the each line from the file
                 while ((currentLine = bin.readLine()) != null)
                 {
                      // Split the line where the tab is present
                      String[] array = currentLine.split("\\s+");  
                      // Check username
                      if (array[0].contains(uName))
                      {
                           System.out.println(" ");
                          // Check password
                          if (array[1].contains(genPwd))                             
                          {
                               flag = 1;
                               break;
                          }
                      }
                 }                
                 // Checks if the user enters more then 3 attempts
                 if(attempts==0)
                 {
                      System.out.println("You are attempted to login more then three times");
                      System.out.println("Exiting...");
                      System.exit(1);
                 }                
                 // If username and password is true
                         
                 if (flag == 1){  
                 // Call adminScreen() method        
                      adminScreen();
                      break;
                 }
                 // If invalid username and password
                 else
                 {
                      System.out.println("Invalid Username or Password.");
                      System.out.println("Please try again.");                    
                      System.out.println(attempts+" more attemptes left.\n");  
                 }
             }while(attempts>0);
         }
         catch (NoSuchAlgorithmException e)
         {
             e.printStackTrace();
         }       
         catch (IOException e) {
             e.printStackTrace();
         }       
    }   
    // Create adminScreen() method
    public static void adminScreen() throws IOException{
        
         System.out.println("Welcome");         
         System.out.println("Enter role: ");
         String role;
        
         // Create Scanner class object to accept data from the user
         Scanner sc= new Scanner(System.in);
         role = sc.nextLine();
         if(role.equals("admin")){
              BufferedReader br = new BufferedReader(new FileReader("admin.txt"));
              String line = null;
              while ((line = br.readLine()) != null) {
              System.out.println(line);
              }
         }
         else if(role.equals("zookeeper")){
             BufferedReader br = new BufferedReader(new FileReader("zookeeper.txt"));
              String line = null;
              while ((line = br.readLine()) != null) {
              System.out.println(line);
              }
         }
         else if(role.equals("veterinarian")){
             BufferedReader br = new BufferedReader(new FileReader("veterinarian.txt"));
              String line = null;
              while ((line = br.readLine()) != null) {
              System.out.println(line);
              }
             
         }
         else{
            role.equals("Exit");
             exit();
    }
         
    }
    public static void exit() throws IOException{
        String logOut;
        
         // Create Scanner class object to accept data from the user
         Scanner sc= new Scanner(System.in);
         System.out.println("Type Exit for log out"); 
         do{
             logOut = sc.nextLine();
         }
         while(!logOut.equals("Exit")); 
             
         // If the user want to exit from admin screen
         if(logOut.equals("Exit")){
             // Call login screen
             loginScreen();
         }
    }
     
}   
    
