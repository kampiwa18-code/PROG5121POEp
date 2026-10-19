package prog5121poep;

import java.util.regex.Pattern;

public class Message {
    private int messageId;
    private String recipientCell;
    private String messageContent;

    // Constructor to initialize a message
    public Message(int messageId, String recipientCell, String messageContent) {
        this.messageId = messageId;
        this.recipientCell = recipientCell;
        this.messageContent = messageContent;
    }

    // Validates that the cell number starts with +27 and has 9 digits following it
    public String checkRecipientCell() {
        if (recipientCell == null) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        String regex = "^\\+27\\d{9}$";
        if (Pattern.matches(regex, recipientCell) && recipientCell.length() >= 12) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
    }

    // Validates that the message is 250 characters or less
    public String checkMessageLength() {
        if (messageContent != null && messageContent.length() <= 250) {
            return "Message ready to send.";
        } else {
            return "Please check the message size; it exceeds 250 characters.";
        }
    }

    // Returns a formatted string displaying the message details
    public String printMessage() {
        return "Message ID: " + messageId + 
               "\nRecipient: " + recipientCell + 
               "\nContent: " + messageContent;
    }
}
