package sep.commands;

import sep.tinee.client.ClientState;
import sep.tinee.client.ClientUI;

/**
 * Implementation of the 'line' user command.
 * Add a line of text to the current draft.
 * 
 * @author Manuel Gomes Rosmaninho
 */
public class LineCommand implements Command {
    private final ClientUI client;
    private final String[] inputArgs;
    private final ClientState state;
    
    /**
     * Constructor for objects of class LineCommand.
     * @param client the client UI
     * @param inputArgs the command line arguments
     * @param state the client state
     */
    public LineCommand(ClientUI client, String[] inputArgs, ClientState state) {
        this.client = client;
        this.inputArgs = inputArgs;
        this.state = state;
    }
    
    /**
     * 'Line' was entered.
     */
    @Override
    public void execute() {
        if(state.getState()!=2) {
            client.printParseMessage();
            return;
        } else if(inputArgs.length < 1) {
            System.out.println(client.strings.getString("line_command_message"));
            return;
        }
        String line = String.join(" ", inputArgs);
        client.addDraftLines(line);
    }
}
