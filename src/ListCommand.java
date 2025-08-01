public class ListCommand implements Command {
    private String params;
    private Receiver receiver;

    public ListCommand(Receiver receiver, String params) {
        this.receiver = receiver;
        this.params = params;
    }

    @Override
    public void execute() {

    }
}
