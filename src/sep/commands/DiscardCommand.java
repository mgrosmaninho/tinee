package sep.commands;

import sep.tinee.client.ClientState;
import sep.tinee.client.ClientUI;

/**
 * Implementation of the 'discard' user command.
 * Discard all drafted tines without pushing them to the server.
 * 
 * @author Manuel Gomes Rosmaninho
 */
public class DiscardCommand implements Command {
    private final ClientUI client;
    private final ClientState state;
    
    /**
     * Constructor for objects of class DiscardCommand.
     * @param client the client UI
     * @param state the client state
     */
    public DiscardCommand(ClientUI client, ClientState state) {
        this.client = client;
        this.state = state;
    }
    
    /**
     * 'Discard' was entered.
     */
    @Override
    public void execute() {
        if(state.getState()!=2) {
            client.printParseMessage();
            return;
        } else if(client.getDraftLines().isEmpty()) {
            System.out.println(client.strings.getString("discard_command_message"));
            return;
        }
        client.createDraftLines();
        state.setState(1);
    }
}
