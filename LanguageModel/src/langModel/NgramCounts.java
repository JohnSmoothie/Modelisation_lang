package langModel;


import com.sun.javafx.sg.prism.NGAmbientLight;
import com.sun.tools.hat.internal.util.Misc;

import java.util.*;


/**
 * Class NgramCounts: class implementing the interface NgramCountsInterface.
 *
 * @author N. Hernandez and S. Quiniou (2017)
 */
public class NgramCounts implements NgramCountsInterface {
    /**
     * The maximal order of the n-gram counts.
     */
    protected int order;

    /**
     * The map containing the counts of each n-gram.
     */
    protected Map<String, Integer> ngramCounts;

    /**
     * The total number of words in the corpus.
     * In practice, the total number of words will be increased when parsing a corpus
     * or when parsing a NgramCountsInterface file (only if the ngram encountered is a unigram one).
     */
    protected int nbWordsTotal;

    /**
     * Constructor.
     */
    public NgramCounts() {
        this.nbWordsTotal = 0;
        this.ngramCounts = new HashMap<>();
        this.order = 0;
    }

    /**
     * Setter of the maximal order of the ngrams considered.
     * <p>
     * In practice, the method will be called when parsing the training corpus,
     * or when parsing the NgramCountsInterface file (using the maximal n-gram length encountered).
     *
     * @param order the maximal order of n-grams considered.
     */
    private void setMaximalOrder(int order) {
        this.order = order;
    }

    /**
     * Getter of the maximal order of the n-grams in the training corpus.
     * <p>
     * A private method should be considered to set the value in the class implementing
     * the interface. In practice, the set method will be called when parsing the training corpus,
     * or when parsing the NgramCountsInterface file (using the maximal n-gram length encountered).
     *
     * @return the maximal order of n-grams considered.
     */
    @Override
    public int getMaximalOrder() {
        return this.order;
    }

    /**
     * Getter of the data structure which maps the n-grams to the counts
     * and return the number of mapped n-grams.
     *
     * @return the number of distinct n-grams.
     */
    @Override
    public int getNgramCounterSize() {
        return this.ngramCounts.size();
    }

    /**
     * Getter of the total number of words attribute.
     *
     * @return the total number of words in the corpus (different or not)
     */
    @Override
    public int getTotalWordNumber() {
        return this.nbWordsTotal;
    }

    /**
     * Getter of the data structure which maps the n-grams to the counts
     * and return the set of n-grams present in the structure.
     *
     * @return the set of distinct n-grams.
     */
    @Override
    public Set<String> getNgrams() {
        return this.ngramCounts.keySet();
    }

    /**
     * Getter of the data structure which maps the n-grams to the counts
     * and return the count of a particular n-gram.
     * If the n-gram is not present, it should return zero.
     *
     * @param ngram the n-gram to consider.
     *
     * @return the count of the given n-gram.
     */
    @Override
    public int getCounts(String ngram) {
        if (getNgrams().contains(ngram)) {
            return this.ngramCounts.get(ngram);
        } else {
            return 0;
        }
    }

    /**
     * Method incrementing the count of the given n-gram.
     * If the n-gram has never been seen before, then it sets the counts to 1,
     * else increments the counts.
     * The method is used when parsing a corpus file.
     *
     * @param ngram the n-gram whose counts is to increase.
     */
    @Override
    public void incCounts(String ngram) {
        this.ngramCounts.put(ngram, getCounts(ngram) + 1);
        if (NgramUtils.getSequenceSize(ngram) == 1) {
            this.nbWordsTotal++;
        }
    }

    /**
     * Setter of the counts of the given n-gram to a given count value.
     * The method is used when parsing a file containing previously saved n-gram counts.
     *
     * @param ngram  the n-gram to consider.
     * @param counts the counts of the given n-gram.
     */
    @Override
    public void setCounts(String ngram, int counts) {
        this.ngramCounts.put(ngram, counts);
        this.nbWordsTotal += counts;
    }

    /**
     * Method loading the text contained in a specified file (corresponding to a training corpus)
     * and scanning the text to count all the n-grams of each sentence.
     *
     * @param filePath     the path of the file corresponding to the training corpus.
     * @param vocab        the object corresponding to the vocabulary.
     * @param maximalOrder the maximal order of n-grams to consider.
     */
    @Override
    public void scanTextFile(String filePath, VocabularyInterface vocab, int maximalOrder) {
        setMaximalOrder(maximalOrder);

        List<String> sentences = MiscUtils.readTextFileAsStringList(filePath);
        Iterator sentenceIterator = sentences.iterator();

        while (sentenceIterator.hasNext()) {
            String sentence = sentenceIterator.next().toString();
            String sentenceVocab = NgramUtils.getStringOOV(sentence, vocab);

            List<String> ngramsList = NgramUtils.generateNgrams(sentenceVocab, 1, getMaximalOrder());
            Iterator ngramsListIterator = ngramsList.iterator();

            while (ngramsListIterator.hasNext()) {
                String ngram = ngramsListIterator.next().toString();
                incCounts(ngram);
            }
        }
    }

    /**
     * Method saving the current n-gram counts in a file.
     * The file should contain one n-gram per line, each line being made of the n-gram and its count
     * separated by a tabulation character '\t'.
     *
     * @param filePath the path of the file used to save the counts of the n-grams.
     */
    @Override
    public void writeNgramCountFile(String filePath) {
        Iterator ngramCountIterator = getNgrams().iterator();
        StringBuilder ngram = new StringBuilder();
        String ngramCountString = "";

        while (ngramCountIterator.hasNext()) {
            ngramCountString = ngramCountIterator.next().toString();
            ngram.append(ngramCountString);
            ngram.append("\\t");
            ngram.append(getCounts(ngramCountString));

            MiscUtils.writeFile(ngram.toString(), filePath, true);
        }
    }

    /**
     * Method reading the current n-gram counts from a file.
     * The file should contain one n-gram per line, each line being made of the n-gram and its count
     * separated by a tabulation character '\t'.
     * The method should also set the maximum encountered n-gram length (i.e. the NgramCountsInterface order).
     *
     * @param filePath the path of the file in which the counts of the n-grams are saved.
     */
    @Override
    public void readNgramCountsFile(String filePath) {
        List<String> ngramCountList = MiscUtils.readTextFileAsStringList(filePath);
        Iterator ngramCountListIterator = ngramCountList.iterator();
        String[] ngramCountString;
        int ngramSize;

        setMaximalOrder(0);

        while (ngramCountListIterator.hasNext()) {
            String ngramCountListString = ngramCountListIterator.next().toString();
            ngramCountString = ngramCountListString.split("\\t");
            incCounts(ngramCountString[0]);

            ngramSize = ngramCountString[0].split("\\s+").length;
            if (ngramSize > getMaximalOrder()) {
                setMaximalOrder(ngramSize);
            }
        }
    }

}
