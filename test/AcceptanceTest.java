
import sep.tinee.client.Main;
import java.nio.charset.StandardCharsets;
import java.io.*;
import org.junit.*;
import static org.junit.Assert.*;
import sep.tinee.client.ClientState;
import sep.tinee.server.Server;

/**
 * Acceptance Test
 * 
 * @author Manuel Gomes Rosmaninho
 */
public class AcceptanceTest {

    private ClientState state;
    private Server server;
    private final String[] args = {"username", "localhost", "8888"};
    
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    
    //private ByteArrayInputStream testIn;
    private static ByteArrayOutputStream testOut;
        
    public AcceptanceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        //client = new ClientUI("username", "localhost", 8888);
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
        //client = new ClientUI("username", "localhost", 8888);
        //System.setOut(systemOut);
        this.server = new Server(8888);
        new Thread(() -> this.server.run()).start();
    }
    
    public String asString() {
        return testOut.toString();
    }
    
    @After
    public void tearDown() {
        System.setIn(systemIn);
        System.setOut(systemOut);
        this.server.close();
    }
    
    /**
     * Test of Exit Command in Main State
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testExitCommandInMainState() throws IOException, ClassNotFoundException {
        String input = "exit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        Main.main(args);
    }
    
    /**
     * Test of Exit Command in Draft State
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testExitCommandInDraftState() throws IOException, ClassNotFoundException {
        String input = "manage foo\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        Main.main(args);
    }
    
    /**
     * Test of Read Command
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testReadCommand() throws IOException, ClassNotFoundException {
        String input = "read foo\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        Main.main(args);
    }
    
    /**
     * Test of Read Command without arguments
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testReadCommandWithoutArgs() throws IOException, ClassNotFoundException {
        String input = "read\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        Main.main(args);
    }
    
    /**
     * Test of Read Command out of Main State
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testReadCommandOutMainState() throws IOException, ClassNotFoundException {
        String input = "manage foo\nread foo\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        Main.main(args);
    }
    
    /**
     * Test of Manage Command
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testManageCommand() throws IOException, ClassNotFoundException {
        String input = "manage foo\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        Main.main(args);
    }
    
    /**
     * Test of Manage Command without arguments
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testManageCommandWithoutArgs() throws IOException, ClassNotFoundException {
        String input = "manage\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        Main.main(args);
    }
    
    /**
     * Test of Manage Command out of Main State
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testManageCommandOutMainState() throws IOException, ClassNotFoundException {
        String input = "manage foo\nmanage foo\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        Main.main(args);
    }
    
    /**
     * Test of Line Command
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testLineCommand() throws IOException, ClassNotFoundException {
        String input = "manage foo\nline foo1\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        Main.main(args);
    }
    
    /**
     * Test of Line Command without Arguments
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testLineCommandWithoutArgs() throws IOException, ClassNotFoundException {
        String input = "manage foo\nline\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        Main.main(args);
    }
    
    /**
     * Test of Line Command out of Draft State
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testLineCommandOutDraftState() throws IOException, ClassNotFoundException {
        String input = "line foo1\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        Main.main(args);
    }
    
    /**
     * Test of Line Command with multiple arguments
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testLineCommandMultipleArgs() throws IOException, ClassNotFoundException {
        String input = "manage foo\nline foo1 foo2 foo3\npush\nread foo\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        Main.main(args);
    }
    
    /**
     * Test of Push Command
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testPushCommand() throws IOException, ClassNotFoundException {
        String input = "manage foo\nline foo1\npush\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        Main.main(args);
    }
    
    /**
     * Test of Push Command without line input
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testPushCommandWithoutInput() throws IOException, ClassNotFoundException {
        String input = "manage foo\npush\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        Main.main(args);
    }
    
    /**
     * Test of Push Command out of Draft State
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testPushCommandOutDraftState() throws IOException, ClassNotFoundException {
        String input = "push\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        Main.main(args);
    }
    
    /**
     * Test of Undo Command out of Draft State
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testUndoCommandOutDraftState() throws IOException, ClassNotFoundException {
        String input = "undo\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        Main.main(args);
    }
    
    /**
     * Test of Undo Command with empty list
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testUndoCommandEmpty() throws IOException, ClassNotFoundException {
        String input = "manage foo\nundo\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        Main.main(args);
    }
    
    /**
     * Test of Undo Command with 1 undo
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testUndoCommand1Undo() throws IOException, ClassNotFoundException {
        String input = "manage foo\nline foo1\nundo\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        Main.main(args);
    }
    
    /**
     * Test of Undo Command with 2 undo
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testUndoCommand2Undo() throws IOException, ClassNotFoundException {
        String input = "manage foo\nline foo1\nline foo2\nundo\nundo\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        Main.main(args);
    }
    
    @Test
    public void test() throws IOException, ClassNotFoundException {
        String input = "exit\n";
        String expect = "Good bye";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        Main.main(args);
        boolean actual = asString().contains(expect);
        assertTrue(actual);
    }
    
    /**
     * Test of Main State
     */
    @Test
    public void testMainState() {
        state = ClientState.MAIN;
        state.setState(1);
        int expected = 1;
        int actual = state.getState();
        assertEquals(expected, actual);
    }
    
    /**
     * Test of Draft State
     */
    @Test
    public void testDraftState() {
        state = ClientState.MAIN;
        state.setState(2);
        int expected = 2;
        int actual = state.getState();
        assertEquals(expected, actual);
    }
    
//    @Test
//    public void new() throws IOException, ClassNotFoundException {
//        client.run();
//    }
    

    
        //System.setOut(systemOut);
        //System.out.print(client.getState());
}
