import java.io.*;

//�ļ�������
public class FileDeal 
{ 
	//�ļ���ȡ
	public String ReadFile(String path) throws IOException 
	{ 
		
		File file = new File(path);
		if (!file.exists() || file.isDirectory()) 
		{
			System.out.println("��������ȷ�ļ�����");
			throw new FileNotFoundException();
		}
		FileInputStream fis = new FileInputStream(file);
		byte[] buf = new byte[1024];
		StringBuffer sb = new StringBuffer();
		while ((fis.read(buf)) != -1) 
		{
			sb.append(new String(buf));
			buf = new byte[1024];
		}
		return sb.toString();
	}
	
	//�ļ�д��
	public void WriteToFile(String str,String file) throws IOException 
	{ 
		File writename = new File(file);
		//�������ļ�
		writename.createNewFile(); 
		BufferedWriter out = new BufferedWriter(new FileWriter(writename));
		out.write(str);
		out.flush(); 
		out.close(); 
	}

}