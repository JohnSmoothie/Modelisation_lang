package langModel;


import java.util.*;


/**
 * Class NgramUtils: class containing useful functions to deal with n-grams.
 *
 * @author Evann BACALA, Victor BOIX
 */
public class NgramUtils {

    /**
     * Method counting the number of words in a given sequence
     * (the sequence can be a n-gram or a sentence).
     *
     * @param sequence the sequence to consider.
     *
     * @return the number of words of the given sequence.
     */
    public static int getSequenceSize(String sequence) {
        if (sequence.trim().equals("")) return 0;
        else return sequence.split("\\s+").length;
    }


    /**
     * Method parsing a n-gram and returning its history, i.e. the n-1 preceding words.
     * <p>
     * Example:
     * let the ngram be "l' historique de cette phrase";
     * the history will be given for the last word of the ngram, here "phrase":
     * if the order is 2 then the history will be "cette";
     * if the order is 3 then it will be "de cette".
     *
     * @param ngram the n-gram to consider.
     * @param order the order to consider for the n-gram.
     *
     * @return history of the given n-gram (the length of the history is order-1).
     */
    public static String getHistory(String ngram, int order) {
        StringBuilder history = new StringBuilder();
        String[] words = ngram.split("\\s+");

        /*for(int i = words.length - 2; i >= 0 && i >= words.length - order; i--) {
            history.append(words[i]);
            history.append(" ");
        }*/

        if (words.length - order < 0) order--;

        for (int i = words.length - order; i >= 0 && i < words.length - 1; i++) {
            history.append(words[i]);
            history.append(" ");
        }

        return history.toString().trim();
    }


    /**
     * Method decomposing the given sentence into n-grams of the given order.
     * <p>
     * This method will be used in the LanguageModelInterface class for computing
     * the probability of a sentence as the product of the probabilities of its n-grams.
     * <p>
     * Example
     * given the sentence "a b c d e f g", with order=3,
     * it will result in the following list:
     * [a, a b, a b c, b c d, c d e, d e f, e f g]
     *
     * @param sentence the sentence to consider.
     * @param order    the maximal order for the n-grams to create from the sentence.
     *
     * @return the list of n-grams constructed from the sentence.
     */
    public static List<String> decomposeIntoNgrams(String sentence, int order) {
        if (sentence.trim().equals("")) return null;

        List<String> ngramsList = new ArrayList<String>();
        String[] words = sentence.split("\\s+");
        StringBuilder subSentence = new StringBuilder();

        for (String word : words) {
            subSentence.append(word);
            subSentence.append(" ");
            ngramsList.add(getHistory(subSentence.toString(), order) + " " + word);
        }

        List<String> res = new ArrayList<String>();

        for (String ngram : ngramsList) {
            ngram = ngram.trim();
            res.add(ngram);
        }
        return res;
    }


    /**
     * Method parsing the given sentence and generate all the combinations of ngrams,
     * by varying the order n between the given minOrder and maxOrder.
     * <p>
     * This method will be used in the NgramCount class for counting the ngrams
     * occurring in a corpus.
     * <p>
     * Algorithm (one possible algo...)
     * initialize list of ngrams
     * for n = minOrder to maxOrder (for each order)
     * for i = 0 to sentence.length-n (parse the whole sentence)
     * initialize ngram string parsedSentence
     * for j = i to i+n-1 (create a ngram made of the following sequence of words starting from i to i + the order size)
     * ngram = ngram + " " + sentence[j]
     * add ngramm to list ngrams
     * return list ngrams
     * <p>
     * Example
     * given the sentence "a b c d e f g", with minOrder=1 and maxOrder=3, it will result in the following list:
     * [a, b, c, d, e, f, g, a b, b c, c d, d e, e f, f g, a b c, b c d, c d e, d e f, e f g]
     *
     * @param sentence the sentence from which to generate n-grams.
     * @param minOrder the minimal order of the n-grams to create.
     * @param maxOrder the maximal order of the n-grams to create.
     *
     * @return a list of generated n-grams from the sentence.
     */
    public static List<String> generateNgrams(String sentence, int minOrder, int maxOrder) {
        List<String> ngramsList = new ArrayList<>();
        String words[] = sentence.split("\\s+");
        StringBuilder ngramParsedSentence;

        if (minOrder <= maxOrder && minOrder > 0 && maxOrder < words.length) {
            for (int n = minOrder; n <= maxOrder; n++) {
                for (int i = 0; i <= words.length - n; i++) {
                    ngramParsedSentence = new StringBuilder();
                    for (int j = i; j < i + n; j++) {
                        ngramParsedSentence.append(" ");
                        ngramParsedSentence.append(words[j]);
                    }
                    ngramsList.add(ngramParsedSentence.toString().trim());
                }
            }
        }
        return ngramsList;
    }

    /**
     * Method parsing a sequence of words and returning the modified string where
     * out-of-vocabulary words are replaced with the OOV tag.
     *
     * @param s     the string to consider.
     * @param vocab the vocabulary to consider.
     *
     * @return the sequence of words with OOV tags according to the vocabulary.
     */
    public static String getStringOOV(String s, VocabularyInterface vocab) {
        String[] words = s.split("\\s+");
        StringBuilder newSentence = new StringBuilder();

        for (String word : words) {
            if (vocab.contains(word)) {
                word = VocabularyInterface.OOV_TAG;
            }
            newSentence.append(word);
            newSentence.append(" ");
        }
        return newSentence.toString().trim();
    }

}
