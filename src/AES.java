import java.util.Arrays;

public class AES {

    /**
     * 列混淆
     * @param n
     * @param roundPart
     * @return int
     */
    public static int[] multiply(int n, String roundPart) {
        int a = 10;
        int b = 11;
        int c = 12;
        int d = 13;
        int e = 14;
        int f = 15;

        int[][] xBox = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, a, b, c, d, e, f},
                {0, 2, 4, 6, 8, a, c, e, 3, 1, 7, 5, b, 9, f, d},
                {0, 3, 6, 5, c, f, a, 9, b, 8, d, e, 7, 4, 1, 2},
                {0, 4, 8, c, 3, 7, b, f, 6, 2, e, a, 5, 1, d, 9},
                {0, 5, a, f, 7, 2, d, 8, e, b, 4, 1, 9, c, 3, 6},
                {0, 6, c, a, b, d, 7, 1, 5, 3, 9, f, e, 8, 2, 4},
                {0, 7, e, 9, f, 8, 1, 6, d, a, 3, 4, 2, 5, c, b},
                {0, 8, 3, b, 6, e, 5, d, c, 4, f, 7, a, 2, 9, 1},
                {0, 9, 1, 8, 2, b, 3, a, 4, d, 5, c, 6, f, 7, e},
                {0, a, 7, d, e, 4, 9, 3, f, 5, 8, 2, 1, b, 6, c},
                {0, b, 5, e, a, 1, f, 4, 7, c, 2, 9, d, 6, 8, 3},
                {0, c, b, 7, 5, 9, e, 2, a, 6, 1, d, f, 3, 4, 8},
                {0, d, 9, 4, 1, c, 8, 5, 2, f, b, 6, 3, e, a, 7},
                {0, e, f, 1, d, 3, 2, c, 9, 7, 6, 8, 4, a, b, 5},
                {0, f, d, 2, 9, 6, 4, b, 1, e, c, 3, 8, 7, 5, a}
        };

        int roundPart10 = Integer.parseInt(roundPart, 2);
        int sii = xBox[n][roundPart10];
        String siiBinary = String.format("%04d", Integer.parseInt(Integer.toBinaryString(sii)));
        int[] sMult = new int[4];
        for (int i = 0; i < 4; i++) {
            sMult[i] = Integer.parseInt(String.valueOf(siiBinary.charAt(i)));
        }

        return sMult;
    }

    /**
     * S盒置换
     * @param w
     * @return int[8]
     */
    public static int[] sBox(String w) {
        String leftHalf = w.substring(4);
        String rightHalf = w.substring(0, 4);

        int[][] SBOX = {
                {9, 4, 10, 11},
                {13, 1, 8, 5},
                {6, 2, 0, 3},
                {12, 14, 15, 7}
        };

        String leftHalf1 = leftHalf.substring(0, 2);
        String leftHalf2 = leftHalf.substring(2, 4);
        String rightHalf1 = rightHalf.substring(0, 2);
        String rightHalf2 = rightHalf.substring(2, 4);

        int leftDecimal1 = Integer.parseInt(leftHalf1, 2);
        int leftDecimal2 = Integer.parseInt(leftHalf2, 2);
        int rightDecimal1 = Integer.parseInt(rightHalf1, 2);
        int rightDecimal2 = Integer.parseInt(rightHalf2, 2);

        int sLeft = SBOX[leftDecimal1][leftDecimal2];
        int sRight = SBOX[rightDecimal1][rightDecimal2];

        String sLeft1Binary = String.format("%04d", Integer.parseInt(Integer.toBinaryString(sLeft)));
        String sRight1Binary = String.format("%04d", Integer.parseInt(Integer.toBinaryString(sRight)));

        String F1Binary = sLeft1Binary + sRight1Binary;
        int[] F1 = new int[8];
        for (int i = 0; i < 8; i++) {
            F1[i] = Integer.parseInt(String.valueOf(F1Binary.charAt(i)));
        }

        return F1;
    }

    /**
     * S盒置换
     * @param roundPart
     * @return F1[4]
     */
    public static int[] sBox1(String roundPart) {
        int[][] SBOX1 = {
                {9, 4, 10, 11},
                {13, 1, 8, 5},
                {6, 2, 0, 3},
                {12, 14, 15, 7}
        };

        String roundPart1 = roundPart.substring(0, 2);
        String roundPart2 = roundPart.substring(2, 4);

        int roundPartDecimal1 = Integer.parseInt(roundPart1, 2);
        int roundPartDecimal2 = Integer.parseInt(roundPart2, 2);

        int roundPartS = SBOX1[roundPartDecimal1][roundPartDecimal2];

        String roundPartSBinary = String.format("%04d", Integer.parseInt(Integer.toBinaryString(roundPartS)));

        int[] F1 = new int[4];
        for (int i = 0; i < 4; i++) {
            F1[i] = Integer.parseInt(String.valueOf(roundPartSBinary.charAt(i)));
        }

        return F1;
    }


    /**
     * 逆s盒
     * @param roundPart
     * @return
     */
    public static int[] sBox2(String roundPart) {
        int[][] SBOX2 = {
                {10, 5, 9, 11},
                {1, 7, 8, 15},
                {6, 0, 2, 3},
                {12, 4, 13, 14}
        };

        String roundPart1 = roundPart.substring(0, 2);
        String roundPart2 = roundPart.substring(2, 4);

        int roundPartDecimal1 = Integer.parseInt(roundPart1, 2);
        int roundPartDecimal2 = Integer.parseInt(roundPart2, 2);

        int roundPartS = SBOX2[roundPartDecimal1][roundPartDecimal2];

        String roundPartSBinary = String.format("%04d", Integer.parseInt(Integer.toBinaryString(roundPartS)));

        int[] F1 = new int[4];
        for (int i = 0; i < 4; i++) {
            F1[i] = Integer.parseInt(String.valueOf(roundPartSBinary.charAt(i)));
        }

        return F1;
    }

    /**
     * 将输入的字符转化为二进制位数组，并返回这个位数组
     * @param kerOrtext
     * @return
     */
    public int[] characterProcess(String kerOrtext) {
        int kerOrtextAscii1 = (int) kerOrtext.charAt(0);
        int kerOrtextAscii2 = (int) kerOrtext.charAt(1);

        String kerOrtextBinary1 = String.format("%08d", Integer.parseInt(Integer.toBinaryString(kerOrtextAscii1)));
        String kerOrtextBinary2 = String.format("%08d", Integer.parseInt(Integer.toBinaryString(kerOrtextAscii2)));

        int[] kerOrtext1 = new int[8];
        int[] kerOrtext2 = new int[8];

        for (int i = 0; i < 8; i++) {
            kerOrtext1[i] = Character.getNumericValue(kerOrtextBinary1.charAt(i));
            kerOrtext2[i] = Character.getNumericValue(kerOrtextBinary2.charAt(i));
        }

        int[] kerOrtextArr = new int[16];
        System.arraycopy(kerOrtext1, 0, kerOrtextArr, 0, 8);
        System.arraycopy(kerOrtext2, 0, kerOrtextArr, 8, 8);

        return kerOrtextArr;
    }

    /**
     * 密钥处理，密钥经过一系列变换得到三个不同的轮密钥
     * @param key
     * @return
     */
    public static int[][] keyProcess(int[] key) {
        int[] RC1 = {1, 0, 0, 0, 0, 0, 0, 0};
        int[] RC2 = {0, 0, 1, 1, 0, 0, 0, 0};

        int[] w0 = Arrays.copyOfRange(key, 0, 8);
        int[] w1 = Arrays.copyOfRange(key, 8, 16);

        // 转换为String类型匹配方法
        String w1string = AESUtils.arrayToString(w1);

        int[] sBoxW1 = sBox(w1string);

        int[] g1 = AESUtils.xor(sBoxW1, RC1);

        int[] w2 = AESUtils.xor(w0, g1);
        int[] w3 = AESUtils.xor(w1, w2);

        // 转换为String类型匹配方法
        String w3string = AESUtils.arrayToString(w3);
        int[] sBoxW3 = sBox(w3string);

        int[] g2 = AESUtils.xor(sBoxW3, RC2);

        int[] w4 = AESUtils.xor(w2, g2);
        int[] w5 = AESUtils.xor(w3, w4);

        int[] key1 = key;
        int[] key2 = new int[16];
        int[] key3 = new int[16];

        System.arraycopy(w2, 0, key2, 0, 8);
        System.arraycopy(w3, 0, key2, 8, 8);

        System.arraycopy(w4, 0, key3, 0, 8);
        System.arraycopy(w5, 0, key3, 8, 8);

        return new int[][]{key1, key2, key3};
    }

    /**
     * 加密操作
     * @param plaintext
     * @param key
     * @return
     */
    public static int[] encrypt(int[] plaintext, int[] key) {
        int[] key1, key2, key3;
        int tempkey[][] = keyProcess(key);
        key1 = tempkey[0];
        key2 = tempkey[1];
        key3 = tempkey[2];

        int[] roundKeyAddition = AESUtils.xor(plaintext, key1);

        // 第一轮
        int[] roundPart00 = Arrays.copyOfRange(roundKeyAddition, 0, 4);
        int[] roundPart10 = Arrays.copyOfRange(roundKeyAddition, 4, 8);
        int[] roundPart01 = Arrays.copyOfRange(roundKeyAddition, 8, 12);
        int[] roundPart11 = Arrays.copyOfRange(roundKeyAddition, 12, 16);

        // 转换成String
        String roundPart00String = AESUtils.arrayToString(roundPart00);
        String roundPart10String = AESUtils.arrayToString(roundPart10);
        String roundPart01String = AESUtils.arrayToString(roundPart01);
        String roundPart11String = AESUtils.arrayToString(roundPart11);


        int[] roundPartS00 = sBox1(roundPart00String);
        int[] roundPartS10 = sBox1(roundPart10String);
        int[] roundPartS01 = sBox1(roundPart01String);
        int[] roundPartS11 = sBox1(roundPart11String);

        int[] temp = roundPartS10.clone();
        roundPartS10 = roundPartS11.clone();
        roundPartS11 = temp;

        // 转换成String
        String roundPartS00String = AESUtils.arrayToString(roundPartS00);
        String roundPartS10String = AESUtils.arrayToString(roundPartS10);
        String roundPartS01String = AESUtils.arrayToString(roundPartS01);
        String roundPartS11String = AESUtils.arrayToString(roundPartS11);


        int[] S00 = AESUtils.xor(multiply(1, roundPartS00String), multiply(4, roundPartS10String));
        int[] S10 = AESUtils.xor(multiply(4, roundPartS00String), multiply(1, roundPartS10String));
        int[] S01 = AESUtils.xor(multiply(1, roundPartS01String), multiply(4, roundPartS11String));
        int[] S11 = AESUtils.xor(multiply(4, roundPartS01String), multiply(1, roundPartS11String));

        int[] beforeFirstRoundResults = AESUtils.concatenate(S00, S10, S01, S11);
        int[] firstRoundResults = AESUtils.xor(beforeFirstRoundResults, key2);

        // 第二轮
        int[] roundPart2_00 = Arrays.copyOfRange(firstRoundResults, 0, 4);
        int[] roundPart2_10 = Arrays.copyOfRange(firstRoundResults, 4, 8);
        int[] roundPart2_01 = Arrays.copyOfRange(firstRoundResults, 8, 12);
        int[] roundPart2_11 = Arrays.copyOfRange(firstRoundResults, 12, 16);

        // 转换成String
        String roundPart2_00String = AESUtils.arrayToString(roundPart2_00);
        String roundPart2_10String = AESUtils.arrayToString(roundPart2_10);
        String roundPart2_01String = AESUtils.arrayToString(roundPart2_01);
        String roundPart2_11String = AESUtils.arrayToString(roundPart2_11);


        int[] roundPartS2_00 = sBox1(roundPart2_00String);
        int[] roundPartS2_10 = sBox1(roundPart2_10String);
        int[] roundPartS2_01 = sBox1(roundPart2_01String);
        int[] roundPartS2_11 = sBox1(roundPart2_11String);

        int[] temp2 = roundPartS2_10.clone();
        roundPartS2_10 = roundPartS2_11.clone();
        roundPartS2_11 = temp2;

        int[] beforeSecondRoundResults = AESUtils.concatenate(roundPartS2_00, roundPartS2_10, roundPartS2_01, roundPartS2_11);
        int[] secondRoundResults = AESUtils.xor(beforeSecondRoundResults, key3);

        return secondRoundResults;
    }

    /**
     * 解密
     * @param ciphertext
     * @param key
     * @return
     */
    public static int[] decrypt(int[] ciphertext, int[] key) {
        int[] key1, key2, key3;
        int tempkey[][] = keyProcess(key);
        key1 = tempkey[0];
        key2 = tempkey[1];
        key3 = tempkey[2];

        int[] roundKeyAddition = AESUtils.xor(ciphertext, key3);

        // 第一轮
        int[] roundPart00 = Arrays.copyOfRange(roundKeyAddition, 0, 4);
        int[] roundPart10 = Arrays.copyOfRange(roundKeyAddition, 4, 8);
        int[] roundPart01 = Arrays.copyOfRange(roundKeyAddition, 8, 12);
        int[] roundPart11 = Arrays.copyOfRange(roundKeyAddition, 12, 16);

        int[] temp = roundPart10.clone();
        roundPart10 = roundPart11.clone();
        roundPart11 = temp;

        // 转换成String
        String roundPart00String = AESUtils.arrayToString(roundPart00);
        String roundPart10String = AESUtils.arrayToString(roundPart10);
        String roundPart01String = AESUtils.arrayToString(roundPart01);
        String roundPart11String = AESUtils.arrayToString(roundPart11);

        int[] roundPartS00 = sBox2(roundPart00String);
        int[] roundPartS10 = sBox2(roundPart10String);
        int[] roundPartS01 = sBox2(roundPart01String);
        int[] roundPartS11 = sBox2(roundPart11String);

        int[] roundHolePart = AESUtils.concatenate(roundPartS00, roundPartS10, roundPartS01, roundPartS11);
        int[] roundHolePartXor = AESUtils.xor(roundHolePart, key2);
        roundPartS00 = Arrays.copyOfRange(roundHolePartXor, 0, 4);
        roundPartS10 = Arrays.copyOfRange(roundHolePartXor, 4, 8);
        roundPartS01 = Arrays.copyOfRange(roundHolePartXor, 8, 12);
        roundPartS11 = Arrays.copyOfRange(roundHolePartXor, 12, 16);


        // 转换成String
        String roundPartS00String = AESUtils.arrayToString(roundPartS00);
        String roundPartS10String = AESUtils.arrayToString(roundPartS10);
        String roundPartS01String = AESUtils.arrayToString(roundPartS01);
        String roundPartS11String = AESUtils.arrayToString(roundPartS11);

        int[] S00 = AESUtils.xor(multiply(9, roundPartS00String), multiply(2, roundPartS10String));
        int[] S01 = AESUtils.xor(multiply(2, roundPartS00String), multiply(9, roundPartS10String));
        int[] S10 = AESUtils.xor(multiply(9, roundPartS01String), multiply(2, roundPartS11String));
        int[] S11 = AESUtils.xor(multiply(2, roundPartS01String), multiply(9, roundPartS11String));

        // 第二轮
        int[] roundPart2_00 = S00;
        int[] roundPart2_10 = S01;
        int[] roundPart2_01 = S10;
        int[] roundPart2_11 = S11;

        int[] temp2 = roundPart2_10.clone();
        roundPart2_10 = roundPart2_11.clone();
        roundPart2_11 = temp2;

        // 转换成String
        String roundPart2_00String = AESUtils.arrayToString(roundPart2_00);
        String roundPart2_10String = AESUtils.arrayToString(roundPart2_10);
        String roundPart2_01String = AESUtils.arrayToString(roundPart2_01);
        String roundPart2_11String = AESUtils.arrayToString(roundPart2_11);

        int[] roundPartS2_00 = sBox2(roundPart2_00String);
        int[] roundPartS2_10 = sBox2(roundPart2_10String);
        int[] roundPartS2_01 = sBox2(roundPart2_01String);
        int[] roundPartS2_11 = sBox2(roundPart2_11String);

        int[] beforeSecondRoundResults = AESUtils.concatenate(roundPartS2_00, roundPartS2_10, roundPartS2_01, roundPartS2_11);
        int[] secondRoundResults = AESUtils.xor(beforeSecondRoundResults, key1);

        return secondRoundResults;
    }

    /**
     * 双重加密
     * @param ciphertext
     * @param key
     * @return
     */
    public static int[] doubleEncrypt(int[] ciphertext, int[] key) {
        int[] K2 = new int[16];
        int[] K1 = new int[16];

        // 从密钥数组中提取 K1
        for (int i = 0; i < 16; i++) {
            K1[i] = key[i];
        }

        // 从密钥数组中提取 K2
        for (int i = 16; i < 32; i++) {
            K2[i - 16] = key[i];
        }

        int[] firstDecryption = decrypt(ciphertext, K1);
        // 第二次加密
        int[] plaintext = decrypt(firstDecryption, K2);

        return plaintext;
    }


    /**
     * 双重解密
     * @param ciphertext
     * @param key
     * @return
     */
    public static int[] doubleDecrypt(int[] ciphertext, int[] key) {
        int[] K2 = new int[16];
        int[] K1 = new int[16];

        // 从密钥数组中提取 K1
        for (int i = 0; i < 16; i++) {
            K1[i] = key[i];
        }

        for (int i = 16; i < 32; i++) {
            K2[i - 16] = key[i];
        }

        int[] firstDecryption = decrypt(ciphertext, K1);
        int[] plaintext = decrypt(firstDecryption, K2);

        return plaintext;
    }


    public static void main(String[] args) {
        int[] ciphertext = {1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1};  // 示例密文
        int[] plaintext = {1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1};   // 示例明文
        int[] key = {0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0};         // 示例密钥


        int[] ciphertext1 = encrypt(plaintext, key);
        int[] plaintext1 = decrypt(ciphertext, key);

        System.out.print("密文: ");
        for (int bit : ciphertext1) {
            System.out.print(bit);
        }

        System.out.println();

        System.out.print("明文: ");
        for (int bit : plaintext1) {
            System.out.print(bit);
        }

        System.out.println();

        System.out.print("密钥: ");
        for (int bit : key) {
            System.out.print(bit);
        }


        String s = "1010101010101010";
        int[] arr = AESUtils.stringToIntegerArray(s);
        System.out.println(arr);
    }


}



