package sep.commands;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sep.tinee.client.ClientState;
import sep.tinee.client.ClientUI;
import sep.tinee.net.channel.ClientChannel;
import sep.tinee.net.message.ShowReply;
import sep.tinee.net.message.ShowRequest;

/**
 * Implementation of the 'show' user command.
 * Get the set of ticket tags and creators (the initial authors) from the server.
 * 
 * @author Manuel Gomes Rosmaninho
 */
public class ShowCommand implements Command {
    private final ClientUI client;
    private final ClientChannel channel;
    private final ClientState state;
    
    /**
     * Constructor for objects of class ShowCommand.
     * @param client the client UI
     * @param channel the communication to the server
     * @param state the client state
     */
    public ShowCommand(ClientUI client, ClientChannel channel, ClientState state) {
        this.client = client;
        this.channel = channel;
        this.state = state;
    }
    
    /**
     * 'Show' was entered. Sends a show request to server and prints its reply.
     */
    @Override
    public void execute() {
        if(state.getState()!=1) {
            client.printParseMessage();
            return;
        }
        try {
            channel.send(new ShowRequest());
            ShowReply reply = (ShowReply) channel.receive();
            System.out.print(reply.toString());
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ReadCommand.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    
//    String printFormatShow(LinkedHashMap<String, String> tags) {
//        
//        return str.toString();
//    }
}
