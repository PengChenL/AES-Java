import java.util.Arrays;

public class CBC_workmode {
    public static int[] cbcEncrypt(int[] plaintext, int[] iv, int[] key) {
        int[] ciphertext = new int[plaintext.length];

        int[] ivTemp = Arrays.copyOf(iv, iv.length); // 用于保存前一个块的密文

        int blockSize = iv.length; // 块的大小

        for (int i = 0; i < plaintext.length; i += blockSize) {
            int[] block = Arrays.copyOfRange(plaintext, i, i + blockSize); // 获取当前块

            // 与前一个块的密文进行异或运算
            int[] K = AESUtils.xor(block, ivTemp);

            // 使用密钥加密当前块
            int[] encryptedBlock = AES.encrypt(K, key);

            // 将加密后的块作为密文
            System.arraycopy(encryptedBlock, 0, ciphertext, i, blockSize);

            // 更新前一个块的密文为当前块的密文
            ivTemp = Arrays.copyOf(encryptedBlock, blockSize);
        }

        return ciphertext;

    }

    public static int[] cbcDecrypt(int[] ciphertext, int[] iv, int[] key) {
        int[] plaintext = new int[ciphertext.length];

        int[] ivTemp = Arrays.copyOf(iv, iv.length); // 用于保存前一个块的密文

        int blockSize = iv.length; // 块的大小

        for (int i = 0; i < ciphertext.length; i += blockSize) {
            int[] block = Arrays.copyOfRange(ciphertext, i, i + blockSize); // 获取当前块

            // 使用密钥解密当前块
            int[] decryptedBlock = AES.decrypt(block, key);

            // 与前一个块的密文进行异或运算
            int[] plaintextBlock = AESUtils.xor(decryptedBlock, ivTemp);

            // 将解密后的块作为明文
            System.arraycopy(plaintextBlock, 0, plaintext, i, blockSize);

            // 更新前一个块的密文为当前块
            ivTemp = Arrays.copyOf(block, blockSize);
        }

        return plaintext;

    }

    public static void main(String[] args) {

        int[] ciphertext = {
                1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1,
                0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1,
                1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0,
                0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1
        };  // 示例密文

        int[] plaintext = {
                1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,
                1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,
                1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,
                1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0
        };  // 示例明文

        int[] key = {
                0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1
        };  // 示例密钥

        int[] iv = {
                1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1
        };  // 初始向量

        int[] ciphertext1 = cbcEncrypt(plaintext, iv, key);
        int[] plaintext1 = cbcDecrypt(ciphertext, iv, key);

        System.out.println("Ciphertext: " + Arrays.toString(ciphertext1));
        System.out.println("Plaintext: " + Arrays.toString(plaintext1));
    }
}
