
import java.io.IOException;

/**
 * This main class creates and initialises ClientUI class.
 * 
 * @author Manuel Gomes Rosmaninho
 */
public class Main {
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String user = args[0];
        String host = args[1];
        int port = Integer.parseInt(args[2]);
        ClientUI client = new ClientUI(user, host, port);
        client.run();
    }
}
