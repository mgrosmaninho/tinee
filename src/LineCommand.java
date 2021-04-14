
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Implementation of the 'line' user command.
 * 
 * @author Manuel Gomes Rosmaninho
 */
public class LineCommand implements Command {
    private ClientUI client;
    private final String[] inputArgs;
    
    /**
     * Constructor for objects of class LineCommand.
     * @param inputArgs 
     */
    public LineCommand(ClientUI client, String[] inputArgs) {
        this.client = client;
        this.inputArgs = inputArgs;
    }
    
    /**
     * 'Line' was entered.
     */
    @Override
    public void execute() {
        String line = Arrays.stream(inputArgs).collect(Collectors.joining());
        client.draftLines.add(line);
    }
}
