import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        Cipher cipher = new Cipher();
        cipher.createAlphabet();

        System.out.println("Message: ");
        String message = "hello! my name is vova. I am from Russia. How are you? I'm nice. And what about you?";
        System.out.println(message);

        System.out.println("Coded message: ");
        message = cipher.coding(message);
        System.out.println(message);

        System.out.println("frequencyAnalysis: ");
        System.out.println(cipher.frequencyAnalysis(message));

        System.out.println("Decoded message: ");
        message = cipher.deCoding(message);
        System.out.println(message);

    }
}