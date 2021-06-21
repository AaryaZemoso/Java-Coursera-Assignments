package Course1.Week2.StringsFirstAssignment;

public class Part2 {

    public String findSimpleGene(String dna, String startCodon, String stopCodon)
    {
        int startCodonIdx = dna.indexOf(startCodon);
        if(startCodonIdx == -1)
            return "";

        int endCodonIdx = dna.indexOf(stopCodon);
        if(endCodonIdx == -1)
            return "";

        while(endCodonIdx != -1)
        {
            if(((endCodonIdx - startCodonIdx) % 3) == 0)
                return dna.substring(startCodonIdx, endCodonIdx + 3);

            else
                endCodonIdx = dna.indexOf(stopCodon, endCodonIdx + 1);
        }

        return "";
    }

    public void testSimpleGene()
    {
        String s1 = "TAA";
        String s2 = "ATG";
        String s3 = "ATGxyzTAA";
        String s4 = "ATGwxyzTAA";
        String s5 = "ATGxyTAAxTAA";

        String startCodon = "ATG";
        String stopCodon = "TAA";

        System.out.println(s1 + " : " + findSimpleGene(s1, startCodon, stopCodon));
        System.out.println(s2 + " : " + findSimpleGene(s2, startCodon, stopCodon));
        System.out.println(s3 + " : " + findSimpleGene(s3, startCodon, stopCodon));
        System.out.println(s4 + " : " + findSimpleGene(s4, startCodon, stopCodon));
        System.out.println(s5 + " : " + findSimpleGene(s5, startCodon, stopCodon));
    }
}
