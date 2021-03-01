package WordCount;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class CountUtil 
{
	/**
	 * �����ַ���
	 * @param file_paths
	 * @return
	 */
	public String ReturnCharactersNum(String file_path) {
        int count = 0,bytes = 0;
        String result = "";//���ڴ洢����ֵ
        byte [] item = new byte[20*1024];//�ô洢��ȡ���ݵĶ����ֽ�����
        int len = item.length;//�õ�item�ĳ����Ա���ѭ��ʱ��������.length
        FileInputStream in = null;//����һ���ļ�������
        try {
            //for (String file_path : file_paths) {
                 in = new FileInputStream(file_path);//�õ��ַ���������stringΪ�ļ�����·��         
                while ((bytes = in.read(item,0,len))!=-1) {
                		count+=bytes;//ͳ���ۼƶ�ȡ���ַ���,һ��Ӣ���ַ�ռһ���ֽ�
                }
                result += "characters: "+count+"\n";//����ַ���ƴ��
                count = 0;  
            //}    
        } catch (FileNotFoundException e) {
            System.out.println("���ļ����������˶ԣ����������ʹ�����·������ʹ�þ���·����"); //��鵽�ļ������ڣ���ʾ����
            System.exit(0); //��������
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();//�ر�������
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
	/**
	 * ���ص�����
	 * @param file_paths
	 * @return
	 */
	public String ReturnWordsNum(String file_path){
		int count=0;
		String result="";
		StringBuffer saveString=new StringBuffer();
		String tmp="";
		FileInputStream in=null;//�ļ��ַ�������
		InputStreamReader isr=null;//�ֽ�������
		BufferedReader bis=null;//����������
		try {
			//for(String file_path:file_paths)
			{
				in=new FileInputStream(file_path);
				isr=new InputStreamReader(in);
				bis=new BufferedReader(isr);
				while((tmp=bis.readLine())!=null)//readLine���ض�ȡ�ֽڣ�û���˾ͷ���null
				{
					saveString.append(tmp);//���¶����������ݱ��浽���еĺ���
					saveString.append(" ");//�Ա�ָ��ټ�һ���ո񣬶�һ�м�һ��
				}
				//saveString.toString().replaceAll("[^A-Za-z0-9]"," ");
				tmp = saveString.toString(); //���ַ����洢������split�������ֵ���
				//tmp.replaceAll("[\\W]|_", " ");//���з���ĸ���ַ����滻�ɿո�
				tmp=tmp.replaceAll("[^A-Za-z0-9]", " ");//�滻���з���ĸ���ֵķ���Ϊ�ո�
				tmp=tmp.toLowerCase();//ȫ����ΪСд�������ǰ�ĸ��Ƿ�Ϊ��ĸ
				String [] total = tmp.split("[\\s+]");//�����Կո�ָ�
				count=total.length;
			    for(int i=0;i<total.length;i++)
			    {
			    	String s=total[i].toString();
			    	if(s.length()<4)
			    	{
			    		count--;
			    	}
			    	else
			    	{
			    		for(int j=0;j<4;j++)
			    		{
			    			char c=s.charAt(j);
			    			if(!(c>='a'&&c<='z'))//ֻҪ����ĸ���Ѿ�����Сд��
			    			{
			    				count--;
			    				break;
			    			}
			    		}
			    	}
			    }
				result += "words: "+count+"\n"; //����ַ���ƴ��
				count=0;
			}
		}catch (FileNotFoundException e) {
				System.out.println("���ļ����������˶ԣ����������ʹ�����·������ʹ�þ���·����");
				System.exit(0); //��������
	    }catch (IOException e) {
	    	e.printStackTrace();
		}finally {
			try {
				in.close();//�ر��ļ��ַ�������
				isr.close();//�ر��ֽ�������
				bis.close();//�رջ���������
				} catch (IOException e) {
					 e.printStackTrace();
				}
		}
		return result;
    }
	/**
	 * ��������
	 * @param file_paths
	 * @return
	 */
	public String ReturnLinesNum(String file_path){
		int count=0;
		String result="";
		FileInputStream in=null;//�ļ��ַ�������
		InputStreamReader isr=null;//�ֽ�������
		BufferedReader bis=null;//����������
		try {
			//for(String file_path:file_paths)
			{
				in=new FileInputStream(file_path);
				isr=new InputStreamReader(in);
				bis=new BufferedReader(isr);
				while(bis.readLine()!=null)
				{
					count++;
				}
				result += "lines: "+count+"\n"; //����ַ���ƴ��
				count=0;
			}
		}catch (FileNotFoundException e) {
				System.out.println("���ļ����������˶ԣ����������ʹ�����·������ʹ�þ���·����");
				System.exit(0); //��������
	    }catch (IOException e) {
	    	e.printStackTrace();
		}finally {
			try {
				in.close();//�ر��ļ��ַ�������
				isr.close();//�ر��ֽ�������
				bis.close();//�رջ���������
				} catch (IOException e) {
					 e.printStackTrace();
				}
		}
		return result;
	}
	public String ReturnMaxWordsNum(String file_path){
		String result="";
		StringBuffer saveString=new StringBuffer();
		String tmp="";
		FileInputStream in=null;//�ļ��ַ�������
		InputStreamReader isr=null;//�ֽ�������
		BufferedReader bis=null;//����������
		try {
			//for(String file_path:file_paths)
			{
				in=new FileInputStream(file_path);
				isr=new InputStreamReader(in);
				bis=new BufferedReader(isr);
				while((tmp=bis.readLine())!=null)//readLine���ض�ȡ�ֽڣ�û���˾ͷ���null
				{
					saveString.append(tmp);//���¶����������ݱ��浽���еĺ���
					saveString.append(" ");//�������ֵ��ʣ���һ�ж�ȡ��һ���ո�
				}
				Map<String,Integer>map = new HashMap<String, Integer>();//���ù�ϣ����ķ�����������
				tmp = saveString.toString(); //���ַ����洢������split�������ֵ���
				/*for(int i=0;i<tmp.length();i++)//�滻����
				{
					char c = tmp.charAt(i);
					int v = (int)c;
					if(v>=19968 && v <= 171941){
						tmp.replace(new String(), " ");
					}
				}*/
				tmp=tmp.replaceAll("[^A-Za-z0-9]", " ");//���з���ĸ���ַ����滻�ɿո�
				tmp=tmp.toLowerCase();//ȫ������Сд
	            StringTokenizer st = new StringTokenizer(tmp," ");//�ָ��ַ���
	            //�ѷָ�õĵ��ʱ�����letter�ַ�����
	            while (st.hasMoreTokens()) 
	            {
	                 String letter = st.nextToken();
	                 int count;
	                 if (map.get(letter) == null) {
	                     count = 1;//������û�н��зָ
	                 } else {
	                     count = map.get(letter).intValue() + 1;
	                 }
	                 map.put(letter,count);
	            }
	            Set<WordEntity> set = new TreeSet<WordEntity>();
	            for (String key : map.keySet()) {
	                 set.add(new WordEntity(key,map.get(key)));
	             }
	            int count=1;
	            for (Iterator<WordEntity> it = set.iterator(); it.hasNext();) {
	                 WordEntity w = it.next();
	                 boolean isWords=true;
	                 if(w.getKey().length()>4)
	                 {
	                	 String s=w.getKey();
	                	 for(int i=0;i<4;i++)
	                	 {
	                		 char c=s.charAt(i);
  			    			 if(!(c>='a'&&c<='z'))//ֻҪ����ĸ���Ѿ�����Сд��
  			    			 {
  			    				isWords=false;
  			    				break;
  			    			 }
	                	 }
	                	 if(isWords==true)
	                	 {
	                	     result+=w.getKey()+": "+w.getCount()+"\n";
	                	     if(count==10)
	                	     {
	                		    break;
	                	     }   
	                         count++;
	                	 }
	                 }
	            }
			}
		}catch (FileNotFoundException e) {
				System.out.println("���ļ����������˶ԣ����������ʹ�����·������ʹ�þ���·����");
				System.exit(0); //��������
	    }catch (IOException e) {
	    	e.printStackTrace();
		}finally {
			try {
				in.close();//�ر��ļ��ַ�������
				isr.close();//�ر��ֽ�������
				bis.close();//�رջ���������
				} catch (IOException e) {
					 e.printStackTrace();
				}
		}
		return result;
	}
	public boolean OutputFile(String File_path,String Context){
		File OutputFile = new File(File_path); //����File����
		FileOutputStream os = null; //���� �ļ������
		byte [] a = null; //���ڴ洢Contextת����byte�ֽ�����
		try {
			if(!OutputFile.exists()) {        //�ж��ļ��Ƿ����
				OutputFile.createNewFile(); //�����ڣ�����һ���ļ�
			}
			FileWriter fileWriter =new FileWriter(File_path);//��������ļ����ݹ���
			fileWriter.write("");
		    fileWriter.flush();
		    fileWriter.close();
			os = new FileOutputStream(OutputFile); //������������
			a = Context.getBytes(); //��Contextת��ΪByte���飬�Ա�д���ļ�
			os.write(a); //��byte����д���ļ�
		}catch(IOException e) {
			e.printStackTrace();
		}finally{
			try {
				os.close(); //�ر������
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
}