package Course1.Week2.StringsThirdAssignment;

import edu.duke.StorageResource;

public class Part1 {

    StorageResource getAllGenes(String dna)
    {
        Course1.Week2.StringsSecondAssignment.Part1 p1 = new Course1.Week2.StringsSecondAssignment.Part1();
        StorageResource sr = new StorageResource();

        while(true)
        {
            String s = p1.findGene(dna);
            if(s.length() == 0)
                break;

            sr.add(s);
            dna = dna.substring(dna.indexOf(s)+s.length()+1);
        }

        return sr;
    }

    public void testing()
    {
        StorageResource sr = getAllGenes("ATGTAAGATGCCCTAGT");
        for(String s : sr.data())
            System.out.println(s);

        System.out.println(cgRatio("ATGCCATAG"));
    }

    double cgRatio(String dna)
    {
        int count = 0;
        for(int i=0;i<dna.length();i++)
            if(dna.charAt(i) == 'C' || dna.charAt(i) == 'G')
                count++;
        return count / (double)dna.length();
    }

    int countCTG(String dna)
    {
        int count = 0;

        while(dna.indexOf("CTG")!=-1)
        {
            count++;
            dna = dna.substring(dna.indexOf("CTG"));
        }

        return count;
    }

    void processGenes(StorageResource sr)
    {
        int count = 0;
        for(String s : sr.data())
            if(s.length() > 9)
            {
                count++;
                System.out.println(s);
            }

        System.out.println("Count : " + count);

        System.out.println("CG Ratio > 0.35");
        int countCG = 0;
        for(String s : sr.data())
            if(cgRatio(s) > 0.35)
            {
                countCG++;
                System.out.println(s);
            }

        System.out.println("Count CG : " + countCG);

        String longestDNA = null;
        for(String s : sr.data())
            if(longestDNA == null)
                longestDNA = s;
            else
                if(s.length() > longestDNA.length())
                    longestDNA = s;

        System.out.println("Longest DNA length : " + longestDNA.length());

    }
}
