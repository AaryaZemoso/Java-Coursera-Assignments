package Course2.Week3;

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("data/short-test_log");
        la.printAll();

        System.out.println("Count Unique IPs : " + la.countUniqueIPs());
        la.printAllHigherThanNum(200);
        System.out.println("Unique Visits in day : " + la.uniqueIPsVisitsOnDay("Sep 30"));
        System.out.println("Unique IPs in range : " + la.uniqueIPsInRange(300, 399));

        HashMap<String, Integer> map = new HashMap<>(la.countVisitsPerIP());

        System.out.println("Count IPs per visit " + map);
        System.out.println("Max IPs per visit " + la.mostNumberOfVisitsByIP(map));
        System.out.println("Most Visits IPs : " + la.iPsMostVisits(map));

        HashMap<String, List<String>> map1 = new HashMap<>(la.iPsForDays());

        System.out.println("IPs for Days : " + map1);
        System.out.println("Max IPs for Days : " + la.dayWithMostIPVisits(map1));
    }
}
