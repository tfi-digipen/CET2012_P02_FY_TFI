package Commands;

import CustomException.CustomException;
import MasterFunction.MasterFunction;
import Receiver.*;

public class UpdateCommand implements Command {
    private final Receiver receiver;
    private boolean isProcessed;
    private boolean doneUndo;
    private final String data;
    private Receiver.BasicEmployeeInfo originalData;

    public UpdateCommand(Receiver receiver, String data) {
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
        originalData = receiver.getDataByIndex(dataStoredPosition);
        String data1 = splitData[1];
        if (splitData.length > 2) {
            String data2 = splitData[2];
            if (splitData.length > 3) {
                String data3 = splitData[3];
                if (!MasterFunction.checkIsValidEmail(data3)) {
                    throw new CustomException("Error! Invalid input! Email address is not valid");
                }
                receiver.updateById(originalData.id, data1, data2, data3);
            } else {
                receiver.updateById(originalData.id, data1, data2);
            }
        } else {
            receiver.updateById(originalData.id, data1);
        }
        isProcessed = true;
        System.out.println("Update # " + data);
    }

    @Override
    public void undo() throws CustomException {
        if (!isProcessed) {
            throw new CustomException("Error! Command has never been procesed before!");
        }
        if (doneUndo) {
            throw new CustomException("Error! Command has already been undone!");
        }
        receiver.updateById(originalData.id, originalData.data1, originalData.data2, originalData.data3);
        doneUndo = true;
    }
}