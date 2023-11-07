import java.util.Arrays;

public class TripleEn_Decrypt {

    public static int[] tripleEncrypt(int[] ciphertext, int[] key){
        int[] K1 = Arrays.copyOfRange(key, 0, 16);
        int[] K2 = Arrays.copyOfRange(key, 16, 32);
        int[] K3 = Arrays.copyOfRange(key, 32, key.length);


        int[] firstEncryption = AES.encrypt(ciphertext, K3);

        int[] secondEncryption = AES.encrypt(firstEncryption, K2);

        int[] plaintext = AES.encrypt(secondEncryption, K1);

        return plaintext;
    }


    public static int[] tripleDecrypt(int[] ciphertext, int[] key){
        int[] K1 = Arrays.copyOfRange(key, 0, 16);
        int[] K2 = Arrays.copyOfRange(key, 16, 32);
        int[] K3 = Arrays.copyOfRange(key, 32, key.length);

        // 调用 decrypt 方法执行第一次解密
        int[] firstDecryption = AES.decrypt(ciphertext, K3);

        // 调用 decrypt 方法执行第二次解密，使用K2作为密钥
        int[] secondDecryption = AES.decrypt(firstDecryption, K2);

        // 调用 decrypt 方法执行第三次解密，使用K1作为密钥
        int[] plaintext = AES.decrypt(secondDecryption, K1);

        return plaintext;
    }

    public static void main(String[] args) {
        int[] ciphertext = {0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1};
        // int[] plaintext = {1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1};
        int[] key = {1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1};


        int[] plaintext = tripleDecrypt(ciphertext, key);

        System.out.print("Decrypted Text: ");
        for (int num : plaintext) {
            System.out.print(num);
        }
    }
}
