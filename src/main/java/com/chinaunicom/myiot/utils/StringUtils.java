package com.chinaunicom.myiot.utils;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;
import java.util.Random;

public class StringUtils {

    
    private StringUtils() {
    }

    
    
    
    public static boolean isEmpty(String str) {
        return (str == null || str.length() == 0);
    }

    
    public static boolean isNotEmpty(String str) {
        return (str != null && str.length() > 0);
    }

    
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    
    public static boolean isNotBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return false;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static String nullToEmpty(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    
    public static String replaceString(
            String sourceString,
            String oldString,
            String newString) {
        if (sourceString == null) {
            return "";
        }
        String str = "";
        int beginIndex = 0;
        int endIndex = 0;
        while ((endIndex = sourceString.indexOf(oldString, beginIndex)) >= 0) {
            str += sourceString.substring(beginIndex, endIndex) + newString;
            beginIndex = endIndex + oldString.length();
        }
        str += sourceString.substring(beginIndex);
        return str;
    }

    
    public static String[] split(String str, String delim) {
        StringTokenizer st = new StringTokenizer(str, delim);
        String[] tokens = new String[st.countTokens()];
        for (int i = 0; st.hasMoreTokens(); i++) {
            tokens[i] = st.nextToken();
        }
        return tokens;
    }

    
    public static String trim(String str, int maxLen) {
        return getLimitLengthString(str, maxLen, "…");
    }

    
    public static String getLimitLengthString(String str, int maxLen, String symbol) {
        if (str == null) {
            return "";
        } else {

            str = str.trim();
        }
        try {
            byte b[] = str.getBytes("GBK");
            if (b.length <= maxLen) {
                return str;
            }

            int len = maxLen - symbol.getBytes("GBK").length;
            int endIndex = 0;

            for (int i = 0, tmpLen = 0; i < str.length(); i++) {
                String subStr = str.substring(i, i + 1);
                tmpLen += subStr.getBytes("GBK").length;
                if (tmpLen == len) {
                    endIndex = i + 1;
                    break;
                } else if (tmpLen > len) {
                    endIndex = i;
                    break;
                }
            }

            return str.substring(0, endIndex) + symbol;
        } catch (UnsupportedEncodingException uee) {
            String errorMessage = "内部错误：本计算机的Java环境不支持GBK编码！";

            throw new IllegalStateException(errorMessage, uee);
        }
    }

    public static String trimAll(String str) {
        if (str == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            
            if (!Character.isWhitespace(ch)) {
                sb.append(ch);
            }
        }

        return sb.toString();
    }

    
    public static String trim(String str) {
        if (str == null || str.equals("")) {
            return "";
        } else {
            
            return str.replaceAll("^[　 ]+|[　 ]+$", "");
        }
    }

    public static String leftTrim(String str) {
        if (str == null || str.equals("")) {
            return "";
        } else {
            return str.replaceAll("^[　 ]+", "");
        }
    }

    public static String rightTrim(String str) {
        if (str == null || str.equals("")) {
            return "";
        } else {
            return str.replaceAll("[　 ]+$", "");
        }
    }

    
    public static String changeCodeISO(String sourceStr) {
        String newStr = "";
        try {
            newStr = new String(sourceStr.getBytes("GBK"), "ISO-8859-1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newStr;
    }

    
    
    
    public static String getStringNumber(int number) {
        
        String[] chineseNumber = new String[]{
            "○", "一", "二", "三", "四", "五", "六", "七", "八", "九"
        };
        if (number < 0) {
            return "负" + getStringNumber(-number);
        } else if (number < 10) {
            return chineseNumber[number];
        } else {
            return getStringNumber(number / 10) + getStringNumber(number % 10);
        }
    }

    
    public static String getChineseNumber(int number) {
        
        String[] chineseNumber = new String[]{
            "零", "一", "二", "三", "四", "五", "六", "七", "八", "九"
        };
        
        String[] chineseUnit = new String[]{
            "", "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千"
        };



        if (number < 0) {
            
            return "负" + getChineseNumber(-number);
        } else if (number < 10) {
            
            return chineseNumber[number];
        } else if (number < 20) {
            if (number % 10 == 0) {
                
                return chineseUnit[1];
            } else {
                
                return chineseUnit[1] + chineseNumber[number % 10];
            }
        } else if (number < 100) {
            if (number % 10 == 0) {
                
                return chineseNumber[number / 10] + chineseUnit[1];
            } else {
                
                return chineseNumber[number / 10] + chineseUnit[1]
                        + chineseNumber[number % 10];
            }
        } else {
            throw new IllegalArgumentException("暂不支持绝对值大于99的转换");
        }
    }

    
    public static int substringCount(String source, String substring) {
        if (source == null || substring == null || source.length() == 0
                || substring.length() == 0) {
            return 0;
        }
        int count = 0;
        int i = 0;
        while (source.indexOf(substring, i) != -1) {
            count++;
            i = source.indexOf(substring, i) + substring.length();
        }
        return count;
    }

    
    public static String replaceStringForSingleSign(String sourceString, String splitChar) {
        if (sourceString == null || splitChar == null) {
            return null;
        }

        
        String[] sign = new String[]{
            " ", ",", "，", ";", ".", ":", "!", "@", "$", "%", "*", "(", ")",
            "-", "——", "_", "“", "”", "+", "=", "|", "\\", "/", "\"", "'",
            "?", "<", ">", "~", "`", "&", "、"
        };
        String newString = ""; 
        String[] newStringArray = null; 
        
        


        for (int i = 0; i < sign.length; i++) {
            sourceString = replaceString(sourceString, sign[i], "#");
        }



        newStringArray = sourceString.split("#");
        for (int i = 0; i < newStringArray.length; i++) {
            if (newStringArray[i].equals("")) {
                continue;
            }
            if (newString.length() != 0) {
                newString += splitChar;
            }
            newString += newStringArray[i];
        }
        return newString.trim();
    }

    
    public static String replaceMacro(String str, String macro, String replacement) {
        StringBuilder regex = new StringBuilder();
        for (int i = 0; i < macro.length(); i++) {
            char ch = macro.charAt(i);
            if (ch == '$' || ch == '{' || ch == '}' || ch == '\\') {
                regex.append("\\");
            }
            regex.append(ch);
        }

        Pattern pattern = Pattern.compile(regex.toString(), Pattern.CASE_INSENSITIVE);
        return pattern.matcher(str).replaceAll(replacement);
    }

    
    public static String repeat(String str, int repeat) {
        if (str == null || str.length() == 0 || repeat <= 0) {
            return "";
        }
        StringBuilder buf = new StringBuilder(str.length() * repeat);
        for (int i = 0; i < repeat; i++) {
            buf.append(str);
        }

        return buf.toString();
    }

    
    public static String getId18(String id) {
        if (id == null || (id.length() != 15 && id.length() != 17)) {
            return id;
        }
        if (id.length() == 15) {
            id = id.substring(0, 6) + "19" + id.substring(6);
        }
        char[] pszSrc = id.toCharArray();
        int[] iW = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        char[] szVerCode = new char[]{'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
        int iS = 0;
        for (int i = 0; i < 17; i++) {
            iS += (pszSrc[i] - '0') * iW[i];
        }
        return id + szVerCode[iS % 11];
    }

    
    public static String getMQString(Object obj) {
        if (obj == null) {
            return "";
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof Number) {
            return ((Number) obj).toString();
        }
        if (obj instanceof Date) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            return sdf.format((Date) obj);
        }
        throw new IllegalArgumentException("不能识别对象类型[" + obj + "]");
    }

    
    public static boolean isAscii(char c) {
        String str = String.valueOf(c);
        byte[] b = null;
        try {
            b = str.getBytes("GBK");
        } catch (UnsupportedEncodingException uee) {
            throw new IllegalStateException("内部错误：本计算机的Java环境不支持GBK编码: " + uee.getMessage());
        }
        if (b.length == 1) {
            return true;
        }
        return false;
    }

    
    public static boolean isGb2312(char c) {
        
        final int GB2312_HIGH_PUNCTUATION_MIN = 0xA1;
        
        final int GB2312_HIGH_PUNCTUATION_MAX = 0xA9;
        
        final int GB2312_HIGH_FIRSTPLANE_MIN = 0xB0;
        
        final int GB2312_HIGH_FIRSTPLANE_MAX = 0xD7;
        
        final int GB2312_HIGH_SECONDPLANE_MIN = 0xD8;
        
        final int GB2312_HIGH_SECONDPLANE_MAX = 0xF7;
        
        final int GB2312_LOW_MIN = 0xA1;
        
        final int GB2312_LOW_MAX = 0xFE;

        String str = String.valueOf(c);
        byte[] b = null;
        try {
            b = str.getBytes("GBK");
        } catch (UnsupportedEncodingException uee) {
            throw new IllegalStateException("内部错误：本计算机的Java环境不支持GBK编码: " + uee.getMessage());
        }
        if (b.length == 2) {








            int ch0 = 0xff & b[0]; 
            int ch1 = 0xff & b[1]; 

            if (((ch0 >= GB2312_HIGH_PUNCTUATION_MIN && ch0 <= GB2312_HIGH_PUNCTUATION_MAX)
                    || (ch0 >= GB2312_HIGH_FIRSTPLANE_MIN && ch0 <= GB2312_HIGH_FIRSTPLANE_MAX)
                    || (ch0 >= GB2312_HIGH_SECONDPLANE_MIN && ch0 <= GB2312_HIGH_SECONDPLANE_MAX))
                    && (ch1 >= GB2312_LOW_MIN && ch1 <= GB2312_LOW_MAX)) {
                return true;
            }
        }
        return false;
    }

    
    public static boolean isGbk(char c) {
        
        final int GBK_HIGH_MIN = 0x81;
        
        final int GBK_HIGH_MAX = 0xFE;
        
        final int GBK_LOW_MIN = 0x40;
        
        final int GBK_LOW_MAX = 0xFE;
        
        final int GBK_LOW_EXCEPTION = 0x7F;

        String str = String.valueOf(c);
        byte[] b = null;
        try {
            b = str.getBytes("GBK");
        } catch (UnsupportedEncodingException uee) {
            throw new IllegalStateException("内部错误：本计算机的Java环境不支持GBK编码: " + uee.getMessage());
        }
        if (b.length == 2) {








            int ch0 = 0xff & b[0]; 
            int ch1 = 0xff & b[1]; 

            if (ch0 >= GBK_HIGH_MIN && ch0 <= GBK_HIGH_MAX
                    && ch1 >= GBK_LOW_MIN && ch1 <= GBK_LOW_MAX
                    && ch1 != GBK_LOW_EXCEPTION) {
                return true;
            }
        }
        return false;
    }

    
    public static boolean isRareWord(char c) {
        
        final int GB2312_HIGH_PUNCTUATION_MIN = 0xA1;
        
        final int GB2312_HIGH_PUNCTUATION_MAX = 0xA9;
        
        final int GB2312_HIGH_FIRSTPLANE_MIN = 0xB0;
        
        final int GB2312_HIGH_FIRSTPLANE_MAX = 0xD7;
        
        final int GB2312_HIGH_SECONDPLANE_MIN = 0xD8;
        
        final int GB2312_HIGH_SECONDPLANE_MAX = 0xF7;
        
        final int GB2312_LOW_MIN = 0xA1;
        
        final int GB2312_LOW_MAX = 0xFE;

        
        final int GBK_HIGH_MIN = 0x81;
        
        final int GBK_HIGH_MAX = 0xFE;
        
        final int GBK_LOW_MIN = 0x40;
        
        final int GBK_LOW_MAX = 0xFE;
        
        final int GBK_LOW_EXCEPTION = 0x7F;

        String str = String.valueOf(c);
        byte[] b = null;
        try {
            b = str.getBytes("GBK");
        } catch (UnsupportedEncodingException uee) {
            throw new IllegalStateException("内部错误：本计算机的Java环境不支持GBK编码: " + uee.getMessage());
        }
        if (b.length == 2) {








            int ch0 = 0xff & b[0]; 
            int ch1 = 0xff & b[1]; 

            if (!(((ch0 >= GB2312_HIGH_PUNCTUATION_MIN && ch0 <= GB2312_HIGH_PUNCTUATION_MAX)
                    || (ch0 >= GB2312_HIGH_FIRSTPLANE_MIN && ch0 <= GB2312_HIGH_FIRSTPLANE_MAX)
                    || (ch0 >= GB2312_HIGH_SECONDPLANE_MIN && ch0 <= GB2312_HIGH_SECONDPLANE_MAX))
                    && (ch1 >= GB2312_LOW_MIN && ch1 <= GB2312_LOW_MAX))
                    && (ch0 >= GBK_HIGH_MIN && ch0 <= GBK_HIGH_MAX
                    && ch1 >= GBK_LOW_MIN && ch1 <= GBK_LOW_MAX
                    && ch1 != GBK_LOW_EXCEPTION)) {
                return true;
            }
        }
        return false;
    }

    
    public static String getRareWords(String s) {
        StringBuilder sb = new StringBuilder();
        if (s != null) {
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (StringUtils.isRareWord(c)) {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

    
    public static String getCapitalWords(String str) {
        int size = str.length();
        char[] chs = str.toCharArray();
        for (int i = 0; i < size; i++) {
            if (chs[i] <= 'Z' && chs[i] >= 'A') {
                chs[i] = (char) (chs[i] + 32);
            } else if (chs[i] <= 'z' && chs[i] >= 'a') {
                chs[i] = (char) (chs[i] - 32);
            }
        }
        return new String(chs);
    }

    
    public static String toString(List list, String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                sb.append(delimiter);
            }
            sb.append(list.get(i));
        }
        return sb.toString();
    }

    
    public static String toString(Object[] array, String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                sb.append(delimiter);
            }
            sb.append(array[i]);
        }
        return sb.toString();
    }
	public static String getString(double d, int n) {
		// 格式化double类型为2位小数表示
		DecimalFormat df = new DecimalFormat("#.00");
		String money = df.format(Double.valueOf(d).doubleValue());
		String str = money.substring(0, money.length() - 3)
				+ money.substring(money.length() - 2, money.length());
		String str1 = str;
		if (str.length() < n) {
			for (int i = 1; i <= n - str.length(); i++) {
				str1 = "0" + str1;
			}
		}
		return str1;
	}

	
	public static double changeDoubleValue(String str){
		if(str != null && !"".equals(str)){
			try {
				return  Double.parseDouble(str) ;
			} catch (NumberFormatException e) {
				return 0;
			}
		}
		return 0;
	}
	public static String stuff(String a,int len){
		if(a == null){
			a = "";
		}
    	StringBuffer s = new StringBuffer(a.trim());
    	int l = len - s.length();
    	if(l>0){
    		for(int i=0;i<l;i++){
    		    s.append(" ");	
    		}
    	}
    	return s.toString();
	}
	
	public static String subStr(String origStr,int start,int end){
		byte a[] = origStr.getBytes() ;
		byte b[] = new byte[end-start];
		for(int i=0;i<a.length;i++){
			if(i>=start&&i<end){
				b[i-start]=a[i];
			}
		}
		return new String(b);
	}
	
	public static String[] splitToAS400String(String str, int maxLength) {
        List list = new ArrayList();
        
        StringBuilder sb = new StringBuilder(maxLength);
        
        StringBuilder sb2 = new StringBuilder(maxLength);
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            sb2.append(c);
            int length = getAS400StringLength(sb2.toString());
            if (length < maxLength) {
                sb.append(c);
            } else if (length == maxLength) {
                list.add(sb2.toString());
                sb.setLength(0);
                sb2.setLength(0);
            } else if (length > maxLength) {
                list.add(sb.toString());
                sb.setLength(0);
                sb2.setLength(0);
                sb.append(c);
                sb2.append(c);
            }
        }
        if (sb.length() > 0) {
            list.add(sb.toString());
        }

        return (String[]) list.toArray(new String[0]);
    }
	
   public static int getAS400StringLength(String str) {
        byte[] gbkBytes = getGBKBytes(str);

        
        int state = 0;
        
        int cPara = 0;
        for (int i = 0; i < str.length(); i++) {
            if (StringUtils.isGbk(str.charAt(i))) {
                
                if (state == 0) {
                    cPara++;
                    state = 1;
                }
            } else {
                
                if (state != 0) {
                    state = 0; 
                }
            }
        }

        return gbkBytes.length + 2 * cPara;
    }
   
   private static byte[] getGBKBytes(String str) {
        byte[] gbkBytes = null;
        try {
            gbkBytes = str.getBytes("GBK");
        } catch (UnsupportedEncodingException uee) {
            String errorMessage = "内部错误：本计算机的Java环境不支持GBK编码！";

            throw new IllegalStateException(errorMessage, uee);
        }
        return gbkBytes;
    }
   
   public static String standNum(double d)
	 {
		 DecimalFormat objFormat = new DecimalFormat("#.##");
		 String s = String.valueOf(Double.parseDouble(objFormat.format(d)));
		 if(s.substring(s.indexOf(".")+1).length()==1){
		 	s = s + "0";
		 }
		 return s;
	  }
 //before = ture右对齐  before = false左对齐
   public static String fillWithString(String oldStr, int length, String fillStr,boolean before) 
 	{
 		String str = (oldStr == null ? "" : oldStr);
 		while (str.getBytes().length < length) 
 		{
 			if (before) 
 			{
 				str = fillStr + str;
 			} else 
 			{
 				str = str + fillStr;
 			}
 		}
 		return str;
 	}
   
   public static String getRandomString(int length){
	    //定义一个字符串（A-Z，a-z，0-9）即62位；
	    String str="zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
	    //由Random生成随机数
	        Random random=new Random(); 
	        StringBuffer sb=new StringBuffer();
	        //长度为几就循环几次
	        for(int i=0; i<length; ++i){
	          //产生0-61的数字
	          int number=random.nextInt(62);
	          //将产生的数字通过length次承载到sb中
	          sb.append(str.charAt(number));
	        }
	        //将承载的字符转换成字符串
	        return sb.toString();
	  }
   
}
