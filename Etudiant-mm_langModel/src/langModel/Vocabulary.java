package langModel;

import java.util.Set;


/**
 * Class Vocabulary: class implementing the interface VocabularyInterface.
 * 
 * @author ... (2017)
 *
 */
public class Vocabulary implements VocabularyInterface {
	/**
	 * The set of words corresponding to the vocabulary.
	 */
	protected Set<String> vocabulary;

	
	/**
	 * Constructor.
	 */
	public Vocabulary(){
		//TODO
	}
	
	
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<String> getWords() {
		// TODO Auto-generated method stub
		return null;
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