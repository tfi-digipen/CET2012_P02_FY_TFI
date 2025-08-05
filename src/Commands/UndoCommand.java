package Commands;

import java.util.Stack;
import Receiver.Receiver;
import CustomException.CustomException;

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
            throw new CustomException("Error! History stack cannot be empty or null");
        }
        Command commandToUndo = history.pop();
        commandToUndo.undo();
        System.out.println("Undo");
    }

    @Override
    public void undo() {
    }
}