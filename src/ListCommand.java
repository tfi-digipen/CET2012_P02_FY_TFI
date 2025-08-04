public class ListCommand implements Command {
    private Receiver receiver;

    public ListCommand(Receiver receiver) {
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
    public String getCommandName() {
        return "List";
    }

    @Override
    public void execute() {
        if (receiver.dataStore.isEmpty()) {
            System.out.println("No data to display");
            return;
        }
        for (int i = 0; i < receiver.dataStore.size(); i++) {
            var data = receiver.dataStore.get(i);
            System.out.printf("%02d. %s %s %s\n", i + 1, data[1], data[2], data[3]);
        }
    }

    @Override
    public void undo() {
    }
}