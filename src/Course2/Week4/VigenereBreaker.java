package Course2.Week4;

import java.io.File;
import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        String str = "";
        for(int i=whichSlice;i<message.length();i+=totalSlices)
            str += message.charAt(i);

        return str;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE

        CaesarCracker cc = new CaesarCracker(mostCommon);
        for(int i=0;i<klength;i++)
            key[i] = cc.getKey(sliceString(encrypted, i, klength));

        return key;
    }

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        String encryptedString = new FileResource().asString();

//        int[] key = tryKeyLength(encryptedString, 5, 'e');
//        VigenereCipher vc = new VigenereCipher(key);
//        String decryptedString = vc.decrypt(encryptedString);
//        System.out.println("Decrypted String :- \n" + decryptedString);

//        FileResource dict = new FileResource();
//        HashSet<String> dictionary = readDictionary(dict);

//        String decrypted = breakForLanguage(encryptedString, dictionary);
//        System.out.println(decrypted);

        DirectoryResource dr = new DirectoryResource();
        HashMap<String, HashSet<String>> languages = new HashMap<>();

        for(File f : dr.selectedFiles())
        {
            languages.put(f.getName(), readDictionary(new FileResource(f)));
            System.out.println("Added dictionary : " + f.getName());
        }

        String decryptedString = breakForAllLangs(encryptedString, languages);
        System.out.println(decryptedString);
    }

    public HashSet<String> readDictionary(FileResource fr)
    {
        HashSet<String> set = new HashSet<>();
        for(String line : fr.lines())
        {
            line = line.toLowerCase();
            set.add(line);
        }

        return set;
    }

    public int countWords(String message, HashSet<String> dictionary)
    {
        int count = 0;
        String[] words = message.split("\\W+");
        for(String word : words)
            if(dictionary.contains(word.toLowerCase()))
                count++;

        return count;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary)
    {
        int maxRealWordsCount = 0;
        String decryptedMessage = "";

        for(int i=1;i<=100;i++)
        {
            char common = mostCommonCharIn(dictionary);
            int keys[] = tryKeyLength(encrypted, i, common);
            VigenereCipher vc = new VigenereCipher(keys);
            String decrypted = vc.decrypt(encrypted);
            int countRealWords = countWords(decrypted, dictionary);

            if(countRealWords > maxRealWordsCount)
            {
                maxRealWordsCount = countRealWords;
                decryptedMessage = decrypted;
            }
        }

        return decryptedMessage;
    }

    public char mostCommonCharIn(HashSet<String> dictionary)
    {
        HashMap<Character, Integer> map = new HashMap<>();
        char c = '\0';

        for(String word : dictionary)
            for(char ch : word.toCharArray())
            {
                map.put(ch, map.getOrDefault(ch, 0) + 1);
                if(c == '\0')
                    c = ch;

                if(map.get(c) < map.get(ch))
                    c = ch;
            }

        return c;
    }

    public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages)
    {
        int maxCount = 0;
        String decryptedString = "";

        for(String key : languages.keySet())
        {
            HashSet<String> dict = languages.get(key);
            String decrypted = breakForLanguage(encrypted, dict);

            int count = countWords(decrypted, dict);
            if(count > maxCount)
            {
                maxCount = count;
                decryptedString = decrypted;
            }
        }

        return decryptedString;
    }
}
