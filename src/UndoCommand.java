public class UndoCommand implements Command {
    private Receiver receiver;

    public UndoCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    public UndoCommand(Receiver receiver, String payload) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.undoCommand();
    }

    @Override
    public void undo() {
    }
}