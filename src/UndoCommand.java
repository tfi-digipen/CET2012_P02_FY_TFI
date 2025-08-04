public class UndoCommand implements Command {
    private Receiver receiver;

    public UndoCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public String[] getData() {
        return new String[0];
    }

    @Override
    public int getDataStoredPosition() {
        return -1;
    }

    @Override
    public int getDataStoredUUID() {
        return -1;
    }

    @Override
    public String getCommandName() {
        return "Undo";
    }

    @Override
    public void execute() throws CustomException {
        if (receiver.commandStack.isEmpty() || receiver.lastUndoablePosition == receiver.commandStack.size()) {
            throw new CustomException("Error! Invalid input! No commands to undo.");
        }
        receiver.undoCommand();
    }
}