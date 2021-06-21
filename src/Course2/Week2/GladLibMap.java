package Course2.Week2;

import edu.duke.FileResource;
import edu.duke.URLResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GladLibMap {

    private HashMap<String, ArrayList<String>> map;

    private ArrayList<String> adjectiveList;
    private ArrayList<String> nounList;
    private ArrayList<String> colorList;
    private ArrayList<String> countryList;
    private ArrayList<String> nameList;
    private ArrayList<String> animalList;
    private ArrayList<String> timeList;
    private ArrayList<String> verbList;
    private ArrayList<String> fruitList;

    private ArrayList<String> wordsUsed;

    private Random myRandom;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";

    public GladLibMap(){
        map = new HashMap<>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        wordsUsed = new ArrayList<>();

    }

    public GladLibMap(String source){
        map = new HashMap<>();
        initializeFromSource(source);
        myRandom = new Random();
        wordsUsed = new ArrayList<>();

    }

    private void initializeFromSource(String source) {
        adjectiveList= readIt(source+"/adjective.txt");
        nounList = readIt(source+"/noun.txt");
        colorList = readIt(source+"/color.txt");
        countryList = readIt(source+"/country.txt");
        nameList = readIt(source+"/name.txt");
        animalList = readIt(source+"/animal.txt");
        timeList = readIt(source+"/timeframe.txt");
        verbList = readIt(source+"/verb.txt");
        fruitList = readIt(source+"/fruit.txt");

        map.put("adjective", adjectiveList);
        map.put("noun", nounList);
        map.put("color", colorList);
        map.put("country", countryList);
        map.put("name", nameList);
        map.put("animal", animalList);
        map.put("time", timeList);
        map.put("verb", verbList);
        map.put("fruit", fruitList);
    }

    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {

        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }

        if(map.containsKey(label))
            return randomFrom(map.get(label));

        return "**UNKNOWN**";
    }

    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        while(wordsUsed.contains(sub))
            sub = getSubstitute(w.substring(first+1, last));
        wordsUsed.add(sub);
        return prefix+sub+suffix;
    }

    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }

    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\nReplaced Words : " + wordsUsed.size());
    }

    int totalWordsInMap()
    {
        int count = 0;
        for(String key : map.keySet())
            count += map.get(key).size();
        return count;
    }
}
