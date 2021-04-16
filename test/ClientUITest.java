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
    private final InputStream stdin = System.in;
    private final PrintStream stdout = System.out;
    
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
     * Test of Exit Command in Main State
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testExitCommandInMainState() throws IOException, ClassNotFoundException {
        String input = "exit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes("UTF8"));
        System.setIn(in);
        main.main(args);
    }
    
    /**
     * Test of Exit Command in Draft State
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testExitCommandInDraftState() throws IOException, ClassNotFoundException {
        String input = "manage foo\nexit\n";
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
     * Test of Read Command without arguments
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testReadCommandWithoutArgs() throws IOException, ClassNotFoundException {
        String input = "read\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes("UTF8"));
        System.setIn(in);
        main.main(args);
    }
    
    /**
     * Test of Read Command out of Main State
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testReadCommandOutMainState() throws IOException, ClassNotFoundException {
        String input = "manage foo\nread foo\nexit\n";
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
     * Test of Manage Command without arguments
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testManageCommandWithoutArgs() throws IOException, ClassNotFoundException {
        String input = "manage\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes("UTF8"));
        System.setIn(in);
        main.main(args);
    }
    
    /**
     * Test of Manage Command out of Main State
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testManageCommandOutMainState() throws IOException, ClassNotFoundException {
        String input = "manage foo\nmanage foo\nexit\n";
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
     * Test of Line Command without Arguments
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testLineCommandWithoutArgs() throws IOException, ClassNotFoundException {
        String input = "manage foo\nline\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes("UTF8"));
        System.setIn(in);
        main.main(args);
    }
    
    /**
     * Test of Line Command out of Draft State
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testLineCommandOutDraftState() throws IOException, ClassNotFoundException {
        String input = "line foo1\nexit\n";
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
    
    /**
     * Test of Push Command without line input
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testPushCommandWithoutInput() throws IOException, ClassNotFoundException {
        String input = "manage foo\npush\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes("UTF8"));
        System.setIn(in);
        main.main(args);
    }
    
    /**
     * Test of Push Command out of Draft State
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testPushCommandOutDraftState() throws IOException, ClassNotFoundException {
        String input = "push\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes("UTF8"));
        System.setIn(in);
        main.main(args);
    }
    
}
