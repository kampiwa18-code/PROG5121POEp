
package prog5121poep;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class PROG5121POEpTest {
    
    public PROG5121POEpTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class PROG5121POEp.
     */
    @Test
    public void testMain() {
        
    }

    /**
     * Test of checkusername method, of class PROG5121POEp.
     */
    @Test
    public void testCheckusername() {
      // Test 1: Valid username
        String usernameValid = "kyl_1";
        boolean resultValid = PROG5121POEp.checkusername(usernameValid);
        assertTrue(resultValid);

        // Test 2: Invalid username
        String usernameInvalid = "kyle!!!!!!";
        boolean resultInvalid = PROG5121POEp.checkusername(usernameInvalid);
        assertFalse(resultInvalid);
        
    }

    /**
     * Test of checkpasswordcomplexity method, of class PROG5121POEp.
     */
    @Test
    public void testCheckpasswordcomplexity() {
       System.out.println("checkpasswordcomplexity");
        
        // Test 1: Valid password complexity
        String passwordValid = "Ch&&sec@ke99!";
        boolean resultValid = PROG5121POEp.checkpasswordcomplexity(passwordValid);
        assertTrue(resultValid);

        // Test 2: Invalid password complexity
        String passwordInvalid = "password";
        boolean resultInvalid = PROG5121POEp.checkpasswordcomplexity(passwordInvalid);
        assertFalse(resultInvalid);
    }

    /**
     * Test of checkcellphonenumber method, of class PROG5121POEp.
     */
    @Test
    public void testCheckcellphonenumber() {
      System.out.println("checkcellphonenumber");
        
        // Test 1: Valid cell number format
        String cellPhoneValid = "+27838968976";
        boolean resultValid = PROG5121POEp.checkcellphonenumber(cellPhoneValid);
        assertTrue(resultValid);

        // Test 2: Invalid cell number format
        String cellPhoneInvalid = "08966553";
        boolean resultInvalid = PROG5121POEp.checkcellphonenumber(cellPhoneInvalid);
        assertFalse(resultInvalid);
    }

    /**
     * Test of loginuser method, of class PROG5121POEp.
     */
    @Test
    public void testLoginuser() {
        
       // Test 1: Successful login
        boolean resultSuccess = PROG5121POEp.loginuser("kyl_1", "Ch&&sec@ke99!", "kyl_1", "Ch&&sec@ke99!");
        assertTrue(resultSuccess);

        // Test 2: Failed login
        boolean resultFailed = PROG5121POEp.loginuser("wrongUser", "wrongPass", "kyl_1", "Ch&&sec@ke99!");
        assertFalse(resultFailed);
    }

    /**
     * Test of returnloginstatus method, of class PROG5121POEp.
     */
    @Test
    public void testReturnloginstatus() {
       System.out.println("returnloginstatus");
        
        boolean isLoggedIn = true;
        String firstName = "Kyle";
        String lastName = "Smith";
        String expResult = "Welcome Kyle, Smith it is great to see you.";
        
        String result = PROG5121POEp.returnloginstatus(isLoggedIn, firstName, lastName);
        assertEquals(expResult, result);
        
    }
    
}
