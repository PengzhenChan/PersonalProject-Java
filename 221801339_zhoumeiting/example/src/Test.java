package WordCount;

import java.io.File;
import java.io.FileOutputStream;

public class Test {
	public void reduceText(String file_path) {
		 File file=new File(file_path);
         String title="";
         try {
        	FileOutputStream outFile=new FileOutputStream(file);
			for(int i=0;i<1000;i++)
            {
    	      int num=(int)(Math.random()*95+32); //�������32-127��������Ӧ������ʾ��ASCII��
    	      title+=(char) num;
             }
             outFile.write(title.getBytes());
             outFile.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	 }
}
