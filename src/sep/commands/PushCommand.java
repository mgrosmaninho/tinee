package sep.commands;

import sep.tinee.client.ClientUI;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sep.tinee.client.ClientState;
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
    private final ClientState state;
    
    /**
     * Constructor for objects of class PushCommand.
     * @param client the client UI
     * @param channel the communication to the server
     * @param state the client state
     */
    public PushCommand(ClientUI client, ClientChannel channel, ClientState state) {
        this.client = client;
        this.channel = channel;
        this.state = state;
    }
    
    /**
     * 'Push' was entered.
     */
    @Override
    public void execute() {
        if(state.getState()!=2) {
            client.printParseMessage();
            return;
        } else if(client.getDraftLines().isEmpty()) {
            System.out.println(client.strings.getString("push_command_message"));
            return;
        }
        try {
            channel.send(new Push(client.user, client.getDraftTag(), client.getDraftLines()));
            //client.draftLines = new LinkedList<>();
            client.createDraftLines();
        } catch (IOException ex) {
            Logger.getLogger(PushCommand.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        state.setState(1);
    }
}
