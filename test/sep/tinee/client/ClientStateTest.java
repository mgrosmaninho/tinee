package sep.tinee.client;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * ClientState Test
 * 
 * @author Manuel Gomes Rosmaninho
 */
public class ClientStateTest {
    
    private ClientState state;
    
    public ClientStateTest() {
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
     * Test of setState method to Main State, of class ClientState.
     */
    @Test
    public void testSetMainState() {
        state = ClientState.MAIN;
        state.setState(1);
        int expected = 1;
        int actual = state.getState();
        assertEquals("MainState", expected, actual);
    }

    /**
     * Test of setState method to Draft State, of class ClientState.
     */
    @Test
    public void testSetDraftState() {
        state = ClientState.MAIN;
        state.setState(2);
        int expected = 2;
        int actual = state.getState();
        assertEquals("DraftState", expected, actual);
    }
    
    /**
     * Test of setState method to Draft State, of class ClientState.
     */
    @Test
    public void testSetDoneState() {
        state = ClientState.MAIN;
        state.setState(0);
        int expected = 0;
        int actual = state.getState();
        assertEquals("DoneState", expected, actual);
    }
}
