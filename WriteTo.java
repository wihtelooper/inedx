package SWMARK;
import java.io.*;
import javax.swing.*;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WriteTo extends JFrame implements ActionListener{

 /**
	 * 
	 */
private static final long serialVersionUID = 1L;

public static boolean ContainsStr(String s1, String s2)
{ if(s1.indexOf(s2)>=0)

{ 
	 if(s1.length()==s2.length())
	 {
	 return true;}
	 else {return false;
		 
	 }
   } 

else{ return false;}
}
JButton b,j,y,p;
	 int t = 0;
    JTextField n;
    JTextField x;
    JTextField v1,v2,sign;
    static JTextArea  s;
   
	private static String path = "D:\\data\\";
    public WriteTo(){
        JFrame frame = new JFrame("SWMAKER");
        frame .setSize(550,300);
        frame .setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();    
        // 添加面板
        frame.add(panel);
        panel.setLayout(null);
        //文件名
        JLabel IDLabel = new JLabel("ID:");
        IDLabel.setBounds(50,20,80,25);
        panel.add(IDLabel);
        JTextField IDText = new JTextField(20);
        IDText.setBounds(100,20,165,25);
        panel.add(IDText);
        //文献名
        JLabel nameLabel = new JLabel("book:");
        nameLabel.setBounds(50,50,80,25);
        panel.add(nameLabel);
        JTextField nameText = new JTextField(20);
        nameText.setBounds(100,50,165,50);
        panel.add(nameText);
        //检索词
        JLabel indexLabel = new JLabel("index:");
        indexLabel.setBounds(280,20,80,25);
        panel.add(indexLabel);
        JTextField indexsign = new JTextField(20);
        indexsign.setBounds(400,20,25,25);
        panel.add(indexsign);
        JTextField indexText1 = new JTextField(20);
        indexText1.setBounds(320,20,70,25);
        panel.add(indexText1);
        JTextField indexText2 = new JTextField(20);
        indexText2.setBounds(430,20,70,25);
        panel.add(indexText2);
        //检索结果
        JLabel resultLabel = new JLabel("result:");
        resultLabel.setBounds(280,50,80,25);
        panel.add(resultLabel);
        JTextArea  resultText = new JTextArea ();
        JScrollPane scrollPane_1 = new JScrollPane(resultText);
        scrollPane_1.setBounds(320,50,180,50);
        scrollPane_1.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane_1.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane_1);
        s=resultText;
        n=nameText;
        x=IDText;
        v1=indexText1;
        v2=indexText2;
        sign=indexsign;
        b=new JButton("写入");
        b.setBounds(100, 150, 80, 25);
        j=new JButton("生成");
        j.setBounds(300, 150, 80, 25);
        y=new JButton("抽词");
        y.setBounds(200, 150, 80, 25);
        p=new JButton("检索");
        p.setBounds(400, 150, 80, 25);
        p.addActionListener(this);
        b.addActionListener(this);
        j.addActionListener(this);
        y.addActionListener(this);
        panel.add(nameLabel);
        panel.add(v1); 
        panel.add(v2); 
        panel.add(sign);
        panel.add(n); 
        panel.add(x);
        panel.add(p); 
        panel.add(b);
        panel.add(j);
        panel.add(y);
        this.pack();
        frame.setVisible(true);
    }
    
 public  void actionPerformed(ActionEvent e)  {
	 
     if(e.getSource()==b){
    	 t++;
         if(n.getText().equals("")){
             JOptionPane.showMessageDialog(null,"请输入内容~","错误",JOptionPane.ERROR_MESSAGE);
             n.grabFocus();
         }else{ 
        	 write(t,n.getText(),x.getText());
        	 n.setText("");
             JOptionPane.showMessageDialog(null,"写入成功","提示",JOptionPane.INFORMATION_MESSAGE);
         }
         
     }
     else if(e.getSource()==j) {
    	 if(x.getText().equals("")){
             JOptionPane.showMessageDialog(null,"请输入ID","错误",JOptionPane.ERROR_MESSAGE);
             n.grabFocus();}
    	 else {
    	 try {
			Main.Go(x.getText());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	 JOptionPane.showMessageDialog(null,"生成成功","提示",JOptionPane.INFORMATION_MESSAGE);
     }}
 else if(e.getSource()==y) {
	 if(x.getText().equals("")){
         JOptionPane.showMessageDialog(null,"请输入ID","错误",JOptionPane.ERROR_MESSAGE);
         n.grabFocus();}
	 else {
    	 FileExcludeStopWord.ex(x.getText()); 
    	 JOptionPane.showMessageDialog(null,"抽词成功","提示",JOptionPane.INFORMATION_MESSAGE);
     }
	 }
 else if(e.getSource()==p) {
	 if(x.getText().equals("")||v1.getText().equals("")){
         JOptionPane.showMessageDialog(null,"请输入ID或检索词","错误",JOptionPane.ERROR_MESSAGE);
         }
	 /*else if(sign.getText().equals("")){
		 JOptionPane.showMessageDialog(null,"请输入检索符号","错误",JOptionPane.ERROR_MESSAGE);
	 }*/
	 else {
    	 try {
    		 s.setText("");
			Index(v1.getText(),v2.getText(),x.getText(),sign.getText());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
 
     }
	 }
 }
 
 
    public void write(int name,String line,String x){
        try{
        	
            File f=new File(path + x + ".txt");
            FileWriter fw=new FileWriter(f,true);
            if(t==1) {
            fw.write(name+","+line);}
            else {fw.write("\r\n"+name+","+line);}
            fw.close();
       
        }catch(Exception e){
        }
    }
    public void index(){
    	
    }
    public static void main(String[] args) {
        new WriteTo();
        

    }
    static List<String> resultwords=new ArrayList<String>();
    public static String listToString(List<String> list){
    	 
    	   if(list==null){
    	      return null;
    	   }
    	 
    	   StringBuilder result = new StringBuilder();
    	   for(String string :list) {
    	    
    	      result.append(string);
    	   }
    	   return result.toString();
    }
    /*public static List<Integer> solution(String sign,List<Integer> number1,List<Integer> number2){
    	
   if(sign=="*") {
      	  number1.retainAll(number2); //交集
      	  }
      	 else if(sign=="+") {
    
      		 number2.removeAll(number1);
      		 number1.addAll(number2);//并集
      	 }
      	 else if(sign=="-") {
      	  number1.removeAll(number2);//差集
      	 }
return number1;
   	 
   	}*/
    //检索函数
    public static void Index(String strWord1,String strWord2,String i,String sign) throws IOException { 
    	File f=new File("D:\\data\\"+i+".txt");
        BufferedReader br=new BufferedReader(new FileReader(f));
        String strRead=null;
    try {   
    	
         List<String> resultwords=new ArrayList<String>();
         List<Integer> number1=new ArrayList<Integer>();
         List<Integer> number2=new ArrayList<Integer>();
    	int line=1;
    	Map<Integer,String> page=new HashMap<>();
    	while((strRead=br.readLine())!=null) { 
    	
    
    	page.put(line,strRead);
    	line++;
    	
	     } 
  
    	
       
    
        //System.out.println(Main.Go(i));
    	 if(strWord2 == "") {
       for(String strTemp:Main.Go(i).keySet()) { 
     
    	  
    	   boolean b=ContainsStr(strTemp,strWord1);
           if(b) { 
        	   
        	   for(Integer strTemp1:Main.Go(i).get(strTemp)) {
        		   
        		   resultwords.add(page.get(strTemp1)+"\r\n");

           } 
        	   
       }
  
    }
       }
      else {
    	  for(String strTemp:Main.Go(i).keySet()) { 
    	  boolean a=ContainsStr(strTemp,strWord1);
    	  boolean b=ContainsStr(strTemp,strWord2);
          if(a) { 
       	   
       	   for(Integer strTemp1:Main.Go(i).get(strTemp)) {
       		
       		 number1.add(strTemp1);
       	   }
          }
      	if(b) {
       for(Integer strTemp2:Main.Go(i).get(strTemp)) {
    	   
    	   number2.add(strTemp2);
       		 }
       		} 	     
      	  
      }
    	  if(sign.equals("*") ){
          	  number1.retainAll(number2); //交集
          	  }
          	 else if(sign.equals("+") ) {
        
          		 number2.removeAll(number1);
          		 number1.addAll(number2);//并集
          	 }
          	 else if(sign.equals("-") ) {//差集

          		number2.retainAll(number1);
          		number1.removeAll(number2);
         
          	 }
    	//solution(sign,number1,number2);
    	  Collections.sort(number1);
      for(Integer number: number1) {
    	  resultwords.add(page.get(number)+"\r\n");
    	 
      }
      String index=listToString(resultwords);
      s.setText(index);
    }

       br.close(); }  
    catch (IOException e) {
    	
    	e.printStackTrace();
    }
    
}
    }