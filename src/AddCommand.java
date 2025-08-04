public class AddCommand implements Command {
    private Receiver receiver;
    protected String data1;
    protected String data2;
    protected String data3;
    private int dataStoredPosition;
    private int dataStoredUUID;

    public AddCommand(Receiver receiver, String data1, String data2, String data3) {
        this.receiver = receiver;
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
    }

    @Override
    public String[] getData() {
        return new String[]{data1, data2, data3};
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
        return "Add";
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
        var reply = receiver.addCommand(this, true);
        setValue(reply[0], reply[1]);
    }

    public void setValue(int uuid, int position) {
        dataStoredUUID = uuid;
        dataStoredPosition = position;
    }
}