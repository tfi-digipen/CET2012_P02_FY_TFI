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
     * Original data
     */
    private Receiver.BasicEmployeeInfo originalData;

    /**
     * Main constructor for UpdateCommand
     *
     * @param receiver Receiver to be used across same command group
     * @param data     Data to be processed
     */
    public UpdateCommand(Receiver receiver, String data) {
        this.receiver = receiver;
        this.data = data;
    }

    /**
     * UpdateCommand support undo operation
     *
     * @return true
     */
    @Override
    public boolean isUndoable() {
        return true;
    }

    /**
     * Execute the update command
     *
     * @throws CustomException if failed validation
     */
    @Override
    public void execute() throws CustomException {
        if (receiver == null) {
            throw new CustomException("Error in Update Command! Receiver is null");
        }
        if (isExecuted) {
            throw new CustomException("Error in Update Command! Command executed before");
        }
        if (data == null) {
            throw new CustomException("Error in Update Command! Payload cannot be empty or null");
        }
        String[] splitData = data.split(" ");
        if (splitData.length < 2)
            throw new CustomException("Error in Update Command! Payload need at least 2 args");
        if (splitData.length > 4)
            throw new CustomException("Error in Update Command! Payload at most 4 args");
        String index = splitData[0];
        int dataStoredPosition = -1;
        try {
            dataStoredPosition = Integer.parseInt(index) - 1;
        } catch (NumberFormatException | NullPointerException e) {
            throw new CustomException("Error in Update Command! Index value not valid integer number");
        }
        //get original data inside list before update
        originalData = receiver.getDataByIndex(dataStoredPosition);
        String updatedData1 = splitData[1];
        if (splitData.length > 2) {
            String updatedData2 = splitData[2];
            if (splitData.length > 3) {
                String updatedData3 = splitData[3];
                if (!MasterFunction.checkIsValidEmailOrData3(updatedData3)) {
                    throw new CustomException("Error in Update Command! Invalid data3 or email input");
                }
                receiver.updateById(originalData.id, updatedData1, updatedData2, updatedData3);
            } else {
                receiver.updateById(originalData.id, updatedData1, updatedData2);
            }
        } else {
            receiver.updateById(originalData.id, updatedData1);
        }
        isExecuted = true;
        System.out.println("Update # " + data);
    }

    /**
     * Execute the undo operation for Update command
     *
     * @throws CustomException if failed validation
     */
    @Override
    public void undo() throws CustomException {
        if (!isExecuted) {
            throw new CustomException("Error in Update Command! Command not executed before!");
        }
        if (doneUndo) {
            throw new CustomException("Error in Update Command! Command has already been undone!");
        }
        //Update original data through unique ID
        receiver.updateById(originalData.id, originalData.data1, originalData.data2, originalData.data3);
        doneUndo = true;
    }
}