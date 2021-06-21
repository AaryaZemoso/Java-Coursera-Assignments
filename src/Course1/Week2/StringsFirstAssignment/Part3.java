package Course1.Week2.StringsFirstAssignment;

public class Part3 {

    boolean twoOccurences(String a, String b)
    {
        return b.indexOf(a) != -1 ? (b.indexOf(a, b.indexOf(a) + 1) != -1 ? true : false) : false;
    }

    public void testing()
    {
        System.out.println(twoOccurences("by", "A story by Abby Long"));
        System.out.println(twoOccurences("a", "banana"));
        System.out.println(twoOccurences("ba", "banana"));

        System.out.println("Last part = " + lastPart("an", "banana"));
        System.out.println("Last part = " + lastPart("zoo", "forest"));
    }

    String lastPart(String a, String b)
    {
        if(b.indexOf(a) == -1)
            return b;

        return b.substring(b.indexOf(a) + a.length());
    }

}
