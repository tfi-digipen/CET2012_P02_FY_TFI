public class UpdateCommand implements Command {
    private Receiver receiver;
    private String params;

    public UpdateCommand(Receiver receiver, String params) {
        this.receiver = receiver;
        this.params = params;
    }

    @Override
    public void execute() {
        var parameters = params.split(" ");
        if (parameters.length != 4) {
            System.out.println("Error! Invalid input! Update command need 4 parameters");
            return;
        }
        int index = -1;
        try {
            index = Integer.parseInt(parameters[0]);
        } catch (Exception e) {
            System.out.println("Error! Invalid input! Index value not valid integer number");
        }
        if (index < 0) {
            System.out.println("Error! Invalid input! Index value must be positive number");
            return;
        }
        var count = receiver.dataStore.size();
        if (count < index) {
            System.out.println("Error! Invalid input! Index value exceed stored data count");
            return;
        }
        //check email
        var data = parameters[1] + " " + parameters[2] + " " + parameters[3];
        int toUpdatePosition = index - 1;
        receiver.dataStore.set(toUpdatePosition, data);
        receiver.commandStack.set(toUpdatePosition, this);
    }
}