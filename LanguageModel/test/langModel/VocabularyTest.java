package langModel;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;


/**
 * Class de test pour Vocabulary
 *
 * @author Victor BOIX, Evann BACALA
 */

public class VocabularyTest {

    VocabularyInterface vocabulary_empty;
    VocabularyInterface vocabulary_full;

    @Before
    public void initTest() {
        vocabulary_empty = new Vocabulary();
        vocabulary_full = new Vocabulary();
        vocabulary_full.addWord("mot");
        vocabulary_full.addWord("bonjour");
        vocabulary_full.addWord("salut");
        vocabulary_full.addWord("hey");
    }

    @Test
    public void getSize1() {
        assertEquals(0, vocabulary_empty.getSize());
    }

    @Test
    public void getSize2() {
        assertEquals(4, vocabulary_full.getSize());
    }

    @Test
    public void getWord1() {
        assertEquals(new HashSet<String>(), vocabulary_empty.getWords());
    }

    @Test
    public void contains1() {
        assertEquals(false, vocabulary_empty.contains("salut"));
    }

    @Test
    public void contains2() {
        assertEquals(true, vocabulary_full.contains("salut"));
    }

    @Test
    public void contains3() {
        assertEquals(false, vocabulary_full.contains("coucou"));
    }

    @Test
    public void addWord1() {
        vocabulary_empty.addWord("test");
        assertEquals(true, vocabulary_empty.contains("test"));
    }

    @Test
    public void removeWord1() {
        vocabulary_full.removeWord("bonjour");
        assertEquals(false, vocabulary_full.contains("bonjour"));
    }

    @Test
    public void readVocabularyFile1() {
        String path = "lm/small_corpus/vocabulary1_in.txt";
        VocabularyInterface vocabulary_test = new Vocabulary();
        vocabulary_test.readVocabularyFile(path);
        assertEquals(true, vocabulary_test.contains("antoine"));
    }

    @Test
    public void readVocabularyFile2() {
        String path = "WrongPath..";
        VocabularyInterface vocabulary_test = new Vocabulary();
        vocabulary_test.readVocabularyFile(path);
        assertEquals(false, vocabulary_test.contains("antoine"));
    }

    @Test
    public void writeVocabularyFile1() {
        String newPath = "lm/small_corpus/vocabulary1_in_test.txt";
        vocabulary_full.writeVocabularyFile(newPath);
        VocabularyInterface vocabulary_test = new Vocabulary();
        vocabulary_test.readVocabularyFile(newPath);
        assertEquals(true, vocabulary_test.contains("bonjour"));
    }

    @Test
    public void scanNgramSet1() {
        Set<String> ngramTest = new HashSet<String>();
        ngramTest.add("<s>");
        ngramTest.add("il");
        ngramTest.add("fait");
        ngramTest.add("beau");
        ngramTest.add("</s>");
        ngramTest.add("<s> il");
        ngramTest.add("il fait");
        ngramTest.add("fait beau");
        ngramTest.add("beau </s>");
        ngramTest.add("<s> il fait");
        ngramTest.add("il fait beau");
        ngramTest.add("fait beau </s>");
        VocabularyInterface vocabulary_test = new Vocabulary();
        vocabulary_test.scanNgramSet(ngramTest);

        vocabulary_test.writeVocabularyFile("lm/small_corpus/vocabulary1_in_test2.txt");

        assertEquals(true, vocabulary_test.contains("fait"));
    }
}
