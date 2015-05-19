package dictionary;
public class PrefixTree
{
    private int offset = 97; //ASCII offset for lower case letters of the English alphabet
    public static TrieNode createTree()
    {
        return(new TrieNode('\0'));
    }

    public void insertWord(TrieNode root, String word)
    {
        int l = word.length();
        char[] letters = word.toCharArray();
        TrieNode curNode = root;

        for (int i = 0; i < l; i++)
        {
            if (curNode.links[letters[i]-this.offset] == null)
                curNode.links[letters[i]-this.offset] = new TrieNode(letters[i]);
            curNode = curNode.links[letters[i]-offset];
        }
        curNode.fullWord = true;
    }

    /**
     * Finds either a full word or a prefix
     */
    public boolean find(TrieNode root, String word, Boolean fullWord)
    {
        char[] letters = word.toCharArray();
        int l = letters.length;
        TrieNode curNode = root; //before the first letter

        int i;
        for (i = 0; i < l; i++)
        {
            if (curNode == null)
                return false;
            curNode = curNode.links[letters[i]-offset];
        }

        if (i == l && curNode == null)
            return false;

        if (curNode != null && !curNode.fullWord && fullWord )
            return false;

        return true;
    }

    static void printTree(TrieNode root, int level, char[] branch)
    {
        if (root == null)
            return;

        for (int i = 0; i < root.links.length; i++)
        {
            branch[level] = root.letter;
            printTree(root.links[i], level+1, branch);
        }

        if (root.fullWord)
        {
            for (int j = 1; j <= level; j++)
                System.out.print(branch[j]);
            System.out.println();
        }
    }
}