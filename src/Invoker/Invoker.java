package Invoker;

import Commands.Command;
import CustomException.CustomException;

import java.util.Stack;

/**
 * The Invoker class is responsible for initiating the commands. It must have a field to store the
 * reference to a command object. The Invoker triggers the commands instead of sending the
 * request directly to the receiver. The invoker is not responsible for creating the command
 * object, it usually gets a pre-created command from the client.
 */
public class Invoker {
    /**
     * Storage for commands to be executed
     */
    private Command[] cmdToExecute;

    /**
     * Main constructor for Invoker
     */
    public Invoker() {}

    /**
     * Store commands from user input to storage location
     *
     * @param commands
     */
    public void setCommandsForExecution(Command[] commands) {
        cmdToExecute = commands;
    }

    /**
     * Execute commands inside storage
     *
     * @param history To store successfully executed command (Add, Update, Delete)
     */
    public void executeCommand(Stack<Command> history) {
        if (cmdToExecute == null) {
            System.out.println("No cmd to execute");
        }
        for (Command command : cmdToExecute) {
            if (command == null) {
                System.out.println("Error! Invalid command");
                continue;
            }
            try {
                command.execute();
                if (command.isUndoable()) {
                    history.push(command);
                }
            } catch (CustomException e) {
                System.out.println(e.getMessage());
            }
        }
        cmdToExecute = null;
    }
}