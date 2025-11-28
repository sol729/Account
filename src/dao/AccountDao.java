package dao;

public interface AccountDao {
	//1. 내용 추가" 
	void insert();
	//2. 전체 내용 보기
	void all();
	//3. 날짜 검색
	void findByDate();
	//4. 제목 검색
	void findByTitle();
	//5. 월별 정산
	void getMonthlyReport();
	//6. 기간별 정산
	void getPeriodReport();
	// 7. 삭제
	void delete();
	// 8. 수정
	void update();
	// 9. 저장
	void save();
	void load();
}
