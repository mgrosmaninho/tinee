
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sep.tinee.net.channel.ClientChannel;
import sep.tinee.net.message.ReadReply;
import sep.tinee.net.message.ReadRequest;

/**
 *
 * @author portuga
 */
public class ReadCommand implements Command {
    private final ClientChannel channel;
    String[] inputArgs;
    
    public ReadCommand(ClientChannel channel, String[] inputArgs) {
        this.channel = channel;
        this.inputArgs = inputArgs;
    }
    
    @Override
    public void execute(ClientUI client) {
        try {
            System.out.println("new read");
            channel.send(new ReadRequest(inputArgs[0]));
            ReadReply reply = (ReadReply) channel.receive();
            System.out.print(formatRead(inputArgs[0], reply.users, reply.lines));
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ReadCommand.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    
    String formatRead(String tag, List<String> users, List<String> read) {
        StringBuilder b = new StringBuilder("Read: # ");
        b.append(tag);
        Iterator<String> it = read.iterator();
        for(String user:users) {
            b.append("\n");
            b.append(String.format("%12s", user));
            b.append("  ");
            b.append(it.next());
        }
        b.append("\n");
        return b.toString();
    }
}
