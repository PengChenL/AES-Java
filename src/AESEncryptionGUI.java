import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AESEncryptionGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("AES Encryption/Decryption");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new GridBagLayout()); // Use GridBagLayout


        // Center the window on the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screenSize.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);

        JPanel inputPanel = new JPanel();
        JLabel inputLabel = new JLabel("Input Text: ");
        JTextField inputField = new JTextField(20);
        inputField.setFont(new Font("Arial", Font.PLAIN, 24));
        inputPanel.add(inputLabel);
        inputPanel.add(inputField);

        JPanel keyPanel = new JPanel();
        JLabel keyLabel = new JLabel("Key: ");
        JTextField keyField = new JTextField(20);
        keyField.setFont(new Font("Arial", Font.PLAIN, 24));
        keyPanel.add(keyLabel);
        keyPanel.add(keyField);

        JPanel resultPanel = new JPanel();
        JLabel resultLabel = new JLabel("Result: ");
        JTextField resultField = new JTextField(20);
        resultField.setFont(new Font("Arial", Font.PLAIN, 24));
        resultField.setEditable(false);
        resultPanel.add(resultLabel);
        resultPanel.add(resultField);

        JPanel buttonPanel = new JPanel();
        JButton encryptButton = new JButton("Encrypt");
        JButton decryptButton = new JButton("Decrypt");
        JButton clearButton = new JButton("Clear");
        encryptButton.setFont(new Font("Arial", Font.BOLD, 20));
        decryptButton.setFont(new Font("Arial", Font.BOLD, 20));
        clearButton.setFont(new Font("Arial", Font.BOLD, 20));
        buttonPanel.add(encryptButton);
        buttonPanel.add(decryptButton);
        buttonPanel.add(clearButton);

        JButton doubleEncryptButton = new JButton("DoubleEncrypt");
        JButton doubleDecryptButton = new JButton("DoubleDecrypt");
        doubleEncryptButton.setFont(new Font("Arial", Font.BOLD, 20));
        doubleDecryptButton.setFont(new Font("Arial", Font.BOLD, 20));
        JPanel buttonPanel2 = new JPanel();
        buttonPanel2.add(doubleEncryptButton);
        buttonPanel2.add(doubleDecryptButton);

        // Add components with GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(inputPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(keyPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.add(resultPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        frame.add(buttonPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        frame.add(buttonPanel2, gbc);



        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = inputField.getText();
                String keyText = keyField.getText();

                if(AESUtils.isLegalInput(inputText) && AESUtils.isLegalInput(keyText)) {
                    if(inputText.length() == 2){
                        String temp1 = AESUtils.charToBinaryString(inputText);
                        int[] encryptedText = AES.encrypt(AESUtils.stringToIntegerArray(temp1), AESUtils.stringToIntegerArray(keyText));
                        String encryptedTextString = AESUtils.arrayToString(encryptedText);
                        String temp2 = AESUtils.binaryStringToTwoCharacterString(encryptedTextString);
                        resultField.setText(temp2);
                    }
                    else{
                        int[] encryptedText = AES.encrypt(AESUtils.stringToIntegerArray(inputText), AESUtils.stringToIntegerArray(keyText));
                        String encryptedTextString = AESUtils.arrayToString(encryptedText);
                        resultField.setText(encryptedTextString);
                    }

                }else{
                    JOptionPane.showMessageDialog(frame, "Input text or key is not legal!");
                    return;
                }
            }
        });

        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = inputField.getText();
                String keyText = keyField.getText();

                if(AESUtils.isLegalInput(inputText) && AESUtils.isLegalInput(keyText)) {
                    if(inputText.length() == 2){
                        String temp1 = AESUtils.charToBinaryString(inputText);
                        int[] encryptedText = AES.decrypt(AESUtils.stringToIntegerArray(temp1), AESUtils.stringToIntegerArray(keyText));
                        String encryptedTextString = AESUtils.arrayToString(encryptedText);
                        String temp2 = AESUtils.binaryStringToTwoCharacterString(encryptedTextString);
                        resultField.setText(temp2);
                    }
                    else{
                        int[] encryptedText = AES.decrypt(AESUtils.stringToIntegerArray(inputText), AESUtils.stringToIntegerArray(keyText));
                        String encryptedTextString = AESUtils.arrayToString(encryptedText);
                        resultField.setText(encryptedTextString);
                    }

                }else{
                    JOptionPane.showMessageDialog(frame, "Input text or key is not legal!");
                    return;
                }

            }

        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputField.setText("");
                keyField.setText("");
                resultField.setText("");
            }
        });


        // 双重加密按钮实现
        doubleEncryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = inputField.getText();
                String keyText = keyField.getText();

                if(AESUtils.isLegalInput(inputText) && AESUtils.isLegalInput(keyText)) {
                    if(inputText.length() == 2){
                        String temp1 = AESUtils.charToBinaryString(inputText);
                        int[] encryptedText = AES.doubleEncrypt(AESUtils.stringToIntegerArray(temp1), AESUtils.stringToIntegerArray(keyText));
                        String encryptedTextString = AESUtils.arrayToString(encryptedText);
                        String temp2 = AESUtils.binaryStringToTwoCharacterString(encryptedTextString);
                        resultField.setText(temp2);
                    }
                    else{
                        int[] encryptedText = AES.doubleEncrypt(AESUtils.stringToIntegerArray(inputText), AESUtils.stringToIntegerArray(keyText));
                        String encryptedTextString = AESUtils.arrayToString(encryptedText);
                        resultField.setText(encryptedTextString);
                    }

                }else{
                    JOptionPane.showMessageDialog(frame, "Input text or key is not legal!");
                    return;
                }
            }
        });

        // 双重解密按钮实现
        doubleDecryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = inputField.getText();
                String keyText = keyField.getText();

                if(AESUtils.isLegalInput(inputText) && AESUtils.isLegalInput(keyText)) {
                    if(inputText.length() == 2){
                        String temp1 = AESUtils.charToBinaryString(inputText);
                        int[] encryptedText = AES.doubleDecrypt(AESUtils.stringToIntegerArray(temp1), AESUtils.stringToIntegerArray(keyText));
                        String encryptedTextString = AESUtils.arrayToString(encryptedText);
                        String temp2 = AESUtils.binaryStringToTwoCharacterString(encryptedTextString);
                        resultField.setText(temp2);
                    }
                    else{
                        int[] encryptedText = AES.doubleDecrypt(AESUtils.stringToIntegerArray(inputText), AESUtils.stringToIntegerArray(keyText));
                        String encryptedTextString = AESUtils.arrayToString(encryptedText);
                        resultField.setText(encryptedTextString);
                    }

                }else{
                    JOptionPane.showMessageDialog(frame, "Input text or key is not legal!");
                    return;
                }

            }

        });


        frame.setVisible(true);
    }
}
