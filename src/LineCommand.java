
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 *
 * @author portuga
 */
public class LineCommand implements Command {
    String[] inputArgs;
    
    public LineCommand(String[] inputArgs) {
        this.inputArgs = inputArgs;
    }
    
    @Override
    public void execute(ClientUI client) {
        System.out.println("new line");
        String line = Arrays.stream(inputArgs).collect(Collectors.joining());
        client.draftLines.add(line);
    }
}
