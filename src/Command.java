public interface Command {
    void execute() throws CustomException;

    String[] getData();

    int getDataStoredPosition();

    String getCommandName();
    void undo();
}