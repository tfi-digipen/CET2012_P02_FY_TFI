public class UpdateCommand implements Command {
    private Receiver receiver;
    private boolean isProcessed;
    private String data;
    private String index;
    private String data1;
    private String data2;
    private String data3;
    private String[] undoData;
    private int dataStoredPosition;

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
        if (isProcessed) {
            throw new CustomException("Error! Command has been processed before");
        }
        if (data == null) {
            throw new CustomException("Error! Payload cannot be empty or null");
        }
        var splitData = data.split(" ");
        if (splitData.length < 2)
            throw new CustomException("Update command need at least 2 args");
        if (splitData.length > 4)
            throw new CustomException("Update command at most 4 args");
        this.index = splitData[0];
        this.data1 = splitData[1];
        if (splitData.length > 2) {
            this.data2 = splitData[2];
            if (splitData.length > 3)
                this.data3 = splitData[3];
        }
        int idx = -1;
        try {
            idx = Integer.parseInt(index);
        } catch (NumberFormatException | NullPointerException e) {
            throw new CustomException("Error! Invalid input! Index value not valid integer number");
        }
        if (idx < 1) {
            throw new CustomException("Error! Invalid input! Index value must be positive number");
        }
        var count = receiver.dataStore.size();
        if (count < idx) {
            throw new CustomException("Error! Invalid input! Index value exceed stored data count");
        }
        if (data3 != null && !MasterFunction.checkIsValidEmail(data3)) {
            throw new CustomException("Error! Invalid input! Email address is not valid");
        }
        dataStoredPosition = idx - 1;
        undoData = receiver.dataStore.get(dataStoredPosition);
        if (data2 != null) {
            if (data3 != null) {
                receiver.update(dataStoredPosition, data1, data2, data3);
            } else {
                receiver.update(dataStoredPosition, data1, data2);
            }
        } else {
            receiver.update(dataStoredPosition, data1);
        }
        isProcessed = true;
    }

    @Override
    public void undo() throws CustomException {
        if (!isProcessed) {
            throw new CustomException("Error! Command has never been procesed before!");
        }
        receiver.update(dataStoredPosition, undoData[0], undoData[1], undoData[2]);
    }
}