
import java.util.List;

/**
 *
 * @author portuga
 */
public class ManageCommand implements Command {
    private final String[] inputArgs;
    
    public ManageCommand(String[] inputArgs) {
        this.inputArgs = inputArgs;
    }
    
    @Override
    public void execute(ClientUI client) {
        client.draftTag = inputArgs[0];
        formatDrafting(client.draftTag, client.draftLines);
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
