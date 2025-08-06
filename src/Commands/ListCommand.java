package Commands;

import CustomException.CustomException;
import Receiver.Receiver;

public class ListCommand implements Command {
    private Receiver receiver;

    public ListCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public boolean isUndoable() {
        return false;
    }

    @Override
    public void execute() throws CustomException {
        if (receiver == null) {
            throw new CustomException("Error! Receiver is null");
        }
        System.out.println("List");
        receiver.list();
    }

    @Override
    public void undo() throws CustomException {
        throw new CustomException("Error! No undo for list");
    }
}