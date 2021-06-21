package Course2.Week2;

import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class WordFrequencies {

    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies()
    {
        myWords = new ArrayList<>();
        myFreqs = new ArrayList<>();
    }

    void findUnique()
    {
        myWords.clear();
        myFreqs.clear();

        FileResource fr = new FileResource();
        for(String word : fr.words())
            if(myWords.contains(word.toLowerCase()))
                myFreqs.set(myWords.indexOf(word.toLowerCase()), myFreqs.get(myWords.indexOf(word.toLowerCase())) + 1);
            else
            {
                myWords.add(word.toLowerCase());
                myFreqs.add(1);
            }
    }

    int findIndexOfMax()
    {
        int maxIdx = 0;

        for(int i=1;i<myFreqs.size();i++)
            if(myFreqs.get(maxIdx) < myFreqs.get(i))
                maxIdx = i;

        return maxIdx;
    }

    public void tester()
    {
        findUnique();

        System.out.println("Number of unique words : " + myWords.size());
        for(int i=0;i<myWords.size();i++)
            System.out.println(myWords.get(i) + " : " + myFreqs.get(i));
        int maxIdx = findIndexOfMax();
        System.out.println("The word that occurs most is : " + this.myWords.get(maxIdx) + " " + this.myFreqs.get(maxIdx));
    }
}
