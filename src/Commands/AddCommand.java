package Commands;

import CustomException.CustomException;
import MasterFunction.MasterFunction;
import Receiver.Receiver;

public class AddCommand implements Command {
    private final Receiver receiver;
    private boolean isProcessed;
    private boolean doneUndo;
    private final String data;

    public AddCommand(Receiver receiver, String data) {
        this.receiver = receiver;
        this.data = data;
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
        if (data == null) {
            throw new CustomException("Error! Payload cannot be empty or null");
        }
        String[] splitData = data.split(" ");
        if (splitData.length != 3) {
            throw new CustomException("Error! Invalid payload! 3 arguments required");
        }
        String data1 = splitData[0];
        String data2 = splitData[1];
        String data3 = splitData[2];
        if (!MasterFunction.checkIsValidEmail(data3)) {
            throw new CustomException("Error! Invalid input! Email address is not valid");
        }
        receiver.add(data1, data2, data3);
        isProcessed = true;
        System.out.println("Add");
    }

    @Override
    public void undo() throws CustomException {
        if (!isProcessed) {
            throw new CustomException("Error! Command has never been processed before!");
        }
        if (doneUndo) {
            throw new CustomException("Error! Command has already been undone!");
        }
        receiver.deleteLastData();
        doneUndo = true;
    }
}