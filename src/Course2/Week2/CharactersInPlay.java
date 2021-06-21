package Course2.Week2;

import edu.duke.FileResource;

import java.util.ArrayList;

public class CharactersInPlay {

    ArrayList<String> characters;
    ArrayList<Integer> count;

    public CharactersInPlay()
    {
        characters = new ArrayList<>();
        count = new ArrayList<>();
    }

    void update(String person)
    {
        if(this.characters.contains(person))
            count.set(characters.indexOf(person), count.get(characters.indexOf(person)) + 1);
        else
        {
            characters.add(person);
            count.add(1);
        }
    }

    void findAllCharacters()
    {
        characters.clear();
        count.clear();

        FileResource fr = new FileResource();

        for(String line : fr.lines())
            if(line.indexOf('.') != -1)
                update(line.substring(0, line.indexOf('.')));
    }

    public void tester()
    {
        findAllCharacters();
        for(int i=0;i<characters.size();i++)
            System.out.println(characters.get(i) + " " + count.get(i));

        charactersWithNumParts(1, 6);
    }

    void charactersWithNumParts(int num1, int num2)
    {
        for(int i=0;i<characters.size();i++)
            if(count.get(i) > num1 && count.get(i) < num2)
                System.out.println(characters.get(i) + " " + count.get(i));
    }
}
