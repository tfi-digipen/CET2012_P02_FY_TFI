import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Stack<Command> history = new Stack<>();
        var receiver = new Receiver();
        Command[] commands = new Command[13];
        commands[0] = new AddCommand(receiver, "first_name", "last_name", "ice-cream@alaskafields.org");
        commands[1] = new AddCommand(receiver, "john", "doe", "simple@example.com");
        commands[2] = new AddCommand(receiver, "hanna", "moon", "tetter.tots@potatoesarelife.com");
        commands[3] = new AddCommand(receiver, "ah", "boon", "green-tea@teaforlife.com");
        commands[4] = new ListCommand(receiver);
        commands[5] = new UpdateCommand(receiver, "3", "Adam");
        commands[6] = new ListCommand(receiver);
        commands[7] = new UpdateCommand(receiver,"1","blue","bell","ice-cream@alaskafields.org");
        commands[8] = new ListCommand(receiver);
        commands[9] = new DeleteCommand(receiver,"1");
        commands[10] = new ListCommand(receiver);
        commands[11] = new UndoCommand(receiver, history);
        commands[12] = new ListCommand(receiver);
        var invoker = new Invoker();
        invoker.setCommandsForExecution(commands);
        invoker.executeCommand(history);

        var email = "aaa@bbb.ccc";
        System.out.println(email + " - " + MasterFunction.checkIsValidEmail(email));
        email = "aaa@bbb.cccc";
        System.out.println(email + " - " + MasterFunction.checkIsValidEmail(email));
        email = "aaa@bbb.ccc.";
        System.out.println(email + " - " + MasterFunction.checkIsValidEmail(email));
        email = "aaa@bb.b.ccc";
        System.out.println(email + " - " + MasterFunction.checkIsValidEmail(email));
        email = "aaa@bbb.c_cc";
        System.out.println(email + " - " + MasterFunction.checkIsValidEmail(email));
        email = "aaa@bb_b.ccc";
        System.out.println(email + " - " + MasterFunction.checkIsValidEmail(email));
        email = "a.a.a@bbb.ccc";
        System.out.println(email + " - " + MasterFunction.checkIsValidEmail(email));
        email = "a__aa@bbb.ccc";
        System.out.println(email + " - " + MasterFunction.checkIsValidEmail(email));
        email = ".aaa@bbb.ccc";
        System.out.println(email + " - " + MasterFunction.checkIsValidEmail(email));
        email = "aaa.@bbb.ccc";
        System.out.println(email + " - " + MasterFunction.checkIsValidEmail(email));
        email = "_aa-a.aaa_@bbb.ccc";
        System.out.println(email + " - " + MasterFunction.checkIsValidEmail(email));
        email = "";
        System.out.println(email + " - " + MasterFunction.checkIsValidEmail(email));
        email = "a@b.c";
        System.out.println(email + " - " + MasterFunction.checkIsValidEmail(email));
        email = "a@b.cc";
        System.out.println(email + " - " + MasterFunction.checkIsValidEmail(email));
        email = "@b.cc";
        System.out.println(email + " - " + MasterFunction.checkIsValidEmail(email));
        email = "a@bb";
        System.out.println(email + " - " + MasterFunction.checkIsValidEmail(email));
        email = "a@bb.cccc.dd";
        System.out.println(email + " - " + MasterFunction.checkIsValidEmail(email));
    }
}