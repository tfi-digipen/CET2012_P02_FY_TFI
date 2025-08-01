public class AddCommand implements Command {
    private Receiver receiver;
    private String params;

    public AddCommand(Receiver receiver, String params) {
        this.receiver = receiver;
        this.params = params;
    }

    @Override
    public void execute() {
    }
}
