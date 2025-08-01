public class DeleteCommand implements Command {
    private Receiver receiver;
    private String params;

    public DeleteCommand(Receiver receiver, String params) {
        this.receiver = receiver;
        this.params = params;
    }

    @Override
    public void execute() {}
}
