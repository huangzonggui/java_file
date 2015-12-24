package com.zhuolang.java.file;

import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	

	private boolean debug = true;
	
	private String exec; //ת��ΪString
	private List<String> param; //����
	
	private String name = "zhuolang";
	private String path; //��ǰĿ¼
	
	private BufferedReader reader;
	
	public Main(){
		path = new File("").getAbsolutePath(); //��ȡ��ǰĿ¼��·��
		param = new ArrayList<String>();
		
		reader = new BufferedReader(new InputStreamReader(System.in)); //��װin
	}
	
	public void start() throws IOException{
			for (;;) 
			{
			param.clear();
			System.out.print("["+name +"@"+path+" ~]$ ");
			String in = reader.readLine();
			String [] ins = in.split(" +"); //�������֣�1�����ϵĿո�
			
			exec = ins[0].trim(); //��Ҫȥ���س�
			int inLen = ins.length; 
			for(int i = 1 ; inLen > i ; i++){
				param.add(ins[i]);
			}
			
			
			if(exec.isEmpty()){
				continue; //�����س��Ͳ�Ҫ����
			}
			
			switch (exec) {
			case "exit":
				exit();
				break;
				
			case "cd":
				cd();
				break;

			case "ls":
				ls();
				break;
		
			case "pwd":
				pwd();
				break;
			case "copy":
				copy();
				break;
			default:
				System.out.println("��˵ʲô����sb");
				break;
			}
			
			System.out.println();
		}
	}
	
	private void cd(){
		if(param.isEmpty()){
			System.out.println("�в��ǲ��ǣ��᲻����ѽ��cd ������Ҫ�������㲻֪����");
		}
		switch (param.get(0)) {
		case ".":
			
			break;
		case "..":
			File f = new File(path);//��·����װ��һ���ļ��������
			path = f.getParent();
			break;
		default:
			File root = new File( path );   
			File[] files = root.listFiles();      
			String s = param.get(0).toString();//������Ĳ�����ȡ����
			for ( File file : files )     
			{          
				if ( file.isDirectory() )        
				{     
					if(s.equals(file.getName())){//�ж������ַ����Ƿ������equals������������������ļ��е�������һ���жϣ�
						path = file.getAbsolutePath();//��ȡ����ļ��еľ���·��
					}else{
						System.out.println("û��"+s+"����ļ���");
					}
				}
			}   
			break;
		}
	}
	
	private void exit(){
		System.out.println("sb �ݰ�");
		System.exit(0);
	}
	private void ls() throws IOException{
			File f = new File(path);
			String[] names = f.list();
			for(String name : names)//for �ĸ߼�ѭ��
			{
				System.out.println(name);
			}
	}
/*�г���ǰ·��*/
	public void pwd() 
	{
		System.out.println(path);
	}

	public void copy()
	{		
		BufferedReader bufr = null;
		BufferedWriter bufw = null;
		String str = null;
		BufferedReader br = null;
		try
		{

			if(param.isEmpty()){
			System.out.println("�в��ǲ��ǣ��᲻����ѽ��copy ������Ҫ�������㲻֪����");
			}
			switch (param.get(0))
			{
			
			case ".":
				System.out.println("������Ҫ�����ļ�����ȷ��·��");
						
			default :

//				bufr = new BufferedReader(new FileReader("copy.txt"));
//					bufw = new BufferedWriter(new FileWriter("���Ƴ������ļ�.txt"));
//
//					String line = null;
//
//					while((line=bufr.readLine())!=null)
//					{
//						bufw.write(line);
//						bufw.newLine();
//						bufw.flush();
//
//					}
//				break;
//				System.out.println("��Ҫ���Ƶ��ļ������ڣ�������ٸ��ƣ�");
				break;
			}

			
		}
		catch (IOException e)
		{
			throw new RuntimeException("��дʧ��");
		}
		finally
		{
			try
			{
				if(bufr!=null)
					bufr.close();
			}
			catch (IOException e)
			{
				throw new RuntimeException("��ȡ�ر�ʧ��");
			}
			try
			{
				if(bufw!=null)
					bufw.close();
			}
			catch (IOException e)
			{
				throw new RuntimeException("д��ر�ʧ��");
			}
		}
	}
	public static void main(String[] args) throws IOException {
		new Main().start();
	}

}
