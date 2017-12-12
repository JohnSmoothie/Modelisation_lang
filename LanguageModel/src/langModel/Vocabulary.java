package langModel;

import java.util.HashSet;
import java.util.Set;


/**
 * Class Vocabulary: class implementing the interface VocabularyInterface.
 *
 * @author Evann BACALA (2017)
 */
public class Vocabulary implements VocabularyInterface {
    /**
     * The set of words corresponding to the vocabulary.
     */
    protected Set<String> vocabulary;


    /**
     * Constructor.
     */
    public Vocabulary() {
        vocabulary = new HashSet<String>();
    }


    /**
     * @return The size of the vocabulary Set
     */
    @Override
    public int getSize() {
        return this.vocabulary.size();
    }

    /**
     * @return The vocabulary Set
     */
    @Override
    public Set<String> getWords() {
       return  this.vocabulary;
    }

    @Override
    public boolean contains(String word) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void addWord(String word) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeWord(String word) {
        // TODO Auto-generated method stub

    }

    @Override
    public void scanNgramSet(Set<String> ngramSet) {
        // TODO Auto-generated method stub

    }

    @Override
    public void readVocabularyFile(String filePath) {
        // TODO Auto-generated method stub

    }

    @Override
    public void writeVocabularyFile(String filePath) {
        // TODO Auto-generated method stub

    }

}
