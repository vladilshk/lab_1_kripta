import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class Cipher {
    public String coding(String message) throws IOException {
        Map<Integer, String> codingAlphabet = getAlphabetForCoding();
        message = message.toLowerCase();
        StringBuilder codingMessage = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            int symbolNum = (int) message.charAt(i);
            if(symbolNum > 96 && symbolNum < 123){
                codingMessage.append(codingAlphabet.get(symbolNum - 97));
            }
            else{
                codingMessage.append(message.charAt(i));
            }
        }
        return codingMessage.toString();
    }

    public String deCoding(String message) throws IOException {
        Map<String, Integer> decodingAlphabet = getAlphabetForDecoding();
        StringBuilder decodingMessage = new StringBuilder();
        for (int i = 0; i < message.length(); i += 2) {
            if((int)message.charAt(i) > 122 || (int)message.charAt(i) < 97){
                decodingMessage.append(message.charAt(i));
                i--;
            }
            else {
                String letter = String.valueOf(message.charAt(i)) + String.valueOf(message.charAt(i + 1));
                decodingMessage.append((char)(decodingAlphabet.get(letter) + 'a'));
            }
        }
        return decodingMessage.toString();
    }

    public void createAlphabet() throws IOException {
        Map<Integer, String> codingAlphabet = new LinkedHashMap<>();
        Random random = new Random();
        for (int i = 0; i < 26; i++) {
            while (true){
                char firstSymbol = (char) (random.nextInt(26) + 'a');
                char secondSymbol = (char) (random.nextInt(26) + 'a');
                String symbol = String.valueOf(firstSymbol) + String.valueOf(secondSymbol);

                if(!codingAlphabet.containsValue(symbol)){
                    codingAlphabet.put(i, symbol);
                    break;
                }
            }
        }
        System.out.println(codingAlphabet);
        FileWriter fileWriter = new FileWriter("alphabet");
        fileWriter.write(codingAlphabet.toString());
        fileWriter.close();
    }

    public Map<String, Integer> getAlphabetForDecoding() throws IOException {
        FileReader fileReader = new FileReader("alphabet");
        char[] buffer = new char[1024];
        fileReader.read(buffer);
        fileReader.close();
        Map<String, Integer> alphabet = new LinkedHashMap<>();
        int i = 1;
        while (buffer[i + 7] != '}'){
            alphabet.put(String.valueOf(buffer[i+2]) + String.valueOf(buffer[i+3]), (int)buffer[i] - 48);
            i += 6;
            if(i == 61)
                break;
        }
        while (buffer[i -2] != '}'){
            alphabet.put(String.valueOf(buffer[i+3]) + String.valueOf(buffer[i+4]), (int)(buffer[i] -48) * 10 + (int)buffer[i+1] - 48);
            i += 7;
        }
        return alphabet;
    }

    public Map<Integer, String> getAlphabetForCoding() throws IOException {
        FileReader fileReader = new FileReader("alphabet");
        char[] buffer = new char[1024];
        fileReader.read(buffer);
        fileReader.close();
        Map<Integer, String> alphabet = new LinkedHashMap<>();
        int i = 1;
        while (buffer[i + 7] != '}'){
            alphabet.put((int)buffer[i] - 48, String.valueOf(buffer[i+2]) + String.valueOf(buffer[i+3]));
            i += 6;
            if(i == 61)
                break;
        }
        while (buffer[i -2] != '}'){
            alphabet.put((int)(buffer[i] -48) * 10 + (int)buffer[i+1] - 48,  String.valueOf(buffer[i+3]) + String.valueOf(buffer[i+4]));
            i += 7;
        }
        return alphabet;
    }

    public String frequencyAnalysis(String message){
        Map<Character, Integer> letters = new LinkedHashMap<>();
        for (int i = 0; i < 26; i++) {
            letters.put((char)((i) + 'a'), 0);
        }
        for (int i = 0; i < message.length(); i++) {
            if(letters.containsKey(message.charAt(i))){
                letters.put(message.charAt(i), letters.get(message.charAt(i)) + 1);
            }
        }
        return letters.toString();
    }


}
