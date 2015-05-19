package dictionary;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PrefixTreeTest {
    private PrefixTree pTree;
    private TrieNode tree;
    @Before
    public void executedBeforeEach() throws IOException{
        pTree = new PrefixTree();
        String[] words = new String[]{"cat", "catty", "unicorn"};
        tree = pTree.createTree();
        for (int i = 0; i < words.length; i++)
            pTree.insertWord(tree, words[i]);
    }

    @Test
    public void catIsAWord(){
        Assert.assertTrue( pTree.find(tree, "cat", true) );
    }

    @Test
    public void catIsAPrefix(){
        Assert.assertTrue( pTree.find(tree, "cat", false) );
    }

    @Test
    public void uniIsAPrefix(){
        Assert.assertTrue( pTree.find(tree, "uni", false) );
    }

    @Test
    public void eviIsNotAWord(){
        Assert.assertFalse( pTree.find(tree, "evil", true) );
    }
}
