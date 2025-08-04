public class DeleteCommand implements Command {
    private Receiver receiver;
    protected String index;
    private int dataStoredPosition;
    private int dataStoredUUID;

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
    public int getDataStoredUUID() {
        return dataStoredUUID;
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
        var tempData = receiver.dataStore.get(dataStoredPosition);
        dataStoredUUID = Integer.parseInt(tempData[0]);
        var found = false;
        for (var x : receiver.commandStack) {
            if (x.getDataStoredUUID() == dataStoredUUID && x.getCommandName().equals("Add")) {
                found = true;
                break;
            }
        }
        if (!found) {
            var addCommandToStore = new AddCommand(receiver, tempData[1], tempData[2], tempData[3]);
            addCommandToStore.setValue(dataStoredUUID, dataStoredPosition);
            receiver.commandStack.push(addCommandToStore);
        }
        receiver.dataStore.remove(dataStoredPosition);
    }
}