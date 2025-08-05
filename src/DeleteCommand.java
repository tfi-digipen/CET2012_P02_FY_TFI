public class DeleteCommand implements Command {
    private Receiver receiver;
    private boolean isProcessed;
    protected String index;
    private int dataStoredPosition;
    protected String[] undoData;

    public DeleteCommand(Receiver receiver, String index) {
        this.receiver = receiver;
        this.index = index;
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
        receiver.delete(dataStoredPosition);
        isProcessed = true;
    }

    @Override
    public void undo() throws CustomException {
        if (!isProcessed) {
            throw new CustomException("Error! Command has never been procesed before!");
        }
        receiver.insert(dataStoredPosition, undoData);
    }
}