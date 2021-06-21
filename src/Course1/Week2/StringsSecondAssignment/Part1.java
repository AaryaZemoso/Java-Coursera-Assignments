package Course1.Week2.StringsSecondAssignment;

import Course1.Week2.StringsFirstAssignment.Part2;

public class Part1 {

    int findStopCodon(String dna, int startIndex, String stopCodon)
    {
        Part2 p = new Part2();
        String substr = p.findSimpleGene(dna.substring(startIndex), "ATG", stopCodon);

        if(substr.length() == 0)
            return -1;

        return startIndex + substr.length() - stopCodon.length();
    }

    public void testFindStopCodon()
    {
        System.out.println(findStopCodon("ATGCCCATGZZZTAA", 6, "TAA"));
    }

    public String findGene(String dna)
    {
        int startIndex = dna.indexOf("ATG");
        if(startIndex == -1)
            return "";

        int taaStopIndex = findStopCodon(dna, startIndex, "TAA");
        int tagStopIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaStopIndex = findStopCodon(dna, startIndex, "TGA");

        if(taaStopIndex == -1 && tagStopIndex == -1 && tgaStopIndex == -1)
            return "";

        int minStopIndex = Math.min(taaStopIndex == -1 ? Integer.MAX_VALUE : taaStopIndex,
                                Math.min(tagStopIndex == -1 ? Integer.MAX_VALUE : tagStopIndex,
                                            tgaStopIndex == -1 ? Integer.MAX_VALUE : tgaStopIndex));

        return dna.substring(startIndex, minStopIndex + 3);
    }

    public void testFindGene()
    {
        System.out.println(findGene("ATGXXXTAVYYYTAGZZZTGA"));

    }

    void printAllGenes(String dna)
    {
        while(true)
        {
            String s = findGene(dna);
            if(s.length() == 0)
                break;

            System.out.println(s);
            dna = dna.substring(dna.indexOf(s)+s.length()+1);
        }
    }

}
