package dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dto.AccountDto;
import file.FileProc;
import single.Singleton;

public class AccountDao_Impl implements AccountDao {
	Scanner sc = new Scanner(System.in);
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	private List<AccountDto> list;
	private FileProc fp;
	private Singleton s;
	
	public AccountDao_Impl() {
		s = Singleton.getInstance();
		fp = new FileProc("Account");
		load();
	}
	private AccountDto search(final String title) {
	    for(AccountDto dto : s.list) {
	        if(dto.getTitle().equals(title)) {
	            return dto;
	        }
	    }
	    return null;
	}
	// CRUD	
	@Override
	public void insert() {
		//title - 제목
		System.out.print("제목 ▶ ");
		String title = sc.next();
		
		//type - 수입/지출
		System.out.print("수입/지출 (income / expense) ▶ ");
	    String type = sc.next();
	    if(type.equals("수입")) type = "income";
	    else if(type.equals("지출")) type = "expense";
	    
		//amount - 금액
	    System.out.print("금액 ▶ ");
	    int amount = sc.nextInt(); 
	    
		//date - 날짜(yyyy-MM-dd)
	    System.out.print("날짜 (yyyy-MM-dd) ▶ ");
	    String date = sc.next();
	    
		//memo	
		System.out.print("메모 ▶ ");
		String memo = "";
		try {
			memo = br.readLine();		
			
		} catch (IOException e) {			
			e.printStackTrace();
		}	
		AccountDto dto = new AccountDto(title, type, amount, date, memo);
		s.list.add(dto);
		System.out.println("가계부에 추가되었습니다");	
	}

	@Override
	public void all() {
		for(AccountDto dto : s.list) {
			System.out.println(dto.toString());
		}
	}

	@Override
	public void findByDate() {
		System.out.print("날짜 검색 ▶ ");
		String date = sc.next();
		
		List<AccountDto> findList =  new ArrayList<AccountDto>();
		for(AccountDto dto : s.list) {
			if(dto.getDate().contains(date)) {
				findList.add(dto);
			}
		}
		if(findList.isEmpty()) {
			System.out.println("검색 날짜가 존재하지 않습니다");
			return;
		}		
		System.out.println("검색된 날짜는 다음과 같습니다");
		for(AccountDto dto : findList) {
			System.out.println(dto.toString());
		}
	}

	@Override
	public void findByTitle() {
		System.out.print("제목의 단어를 검색 ▶ ");
		String title = sc.next();
		
		List<AccountDto> findList =  new ArrayList<AccountDto>();
		for(AccountDto dto : s.list) {
			if(dto.getTitle().contains(title)) {
				findList.add(dto);
			}
		}
		if(findList.isEmpty()) {
			System.out.println("제목이 존재하지 않습니다");
			return;
		}		
		System.out.println("검색된 제목은 다음과 같습니다");
		for(AccountDto dto : findList) {
			System.out.println(dto.toString());
		}
	}
	

	@Override
	public void getMonthlyReport() {
		System.out.println("조회할 연월 입력 (yyyy-MM) ▶ ");
		 int totalIncome = 0;
		 int totalExpense = 0;
		    
		String month = sc.next();
		
		 for (AccountDto dto : s.list) {
			 if (dto.getDate().startsWith(month)) {
				 if (dto.getType().equalsIgnoreCase("income")) {
		                totalIncome += dto.getAmount();
		            } else if (dto.getType().equalsIgnoreCase("expense")) {
		                totalExpense += dto.getAmount();
		            }	 
			 }
		 }
		 System.out.println("\n▶ " + month + " 월별 정산 ◀");
		    System.out.println("총 수입 : " + totalIncome + "원");
		    System.out.println("총 지출 : " + totalExpense + "원");
	}

	@Override
	public void getPeriodReport() {
		System.out.print("조회 시작일 입력 (yyyy-MM-dd) ▶ ");
	    String startDate = sc.next();
	    System.out.print("조회 종료일 입력 (yyyy-MM-dd) ▶ ");
	    String endDate = sc.next();

	    int totalIncome = 0;
	    int totalExpense = 0;

	    for (AccountDto dto : s.list) {
	        String date = dto.getDate();
	        if (date.compareTo(startDate) >= 0 && date.compareTo(endDate) <= 0) {
	            if (dto.getType().equalsIgnoreCase("income")) {
	                totalIncome += dto.getAmount();
	            } else if (dto.getType().equalsIgnoreCase("expense")) {
	                totalExpense += dto.getAmount();
	            }
	        }
	    }

	    System.out.println("\n▶ " + startDate + " ~ " + endDate + " 기간별 정산 ◀");
	    System.out.println("총 수입 : " + totalIncome + "원");
	    System.out.println("총 지출 : " + totalExpense + "원");	
	}

	@Override
	public void delete() {
		//입력
		System.out.print("삭제할 제목 입력 ▶ ");
		String title = sc.next();	
		//검색
		AccountDto dto = search(title);
		if(dto == null) {
			System.out.println("제목을 찾을 수 없습니다.");
			return;
		}
		//삭제
		s.list.remove(dto);
		System.out.println(dto + " 기록이 삭제되었습니다");
	}

	@Override
	public void update() { 
		System.out.print("수정할 제목 입력 ▶ ");
		String title = sc.next();

		if(title == null || title.isEmpty()) {
			System.out.println("수정할 제목을 입력하지 않았습니다.");
			return;
		} 
		AccountDto target = null;
		for(AccountDto dto : s.list) {
			if(dto.getTitle().equals(title)) {
				target = dto;
				break;
			}
		}
		if (target == null) {
			System.out.println(title + " 의 정보가 존재하지 않습니다.");
			return;
		}
		System.out.println("수정할 제목 현재 정보 : ");
		System.out.println(target.toString());

		System.out.println("\n수정할 항목을 선택하세요");
		System.out.println("1. 제목");
		System.out.println("2. 수입/지출(income / expense)");
		System.out.println("3. 금액");
		System.out.println("4. 날짜(yyyy-MM-dd)");
		System.out.println("5. 메모");
		System.out.println("6. 전체 수정");
		System.out.println("선택: ");

		String choice = sc.next();
		sc.nextLine();
		
		try {
			switch (choice) {
			case "1":
				System.out.println("변경할 제목 입력 ▶ ");
				target.setTitle(sc.nextLine());
				break;
			case "2":
				System.out.println("변경할 수입/지출(income / expense) 입력 ▶ ");
				String inputType = sc.nextLine();
				if(inputType.equals("수입")) inputType = "income";
			    else if(inputType.equals("지출")) inputType = "expense";
			    target.setType(inputType);
				break;
			case "3":
				System.out.println("변경할 금액 입력 ▶ ");
				target.setAmount(Integer.parseInt(sc.nextLine()));
				break;
			case "4":
				System.out.println("변경할 날짜(yyyy-MM-dd) 입력 ▶ ");
				target.setDate(sc.nextLine());
				break;
			case "5":
				System.out.println("변경할 메모 입력 ▶ ");
				sc.nextLine();
				String memo = br.readLine();
				target.setMemo(memo);
				break;
			case "6":
				System.out.println("변경할 제목 입력 ▶ ");
				target.setTitle(sc.nextLine());

				System.out.println("변경할 수입/지출(income / expense) 입력 ▶ ");
				String inputType1 = sc.nextLine();
				if(inputType1.equals("수입")) inputType1 = "income";
			    else if(inputType1.equals("지출")) inputType1 = "expense";
			    target.setType(inputType1);

				System.out.println("변경할 금액 입력 ▶ ");
				target.setAmount(Integer.parseInt(sc.nextLine()));

				System.out.println("변경할 날짜(yyyy-MM-dd) 입력 ▶ ");
				target.setDate(sc.nextLine());

				
				System.out.println("변경할 메모 입력 ▶ ");
				sc.nextLine();
				String memo1 = br.readLine();
				target.setMemo(memo1);
				break;
			default:
				System.out.println("잘못된 선택입니다.");
				return;
			}
		} catch (Exception e) {
			System.out.println("※입력 오류※ 다시 시도해주세요.");
			return;
		}
		System.out.println("\n★수정 완료★ 변경된 정보▶");
		System.out.println(target.toString());
	}
	@Override
	public void save() {
		fp.fileSave(s.list);
	}

	@Override
	public void load() {
		s.list = fp.fileLoad();
	}

}
