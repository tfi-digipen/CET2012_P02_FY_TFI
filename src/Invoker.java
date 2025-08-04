import java.util.Stack;

public class Invoker {
    private Command[] cmdToExecute;

    public void setCommandsForExecution(Command[] commands) {
        cmdToExecute = commands;
    }

    public void executeCommand(Stack<Command> history) {
        if (cmdToExecute == null) {
            System.out.println("No cmd to execute");
        }
        for (var command : cmdToExecute) {
            try {
                command.execute();
                if (command.getClass().getName().equals("ListCommand") || command.getClass().getName().equals("UndoCommand")) {
                    continue;
                }
                history.push(command);
            } catch (CustomException e) {
                System.out.println(e.getMessage());
            }
        }
        cmdToExecute = null;
    }
}