package Receiver;

import CustomException.CustomException;
import MasterFunction.MasterFunction;
import java.util.ArrayList;
import java.util.List;

/**
 * The Receiver class contains some business logic. Almost any object may act as a receiver.
 * Most commands only handle the details of how a request is passed to the receiver, while the
 * receiver itself does the actual work.
 */
public class Receiver {
    /**
     * BasicEmployeeInfo class to store unique ID and data1, data2, data3
     * Using unique ID for storage location, it can prevent unauthorised
     * modification of history stack causing issue with the data integrity
     */
    public class BasicEmployeeInfo {
        public int id;
        public String data1;
        public String data2;
        public String data3;

        /**
         * Main constructor for BasicEmployeeInfo
         * @param id unique Id of the data
         * @param data1 Data1
         * @param data2 Data2
         * @param data3 Data3
         */
        public BasicEmployeeInfo(int id, String data1, String data2, String data3) {
            this.id = id;
            this.data1 = data1;
            this.data2 = data2;
            this.data3 = data3;
        }

        public BasicEmployeeInfo(BasicEmployeeInfo basicEmployeeInfo) {
            this.id = basicEmployeeInfo.id;
            this.data1 = basicEmployeeInfo.data1;
            this.data2 = basicEmployeeInfo.data2;
            this.data3 = basicEmployeeInfo.data3;
        }

        /**
         * @return Return concat of data1 data2 data3
         */
        @Override
        public String toString() {
            return data1 + " " + data2 + " " + data3;
        }
    }

    /**
     * ArrayList to store employee data
     */
    private ArrayList<BasicEmployeeInfo> dataStore;
    /**
     * Path + filename for data storage
     */
    private String originalFileName = "./dataStore.txt";
    /**
     * Running counter for unique ID
     */
    private int idCounter;

    /**
     * Main constructor for Receiver class
     */
    public Receiver() {
        dataStore = new ArrayList<>();
        loadFromFileAndStoreIntoDataStoreIfExist();
    }

    /**
     * Load existing data if exist
     */
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

    /**
     * Store existing data to file
     */
    public void storeToFile() {
        StringBuilder sb = new StringBuilder();
        for (BasicEmployeeInfo data : dataStore) {
            sb.append(data.toString()).append("\n");
        }
        MasterFunction.writeToFile(originalFileName, sb.toString());
    }

    /**
     * Add method
     * @param data1 Data1
     * @param data2 Data2
     * @param data3 Data3
     * @return unique ID
     */
    public int add(String data1, String data2, String data3) {
        int id = idCounter++;
        dataStore.add(new BasicEmployeeInfo(id, MasterFunction.toTitleCase(data1),
                MasterFunction.toTitleCase(data2),
                data3.contains("@") ? data3 : MasterFunction.toTitleCase(data3)));
        return id;
    }

    /**
     * Insert method
     * @param data Employee info
     */
    public void insert(BasicEmployeeInfo data) {
        //if storage is empty, just add it directly
        if (dataStore.isEmpty()) {
            dataStore.add(data);
            return;
        }
        //find position to insert data to by comparing unique ID
        for (int i = dataStore.size() - 1; i >= 0; i--) {
            int currentDataID = dataStore.get(i).id;
            if (currentDataID < data.id) {
                dataStore.add(i + 1, data);
                return;
            }
        }
        //insert to first position if current data unique ID is the smallest
        dataStore.add(0, data);
    }

    /**
     * Delete data inside array list by its index / position
     * @param toDeleteIndex Index to be deleted
     * @throws CustomException if index is invalid number
     */
    public void deleteByIndex(int toDeleteIndex) throws CustomException {
        //Will throw CustomException if invalid index
        checkIsValidIndex(toDeleteIndex);
        dataStore.remove(toDeleteIndex);
    }

    /**
     * Delete data inside array list by finding unique ID
     * @param id Unique ID to be deleted
     * @throws CustomException if unique ID not found inside list
     */
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

    /**
     * Update data by unique ID
     * @param id Unique ID of the data
     * @param data1 Data1
     * @throws CustomException if unique ID not found inside list
     */
    public void updateById(int id, String data1) throws CustomException {
        int index = getIndex(id);
        BasicEmployeeInfo tempData = dataStore.get(index);
        tempData.data1 = MasterFunction.toTitleCase(data1);
    }

    /**
     * Update data by unique ID
     * @param id Unique ID of the data
     * @param data1 Data1
     * @param data2 Data2
     * @throws CustomException if unique ID not found inside list
     */
    public void updateById(int id, String data1, String data2) throws CustomException {
        int index = getIndex(id);
        BasicEmployeeInfo tempData = dataStore.get(index);
        tempData.data1 = MasterFunction.toTitleCase(data1);
        tempData.data2 = MasterFunction.toTitleCase(data2);
    }

    /**
     * Update data by unique ID
     * @param id Unique ID of the data
     * @param data1 Data1
     * @param data2 Data2
     * @param data3 Data3
     * @throws CustomException if unique ID not found inside list
     */
    public void updateById(int id, String data1, String data2, String data3) throws CustomException {
        int index = getIndex(id);
        BasicEmployeeInfo tempData = dataStore.get(index);
        tempData.data1 = MasterFunction.toTitleCase(data1);
        tempData.data2 = MasterFunction.toTitleCase(data2);
        tempData.data3 = data3.contains("@") ? data3 : MasterFunction.toTitleCase(data3);
    }

    /**
     * Get index or position of the data inside list through unique ID
     * @param id Unique ID to get
     * @return Index inside list
     * @throws CustomException if unique ID not found inside list
     */
    public int getIndex(int id) throws CustomException {
        for (int i = 0; i < dataStore.size(); i++) {
            if (id == dataStore.get(i).id)
                return i;
        }
        throw new CustomException("Error! Invalid id!");
    }

    /**
     * Get employee data by index inside list
     * @param index Index
     * @return employee data
     * @throws CustomException if index is invalid number
     */
    public BasicEmployeeInfo getDataByIndex(int index) throws CustomException {
        checkIsValidIndex(index);
        return new BasicEmployeeInfo(dataStore.get(index));
    }

    /**
     * List data
     */
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

    /**
     * Method to check whether index is valid input
     * @param index Index to check
     * @throws CustomException if input is not valid
     */
    public void checkIsValidIndex(int index) throws CustomException {
        if (index < 0 || index >= dataStore.size())
            throw new CustomException("Error! Invalid index!");
    }
}