
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sep.tinee.net.channel.ClientChannel;
import sep.tinee.net.message.Push;

/**
 * Implementation of the 'push' user command.
 * 
 * @author Manuel Gomes Rosmaninho
 */
public class PushCommand implements Command {
    private final ClientUI client;
    private final ClientChannel channel;
    
    /**
     * Constructor for objects of class PushCommand.
     * @param client the client UI
     * @param channel the communication to the server
     */
    public PushCommand(ClientUI client, ClientChannel channel) {
        this.client = client;
        this.channel = channel;
    }
    
    /**
     * 'Push' was entered.
     */
    @Override
    public void execute() {
        if(client.getState()!=2) {
            System.out.println(client.strings.getString("parse_command_message"));
            return;
        } else if(client.draftLines.isEmpty()) {
            System.out.println(client.strings.getString("push_command_message"));
            return;
        }
        try {
            channel.send(new Push(client.user, client.draftTag, client.draftLines));
            client.draftLines = new LinkedList<>();
        } catch (IOException ex) {
            Logger.getLogger(PushCommand.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        client.setState(1);
    }
}
