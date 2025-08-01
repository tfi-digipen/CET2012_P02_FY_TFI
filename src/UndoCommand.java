public class UndoCommand implements Command {
    private Receiver receiver;
    private String params;

    public UndoCommand(Receiver receiver, String params) {
        this.receiver = receiver;
        this.params = params;
    }

    @Override
    public void execute() {
    }
}