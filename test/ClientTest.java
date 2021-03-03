/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Manuel Gomes Rosmaninho
 */
public class ClientTest {
    
    Client cc;
    private String[] args = {"username", "localhost", "8888"};
    private InputStream stdin = System.in;
    private PrintStream stdout = System.out;
   
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
        System.setIn(stdin);
        System.setOut(stdout);
    }
    
    
    /**
     * Test of main method with read command without arguments
     * of class Client.
     * @throws java.lang.Exception
     */
    @Test
    public void testMainReadWithoutArgs() throws Exception {
        String input = "read\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes("UTF8"));
        System.setIn(in);
        cc.main(args);
    }
    
    /**
     * Test of drafting method with line command without arguments
     * of class Client.
     * @throws java.lang.Exception
     */
    @Test
    public void testDraftLineWithoutArgs() throws Exception {
        String input = "manage foo\nline\npush\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes("UTF8"));
        System.setIn(in);
        cc.main(args);
    }
    
    /**
     * Test of drafting method with line command with
     * mytext argument with two words (space between words)
     * of class Client.
     * @throws java.lang.Exception
     */
    @Test
    public void testDraftLineTwoWords() throws Exception {
        String input = "manage foo\nline word1 word2\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes("UTF8"));
        System.setIn(in);
        cc.main(args);
    }
    
}
