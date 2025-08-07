package Commands;

import CustomException.CustomException;
import Receiver.Receiver;

/**
 * ListCommand implementing Command interface
 */
public class ListCommand implements Command {
    /**
     * Receiver variable (referenced) shared across same command group
     * Provided by user during instantiating of command
     */
    private Receiver receiver;

    /**
     * Main constructor for ListCommand
     * @param receiver Receiver to be used across same command group
     */
    public ListCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    /**
     * DeleteCommand does not support undo
     * @return false
     */
    @Override
    public boolean isUndoable() {
        return false;
    }

    /**
     * Execute the list command
     * @throws CustomException if failed validation
     */
    @Override
    public void execute() throws CustomException {
        if (receiver == null) {
            throw new CustomException("Error! Receiver is null");
        }
        System.out.println("List");
        receiver.list();
    }

    /**
     * List does not support undo command
     * @throws CustomException due to not supported
     */
    @Override
    public void undo() throws CustomException {
        throw new CustomException("Error! No undo for list");
    }
}