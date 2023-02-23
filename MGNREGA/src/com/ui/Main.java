package com.ui;

import java.util.Scanner;

import com.dao.GPMDaoImp;
import com.exception.DataNotFoundException;
import com.exception.SomethingWentWrong;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		GPMDaoImp gpm = new GPMDaoImp();
		
		do {
			System.out.println("1. Login as BDO\n2. Login as Gram Panchayat Member\n99. Exit");
			int choice = Integer.parseInt(scanner.nextLine());
			boolean flag = false;
			String userId = null;
			String password = null;
			
			switch(choice) {
			case 99 : flag = true;
					 break;
			
			case 1 : System.out.println("Enter DBO userId: ");
					 userId = scanner.nextLine();
					 System.out.println("Enter DBO password: ");
					 password = scanner.nextLine();
					 
					 login.DBOLogin(userId, password);
					 break;
			
			case 2 : System.out.println("Enter DBO userId: ");
					 userId = scanner.nextLine();
					 System.out.println("Enter DBO password: ");
					 password = scanner.nextLine();
					 
					try {
						gpm.login(userId, password);
					} catch (DataNotFoundException | SomethingWentWrong e) {
						e.printStackTrace();
					}
					
					break;
			
			default : System.out.println("invalid input!!!!\n");
			}
			
			if(flag) {
				break;
			}
		} while (true);
		
		System.out.print("Thank you for visting");
		scanner.close();
	}

}
 