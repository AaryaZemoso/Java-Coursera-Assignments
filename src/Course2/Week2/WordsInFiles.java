package Course2.Week2;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.util.*;

public class WordsInFiles {

    private HashMap<String, ArrayList<String>> filenames;

    public WordsInFiles()
    {
        filenames = new HashMap<>();
    }

    private void addWordsFromFile(File f)
    {
        FileResource fr = new FileResource(f);

        for(String word : fr.words())
        {
            if(filenames.containsKey(word))
            {
                if(!filenames.get(word).contains(f.getName()))
                    filenames.get(word).add(f.getName());
            }

            else
            {
                filenames.put(word, new ArrayList<>());
                filenames.get(word).add(f.getName());
            }
        }
    }

    void buildFileMap()
    {
        filenames.clear();

        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles())
        {
            addWordsFromFile(f);
        }
    }

    int maxNumber()
    {
        int max = 0;
        for(String key : filenames.keySet())
            max = Math.max(max, filenames.get(key).size());
        return max;
    }

    ArrayList<String> wordsInFiles(int number)
    {
        ArrayList<String> list = new ArrayList<>();

        for(String key : filenames.keySet())
            if(filenames.get(key).size() == number)
                list.add(key);

        return list;
    }

    void printFilesIn(String word)
    {
        filenames.get(word).stream().forEach(System.out::println);
    }

    public void tester()
    {
        buildFileMap();

        System.out.println("Max Number : " + maxNumber());
        System.out.println("Word in files with size 2 : " + wordsInFiles(2));
        printFilesIn("cats");
    }
}
