/**
 * Implementation of the 'exit' user command.
 * 
 * @author Manuel Gomes Rosmaninho
 */
public class ExitCommand implements Command {
    
    /**
     * Constructor for objects of class ExitCommand.
     */
    public ExitCommand() {
        
    }
    
    /**
     * 'Exit' was entered. Prints message.
     */
    @Override
    public void execute() {
        System.out.print("Good bye\n");
    }
}
