public class AddCommand implements Command {
    private Receiver receiver;
    private String params;

    public AddCommand(Receiver receiver, String params) {
        this.receiver = receiver;
        this.params = params;
    }

    @Override
    public void execute() {
        var parameters = params.split(" ");
        if (parameters.length != 3) {
            System.out.println("Error! Invalid input! Add command need 3 parameters");
            return;
        }
        if (!MasterFunction.checkIsValidEmail(parameters[2])) {
            System.out.println("Error! Invalid input! Email address is not valid");
            return;
        }
        var data = MasterFunction.toTitleCase(parameters[0]) + " " + MasterFunction.toTitleCase(parameters[1]) + " " + MasterFunction.toTitleCase(parameters[2]);
        receiver.addCommand(this, data);
    }
}