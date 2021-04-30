package sep.tinee.client;

import sep.commands.*;
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
    
    // private???
    public final String user;
    //public final String host;
    //public final int port;
    
    //good
    private final ClientChannel channel;
    private String draftTag = null;
    private List<String> draftLines = new LinkedList<>();
    private final ClientState state;
    private final static String RESOURCE_PATH = "sep.resources/MessageBundle";
    public final ResourceBundle strings;
        
    /**
     * Create the client
     * @param user the username
     * @param host the host name
     * @param port the port number
     */
    public ClientUI(String user, String host, int port) {
        this.user = user;
        //this.host = host;
        //this.port = port;
        this.channel = new ClientChannel(host, port);
        this.state = ClientState.MAIN;
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
        
        while(0!=state.getState()) {
            if(state.getState()==1) {
                System.out.print(strings.getString("main_state_message"));
            } else if(state.getState()==2) {
                print(strings.getString("draft_state_message")
                        , printFormatDrafting(draftTag, draftLines));
            }
            
            Command command;
            String inputLine = reader.readLine();
            if("".equals(inputLine)) {
                printParseMessage();
                continue;
            }
            
            List<String> split = Arrays.stream(inputLine.trim().split("\\ "))
                    .map(String::trim).collect(Collectors.toList());
            String cmd = split.remove(0);
            String[] inputArgs = split.stream().toArray(String[]::new);

            switch (cmd) {
                case "read":
                    command = new ReadCommand(this, channel, inputArgs, state);
                    command.execute();
                    break;
                case "show":
                    command = new ShowCommand(this, channel, state);
                    command.execute();
                    break;
                case "manage":
                    command = new ManageCommand(this, channel, inputArgs, state);
                    command.execute();
                    break;
                case "line":
                    command = new LineCommand(this, inputArgs, state);
                    command.execute();
                    break;
                case "discard":
                    command = new DiscardCommand(this, state);
                    command.execute();
                    break;
                case "undo":
                    command = new UndoCommand(this, state);
                    command.execute();
                    break;
                case "push":
                    command = new PushCommand(this, channel, state);
                    command.execute();
                    break;
                case "close":
                    command = new CloseCommand(this, channel, state);
                    command.execute();
                    break;
                case "exit":
                    command = new ExitCommand(this, state);
                    command.execute();
                    break;
                default:
                    printParseMessage();
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
     * Create a new draftLines for user line inputs
     */
    public void createDraftLines() {
        draftLines = new LinkedList<>();
    }
    
    /**
     * Add String to draftLines.
     * @param line String to draftLines
     */
    public void addDraftLines(String line) {
        draftLines.add(line);
    }
    
    /**
     * Remove last line of draftLines (Undo Command)
     * @param lastLine integer containing last line of the draftLines
     */
    public void removeDraftLines(int lastLine) {
        draftLines.remove(lastLine);
    }
    
    /**
     * Return the draftLines.
     * @return draftLines
     */
    public List<String> getDraftLines() {
        return Collections.unmodifiableList(draftLines);
    }
    
    /**
     * 
     * @param tag draftTags
     * @param lines draftLines
     * @return toString for Drafting State
     */
    public String printFormatDrafting(String tag, List<String> lines) {
        StringBuilder str = new StringBuilder("# ");
        str.append(tag);
        int i = 1;
        for (String x:lines) {
            str.append("\n");
            str.append(String.format("%12d", i++));
            str.append("  ");
            str.append(x);
        }
        return str.toString();
    }
    
    /**
     * Print out the messages for the user.
     * @param message the message itself
     * @param params the parameters in the message
     */
    public void print(String message, Object... params) {
        System.out.print(MessageFormat.format(message, params));
    }
    
    /**
     * Print parse command message to the user.
     */
    public void printParseMessage() {
        System.out.println(strings.getString("parse_command_message"));
    }
}
