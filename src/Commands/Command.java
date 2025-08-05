package Commands;

import CustomException.CustomException;

public interface Command {
    void execute() throws CustomException;
    void undo() throws CustomException;
    boolean isUndoable();
}