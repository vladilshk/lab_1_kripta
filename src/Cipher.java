import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class Cipher {
    public String coding(String message) throws IOException {
        Map<Character, String> codingAlphabet = getAlphabetForCoding();
        message = message.toLowerCase();
        StringBuilder codingMessage = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            int symbolNum = (int) message.charAt(i);
            if(codingAlphabet.containsKey(message.charAt(i))){
                codingMessage.append(codingAlphabet.get(message.charAt(i)));
            }
            else{
                codingMessage.append(message.charAt(i));
            }
        }
        return codingMessage.toString();
    }

    public String deCoding(String message) throws IOException {
        Map<String, Character> decodingAlphabet = getAlphabetForDecoding();
        StringBuilder decodingMessage = new StringBuilder();
        for (int i = 0; i < message.length(); i += 2) {
            if((int)message.charAt(i) > 122 || (int)message.charAt(i) < 97){
                decodingMessage.append(message.charAt(i));
                i--;
            }
            else {
                String letter = String.valueOf(message.charAt(i)) + String.valueOf(message.charAt(i + 1));
                decodingMessage.append(decodingAlphabet.get(letter));
            }
        }
        return decodingMessage.toString();
    }

    public void createAlphabet() throws IOException {
        Map<Character, String> codingAlphabet = new LinkedHashMap<>();
        Random random = new Random();
        for (int i = 0; i < 26; i++) {
            while (true){
                char firstSymbol = (char) (random.nextInt(26) + 'a');
                char secondSymbol = (char) (random.nextInt(26) + 'a');
                String symbol = String.valueOf(firstSymbol) + String.valueOf(secondSymbol);

                if(!codingAlphabet.containsValue(symbol)){
                    codingAlphabet.put((char)(i + 'a'), symbol);
                    break;
                }
            }
        }
        System.out.println(codingAlphabet);
        FileWriter fileWriter = new FileWriter("alphabet");
        fileWriter.write(codingAlphabet.toString());
        fileWriter.close();
    }

    public Map<String, Character> getAlphabetForDecoding() throws IOException {
        FileReader fileReader = new FileReader("alphabet");
        char[] buffer = new char[1024];
        fileReader.read(buffer);
        fileReader.close();
        Map<String, Character> alphabet = new LinkedHashMap<>();
        int i = 1;
        while (true){
            alphabet.put(String.valueOf(buffer[i+2]) + String.valueOf(buffer[i+3]), buffer[i]);
            i += 6;
            if(buffer[i -2] == '}'){
                break;
            }
        }
        return alphabet;
    }

    public Map<Character, String> getAlphabetForCoding() throws IOException {
        FileReader fileReader = new FileReader("alphabet");
        char[] buffer = new char[1024];
        fileReader.read(buffer);
        fileReader.close();
        Map<Character, String> alphabet = new LinkedHashMap<>();
        int i = 1;
        while (true){
            alphabet.put(buffer[i], String.valueOf(buffer[i+2]) + String.valueOf(buffer[i+3]));
            i += 6;
            if(buffer[i -2] == '}'){
                break;
            }
        }
        return alphabet;
    }

    public String frequencyAnalysis(String message){
        Map<Character, Double> letters = new LinkedHashMap<>();
        for (int i = 0; i < 26; i++) {
            letters.put((char)((i) + 'a'), (double) 0);
        }

        for (int i = 0; i < message.length(); i++) {
            if(letters.containsKey(message.charAt(i))){
                letters.put(message.charAt(i), letters.get(message.charAt(i)) + 1.0);
            }
        }

        StringBuilder st = new StringBuilder();
        NumberFormat nf = new DecimalFormat("#.##");
        for(Character key : letters.keySet()){
            letters.put(key,  letters.get(key) / message.length());
            st.append(key + " = " + nf.format(letters.get(key)));
            st.append("; ");
        }
        return st.toString();
    }


}
