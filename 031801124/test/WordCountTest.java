

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 

/** 
* WordCount Tester. 
* 
* @author <Authors name> 
* @since <pre>03/03/2021</pre> 
* @version 1.0 
*/ 
public class WordCountTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: main(String[] args) 
* 
*/ 
@Test
public void testMain() throws Exception { 
//TODO: Test goes here...
    String[] files={"D:\\java\\data.txt","D:\\java\\output.txt"};
    WordCount.main(files);

}

    @Test
    public void testMainWrong() throws Exception {
//TODO: Test goes here...
        String[] files={"D:\\java\\datawrong.txt","D:\\java\\output.txt"};
        try{WordCount.main(files);}
        catch (Exception e) {System.out.println("应该出错了");}

    }
} 
