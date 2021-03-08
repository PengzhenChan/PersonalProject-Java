import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

class LibTest {

    Lib lib;
	
	LibTest(){
		lib = new Lib("C:\\Users\\29847\\Desktop\\≤‚ ‘.txt","C:\\Users\\29847\\Desktop\\Ω·π˚.txt");
	}
	
	@Test
	void testLib() {
		assertEquals(lib.wordlines,0);
		assertEquals(lib.characters,0);
		assertEquals(lib.anticle,"");
	}

	@Test
	void testMapValueSort() {
		
	}

	@Test
	void testIsWord() {
		 assertEquals(lib.isWord("1234"),-1);
		 assertEquals(lib.isWord("ttt43"),-1);
		 assertEquals(lib.isWord("tttt3"),1);
		 assertEquals(lib.isWord("aaaaaa3"),1);
		 assertEquals(lib.isWord("t43te"),-1);
	}

	@Test
	void testCalculate() throws FileNotFoundException{
		lib.calculate();
		assertEquals(lib.characters,490);
	//	assertEquals(lib.wordnumbers,50);
		assertEquals(lib.wordlines,7);
		Lib temp = new Lib("C:\\Users\\29847\\Desktop\\≤‚ ‘1.txt","");
		assertEquals(temp.wordlines,2);
		assertEquals(temp.characters,30);
		assertEquals(temp.wordnumbers,6);
	}

	@Test
	void testCalculateWordsNumber() throws FileNotFoundException{
		lib.calculate();
		lib.calculateWordsNumber();
		assertEquals(lib.list.get(0).getKey(),"those");
		assertEquals(lib.list.get(2).getValue(),2);
		assertEquals(lib.list.get(1).getKey(),"make");
	}

	@Test
	void testResultShow() {
	    
	}

	@Test
	void testWork() throws FileNotFoundException{
		lib.work();
		assertEquals(lib.result,"characters:490\r\n" + 
				"words:50\r\n" + 
				"lines:7\r\n" + 
				"word1:those\r\n" + 
				"word2:make\r\n" + 
				"word3:message\r\n" + 
				"word4:really\r\n" + 
				"word5:that\r\n" + 
				"word6:this\r\n" + 
				"word7:when\r\n" + 
				"word8:will\r\n" + 
				"word9:another\r\n" + 
				"word10:appreciate");
	    Lib temp = new Lib("C:\\Users\\29847\\Desktop\\≤‚ ‘1.txt","");
        assertEquals(temp.result,"characters:31\r\n" + 
        		"words:0\r\n" + 
        		"lines:2\r\n" + 
        		"");
        Lib temp1 = new Lib("C:\\Users\\29847\\Desktop\\≤‚ ‘2.txt","");
        assertEquals(temp.result,"characters:41\r\n" + 
        		"words:5\r\n" + 
        		"lines:2\r\n" + 
        		"word1:erer\r\n" + 
        		"word2:dfdk\r\n" + 
        		"word3:dfjhdtn\r\n" + 
        		"word4:dfri\r\n" + 
        		"");
}
}
