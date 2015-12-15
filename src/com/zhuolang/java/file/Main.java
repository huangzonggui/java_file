//package com.zhuolang.java.file;

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
	
	private String exec; //转化为String
	private List<String> param; //参数
	
	private String name = "zhuolang";
	private String path; //当前目录
	
	private BufferedReader reader;
	
	public Main(){
		path = new File("").getAbsolutePath(); //获取当前目录的路径
		param = new ArrayList<String>();
		
		reader = new BufferedReader(new InputStreamReader(System.in)); //封装in
	}
	
	public void start() throws IOException{
			for (;;) 
			{
			param.clear();
			System.out.print("["+name +"@"+path+" ~]$ ");
			String in = reader.readLine();
			String [] ins = in.split(" +"); //正则表达拆分，1个以上的空格
			
			exec = ins[0].trim(); //需要去掉回车
			int inLen = ins.length; 
			for(int i = 1 ; inLen > i ; i++){
				param.add(ins[i]);
			}
			
			
			if(exec.isEmpty()){
				continue; //单单回车就不要理了
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
				System.out.println("你说什么啊，sb");
				break;
			}
			
			System.out.println();
		}
	}
	
	private void cd(){
		if(param.isEmpty()){
			System.out.println("有病是不是，会不会用呀，cd 后面需要跟参数你不知道吗？");
		}
		switch (param.get(0)) {
		case ".":
			
			break;
		case "..":
			File f = new File(path);//将路径封装到一个文件里？？？？
			path = f.getParent();
			break;
		default:
			break;
		}
	}
	
	private void exit(){
		System.out.println("sb 拜拜");
		System.exit(0);
	}
	private void ls() throws IOException{
			File f = new File(path);
			String[] names = f.list();
			for(String name : names)//for 的高级循环
			{
				System.out.println(name);
			}
	}
/*列出当前路径*/
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
			System.out.println("有病是不是，会不会用呀，copy 后面需要跟参数你不知道吗？");
			}
			switch (param.get(0))
			{
			
			case "copy.txt":
					bufr = new BufferedReader(new FileReader("copy.txt"));
					bufw = new BufferedWriter(new FileWriter("复制出来的文件.txt"));

					String line = null;

					while((line=bufr.readLine())!=null)
					{
						bufw.write(line);
						bufw.newLine();
						bufw.flush();

					}
				break;	
			default :
				System.out.println("你要复制的文件不存在！请查正再复制！");
				break;
			}

			
		}
		catch (IOException e)
		{
			throw new RuntimeException("读写失败");
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
				throw new RuntimeException("读取关闭失败");
			}
			try
			{
				if(bufw!=null)
					bufw.close();
			}
			catch (IOException e)
			{
				throw new RuntimeException("写入关闭失败");
			}
		}
	}
	public static void main(String[] args) throws IOException {
		new Main().start();
	}

}
