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
        receiver.listCommand();
    }

    @Override
    public void undo() {
    }
}