public class ListCommand implements Command {
    private Receiver receiver;

    public ListCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.listCommand();
    }

    @Override
    public void undo() {
    }
}