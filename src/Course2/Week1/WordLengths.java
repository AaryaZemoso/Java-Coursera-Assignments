package Course2.Week1;

import edu.duke.FileResource;

import java.util.Arrays;

public class WordLengths {
    void countWordLengths(FileResource fr, int[] counts)
    {
        for(String word : fr.words())
        {
            int length = word.length();
            if(!Character.isLetter(word.charAt(0)))
                length--;
            if(!Character.isLetter(word.charAt(word.length()-1)))
                length--;

            if(length > counts.length)
                counts[counts.length - 1]++;
            else
                counts[length]++;
        }
    }

    public void testCountWordLengths(){

        FileResource fr = new FileResource();
        int[] counts = new int[31];

        countWordLengths(fr, counts);

        System.out.println(Arrays.toString(counts));
        System.out.println(indexOfMax(counts));
    }

    int indexOfMax(int values[])
    {
        int maxIdx = 0;
        int max = 0;

        for(int i=1;i<values.length;i++)
            if(values[i] > max)
            {
                max = values[i];
                maxIdx = i;
            }
        return maxIdx;
    }
}
