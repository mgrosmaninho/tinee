
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
    private ClientUI client;
    private final ClientChannel channel;
    
    /**
     * Constructor for objects of class PushCommand.
     * @param client
     * @param channel 
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
        try {
            channel.send(new Push(client.user, client.draftTag, client.draftLines));
            client.draftLines = new LinkedList<>();
        } catch (IOException ex) {
            Logger.getLogger(PushCommand.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
}
