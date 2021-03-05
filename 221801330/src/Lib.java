import java.io.*;
import java.util.*;

public class Lib {
	private int charSum;
	private int wordSum;
	private int lineSum;
	private HashMap<String, Integer> wordFrequency;
	
	Lib(){
		charSum = 0;
		wordSum = 0;
		lineSum = 0;
		wordFrequency = new HashMap<String,Integer>();
	}
	
	void SetCharSum(int n) {
	    charSum = n;
	}
	
	int GetCharSum() {
		return charSum;
	}
	
	void SetWordSum(int n) {
		wordSum += n;
	}

	int GetWordSum() {
		return wordSum;
	}
	
	void SetLineSum() {
	    lineSum++;
	}

	void SetLineSum(int n) {
		lineSum += n;
	}

	int GetLineSum() {
		return lineSum;
	}
	
	void SetWordFrequency(HashMap<String, Integer> hash) {
		for (String s : hash.keySet()) {
			if (wordFrequency.containsKey(s)) {
				wordFrequency.put(s, wordFrequency.get(s) + hash.get(s));
			}
			else {
				wordFrequency.put(s, 1);
			}
		}
	}

	List<Map.Entry<String, Integer>> GetWordFrequency() {
		List<Map.Entry<String, Integer>> list = null;
		list = new ArrayList<Map.Entry<String, Integer>>(wordFrequency.entrySet());
		list.sort(new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				if (o1.getValue() != o2.getValue()) {
					return o2.getValue().compareTo(o1.getValue());
				}
				else {
					return o1.getKey().compareTo(o2.getKey());
				}
			}
		});
		return list;
	}
	
    static Lib lib = new Lib();
    
    static int CharSum(File f) {
        int charSum = 0;
        try {
            byte[] bytes = new byte[1024];
            int num;
            FileInputStream input = new FileInputStream(f);
            while ((num = input.read(bytes, 0, bytes.length)) != - 1) {
            	charSum += num;
            }
            lib.SetCharSum(charSum);
            input.close();
        }
        catch (FileNotFoundException e) {
        	System.err.println(" error: " + e);
            e.printStackTrace();
        }
        catch (IOException e) {
        	System.err.println(" error: " + e);
            e.printStackTrace();
        }
        finally {
            return charSum;
        }
    }
    
    static boolean WordJudge(String s) {
        if (s.length() < 4) {
        	return false;
        }
        else if (! Character.isDigit(s.charAt(0))) {
               return false;
        }
        else {
        	return true;
        }
    }
    
    static int WordSum(String s) {
        int wordSum = 0;
        String word = "";
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i)) || Character.isUpperCase(s.charAt(i))
                    || Character.isLowerCase(s.charAt(i))) {
                word += s.charAt(i);
            }
            else {
                if (WordJudge(word)) {
                	wordSum++;
                }
                word = "";
            }
        }
        if (WordJudge(word)) {
        	wordSum++;
        }
        return wordSum;
    }

    static HashMap<String, Integer> GetWordFrequency(String s) {
        String word = "";
        HashMap<String, Integer> hash = new HashMap<String, Integer>();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i)) || Character.isUpperCase(s.charAt(i))
                    || Character.isLowerCase(s.charAt(i))) {
                word += s.charAt(i);
            }
            else {
                if (WordJudge(word)) {
                    word = word.toLowerCase();
                    if (hash.containsKey(word)) {
                    	hash.put(word, hash.get(word) + 1);
                    }
                    else {
                        hash.put(word, 1);
                    }
                }
                word = "";
            }
        }
        if (WordJudge(word)) {
            word = word.toLowerCase();
            if (hash.containsKey(word)) {
            	hash.put(word, hash.get(word) + 1);
            }
            else
            	hash.put(word, 1);
        }
        return hash;
    }
	
	static void FileRead(File f1) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(f1));
            String s = "";
            for (s = br.readLine();s != null;s = br.readLine()) {
            	lib.SetWordSum(WordSum(s));
            	lib.SetLineSum();
            	lib.SetWordFrequency(GetWordFrequency(s));
            }
            br.close();
            return;
        }
        catch (FileNotFoundException e) {
        	System.err.println(" error: " + e);
            e.printStackTrace();
        }
        catch (IOException e) {
        	System.err.println(" error: " + e);
            e.printStackTrace();
        }
    }

    static Lib GetData() {
        return lib;
    }
}
