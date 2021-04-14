/**
 * This class is an interface for all command classes.
 * Each user command is implemented by a specific command subclass.
 * 
 * @author Manuel Gomes Rosmaninho
 */
public interface Command {

    /**
     * Execute this command
     */
    public void execute();
}