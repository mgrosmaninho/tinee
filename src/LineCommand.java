
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 *
 * @author portuga
 */
public class LineCommand implements Command {
    private final String[] inputArgs;
    
    public LineCommand(String[] inputArgs) {
        this.inputArgs = inputArgs;
    }
    
    @Override
    public void execute(ClientUI client) {
        String line = Arrays.stream(inputArgs).collect(Collectors.joining());
        client.draftLines.add(line);
    }
}
