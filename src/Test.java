import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        Cipher cipher = new Cipher();
        cipher.createAlphabet();
        String message = "hello! my name is vova";
        System.out.println(message);
        message = cipher.coding(message);

        System.out.println(cipher.frequencyAnalysis(message));


        System.out.println(message);
        message = cipher.deCoding(message);
        System.out.println(message);

    }
}