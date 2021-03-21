import java.io.*;

//文件处理类
public class FileDeal 
{ 
	//文件读取
	public String ReadFile(String path) throws IOException 
	{ 
		
		File file = new File(path);
		if (!file.exists() || file.isDirectory()) 
		{
			System.out.println("请输入正确文件名！");
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
	
	//文件写入
	public void WriteToFile(String str,String file) throws IOException 
	{ 
		File writename = new File(file);
		//创建新文件
		writename.createNewFile(); 
		BufferedWriter out = new BufferedWriter(new FileWriter(writename));
		out.write(str);
		out.flush(); 
		out.close(); 
	}

}