
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sep.tinee.net.channel.ClientChannel;
import sep.tinee.net.message.Push;
import sep.tinee.net.message.ReadReply;
import sep.tinee.net.message.ReadRequest;

/**
 * Implementation of the 'close' user command.
 * 
 * @author Manuel Gomes Rosmaninho
 */
public class CloseCommand implements Command {
    private final ClientUI client;
    private final ClientChannel channel;
    
    /**
     * Constructor for objects of class CloseCommand.
     * @param client the client UI
     * @param channel the communication to the server
     */
    public CloseCommand(ClientUI client, ClientChannel channel) {
        this.client = client;
        this.channel = channel;
    }
    
    /**
     * 'Close' was entered.
     */
    @Override
    public void execute() {
        if(client.getState()!=2) {
            System.out.println(client.strings.getString("parse_command_message"));
            return;
        }
        try {
            channel.send(new ReadRequest(client.draftTag));
            ReadReply reply = (ReadReply) channel.receive();
            String replyUser = reply.users.get(0);
            if(client.user.equals(replyUser)) {
                client.draftLines.add(Push.CLOSE_LINE);
                channel.send(new Push(client.user, client.draftTag, client.draftLines));
            } else {
                System.out.println(client.strings.getString("close_command_message"));
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ReadCommand.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        client.setState(1);
    }
}
