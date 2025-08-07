package Receiver;

import CustomException.CustomException;
import MasterFunction.MasterFunction;
import java.util.ArrayList;
import java.util.List;

public class Receiver {
    public class BasicEmployeeInfo {
        public int id;
        public String data1;
        public String data2;
        public String data3;

        public BasicEmployeeInfo(int id, String data1, String data2, String data3) {
            this.id = id;
            this.data1 = data1;
            this.data2 = data2;
            this.data3 = data3;
        }

        @Override
        public String toString() {
            return data1 + " " + data2 + " " + data3;
        }

        public String[] toStringArray() {
            return new String[]{data1, data2, data3};
        }
    }

    private ArrayList<BasicEmployeeInfo> dataStore;

    private String originalFileName = "./dataStore.txt";

    private int idCounter;

    public Receiver() {
        dataStore = new ArrayList<>();
        loadFromFileAndStoreIntoDataStoreIfExist();
    }

    private void loadFromFileAndStoreIntoDataStoreIfExist() {
        if (MasterFunction.checkIfFileExist(originalFileName)) {
            List<String> content = MasterFunction.getFileContent(originalFileName);
            if (content != null) {
                for (String c : content) {
                    if (c == null)
                        continue;
                    String[] data = c.split(" ");
                    if (data.length != 3)
                        continue;
                    dataStore.add(new BasicEmployeeInfo(idCounter++, data[0], data[1], data[2]));
                }
            }
        }
    }

    public void storeToFile() {
        StringBuilder sb = new StringBuilder();
        for (BasicEmployeeInfo data : dataStore) {
            sb.append(data.toString()).append("\n");
        }
        MasterFunction.writeToFile(originalFileName, sb.toString());
    }

    public int add(String data1, String data2, String data3) {
        int id = idCounter++;
        dataStore.add(new BasicEmployeeInfo(id, MasterFunction.toTitleCase(data1),
                MasterFunction.toTitleCase(data2),
                data3.contains("@") ? data3 : MasterFunction.toTitleCase(data3)));
        return id;
    }

    public void insert(BasicEmployeeInfo data) {
        if (dataStore.isEmpty()) {
            dataStore.add(data);
            return;
        }
        for (int i = dataStore.size() - 1; i >= 0; i--) {
            int currentDataID = dataStore.get(i).id;
            if (currentDataID < data.id) {
                dataStore.add(i + 1, data);
                return;
            }
        }
        dataStore.addFirst(data);
    }

    public void deleteByIndex(int toDeleteIndex) throws CustomException {
        checkIsValidIndex(toDeleteIndex);
        dataStore.remove(toDeleteIndex);
    }

    public void deleteById(int id) throws CustomException {
        for (int i = dataStore.size() - 1; i >= 0; i--) {
            BasicEmployeeInfo data = dataStore.get(i);
            if (data.id == id) {
                dataStore.remove(i);
                return;
            }
        }
        throw new CustomException("Error! Data to remove not found!");
    }

    public void updateById(int id, String data1) throws CustomException {
        int index = getIndex(id);
        BasicEmployeeInfo tempData = dataStore.get(index);
        tempData.data1 = MasterFunction.toTitleCase(data1);
    }

    public void updateById(int id, String data1, String data2) throws CustomException {
        int index = getIndex(id);
        BasicEmployeeInfo tempData = dataStore.get(index);
        tempData.data1 = MasterFunction.toTitleCase(data1);
        tempData.data2 = MasterFunction.toTitleCase(data2);
    }

    public void updateById(int id, String data1, String data2, String data3) throws CustomException {
        int index = getIndex(id);
        BasicEmployeeInfo tempData = dataStore.get(index);
        tempData.data1 = MasterFunction.toTitleCase(data1);
        tempData.data2 = MasterFunction.toTitleCase(data2);
        tempData.data3 = data3.contains("@") ? data3 : MasterFunction.toTitleCase(data3);
    }

    public int getIndex(int id) throws CustomException {
        for (int i = 0; i < dataStore.size(); i++) {
            int dataID = dataStore.get(i).id;
            if (dataID == id)
                return i;
        }
        throw new CustomException("Error! Invalid id!");
    }

    public BasicEmployeeInfo getDataByIndex(int index) throws CustomException {
        checkIsValidIndex(index);
        return dataStore.get(index);
    }

    public void list() {
        if (dataStore.isEmpty()) {
            System.out.println("No data to display");
            return;
        }
        for (int i = 0; i < dataStore.size(); i++) {
            BasicEmployeeInfo data = dataStore.get(i);
            System.out.printf("%02d. %s %s %s\n", i + 1, data.data1, data.data2, data.data3);
        }
    }

    public void checkIsValidIndex(int index) throws CustomException {
        if (index < 0 || index >= dataStore.size())
            throw new CustomException("Error! Invalid index!");
    }
}