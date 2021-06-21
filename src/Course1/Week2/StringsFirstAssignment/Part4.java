package Course1.Week2.StringsFirstAssignment;

import edu.duke.URLResource;

import java.util.Locale;

public class Part4 {

    public String findWebLinks()
    {
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");

        for(String line : ur.lines())
        {
            if(line.toLowerCase().contains("youtube.com"))
                System.out.println(line.substring(line.indexOf("href=") + 5, line.lastIndexOf(">")));
        }

        return null;
    }
}
