/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.ByteArrayInputStream;
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
public class ClientUITest {
    
    private Main main;
    private final String[] args = {"username", "localhost", "8888"};
    private InputStream stdin = System.in;
    private PrintStream stdout = System.out;
    
    public ClientUITest() {
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
     * Test of Exit Command
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testExitCommand() throws IOException, ClassNotFoundException {
        String input = "exit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes("UTF8"));
        System.setIn(in);
        main.main(args);
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
     * Test of Manage Command
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testManageCommand() throws IOException, ClassNotFoundException {
        String input = "manage foo\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes("UTF8"));
        System.setIn(in);
        main.main(args);
    }
    
    /**
     * Test of Line Command
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testLineCommand() throws IOException, ClassNotFoundException {
        String input = "manage foo\nline foo1\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes("UTF8"));
        System.setIn(in);
        main.main(args);
    }
    
    /**
     * Test of Push Command
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testPushCommand() throws IOException, ClassNotFoundException {
        String input = "manage foo\nline foo1\npush\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes("UTF8"));
        System.setIn(in);
        main.main(args);
    }
    
}
