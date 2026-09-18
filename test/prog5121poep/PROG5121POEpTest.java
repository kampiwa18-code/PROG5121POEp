
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
        System.out.println("main");
        String[] args = null;
        PROG5121POEp.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkusername method, of class PROG5121POEp.
     */
    @Test
    public void testCheckusername() {
        System.out.println("checkusername");
        String username = "";
        boolean expResult = false;
        boolean result = PROG5121POEp.checkusername(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkpasswordcomplexity method, of class PROG5121POEp.
     */
    @Test
    public void testCheckpasswordcomplexity() {
        System.out.println("checkpasswordcomplexity");
        String password = "";
        boolean expResult = false;
        boolean result = PROG5121POEp.checkpasswordcomplexity(password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkcellphonenumber method, of class PROG5121POEp.
     */
    @Test
    public void testCheckcellphonenumber() {
        System.out.println("checkcellphonenumber");
        String cellPhone = "";
        boolean expResult = false;
        boolean result = PROG5121POEp.checkcellphonenumber(cellPhone);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loginuser method, of class PROG5121POEp.
     */
    @Test
    public void testLoginuser() {
        System.out.println("loginuser");
        String enteredUser = "";
        String enteredPass = "";
        String registeredUser = "";
        String registeredPass = "";
        boolean expResult = false;
        boolean result = PROG5121POEp.loginuser(enteredUser, enteredPass, registeredUser, registeredPass);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of returnloginstatus method, of class PROG5121POEp.
     */
    @Test
    public void testReturnloginstatus() {
        System.out.println("returnloginstatus");
        boolean isLoggedIn = false;
        String firstName = "";
        String lastName = "";
        String expResult = "";
        String result = PROG5121POEp.returnloginstatus(isLoggedIn, firstName, lastName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
