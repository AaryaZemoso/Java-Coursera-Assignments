package Course1.Week2.StringsSecondAssignment;

public class Part3 {

    int countGenes(String dna)
    {
        int count = 0;
        Part1 p1 = new Part1();

        while(true)
        {
            String s = p1.findGene(dna);
            if(s.length() == 0)
                break;

            count++;
            dna = dna.substring(dna.indexOf(s) + s.length() + 1);
        }

        return count;
    }

    public void testCountGenes()
    {
        System.out.println(countGenes("ATGTAAGATGCCCTAGT"));
    }

}
