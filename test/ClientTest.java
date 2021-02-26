/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.ByteArrayInputStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author portuga
 */
public class ClientTest {
    
    public ClientTest() {
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
     * Test of main method, of class Client.
     */
//    @Test
//    public void testMain() throws Exception {
//        System.out.println("main");
//        String[] args = null;
//        Client.main(args);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of set method, of class Client.
     */
//    @Test
//    public void testSet() {
//        System.out.println("set");
//        String user = "";
//        String host = "";
//        int port = 0;
//        Client instance = new Client();
//        instance.set(user, host, port);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of run method, of class Client.
     */
//    @Test
//    public void testRun() throws Exception {
//        System.out.println("run");
//        Client instance = new Client();
//        instance.run();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of loop method, of class Client.
     */
//    @Test
//    public void testLoop() throws Exception {
//        System.out.println("loop");
//        CLFormatter helper = null;
//        BufferedReader reader = null;
//        Client instance = new Client();
//        instance.loop(helper, reader);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
    /**
     * Test of main method with exit, of class Client.
     */
    @Test
    public void testMainExit() throws Exception {
        String[] args = {"username", "localhost", "8888"};
        String input = "exit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes("UTF8"));
        System.setIn(in); // Sets System.in to the supplied stream
        Client.main(args);
    }
    
}
