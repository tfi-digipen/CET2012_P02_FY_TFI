public class DeleteCommand implements Command {
    private Receiver receiver;
    private String params;

    public DeleteCommand(Receiver receiver, String params) {
        this.receiver = receiver;
        this.params = params;
    }

    @Override
    public void execute() {
        var parameters = params.split(" ");
        if (parameters.length != 1) {
            System.out.println("Error! Invalid input! Delete command need 1 parameter");
            return;
        }
        int index = -1;
        try {
            index = Integer.parseInt(parameters[0]);
        } catch (Exception e) {
            System.out.println("Error! Invalid input! Index value not valid integer number");
            return;
        }
        if (index < 1) {
            System.out.println("Error! Invalid input! Index value must be positive number");
            return;
        }
        var count = receiver.dataStore.size();
        if (count < index) {
            System.out.println("Error! Invalid input! Index value exceed stored data count");
            return;
        }
        int toDeletePosition = index - 1;
        receiver.deleteCommand(toDeletePosition);
    }
}
