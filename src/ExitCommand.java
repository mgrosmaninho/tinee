
/**
 *
 * @author portuga
 */
public class ExitCommand implements Command {
        
    public ExitCommand() {

    }
    
    @Override
    public void execute(ClientUI client) {
        System.out.print("Good bye\n");
    }
}
