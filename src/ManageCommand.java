
import java.util.List;

/**
 * Implementation of the 'manage' user command.
 * 
 * @author Manuel Gomes Rosmaninho
 */
public class ManageCommand implements Command {
    private ClientUI client;
    private final String[] inputArgs;
    
    /**
     * Constructor for objects of class ManageCommand.
     * @param client
     * @param inputArgs 
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
        client.draftTag = inputArgs[0];
        //formatDrafting(client.draftTag, client.draftLines);
    }
    
    String formatDrafting(String tag, List<String> lines) {
        StringBuilder b = new StringBuilder("#");
        b.append(tag);
        int i = 1;
        for (String x:lines) {
            b.append("\n");
            b.append(String.format("%12d", i++));
            b.append("  ");
            b.append(x);
        }
        return b.toString();
    }
}
