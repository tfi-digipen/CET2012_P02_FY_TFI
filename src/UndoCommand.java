import java.util.Stack;

public class UndoCommand implements Command {
    private Receiver receiver;
    private Stack<Command> history;

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
        var commandToUndo = history.pop();
        commandToUndo.undo();
    }

    @Override
    public void undo() {
    }
}