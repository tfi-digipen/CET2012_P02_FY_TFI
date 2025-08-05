package Commands;

import Receiver.Receiver;

public class ListCommand implements Command {
    private Receiver receiver;

    public ListCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public boolean isUndoable() {
        return false;
    }

    @Override
    public void execute() {
        System.out.println("List");
        receiver.list();
    }

    @Override
    public void undo() {
    }
}