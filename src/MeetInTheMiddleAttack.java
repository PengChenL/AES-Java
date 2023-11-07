import java.util.Arrays;

/**
 * 中间相遇攻击实现
 */
public class MeetInTheMiddleAttack {

    public static int[] findKey(int[] plaintext1, int[] ciphertext1, int[] plaintext2, int[] ciphertext2) {
        // 遍历所有可能的密钥
        for (int key = 0; key < 65536; key++) {
            int[] keyBinary = new int[16];
            String binaryString = Integer.toBinaryString(key);
            binaryString = String.format("%16s", binaryString).replace(' ', '0'); // 将密钥转换为16位二进制表示

            for (int i = 0; i < 16; i++) {
                keyBinary[i] = Integer.parseInt(String.valueOf(binaryString.charAt(i)));
            }

            // 加密明文1和明文2，获取中间计算结果
            int[] intermediateResults1 = getIntermediateResults(plaintext1, keyBinary);
            int[] intermediateResults2 = getIntermediateResults(plaintext2, keyBinary);

            // 判断中间计算结果是否相等
            if (Arrays.equals(intermediateResults1, intermediateResults2)) {
                // 对两个轮密钥的异或结果进行异或运算，得到正确的密钥
                int[] correctKey = AESUtils.xor(intermediateResults1, intermediateResults2);
                return correctKey;
            }
        }
        return null;
    }

    // 假设存在一个 getIntermediateResults 方法用于获取中间计算结果
    public static int[] getIntermediateResults(int[] plaintext, int[] key) {
        // 实现获取中间计算结果的逻辑
        // 这里只是一个占位符，你需要替换成你的实际逻辑
        return new int[16];
    }


    public static void main(String[] args) {
        int[] key1 = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        int[] plaintext1 = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        int[] ciphertext1 = AES.encrypt(plaintext1, key1);

        int[] plaintext2 = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int[] ciphertext2 = AES.encrypt(plaintext2, key1);

        int[] key = findKey(plaintext1, ciphertext1, plaintext2, ciphertext2);


        System.out.print("Correct key: ");
        for (int bit : key) {
            System.out.print(bit);
        }
    }
}
