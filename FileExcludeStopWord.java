package SWMARK;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

 
public class FileExcludeStopWord{
	static int line = 1;
	private static String path1 = "D:/data/";
	private static String filenameTemp1;
	public static void ex(String use ) {
		filenameTemp1 = path1 + use + ".txt";
	
		// TODO 1：按句子读取（按行）
		// 读文件，文件是否存在
		List<String> stringList = new ArrayList<String>();
		List<String> stopWordsList = new ArrayList<String>();
		// 读取文件
		File file = new File(filenameTemp1);
		File stopwords=new File("D:\\data\\stop.txt");
		if (!file.exists()&&!stopwords.exists()){
			System.out.println("文件不存在");
			return;
		}
		// 循环，取一句，一行
		BufferedReader reader = null;
		try {
			//System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;

		    
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				tempString=tempString.toLowerCase();
				//System.out.println("line " + line + ": " + tempString);
				// 存入List<String>
				stringList.add(tempString);
				
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		// 循环List ,对每一个元素替换
		List<String> firstStringList = new ArrayList<String>();
		for (String contance : stringList) {// 使用上一步生成的数据
			// 替换
			
				contance = contance.replaceAll("\\d+",""); // 循环把英文标点符号替换成空，即去掉英文标点符号

			firstStringList.add(contance);
		}
		stringList = firstStringList;
		
		
		
		// TODO 2：去标点去空格
		// 2.1.去掉英文标点符号后的字符串
		String[] secondSubStr = new String[] { "[", "]", ".", ",", ":", "\\",
				"/", "?", "!", ";", "\"", "'" };
		// 循环List ,对每一个元素替换
		List<String> secondStringList = new ArrayList<String>();
		for (String contance : stringList) {
			// 替换
			for (String str : secondSubStr) {
				contance = contance.replace(str, ""); // 循环把英文标点符号替换成空，即去掉英文标点符号
			}
			secondStringList.add( contance + " ");
		}
		stringList = secondStringList;
		
		
		// TODO 3：去停用词（去“and”“to””he“....）
		// 停用词 转化成 扫描用数组
		// 调用以前写好的代码过滤
 
		BufferedReader stops = null;
		try {
			stops = new BufferedReader(new FileReader(stopwords));
			String tempString;
			while ((tempString = stops.readLine()) != null) {
				
				stopWordsList.add(tempString);
			}
			List<String> tempStringList = new ArrayList<String>();
			for (String string : stringList) {//取出每一行句子，对每一句分别与每个停用词做replace
				for (String stopWord : stopWordsList) {//循环停用词
					string = string.replace(stopWord+" ", "");
				}
				tempStringList.add(string);
			}
			stringList = tempStringList;//新集合赋值回去
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (String contance : stringList) {
			
			 try{
		            File f=new File(path1 + use+"改" + ".txt");
		            FileWriter fw=new FileWriter(f,true);
			            fw.write(contance);
			            fw.write("\r\n");
		            fw.close();
		        }catch(Exception e){
		        }
			
			 
		}
	}
	
}
