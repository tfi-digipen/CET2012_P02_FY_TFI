import java.util.ArrayList;
import java.util.Stack;

public class Receiver {
    public ArrayList<String[]> dataStore;
    private Stack<Command> commandStack;

    private int currentUUID;
    protected int lastUndoablePosition;

    private String originalFileName = "./dataStore.txt";

    public Receiver(Stack<Command> commandStack) {
        this.commandStack = commandStack;
        dataStore = new ArrayList<>();
        currentUUID = 0;
        if (MasterFunction.checkIfFileExist(originalFileName)) {
            var content = MasterFunction.getFileContent(originalFileName);
            if (content.length > 0) {
                int pos = 0;
                for (var c : content) {
                    var data = c.split(" ");
                    int toStoreUUID = currentUUID++;
                    dataStore.add(new String[]{String.valueOf(toStoreUUID), data[0], data[1], data[2]});
                    var addCommand = new AddCommand(this, data[0], data[1], data[2]);
                    addCommand.setValue(toStoreUUID, pos++);
                    commandStack.add(addCommand);
                }
                lastUndoablePosition = commandStack.size();
            }
        }
    }

    public void storeToFile() {
        var sb = new StringBuilder();
        for (String[] data : dataStore) {
            sb.append(data[1]).append(" ").append(data[2]).append(" ").append(data[3]).append("\n");
        }
        MasterFunction.writeToFile(originalFileName, sb.toString());
    }

    public int[] addCommand(AddCommand command, boolean toStoreCommand) {
        if (toStoreCommand)
            commandStack.push(command);
        int toStoreUUID = currentUUID++;
        dataStore.add(new String[]{String.valueOf(toStoreUUID), MasterFunction.toTitleCase(command.data1), MasterFunction.toTitleCase(command.data2), command.data3});
        return new int[]{toStoreUUID, dataStore.size() - 1};
    }

    public int updateCommand(UpdateCommand command, boolean toStoreCommand) {
        if (toStoreCommand)
            commandStack.push(command);
        int indexToUpdate = command.getDataStoredPosition();
        var tempData = dataStore.get(indexToUpdate);
        tempData[1] = command.data1;
        if (command.data2 != null) {
            tempData[2] = command.data2;
            if (command.data3 != null) {
                tempData[3] = command.data3;
            }
        }
        dataStore.set(indexToUpdate, tempData);
        return Integer.parseInt(tempData[0]);
    }

    public int deleteCommand(DeleteCommand command, boolean toStoreCommand) {
        int toDeleteIndex = command.getDataStoredPosition();
        var tempData = dataStore.get(toDeleteIndex);
        int uuid = Integer.parseInt(tempData[0]);
        var found = false;
        for (var x : commandStack) {
            if (x.getDataStoredUUID() == uuid && x.getCommandName().equals("Add")) {
                found = true;
                break;
            }
        }
        if (!found) {
            var addCommandToStore = new AddCommand(this, tempData[1], tempData[2], tempData[3]);
            addCommandToStore.setValue(uuid, toDeleteIndex);
            commandStack.push(addCommandToStore);
        }
        if (toStoreCommand)
            commandStack.push(command);
        dataStore.remove(toDeleteIndex);
        return uuid;
    }

    public void undoCommand() {
        var lastCommand = commandStack.pop();
        var lastCommandClassName = lastCommand.getCommandName();
        var lastCommandIndex = lastCommand.getDataStoredPosition();
        var lastCommandUUID = lastCommand.getDataStoredUUID();
        if (lastCommandClassName.equals("Add")) {
            dataStore.removeLast();
        } else if (lastCommandClassName.equals("Update")) {
            int dataAddedStackPosition = -1;
            for (int i = commandStack.size() - 1; i >= 0; i--) {
                var command = commandStack.get(i);
                var commandClassName = command.getCommandName();
                if (commandClassName.equals("Add") && command.getDataStoredUUID() == lastCommandUUID) {
                    var data = command.getData();
                    dataStore.set(lastCommandIndex, new String[]{String.valueOf(lastCommandUUID), data[0], data[1], data[2]});
                    dataAddedStackPosition = i;
                    break;
                }
            }
            for (int i = dataAddedStackPosition + 1; i < commandStack.size(); i++) {
                var command = commandStack.get(i);
                if (command.getDataStoredUUID() == lastCommandUUID && command.getCommandName().equals("Update")) {
                    var update = (UpdateCommand) command;
                    updateCommand(update, false);
                }
            }
        } else if (lastCommandClassName.equals("Delete")) {
            int dataAddedStackPosition = -1;
            for (int i = commandStack.size() - 1; i >= 0; i--) {
                var command = commandStack.get(i);
                var commandClassName = command.getCommandName();
                if (commandClassName.equals("Add") && command.getDataStoredUUID() == lastCommandUUID) {
                    var data = command.getData();
                    dataStore.add(lastCommandIndex, new String[]{String.valueOf(lastCommandUUID), data[0], data[1], data[2]});
                    dataAddedStackPosition = i;
                    break;
                }
            }
            for (int i = dataAddedStackPosition + 1; i < commandStack.size(); i++) {
                var command = commandStack.get(i);
                if (command.getDataStoredUUID() == lastCommandUUID && command.getCommandName().equals("Update")) {
                    var update = (UpdateCommand) command;
                    updateCommand(update, false);
                }
            }
        }
    }

    public void listCommand() {
        if (dataStore.isEmpty()) {
            System.out.println("No data to display");
            return;
        }
        for (int i = 0; i < dataStore.size(); i++) {
            var data = dataStore.get(i);
            System.out.printf("%02d. %s %s %s\n", i + 1, data[1], data[2], data[3]);
        }
    }
}