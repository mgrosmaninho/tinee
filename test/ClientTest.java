/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
    
    Main main;
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
     * Test of Read Command
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testReadCommand() throws IOException, ClassNotFoundException {
        String input = "read foo\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes("UTF8"));
        System.setIn(in);
        main.main(args);
    }
    
    /**
     * Test of manage command without arguments
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
//    @Test
//    public void testMainManageWithoutArgs() throws IOException, ClassNotFoundException {
//        String input = "manage\nexit\n";
//        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes("UTF8"));
//        System.setIn(in);
//        main.main(args);
//    }
    
    /**
     * Test of line command without arguments
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
//    @Test
//    public void testDraftLineWithoutArgs() throws IOException, ClassNotFoundException {
//        String input = "manage foo\nline\nexit\n";
//        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes("UTF8"));
//        System.setIn(in);
//        main.main(args);
//    }
    
    /**
     * Test of drafting method with line command with
     * mytext argument with two words (space between words)
     * of class Client.
     * @throws java.lang.Exception
     */
//    @Test
//    public void testDraftLineTwoWords() throws Exception {
//        String input = "manage foo\nline word1 word2\nexit\n";
//        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes("UTF8"));
//        System.setIn(in);
//        main.main(args);
//    }
    
}
