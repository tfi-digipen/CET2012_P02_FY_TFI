public class UndoCommand implements Command {
    private Receiver receiver;

    public UndoCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    public UndoCommand(Receiver receiver, String payload) {
        this.receiver = receiver;
    }

    @Override
    public String[] getData() {
        return new String[0];
    }

    @Override
    public int getDataStoredPosition() {
        return -1;
    }

    @Override
    public int getDataStoredUUID() {
        return -1;
    }

    @Override
    public String getCommandName() {
        return "Undo";
    }

    @Override
    public void execute() throws CustomException {
        if (receiver.commandStack.isEmpty() || receiver.lastUndoablePosition == receiver.commandStack.size()) {
            throw new CustomException("Error! Invalid input! No commands to undo.");
        }
        var lastCommand = receiver.commandStack.pop();
        var lastCommandClassName = lastCommand.getCommandName();
        var lastCommandIndex = lastCommand.getDataStoredPosition();
        var lastCommandUUID = lastCommand.getDataStoredUUID();
        if (lastCommandClassName.equals("Add")) {
            receiver.dataStore.removeLast();
        } else if (lastCommandClassName.equals("Update")) {
            int dataAddedStackPosition = -1;
            for (int i = receiver.commandStack.size() - 1; i >= 0; i--) {
                var command = receiver.commandStack.get(i);
                var commandClassName = command.getCommandName();
                if (commandClassName.equals("Add") && command.getDataStoredUUID() == lastCommandUUID) {
                    var data = command.getData();
                    receiver.dataStore.set(lastCommandIndex, new String[]{String.valueOf(lastCommandUUID), data[0], data[1], data[2]});
                    dataAddedStackPosition = i;
                    break;
                }
            }
            for (int i = dataAddedStackPosition + 1; i < receiver.commandStack.size(); i++) {
                var command = receiver.commandStack.get(i);
                if (command.getDataStoredUUID() == lastCommandUUID && command.getCommandName().equals("Update")) {
                    var update = (UpdateCommand) command;
                    int indexToUpdate = update.getDataStoredPosition();
                    var tempData = receiver.dataStore.get(indexToUpdate);
                    tempData[1] = update.data1;
                    if (update.data2 != null) {
                        tempData[2] = update.data2;
                        if (update.data3 != null) {
                            tempData[3] = update.data3;
                        }
                    }
                    receiver.dataStore.set(indexToUpdate, tempData);
                }
            }
        } else if (lastCommandClassName.equals("Delete")) {
            int dataAddedStackPosition = -1;
            for (int i = receiver.commandStack.size() - 1; i >= 0; i--) {
                var command = receiver.commandStack.get(i);
                var commandClassName = command.getCommandName();
                if (commandClassName.equals("Add") && command.getDataStoredUUID() == lastCommandUUID) {
                    var data = command.getData();
                    receiver.dataStore.add(lastCommandIndex, new String[]{String.valueOf(lastCommandUUID), data[0], data[1], data[2]});
                    dataAddedStackPosition = i;
                    break;
                }
            }
            for (int i = dataAddedStackPosition + 1; i < receiver.commandStack.size(); i++) {
                var command = receiver.commandStack.get(i);
                if (command.getDataStoredUUID() == lastCommandUUID && command.getCommandName().equals("Update")) {
                    var update = (UpdateCommand) command;
                    int indexToUpdate = update.getDataStoredPosition();
                    var tempData = receiver.dataStore.get(indexToUpdate);
                    tempData[1] = update.data1;
                    if (update.data2 != null) {
                        tempData[2] = update.data2;
                        if (update.data3 != null) {
                            tempData[3] = update.data3;
                        }
                    }
                    receiver.dataStore.set(indexToUpdate, tempData);
                }
            }
        }
    }
}