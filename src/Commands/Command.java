package Commands;
import CustomException.CustomException;

/**
 * Interface for Command
 * Current known command: AddCommand, UpdateCommand, DeleteCommand, ListCommand, UndoCommand
 */
public interface Command {
    /**
     * abstract execute method
     * @throws CustomException when fail validation
     */
    void execute() throws CustomException;

    /**
     * abstract undo method
     * @throws CustomException when fail validation
     */
    void undo() throws CustomException;

    /**
     * abstract isUndoable method
     * AddCommand, UpdateCommand, DeleteCommand is undoable
     * ListCommand, UndoCommand cannot undo
     * @return whether command is undoable
     */
    boolean isUndoable();
}