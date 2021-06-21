package Course2.Week1;

public class WordPlay {

    boolean isVowel(char ch)
    {
        switch (ch)
        {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
            case 'A':
            case 'E':
            case 'I':
            case 'O':
            case 'U':   return true;
            default:    return false;
        }
    }

    public void testIsVowel()
    {
        System.out.println(isVowel('a'));
        System.out.println(isVowel('W'));
    }

    String replaceVowels(String phrase, char ch)
    {
        StringBuffer str = new StringBuffer(phrase);
        for(int i=0;i<phrase.length();i++)
            if(isVowel(phrase.charAt(i)))
                str.setCharAt(i, ch);

        return str.toString();
    }

    public void testReplaceVowels()
    {
        System.out.println(replaceVowels("Hello World!", '*'));
    }

    String emphasize(String phrase, char ch)
    {
        StringBuffer str = new StringBuffer(phrase);
        for(int i=0;i<phrase.length();i++)
            if(isVowel(phrase.charAt(i)))
                if((i&1) == 1)
                    str.setCharAt(i, '+');
                else
                    str.setCharAt(i, '*');

        return str.toString();
    }

    public void testEmphasize()
    {
        System.out.println(emphasize("dna ctgaaactga", 'a'));
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }

}
