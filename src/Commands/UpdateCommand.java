package Commands;

import CustomException.CustomException;
import MasterFunction.MasterFunction;
import Receiver.*;

/**
 * UpdateCommand implemeting Command interface
 */
public class UpdateCommand implements Command {
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
     * Storage to store data input from user
     */
    private final String data;
    /**
     * Original data before update
     */
    private Receiver.BasicEmployeeInfo originalData;

    /**
     * Main constructor for UpdateCommand
     * @param receiver Receiver to be used across same command group
     * @param data Data to be processed
     */
    public UpdateCommand(Receiver receiver, String data) {
        this.receiver = receiver;
        this.data = data;
    }

    /**
     * UpdateCommand support undo operation
     * @return true
     */
    @Override
    public boolean isUndoable() {
        return true;
    }

    /**
     * Execute the update command
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
        if (data == null) {
            throw new CustomException("Error! Payload cannot be empty or null");
        }
        String[] splitData = data.split(" ");
        if (splitData.length < 2)
            throw new CustomException("Update command need at least 2 args");
        if (splitData.length > 4)
            throw new CustomException("Update command at most 4 args");
        String index = splitData[0];
        int dataStoredPosition = -1;
        try {
            dataStoredPosition = Integer.parseInt(index) - 1;
        } catch (NumberFormatException | NullPointerException e) {
            throw new CustomException("Error! Invalid input! Index value not valid integer number");
        }
        //get original data inside list before update
        originalData = receiver.getDataByIndex(dataStoredPosition);
        String data1 = splitData[1];
        if (splitData.length > 2) {
            String data2 = splitData[2];
            if (splitData.length > 3) {
                String data3 = splitData[3];
                if (!MasterFunction.checkIsValidEmailOrData3(data3)) {
                    throw new CustomException("Error! Invalid input! Email address is not valid");
                }
                receiver.updateById(originalData.id, data1, data2, data3);
            } else {
                receiver.updateById(originalData.id, data1, data2);
            }
        } else {
            receiver.updateById(originalData.id, data1);
        }
        isExecuted = true;
        System.out.println("Update # " + data);
    }

    /**
     * Execute the undo operation for Update command
     * @throws CustomException if failed validation
     */
    @Override
    public void undo() throws CustomException {
        if (!isExecuted) {
            throw new CustomException("Error! Command has never been procesed before!");
        }
        if (doneUndo) {
            throw new CustomException("Error! Command has already been undone!");
        }
        //Update original data through unique ID
        receiver.updateById(originalData.id, originalData.data1, originalData.data2, originalData.data3);
        doneUndo = true;
    }
}