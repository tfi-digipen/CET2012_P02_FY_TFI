package Commands;

import java.util.Stack;
import Receiver.Receiver;
import CustomException.CustomException;

/**
 * UndoCommand implementing Command interface
 */
public class UndoCommand implements Command {
    /**
     * Receiver variable (referenced) shared across same command group
     * Provided by user during instantiating of command
     */
    private Receiver receiver;
    /**
     * History stack of previously executed command
     */
    private Stack<Command> history;

    /**
     * Main constructor for UndoCommand
     * @param history History stack of previously executed command
     */
    public UndoCommand(Stack<Command> history) {
        this.history = history;
    }

    /**
     * Main constructor for UndoCommand
     * @param receiver Receiver to be used across same command group
     * @param history History stack of previously executed command
     */
    public UndoCommand(Receiver receiver, Stack<Command> history) {
        this.receiver = receiver;
        this.history = history;
    }

    /**
     * UndoCommand does not support undo
     * @return false
     */
    @Override
    public boolean isUndoable() {
        return false;
    }

    /**
     * Execute the undo command
     * @throws CustomException through failed validation
     */
    @Override
    public void execute() throws CustomException {
        //Throw CustomException if history is null or empty
        if (history == null || history.isEmpty()) {
            throw new CustomException("Error! History stack cannot be empty or null");
        }
        //Pop previously executed command inside history stack and run undo
        history.pop().undo();
        System.out.println("Undo");
    }

    /**
     * Undo does not support undo command
     * @throws CustomException due to not supported
     */
    @Override
    public void undo() throws CustomException {
        throw new CustomException("Error! Undo cannot be applied to undo");
    }
}