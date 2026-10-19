package prog5121poep;

import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    @Test
    public void testCheckRecipientCell_Valid() {
        Message msg = new Message(1, "+27831234567", "Hello");
        String result = msg.checkRecipientCell();
        assertEquals("Cell phone number successfully captured.", result);
    }

    @Test
    public void testCheckRecipientCell_Invalid() {
        Message msg = new Message(1, "0831234567", "Hello");
        String result = msg.checkRecipientCell();
        assertEquals("Cell phone number incorrectly formatted or does not contain international code.", result);
    }

    @Test
    public void testCheckMessageLength_Valid() {
        Message msg = new Message(1, "+27831234567", "Test message under 250 characters.");
        String result = msg.checkMessageLength();
        assertEquals("Message ready to send.", result);
    }

    @Test
    public void testCheckMessageLength_Invalid() {
        String longText = "A".repeat(251);
        Message msg = new Message(1, "+27831234567", longText);
        String result = msg.checkMessageLength();
        assertEquals("Please check the message size; it exceeds 250 characters.", result);
    }

    @Test
    public void testPrintMessage() {
        Message msg = new Message(1, "+27831234567", "Hello World");
        String expected = "Message ID: 1\nRecipient: +27831234567\nContent: Hello World";
        assertEquals(expected, msg.printMessage());
    }
}