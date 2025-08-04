import java.util.ArrayList;
import java.util.Stack;

public class Receiver {
    protected ArrayList<String[]> dataStore;
    protected Stack<Command> commandStack;

    protected int currentUUID;
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
                    //var addCommand = new AddCommand(this, data[0], data[1], data[2]);
                    //addCommand.setValue(toStoreUUID, pos++);
                    //commandStack.add(addCommand);
                }
                //lastUndoablePosition = commandStack.size();
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
}