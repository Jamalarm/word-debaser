package dictionary;
public class EnglishAlphabet {
    private char start = 'a';
    private char end = 'z';

    public boolean inAlphabet(char character){
        return(character >= start && character <= end );
    }
}
