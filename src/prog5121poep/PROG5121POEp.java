package prog5121poep;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;


public class PROG5121POEp {
    //Part 3 
      // i used Arraylist here so the grow automatically as messages are added
    
    static ArrayList<String> sentMessagesList = new Arraylist<>();
    static ArrayList<String> disregardedMessagesList = new ArrayList<>();
    static ArrayList<String> storedMessagesList = new ArrayList<>();
    static ArrayList<String> messageHashesList = new ArrayList<>();
    static ArrayList<String> messageIDsList = new ArrayList<>();
   
    
    
    // Etra lists so i can keep track of senders, recipients, IDs and hashes together
       
       static ArrayList<String> sentSendersList = new ArrayList<>();
       static ArrayList<String> sentRecipientsList = new ArrayList<>();
       static ArrayList<String> storedSendersList = new ArrayList<>();
       static ArrayList<String> storedRecipientsList = new ArrayList<>();
       static ArrayList<String> storedIDsList = new ArrayList<>();
       static ArrayList<String> storedHashesList = new ArrayList<>();
       
    // file name for storing message in JSON format
       
       static String STORED_JSON_FILE = "stored_messages.json";
       
 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Load any saved JSON messages into the stored list when the program starts
        loadStoredMessagesFromJson(STORED_JSON_FILE);
        
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
                                System.out.println("\n--- Message" + (i + 1) + " of " + numMessage + "---");
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
                                
                                //Give options on what to do with the vaild message
                                System.out.println("Choose what to do with this message");
                                System.out.println("1) Send Message");
                                System.out.println("2) Disregard Message");
                                System.out.println("3) Store Message to send later");
                                System.out.print("Your choice: ");
                                String choice = scanner.nextLine().trim();
                                
                                if (choice.equals("1")){
                                    sentCount++;
                                    System.out.println("Message successfully sent.");
                                    System.out.println(message.printMessage());
                               
                                }
                                else if (choice.equals("2")){
                                    System.out.println("Press 0 to delete the message: ");
                                    String confirm = scanner.nextLine().trim();
                                    if (confirm.equals("0")){ 
                                        System.out.println("Message disregarded");
                                    }
                                
                                    else {
                                        System.out.println("Disregard cancelled; message was not sent.");
                                        i--;
                                    }
                                
                                }
                                else if (choice.equals("3")) {
                                    System.out.println("Message successfully stored.");
                                    System.out.println(message.printMessage());
                                
                                }
                                else{
                                    System.out.println("Invalid choice. Message not processed.");
                                    i--;
                                }
                           }
                           System.out.println("Total message successfully sent:" + sentCount);
                           break;
                           
                          case "2":
                              System.out.println("Coming Soon.");
                              break;
                          
                          case "3":
                              System.out.println("Thank you for using QuickChat.");
                              break;
                              
                              // Part 3 fourth menu option
                          case "4":
                              loadStoredMessagesFromJson(STORED_JSON_FILE);
                              handleStoredMessagesMenu(scanner);
                              break;
                                
                          default:
                              System.out.println("Invalid option.");
                      }
                      
                  
        
                  }
            }
            }
       
    }
    }
    
                   //PART 3
    
    // Adds a sent message to the sent list, IDs and hashes
     
    public static void recordSentMessage(String sender, String recipient, String content, String msgId, String msgHash) {
        sentMessagesList.add(content);
        sentSendersList.add(sender);
        sentRecipientsList.add(recipient);
        messageIDsList.add(msgId);
        messageHashesList.add(msgHash);
    }
    
    // Adds a disregarded message to the disregarded list, IDs, and hashes
    public static void recordDisregardedMessage(String content, String msgId, String msgHash) {
        disregardedMessagesList.add(content);
        messageIDsList.add(msgId);
        messageHashesList.add(msgHash);
    }
    
    // Adds a stored message to the stored list along with its details
    public static void recordStoredMessage(String sender, String recipient, String content, String msgId, String msgHash) {
        storedMessagesList.add(content);
        storedSendersList.add(sender);
        storedRecipientsList.add(recipient);
        storedIDsList.add(msgId);
        storedHashesList.add(msgHash);
        messageIDsList.add(msgId);
        messageHashesList.add(msgHash);
    }
    
    // Converts list into normal arrays
    public static String[] getSentMessagesArray() {
        String[] arr = new String[sentMessagesList.size()];
        for (int i = 0; i < sentMessagesList.size(); i++) {
            arr[i] = sentMessagesList.get(i);
        }
        return arr;
    }

    public static String[] getDisregardedMessagesArray() {
        String[] arr = new String[disregardedMessagesList.size()];
        for (int i = 0; i < disregardedMessagesList.size(); i++) {
            arr[i] = disregardedMessagesList.get(i);
        }
        return arr;
    }

    public static String[] getStoredMessagesArray() {
        String[] arr = new String[storedMessagesList.size()];
        for (int i = 0; i < storedMessagesList.size(); i++) {
            arr[i] = storedMessagesList.get(i);
        }
        return arr;
    }

    public static String[] getMessageHashesArray() {
        String[] arr = new String[messageHashesList.size()];
        for (int i = 0; i < messageHashesList.size(); i++) {
            arr[i] = messageHashesList.get(i);
        }
        return arr;
    }

    public static String[] getMessageIDsArray() {
        String[] arr = new String[messageIDsList.size()];
        for (int i = 0; i < messageIDsList.size(); i++) {
            arr[i] = messageIDsList.get(i);
        }
        return arr;
    }
    
    // shows the stored Messages menu from option a to f 
    public static void handlestoreMessagesMenu(Scanner scanner){
       System.out.println("\n STORED MESSAGES MENU");
       System.out.println("a) Display sender and the recipient of all stored messages");
       System.out.println("b) Display the longest stored message");
       System.out.println("c) Search for a message ID and display recipient and message");
       System.out.println("d) Search for all the messages stored for a particular recipient");
       System.out.println("e) Delete a message using the message hash");
       System.out.println("f) Display a report listing full details of all stored messages");
       System.out.println("Enter a choice (a-f): ");
       
       String choice = scanner.nextLine().trim().toLowerCase();
       if (choice.equals("a")) {
            System.out.println(displayStoredSendersAndRecipients());
        } else if (choice.equals("b")) {
            System.out.println(getLongestStoredMessage());
        } else if (choice.equals("c")) {
            System.out.print("Enter Message ID: ");
            String id = scanner.nextLine().trim();
            System.out.println(searchByMessageId(id));
        } else if (choice.equals("d")) {
            System.out.print("Enter recipient number (+27...): ");
            String rec = scanner.nextLine().trim();
            System.out.println(searchMessagesByRecipient(rec));
        } else if (choice.equals("e")) {
            System.out.print("Enter Message Hash to delete: ");
            String hash = scanner.nextLine().trim();
            System.out.println(deleteMessageByHash(hash));
        } else if (choice.equals("f")) {
            System.out.println(displayStoredMessagesReport());
        } else {
            System.out.println("Invalid choice.");
        }
       
    }
    
    // (a) Displays the sender and the recipient of every stored message 
    public static String displayStoredSendersAndRecipients(){
      if (storedMessagesList.isEmpty()){
          return "No stored message found";
      }
      String output = "stored Messages (Sender & Recipient): \n";
      for (int i = 0; i < storedMessagesList.size(); i++){
           output = output + (i + 1) + ".Sender: " + storedSendersList.get(i) + "| Recipient:" + storedRecipientsList.get(i) + "\n";
                   }
            return output.trim();
      
    }
    
    
    // (b) finds and returns the longest stored message
    public static String getLongestStoredMessage(){
      if (storedMessagesList.isEmpty()) {
          return "No stored message found. ";
      }
      
      String longest = storedMessagesList.get(0);
      for (int i = 1; i < storedMessagesList.size();  i++){
            if (storedMessagesList.get(i).length() > longest.length()){
                longest = storedMessagesList.get(i);
            }
      }
      return longest;
    }
    
    // (c) Searches stored messages by message ID and shows recipient and message
    public static String searchBymessageId(String messageId){
        for (int i = 0; i < storedIDsList.size(); i++) {
          if (storedIDsList.get(i).equalsIgnoreCase(messageId)){
          return "Recipient: " + storedRecipientsList.get(i) + "\nMessage: " + storedMessagesList.get(i);
        }
       }
       return "Message ID not found. "; 
    }
    
    //(d) Searches all stored messages for a particular recipient 
    public static String searchMessagesByRecipient(String recipient) {
        String matches = "";
        for (int i = 0; i < storedMessagesList.size(); i++) {
            if (storedRecipientsList.get(i).equalsIgnoreCase(recipient)) {
                if (!matches.equals("")) {
                    matches = matches + "\n";
                }
                matches = matches + storedMessagesList.get(i);
            }
        }
        if (matches.equals("")) {
            return "No stored messages found for recipient: " + recipient;
        }
        return matches;
    }
    
    
    // (e) Deletes a stored message using its message hash
   public static String deleteMessageByHash(String messageHash) {
        for (int i = 0; i < storedHashesList.size(); i++) {
            if (storedHashesList.get(i).equalsIgnoreCase(messageHash)) {
                String deletedMsg = storedMessagesList.remove(i);
                storedSendersList.remove(i);
                storedRecipientsList.remove(i);
                storedIDsList.remove(i);
                storedHashesList.remove(i);
                messageHashesList.remove(messageHash);

                saveStoredMessagesToJson(STORED_JSON_FILE);
                return "Message: \"" + deletedMsg + "\" successfully deleted.";
            }
        }
        return "Message with hash \"" + messageHash + "\" not found.";
    }
   
   // (f) Prints a report with details of all stored messages
   public static String displayStoredMessagesReprt(){
       if (storedMessagesList.isEmpty()){
           return "No stored messages available for report.";
       }
       String report = "\n STORED MESSAGES REPORT \n";
        for (int i = 0; i< storedMessagesList.size(); i++){
            report = report + "Message " + (i + 1) + "\n";
            report = report + "Message ID: " + storedIDsList.get(i) + "\n";
            report = report + "Message Hash: " + storedHashesList.get(i) + "\n";
            report = report + "Sender: " + storedSendersList.get(i) + "\n";
            report = report + "Recipient: " + storedRecipientsList.get(i) + "\n";
            report = report + "Message: " + storedMessagesList.get(i) + "\n";
            report = report + "-------------------------\n";
        }
        return report.trim();
   }
   // Saves stored messages into a JSON file so we can read them back into the list
    public static void saveStoredMessagesToJson(String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write("[\n");
            for (int i = 0; i < storedMessagesList.size(); i++) {
                writer.write("  {\n");
                writer.write("    \"messageID\": \"" + storedIDsList.get(i) + "\",\n");
                writer.write("    \"messageHash\": \"" + storedHashesList.get(i) + "\",\n");
                writer.write("    \"sender\": \"" + storedSendersList.get(i) + "\",\n");
                writer.write("    \"recipient\": \"" + storedRecipientsList.get(i) + "\",\n");
                writer.write("    \"message\": \"" + storedMessagesList.get(i) + "\"\n");
                if (i < storedMessagesList.size() - 1) {
                    writer.write("  },\n");
                } else {
                    writer.write("  }\n");
                }
            }
            writer.write("]\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving JSON file: " + e.getMessage());
        }
    }
   
   
   
   
    // Part 1 below
    
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