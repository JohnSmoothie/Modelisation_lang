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
     * @return the size of the vocabulary Set
     */
    @Override
    public int getSize() {
        return this.vocabulary.size();
    }

    /**
     * @return the vocabulary Set
     */
    @Override
    public Set<String> getWords() {
        return this.vocabulary;
    }

    /**
     * @param word the word to consider.
     *
     * @return true if the vocabulary contains the word, false otherwise
     */
    @Override
    public boolean contains(String word) {
        return (this.vocabulary.contains(word));
    }

    /**
     * @param word the word to add.
     */
    @Override
    public void addWord(String word) {
        this.vocabulary.add(word);
    }

    /**
     * @param word the word to remove.
     */
    @Override
    public void removeWord(String word) {
        this.vocabulary.remove(word);
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
