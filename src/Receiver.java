import java.util.ArrayList;
import java.util.Stack;

public class Receiver {
    public AddCommand addCommand;
    public DeleteCommand deleteCommand;
    public UpdateCommand updateCommand;
    public ListCommand listCommand;
    public UndoCommand undoCommand;

    public Stack<Command> commandStack;
    public ArrayList<String> dataStore;

    private int initialDataStoreCount;

    private String originalFileName = "./dataStore.txt";

    public Receiver() {
        if (MasterFunction.checkIfFileExist(originalFileName)) {
            var content = MasterFunction.getFileContent(originalFileName);
            if (content != null) {
                for (var c : content) {
                    dataStore.add(c);
                    initialDataStoreCount++;
                }
            }
        }
    }

    public void addCommand(Command command, String data) {
        commandStack.push(command);
        dataStore.add(data);
    }

    public void updateCommand(int toUpdateIndex, Command command, String data) {
        commandStack.set(toUpdateIndex, command);
        dataStore.set(toUpdateIndex + initialDataStoreCount, data);
    }

    public void deleteCommand(int toDeleteIndex) {
        commandStack.remove(toDeleteIndex);
        dataStore.remove(toDeleteIndex + initialDataStoreCount);
    }

    public void undoCommand() {
    }

    public void displayCommand() {
        for (int i = 0; i < dataStore.size(); i++) {
            var data = dataStore.get(i);
            System.out.printf("%02d. %s\n", i + 1, data);
        }
    }
}