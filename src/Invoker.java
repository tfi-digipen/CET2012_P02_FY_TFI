import java.util.Stack;

public class Invoker {
    private Command[] cmdToExecute;

    public void setCommandsForExecution(Command[] commands) {
        cmdToExecute = commands;
    }

    public void executeCommand(Stack<Command> history) {
        for (var command : history) {
            command.execute();
        }
    }
}