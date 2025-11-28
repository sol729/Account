package dto;

public class AccountDto {
	//제목, 타입, 금액, 날짜, 메모
	public String title;
	public String type;
	public int amount;
	public String date;
	public String memo;
	
	//기본 생성자
	public AccountDto() {		
	}
	 
	//전체 생성자 
	//Alt + Shift + S, O = Generate Constructor using Fields…
	public AccountDto(String title, String type, int amount, String date, String memo) {
		super();
		this.title = title;
		this.type = type;
		this.amount = amount;
		this.date = date;
		this.memo = memo;
	}	
	//Getter & Setter
    // Alt + Shift + S, R = Generate Getters and Setters…
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}	
	//toString
    //Alt + Shift + S, S = Generate toString()…
	
	@Override
	public String toString() {
		String typeKR = "";
	    if(type.equalsIgnoreCase("income")) typeKR = "수입";
	    else if(type.equalsIgnoreCase("expense")) typeKR = "지출";
	    else typeKR = type; // 혹시 다른 값 들어오면 그대로 출력

	    return "기록 [제목 =" + title + ", 분류 =" + typeKR + ", 금액 =" + amount + ", 날짜 =" + date + ", 메모 ="
	            + memo + "]";
	}	
}
