import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;


class testcharCount {

	@Test
	void testCharCount() {
		File file = new File("C:\\Users\\pc-pc\\eclipse-workspace\\WordCout\\src\\input.txt");
		int charcount = charCount.charcount(file);
		assertEquals(charcount, 46);
	}
	
	@Test
	void testLineCount() {
		File file = new File("C:\\Users\\pc-pc\\eclipse-workspace\\WordCout\\src\\input.txt");
		int linecount = lineCount.linecount(file);
		assertEquals(linecount, 3);
	}
	
	@Test
	void testwordCount() {
		File file = new File("C:\\Users\\pc-pc\\eclipse-workspace\\WordCout\\src\\input.txt");
		int wordcount = wordCount.wordcount(file);
		assertEquals(wordcount, 6);
	}
	
	@Test
	void testwordDetail() {
		File file = new File("C:\\Users\\pc-pc\\eclipse-workspace\\WordCout\\src\\input.txt");
		wordDetail.worddetail(file);
	}
	
	@Test
	void testMain() {
		new Main();
	}
}
