package com.zhuolang.java.file;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {

		byte[] execByte = new byte[100];
		String exec;
		
		for (;;) {
			System.in.read(execByte); //读取一行字符串
			exec = new String(execByte).trim(); //需要去掉回车

			switch (exec) {
			case "exit":
				System.out.println("sb bb");
				return;

			default:
				System.out.println("你说什么啊，sb");
				break;
			}
		}


	}

}
