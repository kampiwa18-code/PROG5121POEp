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
               
                    
                     // PART 2 Starts here
                   
                   //The user should only be able to send message if the have logged in successfully and then it will display quickchat message
                  if (isAuthenticated){
                      System.out.println("Welcome to QuickChat.");
  
                      
                  
                      
                      //Displays the menu options for the user to choose from
                      System.out.println("\n QuickChat MENU");
                      System.out.println("1) Send Messages");
                      System.out.println("2) Show recently sent message");
                      System.out.println("3) Quit");
                      System.out.println("Choose an option from (1-3): ");
                      
                      String option = scanner.nextLine().trim();
                      
                      //Handle menu choice using a switch statement
                      switch (option){
                          case "1":
                      
                           System.out.println("How many messages would you like to enter?");
                           int numMessage = Integer.parseInt(scanner.nextLine().trim()); 
                           
                           int sentCount = 0;
                           
                           //Using a for loop to allow entry of the set number of messages
                           for (int i = 0; i < numMessage; i++){
                                System.out.println("\n Message" + (i + 1) + " of " + numMessage + "---");
                                System.out.print("Enter recipient cell number (+27...): ");
                                String recipient = scanner.nextLine().trim();
                                
                                //Validate recipient using the message class
                                Message draft = new Message(i + 1, recipient, "placeholder");
                                String recipientStatus = draft.checkRecipientCell();
                                System.out.println(recipientStatus);
                                
                                // if its valid, lets them try this message slot again
                                if (!recipientStatus.startsWith("Cell phone number successfully")){
                                    i--;
                                    continue;
                                }
                                
                                System.out.println("Enter your message (max 250 characters): ");
                                String msgContent = scanner.nextLine();
                                
                                
                                Message message = new Message(i + 1, recipient, msgContent);
                                String lengthStatus = message.checkMessageLength();
                                System.out.println(lengthStatus);
                                
                                //If message exceeds character limit, tells user to retry
                                if (!lengthStatus.equals("Message ready to send.")){
                                    i--;
                                    continue;
                                }
                           }
                      }
                      
        
                  }
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

