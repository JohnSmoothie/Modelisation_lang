import langModel.*;


public class Application_LanguageModels {

	public static void main(String[] args) {
		String sentence1 = "<s> antoine écoute une chanson </s>";
		String sentence2 = "<s> lionel écoute une chanson </s>";


		/* création des vocabulaires */
		
		VocabularyInterface vocab1, vocab2;
		
		//création du premier vocabulaire
		
		vocab1 = new Vocabulary();
		String path1 = "./lm/small_corpus/vocabulary1_in.txt";
		vocab1.readVocabularyFile(path1);
		
		//création du second vocabulaire
		
		vocab2 = new Vocabulary();
		String path2 = "./lm/small_corpus/vocabulary2_in.txt";
		vocab2.readVocabularyFile(path2);
		
		/* création des modèles de langage */
		
		NgramCountsInterface ngramCounts_bigram_vocab1, ngramCounts_bigram_vocab2;
		LanguageModelInterface lm_bigram_vocab1, lm_bigram_laplace_vocab1, lm_bigram_vocab2, lm_bigram_laplace_vocab2;
		
		//création du modèle de langage sans lissage, avec le premier vocabulaire
		
		ngramCounts_bigram_vocab1 = new NgramCounts();
		ngramCounts_bigram_vocab1.scanTextFile("./data/small_corpus/corpus_fr_test.txt", vocab1, 2);
		
		lm_bigram_vocab1 = new NaiveLanguageModel();
		lm_bigram_vocab1.setNgramCounts(ngramCounts_bigram_vocab1, vocab1);
		
		//création du modèle de langage avec lissage, avec le premier vocabulaire
		
		ngramCounts_bigram_vocab1.scanTextFile("./data/small_corpus/corpus_fr_test.txt", vocab1, 2);
		
		lm_bigram_laplace_vocab1 = new LaplaceLanguageModel();
		lm_bigram_laplace_vocab1.setNgramCounts(ngramCounts_bigram_vocab1, vocab1);
		
		
		//création du modèle de langage sans lissage, avec le second vocabulaire
		
		ngramCounts_bigram_vocab2 = new NgramCounts();
		ngramCounts_bigram_vocab2.scanTextFile("./data/small_corpus/corpus_fr_test.txt", vocab2, 2);
		
		lm_bigram_vocab2 = new NaiveLanguageModel();
		lm_bigram_vocab2.setNgramCounts(ngramCounts_bigram_vocab2, vocab2);
				
		
		//création du modèle de langage avec lissage, avec le second vocabulaire
		
		ngramCounts_bigram_vocab2.scanTextFile("./data/small_corpus/corpus_fr_test.txt", vocab2, 2);
		
		lm_bigram_laplace_vocab2 = new LaplaceLanguageModel();
		lm_bigram_laplace_vocab2.setNgramCounts(ngramCounts_bigram_vocab2, vocab2);
		
		/* utilisation des modèles de langage */
		
		System.out.println("Probabilit� de la phrase sans lissage '<s> antoine écoute une chanson </s>' :  " + lm_bigram_vocab1.getSentenceProb(sentence1));
		System.out.println("Probabilit� de la phrase avec lissage '<s> antoine écoute une chanson </s>' :  " + lm_bigram_laplace_vocab1.getSentenceProb(sentence1));
	}

}
