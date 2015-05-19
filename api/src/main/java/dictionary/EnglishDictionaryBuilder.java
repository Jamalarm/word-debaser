package dictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tong on 19/05/2015.
 */
public class EnglishDictionaryBuilder {
    public PrefixTree getEnglishDictionary() throws IOException{
        PrefixTree pTree = new PrefixTree();
        FileReader fileReader = new FileReader("api/src/main/resources/words");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> lines = new ArrayList<String>();
        String line = null;
        EnglishAlphabet englishAlphabet = new EnglishAlphabet();
        while ((line = bufferedReader.readLine()) != null) {
            boolean addToDictionary = true;
            char[] chars = line.toCharArray();
            for(char c:chars){
                if(!englishAlphabet.inAlphabet(c)){
                    addToDictionary = false;
                    break;
                }
            }
            if(addToDictionary)
                lines.add(line);
        }
        bufferedReader.close();
        String[] words = lines.toArray(new String[lines.size()]);

        TrieNode tree = pTree.createTree();

        for (int i = 0; i < words.length; i++)
            pTree.insertWord(tree, words[i]);
        return pTree;
    }
}

