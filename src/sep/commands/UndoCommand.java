package sep.commands;

import sep.tinee.client.ClientState;
import sep.tinee.client.ClientUI;

/**
 * Implementation of the 'undo' user command.
 * 
 * @author Manuel Gomes Rosmaninho
 */
public class UndoCommand implements Command {
    private final ClientUI client;
    private final ClientState state;
    
    /**
     * Constructor for objects of class UndoCommand.
     * @param client ClientUI
     * @param state the client state
     */
    public UndoCommand(ClientUI client, ClientState state) {
        this.client = client;
        this.state = state;
    }
    /**
     * 'Undo' was entered.
     */
    @Override
    public void execute() {
        if(state.getState()!=2) {
            client.printParseMessage();
            return;
        } else if(client.getDraftLines().isEmpty()) {
            System.out.println(client.strings.getString("undo_command_message"));
            return;
        }
        int lastLine = client.getDraftLines().size()-1;
        client.removeDraftLines(lastLine);
    }
}
