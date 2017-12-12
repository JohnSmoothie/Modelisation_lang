package langModel;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;


/**
 * Class de test pour Vocabulary
 * @author Victor BOIX
 */

public class VocabularyTest {

    VocabularyInterface vocabulaire;

   // @Before
    // public void initTest() {
      // vocabulaire = new Vocabulary();
   // }

    /**
     * The following code displays a separator
     * between each method output
     *
     * (manually added)
     **/
//    @Rule
  //  public TestName name = new TestName();

    @Test
    public void testGetSize() {
        vocabulaire = new Vocabulary();
        assertEquals(0, vocabulaire.getSize());
    }



}
