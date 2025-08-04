public class DeleteCommand implements Command {
    private Receiver receiver;
    protected String index;
    private int dataStoredPosition;
    protected String[] undoData;

    public DeleteCommand(Receiver receiver, String index) {
        this.receiver = receiver;
        this.index = index;
    }

    @Override
    public String[] getData() {
        return new String[]{index};
    }

    @Override
    public int getDataStoredPosition() {
        return dataStoredPosition;
    }

    @Override
    public String getCommandName() {
        return "Delete";
    }

    @Override
    public void execute() throws CustomException {
        int idx = -1;
        try {
            idx = Integer.parseInt(index);
        } catch (Exception e) {
            throw new CustomException("Error! Invalid input! Index value not valid integer number");
        }
        if (idx < 1) {
            throw new CustomException("Error! Invalid input! Index value must be positive number");
        }
        var count = receiver.dataStore.size();
        if (count < idx) {
            throw new CustomException("Error! Invalid input! Index value exceed stored data count");
        }
        dataStoredPosition = idx - 1;
        undoData = receiver.dataStore.get(dataStoredPosition);
        receiver.deleteCommand(dataStoredPosition);
    }

    @Override
    public void undo() {
        receiver.undoDelete(dataStoredPosition, undoData);
    }
}