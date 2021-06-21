package Course1.Week3;

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;

public class ExportData {

    public void tester() throws IOException
    {
        FileResource fr = new FileResource();

        CSVParser parser = fr.getCSVParser();
        System.out.println(countryInfo(parser, "Nauru"));

        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "gold", "diamonds");

        parser = fr.getCSVParser();
        System.out.println("number of sugar exporters : " + numberOfExporters(parser, "sugar"));

        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }

    String countryInfo(CSVParser parser, String country) throws IOException
    {
        for(CSVRecord record : parser.getRecords())
            if(record.get("Country").equals(country))
                return record.get(0) + ": " + record.get(1) + ": " + record.get(2);

        return "NO RECORD";
    }

    void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) throws IOException
    {
        for(CSVRecord record : parser.getRecords())
        {
            if(record.get("Exports").contains(exportItem1) && record.get("Exports").contains(exportItem2))
                System.out.println(record.get("Country"));
        }
    }

    int numberOfExporters(CSVParser parser, String exportItem) throws IOException
    {
        int count = 0;
        for(CSVRecord record : parser.getRecords())
        {
            if(record.get("Exports").contains(exportItem))
                count++;
        }

        return count;
    }

    void bigExporters(CSVParser parser, String amount) throws IOException
    {
        for(CSVRecord record : parser.getRecords())
        {
            if(record.get(2).length() > amount.length())
                System.out.println(record.get(0) + " " + record.get(2));
        }
    }
}
