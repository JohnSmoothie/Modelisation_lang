package langModel;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class NgramCountsTest {
    @Test
    public void getMaximalOrder() {
        NgramCounts ngramCountTest = new NgramCounts();
        ngramCountTest.readNgramCountsFile("lm/small_corpus/ngramCounts_bigram_vocabulary1.txt");

        assertEquals(2, ngramCountTest.getMaximalOrder());
    }

    @Test
    public void getNgramCounterSize() {
        NgramCounts ngramCountTest = new NgramCounts();
        ngramCountTest.readNgramCountsFile("lm/small_corpus/ngramCounts_bigram_vocabulary1.txt");

        assertEquals(28, ngramCountTest.getNgramCounterSize());
    }

    @Test
    public void getTotalWordNumber() {
        NgramCounts ngramCountTest = new NgramCounts();
        ngramCountTest.readNgramCountsFile("lm/small_corpus/ngramCounts_bigram_vocabulary1.txt");

        assertEquals(12, ngramCountTest.getTotalWordNumber());
    }

    @Test
    public void getNgrams() {
        NgramCounts ngramCountTest = new NgramCounts();
        ngramCountTest.readNgramCountsFile("lm/small_corpus/ngramCounts_bigram_vocabulary1.txt");

        Set<String> getNgramsTest = new HashSet<>();
        getNgramsTest.add("<s> denis");
        getNgramsTest.add("écoute une");
        getNgramsTest.add("de");
        getNgramsTest.add("</s>");
        getNgramsTest.add("elle écoute");
        getNgramsTest.add("thom </s>");
        getNgramsTest.add("lionel");
        getNgramsTest.add("denis");
        getNgramsTest.add("une chanson");
        getNgramsTest.add("chanson");
        getNgramsTest.add("<s> elle");
        getNgramsTest.add("autre chanson");
        getNgramsTest.add("elle");
        getNgramsTest.add("denis écoute");
        getNgramsTest.add("chanson </s>");
        getNgramsTest.add("lionel </s>");
        getNgramsTest.add("chanson de");
        getNgramsTest.add("de lionel");
        getNgramsTest.add("thom");
        getNgramsTest.add("<s>");
        getNgramsTest.add("<s> antoine");
        getNgramsTest.add("écoute thom");
        getNgramsTest.add("une");
        getNgramsTest.add("antoine");
        getNgramsTest.add("écoute");
        getNgramsTest.add("une autre");
        getNgramsTest.add("antoine écoute");
        getNgramsTest.add("autre");

        assertEquals(getNgramsTest, ngramCountTest.getNgrams());
    }

    @Test
    public void getCounts1() {
        NgramCounts ngramCountTest = new NgramCounts();
        ngramCountTest.readNgramCountsFile("lm/small_corpus/ngramCounts_bigram_vocabulary1.txt");

        assertEquals(0, ngramCountTest.getCounts("salut"));
    }

    @Test
    public void getCounts2() {
        NgramCounts ngramCountTest = new NgramCounts();
        ngramCountTest.readNgramCountsFile("lm/small_corpus/ngramCounts_bigram_vocabulary1.txt");

        assertEquals(1, ngramCountTest.getCounts("une chanson"));
    }
}