public class UpdateCommand implements Command {
    private Receiver receiver;
    protected String index;
    protected String data1;
    protected String data2;
    protected String data3;
    protected int dataStoredPosition;
    private int dataStoredUUID;

    public UpdateCommand(Receiver receiver, String index, String data1) {
        this.receiver = receiver;
        this.index = index;
        this.data1 = data1;
    }

    public UpdateCommand(Receiver receiver, String index, String data1, String data2) {
        this.receiver = receiver;
        this.index = index;
        this.data1 = data1;
        this.data2 = data2;
    }

    public UpdateCommand(Receiver receiver, String index, String data1, String data2, String data3) {
        this.receiver = receiver;
        this.index = index;
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
    }

    @Override
    public String[] getData() {
        return new String[]{index, data1, data2, data3};
    }

    @Override
    public int getDataStoredPosition() {
        return dataStoredPosition;
    }

    @Override
    public int getDataStoredUUID() {
        return dataStoredUUID;
    }

    @Override
    public String getCommandName() {
        return "Update";
    }

    @Override
    public void execute() throws CustomException {
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
        if (data1 == null) {
            throw new CustomException("Error! Invalid input! Data1 cannot be empty or null");
        }
        dataStoredPosition = idx - 1;
        if (!MasterFunction.checkIsValidEmail(data3)) {
            throw new CustomException("Error! Invalid input! Email address is not valid");
        }
        dataStoredUUID = receiver.updateCommand(this, true);
    }
}