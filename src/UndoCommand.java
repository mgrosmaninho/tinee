/**
 * Implementation of the 'undo' user command.
 * 
 * @author Manuel Gomes Rosmaninho
 */
public class UndoCommand implements Command {
    private final ClientUI client;
    
    /**
     * Constructor for objects of class UndoCommand.
     * @param client
     */
    public UndoCommand(ClientUI client) {
        this.client = client;
    }
    /**
     * 'Undo' was entered.
     */
    @Override
    public void execute() {
        if(client.getState()!=2) {
            System.out.println(client.strings.getString("parse_command_message"));
            return;
        } else if(client.draftLines.isEmpty()) {
            System.out.println(client.strings.getString("undo_command_message"));
            return;
        }
        client.draftLines.remove(client.draftLines.size()-1);
    }
}
