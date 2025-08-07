package Commands;

import CustomException.CustomException;
import Receiver.*;

/**
 * DeleteCommand implementing Command interface
 */
public class DeleteCommand implements Command {
    /**
     * Receiver variable (referenced) shared across same command group
     * Provided by user during instantiating of command
     */
    private final Receiver receiver;
    /**
     * Check whether command is executed before
     */
    private boolean isExecuted;
    /**
     * Check whether command did undo before
     */
    private boolean doneUndo;
    /**
     * Index to delete the data
     */
    private final String index;
    /**
     * Original data before deletion
     */
    private Receiver.BasicEmployeeInfo originalData;

    /**
     * Main constructor for DeleteCommand
     * @param receiver Receiver to be used across same command group
     * @param index Index to delete the data
     */
    public DeleteCommand(Receiver receiver, String index) {
        this.receiver = receiver;
        this.index = index;
    }

    /**
     * DeleteCommand support undo operation
     * @return true
     */
    @Override
    public boolean isUndoable() {
        return true;
    }

    /**
     * Execute the delete command
     * @throws CustomException if failed validation
     */
    @Override
    public void execute() throws CustomException {
        if (receiver == null) {
            throw new CustomException("Error! Receiver is null");
        }
        if (isExecuted) {
            throw new CustomException("Error! Command has been processed before");
        }
        if (index == null) {
            throw new CustomException("Error! Payload cannot be empty or null");
        }
        int dataStoredPosition = -1;
        try {
            dataStoredPosition = Integer.parseInt(index) - 1;
        } catch (Exception e) {
            throw new CustomException("Error! Invalid input! Index value not valid integer number");
        }
        //get original data inside list before deletion
        originalData = receiver.getDataByIndex(dataStoredPosition);
        //delete by index position
        receiver.deleteByIndex(dataStoredPosition);
        isExecuted = true;
        System.out.println("Delete # " + index);
    }

    /**
     * Execute the undo operation for the Delete command
     * @throws CustomException if failed validation
     */
    @Override
    public void undo() throws CustomException {
        if (!isExecuted) {
            throw new CustomException("Error! Command has never been processed before!");
        }
        if (doneUndo) {
            throw new CustomException("Error! Command has already been undone!");
        }
        //undo data by inserting original data to its list, compare ID to determine insertion position
        receiver.insert(originalData);
        doneUndo = true;
    }
}