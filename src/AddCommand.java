public class AddCommand implements Command {
    private Receiver receiver;
    private boolean isProcessed;
    private String data;
    protected String data1;
    protected String data2;
    protected String data3;
    protected int dataStoredPosition;

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
        var splitData = data.split(" ");
        if (splitData.length != 3) {
            throw new CustomException("Error! Invalid input! Wrong number of payload");
        }
        this.data1 = splitData[0];
        this.data2 = splitData[1];
        this.data3 = splitData[2];
        if (data1 == null || data1.isBlank()) {
            throw new CustomException("Error! Invalid input! Data1 cannot be empty or null");
        }
        if (data2 == null || data2.isBlank()) {
            throw new CustomException("Error! Invalid input! Data2 cannot be empty or null");
        }
        if (data3 == null || data3.isBlank()) {
            throw new CustomException("Error! Invalid input! Data3 cannot be empty or null");
        }
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