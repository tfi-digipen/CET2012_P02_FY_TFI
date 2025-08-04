import java.util.ArrayList;
import java.util.Stack;

public class Receiver {
    protected ArrayList<String[]> dataStore;
    protected Stack<Command> commandStack;

    protected int lastUndoablePosition;

    private String originalFileName = "./dataStore.txt";

    public Receiver(Stack<Command> commandStack) {
        this.commandStack = commandStack;
        dataStore = new ArrayList<>();
        if (MasterFunction.checkIfFileExist(originalFileName)) {
            var content = MasterFunction.getFileContent(originalFileName);
            if (content.length > 0) {
                for (var c : content) {
                    var data = c.split(" ");
                    dataStore.add(new String[]{data[0], data[1], data[2]});
                }
            }
        }
    }

    public void storeToFile() {
        var sb = new StringBuilder();
        for (String[] data : dataStore) {
            sb.append(data[0]).append(" ").append(data[1]).append(" ").append(data[2]).append("\n");
        }
        MasterFunction.writeToFile(originalFileName, sb.toString());
    }

    public void addCommand(String data1, String data2, String data3) {
        dataStore.add(new String[]{MasterFunction.toTitleCase(data1), MasterFunction.toTitleCase(data2), data3});
    }

    public void undoAdd(int toUndoindex) {
        dataStore.remove(toUndoindex);
    }

    public void deleteCommand(int toDeleteIndex) {
        dataStore.remove(toDeleteIndex);
    }

    public void undoDelete(int revertPosition, String[] data) {
        dataStore.add(revertPosition, data);
    }

    public void updateCommand(UpdateCommand command) {
        var tempData = dataStore.get(command.dataStoredPosition);
        tempData[0] = command.data1;
        if (command.data2 != null) {
            tempData[1] = command.data2;
            if (command.data3 != null) {
                tempData[2] = command.data3;
            }
        }
    }

    public void undoUpdate(int revertPosition, String[] data) {
        dataStore.set(revertPosition, data);
    }

    public void undoCommand() {
        var command = commandStack.pop();
        command.undo();
    }

    public void listCommand() {
        if (dataStore.isEmpty()) {
            System.out.println("No data to display");
            return;
        }
        for (int i = 0; i < dataStore.size(); i++) {
            var data = dataStore.get(i);
            System.out.printf("%02d. %s %s %s\n", i + 1, data[0], data[1], data[2]);
        }
    }
}