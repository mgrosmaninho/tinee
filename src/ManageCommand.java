
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sep.tinee.net.channel.ClientChannel;
import sep.tinee.net.message.Push;
import sep.tinee.net.message.ReadReply;
import sep.tinee.net.message.ReadRequest;

/**
 * Implementation of the 'manage' user command.
 * 
 * @author Manuel Gomes Rosmaninho
 */
public class ManageCommand implements Command {
    private final ClientUI client;
    private final ClientChannel channel;
    private final String[] inputArgs;
    
    /**
     * Constructor for objects of class ManageCommand.
     * @param client the client UI
     * @param channel the communication to the server
     * @param inputArgs the command line arguments
     */
    public ManageCommand(ClientUI client, ClientChannel channel, String[] inputArgs) {
        this.client = client;
        this.channel = channel;
        this.inputArgs = inputArgs;
    }
     /**
      * 'Manage' was entered.
      */
    @Override
    public void execute() {
        if(client.getState()!=1) {
            System.out.println(client.strings.getString("parse_command_message"));
            return;
        } else if(inputArgs.length < 1) {
            System.out.println(client.strings.getString("manage_command_message"));
            return;
        }
        try {
            client.setDraftTag(inputArgs[0]);
            channel.send(new ReadRequest(client.getDraftTag()));
            ReadReply reply = (ReadReply) channel.receive();
            if(!reply.lines.isEmpty()) {
                String replyLine = reply.lines.get(reply.lines.size() - 1);
                if(Push.CLOSE_LINE.equals(replyLine)) {
                    System.out.println(client.strings.getString("manage_close_command_message"));
                    return;
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ReadCommand.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        client.setState(2);
    }
}
