package prog5121poep;

import java.util.Scanner;
import java.util.regex.Pattern;


public class PROG5121POEp {

 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String firstName;
        String lastName;
        String username;
        String password;
        String cellPhone;
        
        
        System.out.print("Enter your first name: ");
        firstName = scanner.nextLine();

        System.out.print("Enter your last name: ");
        lastName = scanner.nextLine();
        
        System.out.println("*********************");
        System.out.println("    REGISTRATION     ");
        System.out.println("*********************");
        
        // Asking the user to enter USERNAME 
        System.out.print("Enter username: ");
        username = scanner.nextLine();

        // This checks if the user entered the correct formate
        if (!checkusername(username)) {
            System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and"
                    + " is no more than five characters in length.");
        } 
            else {
                System.out.println("Username successfully captured.");
            
            
             // Asking user to enter a PASSWORD
            System.out.print("Enter password: ");
            password = scanner.nextLine();

            // This checks if user entered the correct password formate
        if (!checkpasswordcomplexity(password)) {
                System.out.println("Password is not correctly formatted; please ensure that the password contains at "
                        + "least eight characters, a capital letter, a number, and a special character.");
            } 
            else {
                System.out.println("Password successfully captured.");
                
                
            // Asks user to enter CELL PHONE Number
                System.out.print("Enter cell phone number (+27...): ");
                cellPhone = scanner.nextLine();
          
            // Checks if user inputed the correct digits
        if (!checkcellphonenumber(cellPhone)) {
                    System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
                } else {
                    System.out.println("Cell phone number successfully added.");  
                    
                    
               
                    
            // LOGIN PROCESS
        System.out.println("*********************");
        System.out.println("        LOGIN        ");
        System.out.println("*********************");
        
        //User has to enter their login authentication for username and password
             System.out.print("Enter username: ");
                    String loginUser = scanner.nextLine();       

             System.out.print("Enter password: ");
                    String loginPass = scanner.nextLine();
                    
                    
                    //A helper method to check if login credentials are correct
                    boolean isAuthenticated = loginuser(loginUser, loginPass, username, password);
                    System.out.println(returnloginstatus(isAuthenticated, firstName, lastName));
                }
            }
       
    }
    }
    
     //Makes sure that there is a underscore and its less than 5 for the username
      public static boolean checkusername(String username) {
        return username != null && username.contains("_") && username.length() <= 5;
    }
      
      //Checks that password has capital letter, special characters and at least 8 characters
  public static boolean checkpasswordcomplexity(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
          boolean hasCapital = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        String specialCharacters = "!@#$%^&*()_+-=[]{}|;:'\",.<>?/";

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);

            if (Character.isUpperCase(ch)) {
                hasCapital = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            } else if(specialCharacters.contains(String.valueOf(ch))) {
                hasSpecial = true;
            }
        }

        return hasCapital && hasDigit && hasSpecial;
    }
  
  //Makes sure that phone number starts with +27 and is followed by exactly 9 numbers
   public static boolean checkcellphonenumber(String cellPhone) {
        if (cellPhone == null) {
            return false;
        }
        String regex = "^\\+27\\d{9}$";
        return Pattern.matches(regex, cellPhone) && cellPhone.length() >= 12;}
   
   
   // Checks if credentials taht was entered match registered credentials
  public static boolean loginuser(String enteredUser, String enteredPass, String registeredUser, String registeredPass) {
        return enteredUser != null && enteredPass != null &&
               enteredUser.equals(registeredUser) &&
               enteredPass.equals(registeredPass);
    }
  
  // Given a welcome message if authenticated, or error message if failed
     public static String returnloginstatus(boolean isLoggedIn, String firstName, String lastName) {
        if (isLoggedIn) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

} 

