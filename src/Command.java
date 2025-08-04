public interface Command {
    void execute() throws CustomException;

    String[] getData();

    int getDataStoredPosition();

    int getDataStoredUUID();

    String getCommandName();
}