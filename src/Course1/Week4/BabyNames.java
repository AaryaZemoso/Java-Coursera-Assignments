package Course1.Week4;

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class BabyNames {

    void totalBirths(FileResource fr)
    {
        int totalBirths = 0,
            totalBoyBirths = 0,
            totalGirlBirths = 0,

            totalBoys = 0,
            totalGirls = 0;

        for(CSVRecord record : fr.getCSVParser(false))
        {
            int numBirths = Integer.parseInt(record.get(2));
            totalBirths += numBirths;

            if(record.get(1).equals("M"))
            {
                totalBoyBirths += numBirths;
                totalBoys++;
            }

            else
            {
                totalGirlBirths += numBirths;
                totalGirls++;
            }
        }

        System.out.println("Total num of births : " + totalBirths +
                            "\nTotal no. of boy names : " + totalBoys +
                            "\nTotal no. of boy births : " + totalBoyBirths +
                            "\nTotal no. of girl names : " + totalGirls +
                            "\nTotal no. of girl births : " + totalGirlBirths);
    }

    public void testTotalBirths()
    {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }

    int getRank(int year, String name, String gender) throws IOException
    {
        String filename = "/home/aarydb/Zemoso/Java Coursera Assignments/data/US Baby Names/us_babynames_test";
        filename += "/yob" + year + "short.csv";

        FileResource fr = new FileResource(filename);
        int rank = 1;

        CSVParser parser = fr.getCSVParser(false);
        List<CSVRecord> listOfRecords = parser.getRecords();
        Collections.sort(listOfRecords, new Comparator<CSVRecord>(){
            public int compare(CSVRecord a, CSVRecord b)
            {
                return Integer.parseInt(a.get(2)) - Integer.parseInt(b.get(2));
            }
        });

        listOfRecords = listOfRecords.stream().filter(record -> record.get(1).equals(gender)).collect(Collectors.toList());
        Collections.reverse(listOfRecords);

        for(int i=0;i<listOfRecords.size();i++)
            if(listOfRecords.get(i).get(0).equals(name))
                return i + 1;

        return -1;
    }

    public void testGetRank() throws IOException
    {
        String name = "Mason";
        int year = 2012;
        String gender = "M";

        System.out.println("Rank of " + name + " in year " + year + " is " + getRank(year, name, gender));
    }

    String getName(int year, int rank, String gender) throws IOException
    {
        String filename = "/home/aarydb/Zemoso/Java Coursera Assignments/data/US Baby Names/us_babynames_test";
        filename += "/yob" + year + "short.csv";

        FileResource fr = new FileResource(filename);

        CSVParser parser = fr.getCSVParser(false);
        List<CSVRecord> listOfRecords = parser.getRecords();

        Collections.sort(listOfRecords, new Comparator<CSVRecord>(){
            public int compare(CSVRecord a, CSVRecord b)
            {
                return Integer.parseInt(a.get(2)) - Integer.parseInt(b.get(2));
            }
        });

        listOfRecords = listOfRecords.stream().filter(record -> record.get(1).equals(gender)).collect(Collectors.toList());
        Collections.reverse(listOfRecords);

        return rank >= listOfRecords.size() ? "NO NAME" : listOfRecords.get(rank - 1).get(0);
    }

    public void testGetName() throws IOException
    {
        String gender = "M";
        int rank = 3;
        int year = 2012;

        System.out.println("Name at rank " + rank + " in year " + year + " is " + getName(year, rank, gender));
    }

    void whatIsNameInYear(String name, int year, int newYear, String gender) throws IOException
    {
        int newRank = getRank(year, name, gender);
        String newName = getName(newYear, newRank, gender);

        System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear);
    }

    public void testWhatIsNameInYear() throws IOException
    {
        whatIsNameInYear("Isabella", 2012, 2014, "F");
    }

}
