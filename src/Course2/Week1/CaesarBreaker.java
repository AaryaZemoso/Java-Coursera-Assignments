package Course2.Week1;

public class CaesarBreaker {

    String decrypt(String input, int key)
    {
        String decryptedString = "";
        for(int i=0;i<input.length();i++)
        {
            if(Character.isLetter(input.charAt(i)))
            {
                char caps = Character.isUpperCase(input.charAt(i)) ? 'A' : 'a';

                decryptedString += (char)((input.charAt(i) - caps - key + 26) % 26 + caps);

            }
            else
                decryptedString += input.charAt(i);
        }

        return decryptedString;
    }

    public void testDecryptString()
    {
        String text = "Hello World";

        CaesarCipher cc = new CaesarCipher();
        int key = 5;

        String encryptedString = cc.encrypt(text, key);
        String decryptedString = decrypt(encryptedString, key);

        System.out.println("Encrypted String : " + encryptedString);
        System.out.println("Decrypted String : " + decryptedString);

    }

    String halfOfString(String input, int start)
    {
        String str = "";
        for(int i=start;i<input.length();i+=2)
            str += input.charAt(i);
        return str;
    }

    public void testHalfOfString()
    {
        System.out.println(halfOfString("Qbkm Zgis", 1));
    }

    String decryptTwoKeys(String encyptedString, int key1, int key2)
    {
        String decryptedString = "";

        for(int i=0;i<encyptedString.length();i++)
            decryptedString += decrypt(encyptedString.charAt(i) + "", (i&1) == 0 ? key1 : key2);

        return decryptedString;
    }

    public void testDecryptTwoKeys()
    {
        CaesarCipher cc = new CaesarCipher();
        String encrypt = cc.encryptTwoKeys("Hello World", 14, 5);
        String decrypt = decryptTwoKeys(encrypt, 14, 5);

        System.out.println(encrypt);
        System.out.println(decrypt);
    }
}
