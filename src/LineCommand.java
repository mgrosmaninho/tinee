
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Implementation of the 'line' user command.
 * 
 * @author Manuel Gomes Rosmaninho
 */
public class LineCommand implements Command {
    private final ClientUI client;
    private final String[] inputArgs;
    
    /**
     * Constructor for objects of class LineCommand.
     * @param client
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
        if(client.getState()!=2) {
            System.out.println(client.strings.getString("parse_command_message"));
            return;
        } else if(inputArgs.length < 1) {
            System.out.println(client.strings.getString("line_command_message"));
            return;
        }
        String line = Arrays.stream(inputArgs).collect(Collectors.joining(" "));
        client.draftLines.add(line);
    }
}
