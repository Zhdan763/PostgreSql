package util;

public class ArrayConverter {

    public ArrayConverter() {
    }

    public int [] stringArrayToIntArray (String [] stringArray) {
        int [] intArray = new int[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            intArray[i] = Integer.parseInt(stringArray[i]);
        }
        return intArray;
    }
}
