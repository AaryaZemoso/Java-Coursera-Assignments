package Course2.Week1;

public class CaesarCipher {

    String encrypt(String input, int key)
    {
        String encryptedString = "";
        for(int i=0;i<input.length();i++)
        {
            if(Character.isAlphabetic(input.charAt(i)))
            {
                char caps = Character.isUpperCase(input.charAt(i)) ? 'A' : 'a';
                encryptedString += (char)((input.charAt(i) - caps + key) % 26 + caps);
            }
            else
                encryptedString += input.charAt(i);
        }
        return encryptedString;
    }

    public void testEncrypt()
    {
        System.out.println(encrypt("First Legion!", 23));
    }

    String encryptTwoKeys(String input, int key1, int key2)
    {
        String encryptedString = "";

        for(int i=0;i<input.length();i++)
        {
            encryptedString += encrypt(input.charAt(i) + "", (i&1) == 0 ? key1 : key2);
        }

        return encryptedString;
    }

    public void testEncryptTwoKeys()
    {
        System.out.println(encryptTwoKeys("First Legion", 23, 17));
    }

}
