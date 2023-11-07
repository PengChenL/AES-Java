import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class AESUtils {
    /**
     * 异或操作
     * @param arr1
     * @param arr2
     * @return
     */
    public static int[] xor(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            throw new IllegalArgumentException("Input arrays must have the same length.");
        }

        int[] result = new int[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            result[i] = arr1[i] ^ arr2[i];
        }

        return result;
    }


    /**
     * 数组转换为字符串
     * @param array
     * @return
     */
    public static String arrayToString(int[] array) {
        if (array == null) {
            return "null";
        }
        int length = array.length;
        if (length == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(array[i]);
            if (i < length - 1) {
                sb.append("");
            }
        }
        return sb.toString();
    }

    /**
     * 字符串拼接在一起
     * @param arrays
     * @return
     */
    public static int[] concatenate(int[]... arrays) {
        int totalLength = 0;
        for (int[] array : arrays) {
            totalLength += array.length;
        }

        int[] result = new int[totalLength];
        int destPos = 0;

        for (int[] array : arrays) {
            System.arraycopy(array, 0, result, destPos, array.length);
            destPos += array.length;
        }

        return result;
    }

    /**
     * 判断输入是否合法
     * @param input
     * @return
     */
    public static boolean isLegalInput(String input) {
        boolean flag = false;
        if(input.matches("[01]{16}")) {
            flag = true;
        }

        if(input.matches("[01]{32}")) {
            flag = true;
        }

        if(input.length() == 2 && (int) input.charAt(0) >= 0 && (int) input.charAt(0) <= 127 &&
        (int) input.charAt(1) >= 0 && (int) input.charAt(1) <= 127){
            flag = true;
        }

        return flag;

    }

    /**
     * 字符转换为二进制
     * @param s
     * @return
     */
    public static String charToBinaryString(String s) {
        char firstCharacter = s.charAt(0);
        char secondCharacter = s.charAt(1);

        int firstAsciiValue = (int) firstCharacter;
        int secondAsciiValue = (int) secondCharacter;

        String binaryRepresentation1 = String.format("%8s", Integer.toBinaryString(firstAsciiValue)).replace(' ', '0');
        String binaryRepresentation2 = String.format("%8s", Integer.toBinaryString(secondAsciiValue)).replace(' ', '0');

        return binaryRepresentation1 + binaryRepresentation2;
    }

    /**
     * 字符串转换为整数数组
     * @param input
     * @return
     */
    public static int[] stringToIntegerArray(String input) {
        int length = input.length();
        int[] numberArray = new int[length];

        for (int i = 0; i < length; i++) {
            char charAtI = input.charAt(i);
            if (charAtI == '0') {
                numberArray[i] = 0;
            } else if (charAtI == '1') {
                numberArray[i] = 1;
            } else {
                throw new IllegalArgumentException("Invalid binary character at position " + i);
            }
        }

        return numberArray;
    }

    /**
     * 二进制转换为长度为2的字符串
     * @param binaryString
     * @return
     */
    public static String binaryStringToTwoCharacterString(String binaryString) {
        if (binaryString.length() != 16) {
            throw new IllegalArgumentException("Input binary string must be exactly 16 characters long.");
        }

        int firstValue = Integer.parseInt(binaryString.substring(0, 8), 2);
        int secondValue = Integer.parseInt(binaryString.substring(8, 16), 2);

        char firstChar = (char) firstValue;
        char secondChar = (char) secondValue;

        return String.valueOf(firstChar) + String.valueOf(secondChar);
    }

}
