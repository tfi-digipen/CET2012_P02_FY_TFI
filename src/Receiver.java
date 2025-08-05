import java.util.ArrayList;

public class Receiver {
    protected ArrayList<String[]> dataStore;

    private String originalFileName = "./dataStore.txt";

    public Receiver() {
        dataStore = new ArrayList<>();
        loadFromFileAndStoreIntoDataStoreIfExist();
    }

    private void loadFromFileAndStoreIntoDataStoreIfExist() {
        if (MasterFunction.checkIfFileExist(originalFileName)) {
            String[] content = MasterFunction.getFileContent(originalFileName);
            if (content.length > 0) {
                for (String c : content) {
                    String[] data = c.split(" ");
                    dataStore.add(new String[]{data[0], data[1], data[2]});
                }
            }
        }
    }

    public void storeToFile() {
        StringBuilder sb = new StringBuilder();
        for (String[] data : dataStore) {
            sb.append(data[0]).append(" ").append(data[1]).append(" ").append(data[2]).append("\n");
        }
        MasterFunction.writeToFile(originalFileName, sb.toString());
    }

    protected void add(String data1, String data2, String data3) {
        dataStore.add(new String[]{MasterFunction.toTitleCase(data1), MasterFunction.toTitleCase(data2), data3});
    }

    protected void insert(int insertPosition, String[] data) {
        dataStore.add(insertPosition, data);
    }

    protected void delete(int toDeleteIndex) {
        dataStore.remove(toDeleteIndex);
    }

    protected void update(int toUpdateIndex, String data1) {
        String[] tempData = dataStore.get(toUpdateIndex);
        tempData[0] = MasterFunction.toTitleCase(data1);
    }

    protected void update(int toUpdateIndex, String data1, String data2) {
        String[] tempData = dataStore.get(toUpdateIndex);
        tempData[0] = MasterFunction.toTitleCase(data1);
        tempData[1] = MasterFunction.toTitleCase(data2);
    }

    protected void update(int toUpdateIndex, String data1, String data2, String data3) {
        String[] tempData = dataStore.get(toUpdateIndex);
        tempData[0] = MasterFunction.toTitleCase(data1);
        tempData[1] = MasterFunction.toTitleCase(data2);
        tempData[2] = data3;
    }

    protected void list() {
        if (dataStore.isEmpty()) {
            System.out.println("No data to display");
            return;
        }
        for (int i = 0; i < dataStore.size(); i++) {
            String[] data = dataStore.get(i);
            System.out.printf("%02d. %s %s %s\n", i + 1, data[0], data[1], data[2]);
        }
    }
}