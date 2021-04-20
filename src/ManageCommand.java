/**
 * Implementation of the 'manage' user command.
 * 
 * @author Manuel Gomes Rosmaninho
 */
public class ManageCommand implements Command {
    private final ClientUI client;
    private final String[] inputArgs;
    
    /**
     * Constructor for objects of class ManageCommand.
     * @param client the client UI
     * @param inputArgs the command line arguments
     */
    public ManageCommand(ClientUI client, String[] inputArgs) {
        this.client = client;
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
        client.draftTag = inputArgs[0];
        client.setState(2);
    }
}
