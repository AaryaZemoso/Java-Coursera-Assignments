package Course1.Week3;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.time.format.DateTimeFormatter;

public class WeatherData {

    CSVRecord coldestHourInFile(CSVParser parser)
    {
        CSVRecord coldestTemperatureRecord = null;
        for(CSVRecord record : parser)
        {
            if(coldestTemperatureRecord == null)
                coldestTemperatureRecord = record;

            else
            {
                double coldestTemp = Double.parseDouble(coldestTemperatureRecord.get("TemperatureF"));
                double currTemp = Double.parseDouble(record.get("TemperatureF"));

                if(currTemp != -9999)
                    if(currTemp < coldestTemp)
                        coldestTemperatureRecord = record;
            }
        }
        return coldestTemperatureRecord;
    }

    public void testerColdHourInFile()
    {
        FileResource fr = new FileResource();

        CSVParser parser = fr.getCSVParser();
        System.out.println("Hour : " + coldestHourInFile(parser).get(0));
    }

    String fileWithColdestTemperature()
    {
        DirectoryResource dr = new DirectoryResource();

        double lowestTemp = 10000;
        File coldestTempFile = null;

        for(File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();

            double temperature = Double.parseDouble(coldestHourInFile(parser).get("TemperatureF"));
            if(temperature < lowestTemp)
            {
                lowestTemp = temperature;
                coldestTempFile = f;
            }
        }

        return coldestTempFile.getAbsolutePath();
    }

    public void testFileWithColdestTemperature()
    {
        String filename = fileWithColdestTemperature();
        FileResource fr = new FileResource(new File(filename));

        CSVParser parser = fr.getCSVParser();

        System.out.println("Coldest day was in the file " + filename);
        System.out.println("Coldest temperature on that day was " + coldestHourInFile(parser).get("TemperatureF"));

        System.out.println("All the Temperatures on the coldest day are ");

        parser = fr.getCSVParser();

        for(CSVRecord record : parser)
        {
            System.out.println(record.get("DateUTC") + ": " + record.get("TemperatureF"));
        }
    }

    CSVRecord lowestHumidityInFile(CSVParser parser)
    {
        CSVRecord lowestHumidityRecord = null;

        for(CSVRecord record : parser)
        {
            String value = record.get("Humidity");
            if("N/A".equals(value))
                continue;

            if(lowestHumidityRecord == null)
            {
                lowestHumidityRecord = record;
                continue;
            }

            double humidity = Double.parseDouble(value);
            double lowestHumidity = Double.parseDouble(lowestHumidityRecord.get("Humidity"));

            if(humidity < lowestHumidity)
            {
                lowestHumidityRecord = record;
            }
        }
        return lowestHumidityRecord;
    }

    public void testHumidityInFile()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);

        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }

    CSVRecord lowestHumidityInManyFiles()
    {
        DirectoryResource dr = new DirectoryResource();

        CSVRecord lowestHumidityRecord = null;

        for(File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();

            CSVRecord record = lowestHumidityInFile(parser);

            if(lowestHumidityRecord == null)
                lowestHumidityRecord = record;

            else
            {
                double lowestHumidity = Double.parseDouble(lowestHumidityRecord.get("Humidity"));
                double humidity = Double.parseDouble(record.get("Humidity"));

                if(humidity < lowestHumidity)
                    lowestHumidityRecord = record;
            }
        }

        return lowestHumidityRecord;
    }

    public void testHumidityInManyFiles()
    {
        CSVRecord record = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + record.get("Humidity") + " at " + record.get("DateUTC"));
    }

    double averageTemperatureInFile(CSVParser parser)
    {
        double averageTemperature = 0.0;
        int count = 0;

        for(CSVRecord record : parser)
        {
            double temperature = Double.parseDouble(record.get("TemperatureF"));

            if(temperature != -9999)
            {
                count++;
                averageTemperature += temperature;
            }
        }
        averageTemperature /= count;
        return averageTemperature;
    }

    public void testAverageTemperatureInFile()
    {
        double avgTemperature = averageTemperatureInFile(new FileResource().getCSVParser());
        System.out.println("Average temperature in the file is " + avgTemperature);
    }

    double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value)
    {
        double avgTemperature = 0.0;
        int count = 0;

        for(CSVRecord record : parser)
        {
            double humidity = Double.parseDouble(record.get("Humidity"));
            if(humidity >= value)
            {
                avgTemperature += Double.parseDouble(record.get("TemperatureF"));
                count++;
            }
        }

        return count == 0 ? -1 : avgTemperature / count;
    }

    public void testAverageTemperatureWithHighHumidityInFile()
    {
        double avgTemperature = averageTemperatureWithHighHumidityInFile(new FileResource().getCSVParser(), 80);
        if(avgTemperature == -1)
            System.out.println("No temperature with that humidity");

        else
            System.out.println("Average Temp when high humidity is " + avgTemperature);
    }
}
