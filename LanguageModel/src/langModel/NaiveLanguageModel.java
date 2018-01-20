package langModel;


/**
 * Class NaiveLanguageModel: class implementing the interface LanguageModelInterface by creating a naive language model,
 * i.e. a n-gram language model with no smoothing.
 *
 * @author Evann BACALA
 */
public class NaiveLanguageModel implements LanguageModelInterface {
    /**
     * The NgramCountsInterface corresponding to the language model.
     */
    protected NgramCountsInterface ngramCounts;

    /**
     * The vocabulary of the language model.
     */
    protected VocabularyInterface vocabulary;


    /**
     * Constructor.
     */
    public NaiveLanguageModel() {
        this.ngramCounts = new NgramCounts();
        this.vocabulary = new Vocabulary();
    }

    /**
     * Getter of the language model order.
     * In practice it will get the maximal order of the NgramCountsInterface structure.
     *
     * @return the maximal order of the language model.
     */
    @Override
    public int getLMOrder() {
        return ngramCounts.getMaximalOrder();
    }

    /**
     * Setter associating the current language model to a NgramCountsInterface object
     * and the vocabulary to a VocabularyInterface object.
     *
     * @param ngramCounts the NgramCountsInterface object to set.
     * @param vocab       the VocabularyInterface object to set.
     */
    @Override
    public void setNgramCounts(NgramCountsInterface ngramCounts, VocabularyInterface vocab) {
        this.ngramCounts = ngramCounts;
        this.vocabulary = vocab;
    }

    /**
     * Method computing and returning the probability of the given n-gram,
     * using the NgramCountsInterface structure.
     * An implementation can consider the Laplace backoff smoothing.
     * Another one can compute the log-probability instead of the probability...
     *
     * @param ngram the n-gram whose probability to compute.
     *
     * @return the probability of the given n-gram.
     */
    @Override
    public Double getNgramProb(String ngram) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Method computing and returning the probability of the given sentence,
     * according to its n-grams.
     *
     * @param sentence the sentence whose probability to compute.
     *
     * @return the probability of the given sentence.
     */
    @Override
    public Double getSentenceProb(String sentence) {
        // TODO Auto-generated method stub
        return null;
    }

}
