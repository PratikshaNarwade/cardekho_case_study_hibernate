package com.jspiders.cardekho_case_study_hibernate.dto;

import java.util.Scanner;

import com.jspiders.cardekho_case_study_hibernate.dao.Operations;

public class Menu {
	
	public static void main(String[] args) {
		System.out.println("\t=====WELCOME TO CAR DEKHO APPLICATION=====");
		MenuOptions();
	}
	public static void MenuOptions() {
		Scanner sc = new Scanner(System.in);
		System.out.println("  ----- MENU-----");
		int login = 1;
		Operations op = new Operations();
		do {
			System.out.println("\n1.Add Car Details\n2.Search Car Details\n3.Update Car Details\n4.Remove Car Details\n5.Exit");
			System.out.println("Enter Your Choice : ");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				op.addCarDetails();
				break;
			case 2:
				op.searchCarDetails();
				break;
			case 3:
				op.updateCarDetails();
				break;
			case 4:
				op.removeCarDetails();
				break;
			case 5:
				login = 0;
				sc.close();
				System.out.println("Thank You!!Visit Again");
				break;
			default:
				System.out.println("Invalid Input!!Try Again");
				break;
			}
			
		} while (login == 1);
	}


}
