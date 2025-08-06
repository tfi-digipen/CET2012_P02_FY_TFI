package Commands;

import CustomException.CustomException;
import Receiver.Receiver;

public class DeleteCommand implements Command {
    private final Receiver receiver;
    private boolean isProcessed;
    private boolean doneUndo;
    private final String index;
    private int dataStoredPosition;
    private String[] undoData;

    public DeleteCommand(Receiver receiver, String index) {
        this.receiver = receiver;
        this.index = index;
    }

    @Override
    public boolean isUndoable() {
        return true;
    }

    @Override
    public void execute() throws CustomException {
        if (receiver == null) {
            throw new CustomException("Error! Receiver is null");
        }
        if (isProcessed) {
            throw new CustomException("Error! Command has been processed before");
        }
        if (index == null) {
            throw new CustomException("Error! Payload cannot be empty or null");
        }
        try {
            dataStoredPosition = Integer.parseInt(index) - 1;
        } catch (Exception e) {
            throw new CustomException("Error! Invalid input! Index value not valid integer number");
        }
        undoData = receiver.getData(dataStoredPosition);
        receiver.delete(dataStoredPosition);
        isProcessed = true;
        System.out.println("Delete # " + index);
    }

    @Override
    public void undo() throws CustomException {
        if (!isProcessed) {
            throw new CustomException("Error! Command has never been procesed before!");
        }
        if (doneUndo) {
            throw new CustomException("Error! Command has already been undone!");
        }
        receiver.insert(dataStoredPosition, undoData);
        doneUndo = true;
    }
}