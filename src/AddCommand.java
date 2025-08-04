public class AddCommand implements Command {
    private Receiver receiver;
    protected String data1;
    protected String data2;
    protected String data3;
    protected int dataStoredPosition;

    public AddCommand(Receiver receiver, String data) {
        this.receiver = receiver;
        var splitData = data.split(" ");
        this.data1 = splitData[0];
        this.data2 = splitData[1];
        this.data3 = splitData[2];
    }

    public AddCommand(Receiver receiver, String data1, String data2, String data3) {
        this.receiver = receiver;
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
    }

    @Override
    public void execute() throws CustomException {
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
    }

    @Override
    public void undo() {
        receiver.delete(dataStoredPosition);
    }
}