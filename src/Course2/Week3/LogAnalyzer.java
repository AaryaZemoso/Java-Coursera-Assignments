package Course2.Week3;

import java.util.*;
import java.util.stream.Collectors;

import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<>();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fr = new FileResource(filename);
         for(String line : fr.lines())
             records.add(WebLogParser.parseEntry(line));
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }

     int countUniqueIPs()
     {
         return (int)records.stream().map((x) -> x.getIpAddress()).distinct().count();
     }

     void printAllHigherThanNum(int num)
     {
         records.stream().filter(y -> y.getStatusCode() > num).forEach(System.out::println);
     }

     List<String> uniqueIPsVisitsOnDay(String someday)
     {
         return records.stream().filter(y -> y.getAccessTime().toString().contains(someday))
                 .map(y -> y.getIpAddress())
                 .collect(Collectors.toList());
     }

     int uniqueIPsInRange(int low, int high)
     {
         return (int)records.stream().filter(y -> y.getStatusCode() >= low && y.getStatusCode() <= high).count();
     }

     Map<String, Integer> countVisitsPerIP()
     {
        return records.stream().
                map(x -> x.getIpAddress()).
                distinct().
                collect(Collectors.toMap(
                   value -> value,
                   value -> (int)records.stream().filter(x -> x.getIpAddress().equals(value)).count()
                ));
     }

     int mostNumberOfVisitsByIP(HashMap<String, Integer> map)
     {
         return (int)map.entrySet().stream().filter(x -> x.getValue() == map.values().stream().mapToInt(Integer::intValue).max().getAsInt()).collect(Collectors.toList()).get(0).getValue();
     }

     List<String> iPsMostVisits(HashMap<String, Integer> map)
     {
        return map.entrySet().stream()
                .filter(x -> x.getValue() == mostNumberOfVisitsByIP(map))
                .map(x -> x.getKey())
                .collect(Collectors.toList());
     }

     Map<String, List<String>> iPsForDays()
     {
         return records.stream().map(x -> x.getAccessTime().toString())
                 .distinct().collect(Collectors.toMap(
                         value -> value,
                         value -> records.stream().filter(x -> x.getAccessTime().toString().contains(value))
                                 .map(x -> x.getIpAddress()).collect(Collectors.toList())
                 ));
     }

     String dayWithMostIPVisits(HashMap<String, List<String>> map)
     {
        int max = map.entrySet().stream().mapToInt(x -> x.getValue().size()).max().getAsInt();
        return map.entrySet().stream().filter(x -> x.getValue().size() == max).collect(Collectors.toList()).get(0).getKey();
     }

}
