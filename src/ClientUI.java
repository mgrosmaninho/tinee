
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import sep.tinee.net.channel.ClientChannel;
import sep.tinee.net.message.Bye;

/**
 *
 * @author portuga
 */
public class ClientUI {
    public final String user;
    public final String host;
    public final int port;
    private final ClientChannel channel;
    private BufferedReader reader;
    private final static String RESOURCE_PATH = "resources/MessageBundle";
    private final ResourceBundle strings;
        
    public String draftTag = null;
    public List<String> draftLines = new LinkedList<>();
        
    public ClientUI(String ur, String ht, int pt) {
        this.user = ur;
        this.host = ht;
        this.port = pt;
        this.channel = new ClientChannel(host, port);
        strings = ResourceBundle.getBundle(RESOURCE_PATH, new Locale("en", "GB"));
    }
        
    void run() throws IOException, ClassNotFoundException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        
        try {
            print(strings.getString("welcome_message"), user);
            System.out.print(strings.getString("main_state_message"));
            
            menu(reader, channel);
            
        } catch(IOException ex) {
            throw new IOException(ex);
            
        } finally {
            reader.close();
            if(channel.isOpen()) {
                channel.send(new Bye());
                channel.close();
            }
        }
    }
    
    void menu(BufferedReader reader, ClientChannel channel)
            throws IOException, ClassNotFoundException {
        
        boolean done = false;
        do {
            String inputLine = reader.readLine();
            Command command;
            
            List<String> split = Arrays.stream(inputLine.trim().split("\\ "))
                    .map(x -> x.trim()).collect(Collectors.toList());
            String cmd = split.remove(0);
            String[] inputArgs = split.toArray(new String[split.size()]);
            
            switch(cmd) {
            case "read":
                command = new ReadCommand(channel, inputArgs);
                command.execute(this);
                System.out.print(strings.getString("main_state_message"));
                break;
            case "manage":
                command = new ManageCommand(inputArgs);
                command.execute(this);
                print(strings.getString("draft_state_message"), draftTag);
                break;
            case "line":
                command = new LineCommand(inputArgs);
                command.execute(this);
                print(strings.getString("draft_state_message"), draftTag);
                break;
            case "push":
                command = new PushCommand(channel);
                command.execute(this);
                System.out.print(strings.getString("main_state_message"));
                break;
            case "exit":
                command = new ExitCommand();
                command.execute(this);
                done = true;
                break;
            default:
                System.out.println(strings.getString("parse_command_message"));
            }
        } while(!done);
    }
    
    public void print(String message, Object... params) {
        System.out.print(MessageFormat.format(message, params));
    }
    
}
