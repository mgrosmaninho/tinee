package sep.commands;

import sep.tinee.client.ClientUI;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sep.tinee.client.ClientState;
import sep.tinee.net.channel.ClientChannel;
import sep.tinee.net.message.Push;
import sep.tinee.net.message.ReadReply;
import sep.tinee.net.message.ReadRequest;

/**
 * Implementation of the 'close' user command.
 * Closes the ticket on the server.
 * 
 * @author Manuel Gomes Rosmaninho
 */
public class CloseCommand implements Command {
    private final ClientUI client;
    private final ClientChannel channel;
    private final ClientState state;
    
    /**
     * Constructor for objects of class CloseCommand.
     * @param client the client UI
     * @param channel the communication to the server
     * @param state the client state
     */
    public CloseCommand(ClientUI client, ClientChannel channel, ClientState state) {
        this.client = client;
        this.channel = channel;
        this.state = state;
    }
    
    /**
     * 'Close' was entered.
     * Verify if state is Draft.
     * Verify if the current user is the ticket creator of the [tag], if so
     * pushes ##CLOSE## to the server and sets state to 1 (MAIN) else print a
     * message to the user.
     */
    @Override
    public void execute() {
        if(state.getState()!=2) {
            client.printParseMessage();
            return;
        }
        try {
            channel.send(new ReadRequest(client.getDraftTag()));
            ReadReply reply = (ReadReply) channel.receive();
            String replyUser = reply.users.get(0);
            if(client.getUser().equals(replyUser)) {
                client.createDraftLines();
                client.addDraftLines(Push.CLOSE_LINE);
                channel.send(new Push(client.getUser(), client.getDraftTag(), client.getDraftLines()));
            } else {
                System.out.println(client.strings.getString("close_command_message"));
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ReadCommand.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        state.setState(1);
    }
}
