/**
 *
 * @author portuga
 */
public class ExitCommand implements Command {
    
    /**
     * Constructor for objects of class ExitCommand
     */
    public ExitCommand () {
        
    }
    
    @Override
    public void execute(ClientUI client) {
        System.out.println("new exit");
        System.exit(0);
    }
}
