import java.util.Stack;

public class UndoCommand implements Command {
    private Receiver receiver;
    private Stack<Command> history;

    public UndoCommand(Stack<Command> history) {
        this.history = history;
    }

    public UndoCommand(Receiver receiver, Stack<Command> history) {
        this.receiver = receiver;
        this.history = history;
    }

    @Override
    public boolean isUndoable() {
        return false;
    }

    @Override
    public void execute() throws CustomException {
        if (history == null || history.isEmpty()) {
            System.out.println("Error! History stack cannot be empty or null");
        }
        var commandToUndo = history.pop();
        commandToUndo.undo();
    }

    @Override
    public void undo() {
    }
}