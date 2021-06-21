package Course2.Week2;

import edu.duke.FileResource;

import java.util.HashMap;

public class CodonCount {

    private HashMap<String, Integer> countCodon;

    public CodonCount()
    {
        countCodon = new HashMap<>();
    }

    void buildCodonMap(int start, String dna)
    {
        countCodon.clear();

        for(int i=start;i+3 < dna.length();i+=3)
            countCodon.put(dna.substring(i, i+3),
                        countCodon.getOrDefault(dna.substring(i, i+3), 0) + 1);
    }

    String getMostCommonCodon()
    {
        String name = null;
        for(String key : countCodon.keySet())
            if(name == null)
                name = key;
            else if(countCodon.get(key) > countCodon.get(name))
                name = key;
        return name;
    }

    void printCountCodon(int start, int end)
    {
        for(String key : countCodon.keySet())
            if(countCodon.get(key) >= start && countCodon.get(key) <= end)
                System.out.println(key);
    }

    public void tester()
    {
        String dna = new FileResource().asString();
        int start = 0;

        buildCodonMap(start, dna);
        for(String key : countCodon.keySet())
            System.out.println(key + " " + countCodon.get(key));

        System.out.println("Most Common Codon : " + getMostCommonCodon());

        printCountCodon(1, 2);
    }

}
