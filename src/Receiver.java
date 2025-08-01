import java.util.ArrayList;
import java.util.Stack;

public class Receiver {
    public AddCommand addCommand;
    public DeleteCommand deleteCommand;
    public UpdateCommand updateCommand;
    public ListCommand listCommand;
    public UndoCommand undoCommand;

    public Stack<Command> commandStack;
    public ArrayList<String> dataStore;

    public Receiver() {

    }
}
