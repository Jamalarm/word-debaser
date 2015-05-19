package dictionary;

public class TrieNode {
    char letter;
    TrieNode[] links;
    boolean fullWord;

    TrieNode(char letter)
    {
        this.letter = letter;
        links = new TrieNode[26];
        this.fullWord = false;
    }
}
