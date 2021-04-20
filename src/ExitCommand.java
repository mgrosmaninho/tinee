/**
 * Implementation of the 'exit' user command.
 * 
 * @author Manuel Gomes Rosmaninho
 */
public class ExitCommand implements Command {
    private final ClientUI client;
    
    /**
     * Constructor for objects of class ExitCommand.
     * @param client the client UI
     */
    public ExitCommand(ClientUI client) {
        this.client = client;
    }
    
    /**
     * 'Exit' was entered. Prints message.
     */
    @Override
    public void execute() {
        System.out.print(client.strings.getString("goodbye_message"));
        client.setState(0);
    }
}
