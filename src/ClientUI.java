
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import sep.tinee.net.channel.ClientChannel;
import sep.tinee.net.message.Bye;

/**
 * This class is the main class of the 'Client' application.
 * 'Client' is a very simple, text based client-server application. Users
 * can read, manage and add tickets.
 * 
 * To start this application, create an instance of this class and call
 * the 'run' method.
 * 
 * @author Manuel Gomes Rosmaninho
 */
public class ClientUI {
    public final String user;
    public final String host;
    public final int port;
    private final ClientChannel channel;
    private final static String RESOURCE_PATH = "resources/MessageBundle";
    public final ResourceBundle strings;
    private String draftTag;
    public List<String> draftLines = new LinkedList<>();
    
    public static final int DONE = 0;
    public static final int MAIN = 1;
    public static final int DRAFT = 2;
    private int state;
    
    /**
     * Create the client
     * @param user the username
     * @param host the host name
     * @param port the port number
     */
    public ClientUI(String user, String host, int port) {
        this.user = user;
        this.host = host;
        this.port = port;
        this.channel = new ClientChannel(host, port);
        this.state = MAIN;
        strings = ResourceBundle.getBundle(RESOURCE_PATH, new Locale("en", "GB"));
    }
    
    /**
     * Main run routine.
     * @throws IOException IOException
     * @throws ClassNotFoundException ClassNotFoundException
     */
    public void run() throws IOException, ClassNotFoundException {
        BufferedReader reader;
        reader = new BufferedReader(new InputStreamReader(System.in));
        
        try {
            print(strings.getString("welcome_message"), user);
            menu(reader, channel);
            
        } catch(IOException e) {
            throw new IOException(e);
            
        } finally {
            reader.close();
            if(channel.isOpen()) {
                channel.send(new Bye());
                channel.close();
            }
        }
    }
    
    /**
     * Reads the user input commands. Loops until end of client.
     * @param reader the BufferedReader
     * @param channel the communication to the server
     * @throws IOException IOException
     */
    private void menu(BufferedReader reader, ClientChannel channel)
            throws IOException {
        
        while(DONE!=state) {
            if(state==1) {
                System.out.print(strings.getString("main_state_message"));
            } else if(state==2) {
                print(strings.getString("draft_state_message")
                        , printFormatDrafting(draftTag, draftLines));
            }
            
            String inputLine = reader.readLine();
            Command command;
            
            if("".equals(inputLine)) {
                System.out.println(strings.getString("parse_command_message"));
                continue;
            }
            
            List<String> split = Arrays.stream(inputLine.trim().split("\\ "))
                    .map(String::trim).collect(Collectors.toList());
            String cmd = split.remove(0);
            String[] inputArgs = split.stream().toArray(String[]::new);

            switch (cmd) {
                case "read":
                    command = new ReadCommand(this, channel, inputArgs);
                    command.execute();
                    break;
                case "manage":
                    command = new ManageCommand(this, channel, inputArgs);
                    command.execute();
                    break;
                case "line":
                    command = new LineCommand(this, inputArgs);
                    command.execute();
                    break;
                case "undo":
                    command = new UndoCommand(this);
                    command.execute();
                    break;
                case "push":
                    command = new PushCommand(this, channel);
                    command.execute();
                    break;
                case "close":
                    command = new CloseCommand(this, channel);
                    command.execute();
                    break;
                case "exit":
                    command = new ExitCommand(this);
                    command.execute();
                    break;
                default:
                    System.out.println(strings.getString("parse_command_message"));
                    break;
            }
        }
    }
    
    /**
     * Set DraftTag.
     * @param inputArgs String to draftTag
     */
    public void setDraftTag(String inputArgs) {
        draftTag = inputArgs;
    }
    
    /**
     * Return the draftTag.
     * @return draftTag
     */
    public String getDraftTag() {
        return draftTag;
    }
    
    /**
     * Add String to draftLines.
     * @param line String to draftLines
     */
    public void addDraftLines(String line) {
        draftLines.add(line);
    }
    
    /**
     * Return the draftLines.
     * @return draftLines
     */
    public List<String> getDraftLines() {
        return Collections.unmodifiableList(draftLines);
    }
    
    /**
     * Sets the current state.
     * @param state the current state
     */
    public void setState(int state) {
        this.state = state;
    }
    
    /**
     * Return the current state.
     * @return the current state
     */
    public int getState() {
        return state;
    }
    
    /**
     * 
     * @param tag draftTags
     * @param lines draftLines
     * @return toString for Drafting State
     */
    public String printFormatDrafting(String tag, List<String> lines) {
        StringBuilder b = new StringBuilder("# ");
        b.append(tag);
        int i = 1;
        for (String x:lines) {
            b.append("\n");
            b.append(String.format("%12d", i++));
            b.append("  ");
            b.append(x);
        }
        return b.toString();
    }
    
    /**
     * Print out the messages for the user.
     * @param message the message itself
     * @param params the parameters in the message
     */
    public void print(String message, Object... params) {
        System.out.print(MessageFormat.format(message, params));
    }
}
