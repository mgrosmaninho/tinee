
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sep.tinee.net.channel.ClientChannel;
import sep.tinee.net.message.Push;

/**
 *
 * @author portuga
 */
public class PushCommand implements Command {
    private final ClientChannel channel;
    
    public PushCommand(ClientChannel channel) {
        this.channel = channel;
    }
    
    @Override
    public void execute(ClientUI client) {
        try {
            System.out.println("new push");
            channel.send(new Push(client.user, client.draftTag, client.draftLines));
            client.draftLines = new LinkedList<>();
        } catch (IOException ex) {
            Logger.getLogger(PushCommand.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
}
