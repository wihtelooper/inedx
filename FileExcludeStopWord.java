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
	
		// TODO 1�������Ӷ�ȡ�����У�
		// ���ļ����ļ��Ƿ����
		List<String> stringList = new ArrayList<String>();
		List<String> stopWordsList = new ArrayList<String>();
		// ��ȡ�ļ�
		File file = new File(filenameTemp1);
		File stopwords=new File("D:\\data\\stop.txt");
		if (!file.exists()&&!stopwords.exists()){
			System.out.println("�ļ�������");
			return;
		}
		// ѭ����ȡһ�䣬һ��
		BufferedReader reader = null;
		try {
			//System.out.println("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;

		    
			// һ�ζ���һ�У�ֱ������nullΪ�ļ�����
			while ((tempString = reader.readLine()) != null) {
				// ��ʾ�к�
				tempString=tempString.toLowerCase();
				//System.out.println("line " + line + ": " + tempString);
				// ����List<String>
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
		// ѭ��List ,��ÿһ��Ԫ���滻
		List<String> firstStringList = new ArrayList<String>();
		for (String contance : stringList) {// ʹ����һ�����ɵ�����
			// �滻
			
				contance = contance.replaceAll("\\d+",""); // ѭ����Ӣ�ı������滻�ɿգ���ȥ��Ӣ�ı�����

			firstStringList.add(contance);
		}
		stringList = firstStringList;
		
		
		
		// TODO 2��ȥ���ȥ�ո�
		// 2.1.ȥ��Ӣ�ı����ź���ַ���
		String[] secondSubStr = new String[] { "[", "]", ".", ",", ":", "\\",
				"/", "?", "!", ";", "\"", "'" };
		// ѭ��List ,��ÿһ��Ԫ���滻
		List<String> secondStringList = new ArrayList<String>();
		for (String contance : stringList) {
			// �滻
			for (String str : secondSubStr) {
				contance = contance.replace(str, ""); // ѭ����Ӣ�ı������滻�ɿգ���ȥ��Ӣ�ı�����
			}
			secondStringList.add( contance + " ");
		}
		stringList = secondStringList;
		
		
		// TODO 3��ȥͣ�ôʣ�ȥ��and����to����he��....��
		// ͣ�ô� ת���� ɨ��������
		// ������ǰд�õĴ������
 
		BufferedReader stops = null;
		try {
			stops = new BufferedReader(new FileReader(stopwords));
			String tempString;
			while ((tempString = stops.readLine()) != null) {
				
				stopWordsList.add(tempString);
			}
			List<String> tempStringList = new ArrayList<String>();
			for (String string : stringList) {//ȡ��ÿһ�о��ӣ���ÿһ��ֱ���ÿ��ͣ�ô���replace
				for (String stopWord : stopWordsList) {//ѭ��ͣ�ô�
					string = string.replace(stopWord+" ", "");
				}
				tempStringList.add(string);
			}
			stringList = tempStringList;//�¼��ϸ�ֵ��ȥ
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (String contance : stringList) {
			
			 try{
		            File f=new File(path1 + use+"��" + ".txt");
		            FileWriter fw=new FileWriter(f,true);
			            fw.write(contance);
			            fw.write("\r\n");
		            fw.close();
		        }catch(Exception e){
		        }
			
			 
		}
	}
	
}
