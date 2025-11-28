package main;

import java.util.Scanner;

import dao.AccountDao_Impl;
import dao.AccountDao;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//menu
		AccountDao dao = new AccountDao_Impl();
		boolean out = false;
		
		while(true) {
			System.out.println("▶ 가계부 목차 ◀");
			System.out.println("1. 내용 추가");
			System.out.println("2. 전체 내용 보기");
			System.out.println("3. 날짜 검색");
			System.out.println("4. 제목 검색");
			System.out.println("5. 월별 정산");
			System.out.println("6. 기간별 정산");
			System.out.println("7. 삭제");
			System.out.println("8. 수정");
			System.out.println("9. 저장");
			System.out.println("10. 종료");
			
			System.out.print("메뉴 번호 선택 ▶ ");
			int number = sc.nextInt();
			sc.nextLine();
			
			switch(number) {
			case 1:
				dao.insert();
				break;
			case 2:
				dao.all();
				break;
			case 3:
				dao.findByDate();
				break;
			case 4:
				dao.findByTitle();
				break;	
			case 5:
				dao.getMonthlyReport();
				break;
			case 6:
				dao.getPeriodReport();
				break;
			case 7:
				dao.delete();
				break;
			case 8:
				dao.update();
				break;
			case 9:
				dao.save();
				break;
			case 10:
				// System.exit(0);		
				out = true;
				break;
			}
			if(out) {
				System.out.println("▶ 프로그램 종료 ◀");
				break;
	  }
	}
  }
}
