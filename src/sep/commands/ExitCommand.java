package sep.commands;

import sep.tinee.client.ClientState;
import sep.tinee.client.ClientUI;

/**
 * Implementation of the 'exit' user command.
 * Terminate the application.
 * 
 * @author Manuel Gomes Rosmaninho
 */
public class ExitCommand implements Command {
    private final ClientUI client;
    private final ClientState state;
    
    /**
     * Constructor for objects of class ExitCommand.
     * @param client the client UI
     * @param state the client state
     */
    public ExitCommand(ClientUI client, ClientState state) {
        this.client = client;
        this.state = state;
    }
    
    /**
     * 'Exit' was entered. Prints message.
     */
    @Override
    public void execute() {
        System.out.print(client.strings.getString("goodbye_message"));
        state.setState(0);
    }
}
