package Course2.Week4;

import edu.duke.FileResource;

import java.util.Arrays;

public class Tester {

    public void test()
    {
//        VigenereCipher vc = new VigenereCipher(new int[]{17, 14, 12, 4});
//        String text = new FileResource().asString();
//        String encrypted = "Tcmp-pxety mj nikhqv htee mrfhtii tyv";

//        System.out.println((encrypted = vc.encrypt(text)));
//        System.out.println(vc.decrypt(encrypted));

        VigenereBreaker vb = new VigenereBreaker();
//        String sliced = vb.sliceString("abcdefghijklm", 2, 3);
//        System.out.println(sliced);
//
//        String s = new FileResource().asString();
//        System.out.println(Arrays.toString(vb.tryKeyLength(s, 5, 'e')));
        vb.breakVigenere();
    }
}
