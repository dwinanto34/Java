package basic;

public class DataType {
    public static void main(String[] args) {
        System.out.println("Data type primitive");

        // Data type : Number
        byte byteNum = 100;
        short shortNum = 1000;
        int intNum = 10000;
        long longNum = 100000;

        // Data type : Floating number
        float floatNum = 100.05f;
        double doubleNum = 100.5d;

        // widening casting
        long tempLong = intNum;

        // narrowing casting (must be cautious using this)
        int tempInt = (int) tempLong;

        // math operation
        int result = tempInt * intNum;

        char a = 'a';
        boolean even = true;

        System.out.println("Data type object");
        String word = "word";
        Character ch = 'a';
        Integer intObj = 1;
        Boolean evenObj = false;
    }
}
