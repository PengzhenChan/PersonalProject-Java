import java.util.*;
import java.util.Map.Entry;

//�������ڽ����ļ��еĵ��ʵȴ���
public class WordDeal 
{
	
	private int charNum; //ͳ���ַ���
	String content;//�����ļ�
	private int wordCount; // ��������
	private int validLine;//��Ч����
	private Map<String, Integer> wordFrequence; // ���ʴ�Ƶ
	
	public WordDeal(String content) 
	{
		this.content = content;
	}

	// ͳ���ļ��ַ���
	public int getCharCount() 
	{
		char c;
		int i=0;
		while (i<(content.length())) 
		{
			c = content.charAt(i);
			if (c >= 32 && c <= 126  || c == '\n' || c == '\t' || c == '\r') 
			{
				charNum++;
			}
			i++;
		}
		return charNum;
	}
	
	//ͳ�Ƶ�������
	public int getWordCount() 
	{
		String s= content;
		//��"\\"�����ִ�
		String[] sp = s.split("\\s"); 
		for (int i = 0; i < sp.length; i++) 
		{
			// �жϳ����Ƿ���ڵ���4,��Ϊֻ�д���4�Ĳų�Ϊ����
			if (sp[i].length() < 4) 
			{ 
				continue;
			} 
			else 
			{
				// �ж��ַ�����ǰ��λ�Ƿ���Ӣ����ĸ
				int flag = 1; 
				char c;
				for (int j = 0; j < 4; j++) 
				{
					c = sp[i].charAt(j);
					if (!(c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z')) 
					{
						flag = 0;
					}
				}
				if (flag == 1) 
				{
					wordCount++;
				}
			}
		}
		return wordCount;
	}
	
	
	// ͳ����Ч����
	public int getLineCount() 
	{ 
		//��ÿһ�зֿ�����һ���ַ�������
		String[] line = content.split("\n"); 
		// �ҳ���Ч�У�ͳ����Ч��
		for (int i = 0; i < line.length; i++) 
		{ 

			if (line[i].trim().length() == 0)
				continue;
			validLine = validLine + 1;
		}
		return validLine;
	}
	
	//�Ե��ʴ�Ƶ��Map��������
	public List getWordFreq() 
	{ 
	    wordFrequence = new HashMap<String, Integer>();
		String con = content;
		//�ִ�
		String[] spWord = con.split("\\s"); 
		for (int i = 0; i < spWord.length; i++) 
		{
			if (spWord[i].length() < 4) 
			{
				continue;
			} 
			else 
			{
			int flag = 1;
			char c;
			for (int j = 0; j < 4; j++) 
			{
			    c = spWord[i].charAt(j);
				if (!(c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z')) 
				{
						flag = 0;
				}
			}
			if (flag == 1) 
			{
			    spWord[i] = spWord[i].trim().toLowerCase();
				if (wordFrequence.get(spWord[i]) == null) 
				{
						wordFrequence.put(spWord[i], 1);
				} 
				else
						wordFrequence.put(spWord[i], wordFrequence.get(spWord[i]) + 1);

				}
			}
		}

		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(wordFrequence.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() 
		{
			// ��Map�����ݽ��������Ȱ���Ƶ���ֵ�˳��
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) 
			{ 
				if (o1.getValue() == o2.getValue()) 
				{
					return o1.getKey().compareTo(o2.getKey());
				}
				return o2.getValue() - o1.getValue();
			}

		}
		);
		return list;
	}
	
	// ���������ListԪ��ɸѡ��ǰʮ������������
	public String[] ListToArray(List<Map.Entry<String, Integer>> list) 
	{ 
		String[] arr;
		int i = 0;
		int len = list.size();
		if (len <= 10) 
		{
			arr = new String[len];
			for (Map.Entry<String, Integer> m : list) 
			{
				arr[i] = m.getKey() + ":" + m.getValue();
				i++;
			}
		} 
		else 
		{
			arr = new String[10];
			for (Map.Entry<String, Integer> m : list) 
			{
				if (i == 10)
					break;
				arr[i] =  m.getKey() + ": " + m.getValue();
				i++;
			}
		}
		return arr;
	}
}