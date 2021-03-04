
import java.io.*;
import java.util.*;

public class Frequency {

    //ָ���ļ�·�����ļ���
    private static String path;

    //����һ��������ַ���
    public static String result = "";

    //����һ��map���ϱ��浥�ʺ͵��ʳ��ֵĸ���
    private TreeMap<String,Integer> tm;

    public Frequency(String path) {
        this.path = path;

        File file=new File(path);
        Reader readFile = null;
        BufferedReader bufferedReadFile = null;
        String tempString = null;
        try {
            readFile = new InputStreamReader(new FileInputStream(file),"UTF-8");
            bufferedReadFile = new BufferedReader(readFile);

            tm = new TreeMap<String,Integer>();
            //��ȡ�ļ�
            while ((tempString=bufferedReadFile.readLine())!=null){
                tempString = tempString.toLowerCase();
                String reg1 = "[^a-zA-Z0-9]";
                String reg2 ="[a-z]{4}[a-z0-9]*";
                //����ȡ���ı����зָ�
                String[] str = tempString.split(reg1);
                for (String s: str){
                    if (s.matches(reg2)){
                        //�жϼ������Ƿ��Ѿ����ڸõ��ʣ���������������1�����򽫵�����ӵ������У��Ҹ�����Ϊ1
                        if (!tm.containsKey(s)){
                            tm.put(s,1);
                        }
                        else {
                            tm.put(s,tm.get(s)+1);
                        }
                    }
                }
            }
            bufferedReadFile.close();
        }
        catch (Exception e){
            System.err.println("�ļ�������");
        }
        finally {
            saveResult(tm);
        }
    }

    public static String saveResult(Map<String,Integer> map) {
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                //�������򣬹�o2 cmp o1����Ϊo2>o1ʱ����1��o2<o1ʱ����-1
                return (o2.getValue().compareTo(o1.getValue()));
            }
        });

        int num = list.size();
        //System.out.println(num);
        for (int i = 0; i < num && i < 10; i++) {
            Map.Entry<String, Integer> entry = list.get(i);
            if (entry == null) {
                return "";
            }
            result += (entry.getKey() + ": " + entry.getValue() + '\n');
        }

        return result;
    }
}