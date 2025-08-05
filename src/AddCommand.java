public class AddCommand implements Command {
    private Receiver receiver;
    private boolean isProcessed;
    private String data;
    private String data1;
    private String data2;
    private String data3;
    private int dataStoredPosition;

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
        if (isProcessed) {
            throw new CustomException("Error! Command has been processed before");
        }
        if (data == null) {
            throw new CustomException("Error! Payload cannot be empty or null");
        }
        var splitData = data.split(" ");
        if (splitData.length != 3) {
            throw new CustomException("Error! Invalid input! Wrong number of payload");
        }
        this.data1 = splitData[0];
        this.data2 = splitData[1];
        this.data3 = splitData[2];
        if (!MasterFunction.checkIsValidEmail(data3)) {
            throw new CustomException("Error! Invalid input! Email address is not valid");
        }
        receiver.add(data1, data2, data3);
        dataStoredPosition = receiver.dataStore.size() - 1;
        isProcessed = true;
    }

    @Override
    public void undo() throws CustomException {
        if (!isProcessed) {
            throw new CustomException("Error! Command has never been procesed before!");
        }
        receiver.delete(dataStoredPosition);
    }
}