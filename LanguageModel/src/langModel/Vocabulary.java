package langModel;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

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
     * Getter of the size of the vocabulary
     *
     * @return the size of the vocabulary Set
     */
    @Override
    public int getSize() {
        return this.vocabulary.size();
    }

    /**
     * Method returning the list of words of the vocabulary.
     *
     * @return the vocabulary Set
     */
    @Override
    public Set<String> getWords() {
        return this.vocabulary;
    }

    /**
     * Method testing if the word is present in the vocabulary.
     *
     * @param word the word to consider.
     *
     * @return true if the vocabulary contains the word, false otherwise
     */
    @Override
    public boolean contains(String word) {
        return (this.vocabulary.contains(word));
    }

    /**
     * Method adding a word to the vocabulary.
     *
     * @param word the word to add.
     */
    @Override
    public void addWord(String word) {
        this.vocabulary.add(word);
    }

    /**
     * Method removing a word from the vocabulary.
     *
     * @param word the word to remove.
     */
    @Override
    public void removeWord(String word) {
        this.vocabulary.remove(word);
    }

    /**
     * Method parsing the given set of n-grams and, for each n-gram, listing
     * its words and adding them to the vocabulary.
     * The set of n-grams can come from the set of n-grams present in a NgramCountsInterface object.
     *
     * @param ngramSet the set of n-grams whose words to add to the vocabulary.
     */
    @Override
    public void scanNgramSet(Set<String> ngramSet) {
        // TODO Auto-generated method stub

    }

    /**
     * Method reading a vocabulary from a file containing one word per line.
     *
     * @param filePath the path of the file containing the vocabulary.
     */
    @Override
    public void readVocabularyFile(String filePath) {
        List<String> words = MiscUtils.readTextFileAsStringList(filePath);
        for (int i = 0; i < words.size(); i++) {
            this.addWord(words.get(i));
        }
    }

    /**
     * Method writing a vocabulary to a file with one word per line.
     *
     * @param filePath the path of the file o contain the vocabulary.
     */
    @Override
    public void writeVocabularyFile(String filePath) {
        for (String word : this.getWords()) {
            MiscUtils.writeFile(word, filePath, true);
        }
    }

}
