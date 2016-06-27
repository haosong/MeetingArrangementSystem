package src;

/**
 * Created by ddz on 16/6/25.
 */
public class ConsoleLogFactory extends LogFactory {
    @Override
    public Log Create() {
        return new ConsoleLog();
    }
}
