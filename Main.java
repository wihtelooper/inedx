package SWMARK;
import java.io.BufferedReader;

import java.io.FileReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Main { 
	public static boolean ContainsStr(String s1, String s2) { if(s1.indexOf(s2)>=0) { return true;
    } else{ return false;
    } } 
	   


public static  Map<String, LinkedList<Integer>> Go(String i) throws IOException { 
	Map<String, LinkedList<Integer>> adds=new HashMap<>();
         File f=new File("D:\\data\\"+i+"改"+".txt");
        BufferedReader br=new BufferedReader(new FileReader(f));
        String strRead=null;
        List<String> lstLines=new ArrayList<String>();
        List<String> lstwords=new ArrayList<String>();
       String[] words=null;
    try {    
    	while((strRead=br.readLine())!=null) { 
        	
        	words=strRead.split(" ");
	     for(String string1:words)
             
	        {
	    	 if (!lstwords.contains(string1)) {
			
			lstwords.add(string1);}
	         }
        	lstLines.add(strRead);
                } 

       
        File g=new File("D:\\data\\"+"out"+i+".txt");
        g.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(g);
        PrintStream printStream = new PrintStream(fileOutputStream);
        System.setOut(printStream);
        for (String string :lstwords) {
        	  LinkedList<Integer> list = new LinkedList<Integer>();
    		 int nLineNum =1;
    		String strWord=string ;
       for(String strTemp:lstLines) { 
        	boolean b=ContainsStr(strTemp,strWord);
      
        	if(b) { 
        
           list.add(nLineNum);
           
           }
           nLineNum++;
          }
       adds.put(string,list);
        } 
       br.close();
}
    catch (IOException e) {
	
	e.printStackTrace();
}
    Map<String, LinkedList<Integer>> resultMap = sortMapByKey(adds); 
    System.out.println("单词--------------出现次数--------------单词地址");
   for(String key :  resultMap.keySet()) {
	System.out.println(key+"--------------"+(adds.get(key)).size()+"--------------"+adds.get(key));  
	   
	   
   }
   return resultMap;
}


  

public static Map<String, LinkedList<Integer>> sortMapByKey(Map<String, LinkedList<Integer>> map) {
    if (map == null || map.isEmpty()) {
        return null;
    }

    Map<String, LinkedList<Integer>> sortMap = new TreeMap<String, LinkedList<Integer>>(
            new MapKeyComparator());

    sortMap.putAll(map);

    return sortMap;
}

}


class MapKeyComparator implements Comparator<String>{
public int compare(String str1, String str2) {
    
    return str1.compareTo(str2);
}

}
    
    