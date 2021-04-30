package sep.commands;

import sep.tinee.client.ClientUI;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sep.tinee.client.ClientState;
import sep.tinee.net.channel.ClientChannel;
import sep.tinee.net.message.ReadReply;
import sep.tinee.net.message.ReadRequest;

/**
 * Implementation of the 'read' user command.
 * Read the current tines for [tag] on the server.
 * 
 * @author Manuel Gomes Rosmaninho
 */
public class ReadCommand implements Command {
    private final ClientUI client;
    private final ClientChannel channel;
    private final String[] inputArgs;
    private final ClientState state;
    
    /**
     * Constructor for objects of class ReadCommand.
     * @param client the client UI
     * @param channel the communication to the server
     * @param inputArgs the command line arguments
     * @param state the client state
     */
    public ReadCommand(ClientUI client, ClientChannel channel, String[] inputArgs, ClientState state) {
        this.client = client;
        this.channel = channel;
        this.inputArgs = inputArgs;
        this.state = state;
    }
    
    /**
     * 'Read' was entered. Sends a read request to server and prints its reply.
     */
    @Override
    public void execute() {
        if(state.getState()!=1) {
            client.printParseMessage();
            return;
        } else if(inputArgs.length < 1) {
            System.out.println(client.strings.getString("read_command_message"));
            return;
        }
        
        try {
            channel.send(new ReadRequest(inputArgs[0]));
            ReadReply reply = (ReadReply) channel.receive();
            System.out.print(printFormatRead(inputArgs[0], reply.users, reply.lines));
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ReadCommand.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Method to print the output format for the Read Command.
     * @param tag inputArgs[0]
     * @param users user from server
     * @param read lines from server
     * @return toString with tines from server
     */
    private String printFormatRead(String tag, List<String> users, List<String> read) {
        StringBuilder str = new StringBuilder("Read: # ");
        str.append(tag);
        Iterator<String> iter = read.iterator();
        for(String user : users) {
            str.append("\n");
            str.append(String.format("UserID: %s", user));
            str.append("    Tinee: ");
            str.append(iter.next());
        }
        str.append("\n");
        return str.toString();
    }
}
