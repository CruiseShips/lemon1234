package com.lemon1234.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SensitiveWordsUtil {
	
	private static Map keysMap = new HashMap();

	public static boolean badWordFind(List<String> words, String text) {
		if(words == null) {
			return true;
		}
		SensitiveWordsUtil.initKeyWords(words);
		return SensitiveWordsUtil.isContentKeyWords(text);
	}

	private static boolean isContentKeyWords(String text) {
		for (int i = 0; i < text.length(); i++) {
            int len = checkKeyWords(text, i);
            if (len > 0) {
                return true;
            }
        }
        return false;
	}

	private static int checkKeyWords(String txt, int begin) {
		Map nowhash = keysMap;
        int res = 0;
        for (int i = begin; i < txt.length(); i++) {
            char word = txt.charAt(i);
            Object wordMap = nowhash.get(word);//得到该字符对应的HashMap
            if (wordMap == null) {
                return 0;//如果该字符没有对应的HashMap，return 0
            }

            res++; //如果该字符对应的HashMap不为null，说明匹配到了一个字符，+1
            nowhash = (HashMap) wordMap;//将遍历的HashMap指向该字符对应的HashMap
            if (((String) nowhash.get("isEnd")).equals("1")) { //如果该字符为敏感词的结束字符，直接返回
                return res;
            } else {
                continue;
            }
        }
        return res;
	}

	private static void initKeyWords(List<String> words) {
		for (int i = 0; i < words.size(); i++) {
            String key = words.get(i).trim();
            Map nowhash = keysMap;//初始从最外层遍历
            for (int j = 0; j < key.length(); j++) {
                char word = key.charAt(j);
                Object wordMap = nowhash.get(word);
                if (wordMap != null) {
                    nowhash = (HashMap) wordMap;
                } else {
                    HashMap<String, String> newWordHash = new HashMap<String, String>();
                    newWordHash.put("isEnd", "0");
                    nowhash.put(word, newWordHash);
                    nowhash = newWordHash;
                }
                if (j == key.length() - 1) {
                    nowhash.put("isEnd", "1");
                }
            }
        }
	}
}
