public class UpdateCommand implements Command {
    private Receiver receiver;
    private String params;

    public UpdateCommand(Receiver receiver, String params) {
        this.receiver = receiver;
        this.params = params;
    }

    @Override
    public void execute() {
    }
}