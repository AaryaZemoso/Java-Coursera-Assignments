package Course1.Week2.StringsSecondAssignment;

public class Part2 {

    int howMany(String a, String b)
    {
        int count = 0;
        for(int i=0;i+a.length()<=b.length();)
        {
            if(a.equals(b.substring(i, i+a.length())))
            {
                count++;
                i += a.length();
            }

            else
                i++;
        }

        return count;
    }

    public void testHowMany()
    {
        System.out.println(howMany("GAA", "ATGAACGAATTGAATC"));
        System.out.println(howMany("AA", "ATAAAA"));
    }
}
