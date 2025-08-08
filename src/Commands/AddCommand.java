package Commands;

import CustomException.CustomException;
import MasterFunction.MasterFunction;
import Receiver.Receiver;

/**
 * AddCommand class implementing Command interface
 */
public class AddCommand implements Command {
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
     * Variable to store unique ID of the data associated to this command
     */
    private int id;

    /**
     * Main constructor for AddCommand
     *
     * @param receiver Receiver to be used across same command group
     * @param data     Data to be processed
     */
    public AddCommand(Receiver receiver, String data) {
        this.receiver = receiver;
        this.data = data;
    }

    /**
     * AddCommand support undo operation
     *
     * @return true
     */
    @Override
    public boolean isUndoable() {
        return true;
    }

    /**
     * Execute the add command
     *
     * @throws CustomException if failed validation
     */
    @Override
    public void execute() throws CustomException {
        if (receiver == null) {
            throw new CustomException("Error in Add Command! Receiver is null");
        }
        if (isExecuted) {
            throw new CustomException("Error in Add Command! Command executed before");
        }
        if (data == null) {
            throw new CustomException("Error in Add Command! Payload cannot be empty or null");
        }
        String[] splitData = data.split(" ");
        if (splitData.length != 3) {
            throw new CustomException("Error in Add Command! Invalid payload! 3 arguments required");
        }
        String data1 = splitData[0];
        String data2 = splitData[1];
        String data3 = splitData[2];
        if (!MasterFunction.checkIsValidEmailOrData3(data3)) {
            throw new CustomException("Error in Add Command! Invalid input! Data3 or email is not valid");
        }
        //id to store unique ID pertaining to this add data operation
        id = receiver.add(data1, data2, data3);
        isExecuted = true;
        System.out.println("Add");
    }

    /**
     * Execute the undo operation for Add command
     *
     * @throws CustomException if failed validation
     */
    @Override
    public void undo() throws CustomException {
        if (!isExecuted) {
            throw new CustomException("Error in Add Command! Command not executed before!");
        }
        if (doneUndo) {
            throw new CustomException("Error in Add Command! Command has already been undone!");
        }
        //delete by unique ID as opposed to index position
        receiver.deleteById(id);
        doneUndo = true;
    }
}