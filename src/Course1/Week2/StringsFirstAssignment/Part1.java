package Course1.Week2.StringsFirstAssignment;

public class Part1 {

    String findSimpleGene(String dna)
    {
        int startCodonIdx = dna.indexOf("ATG");
        if(startCodonIdx == -1)
            return "";

        int endCodonIdx = dna.indexOf("TAA");
        if(endCodonIdx == -1)
            return "";

        while(endCodonIdx != -1)
        {
            if(((endCodonIdx - startCodonIdx) % 3) == 0)
                return dna.substring(startCodonIdx, endCodonIdx + 3);

            else
                endCodonIdx = dna.indexOf("TAA", endCodonIdx + 1);
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

        System.out.println(s1 + " : " + findSimpleGene(s1));
        System.out.println(s2 + " : " + findSimpleGene(s2));
        System.out.println(s3 + " : " + findSimpleGene(s3));
        System.out.println(s4 + " : " + findSimpleGene(s4));
        System.out.println(s5 + " : " + findSimpleGene(s5));
    }
}
