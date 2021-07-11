package Course4.application;

import Course4.textgen.*;
import Course4.spelling.*;
import Course4.application.*;
import Course4.document.*;

import java.util.Random;


public class LaunchClass {
	
	public String dictFile = "data/dict.txt";
	
	public LaunchClass() {
		super();
	}
	
	public Document getDocument(String text) {
		// Change this to BasicDocument(text) for week 1 only
		return new EfficientDocument(text);
	}
	
	public MarkovTextGenerator getMTG() {
		return new MarkovTextGeneratorLoL(new Random());
	}
	
	public WordPath getWordPath() {
		return new WPTree();
	}
	
    public AutoComplete getAutoComplete() {
        AutoCompleteDictionaryTrie tr = new AutoCompleteDictionaryTrie();
        DictionaryLoader.loadDictionary(tr, dictFile);
        return tr;
    }
    
    public Dictionary getDictionary() {
        Dictionary d = new DictionaryBST();
        DictionaryLoader.loadDictionary(d, dictFile);
    	return d;
    }
    
    public SpellingSuggest getSpellingSuggest(Dictionary dic) {
    	//return new SpellingSuggestNW(new NearbyWords(dic));
    	return new NearbyWords(dic);
    
    }
}
