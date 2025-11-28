package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import dto.AccountDto;
import single.Singleton;

public class FileProc {
	private File file;
	
	public FileProc(String filename) {
		file = new File("c:/tmp/" + filename + ".txt");
		createNewFile();
	}

	public void createNewFile() {
		try {
			if(file.createNewFile()) {
				System.out.println("파일이 성공적으로 생성되었습니다");
			}else {
				System.out.println("같은 이름의 파일이 존재합니다");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	// data save/load
	public void fileSave(List<AccountDto> list) {
		
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
		
			Singleton s = Singleton.getInstance();
			for (AccountDto dto : list) {
			    pw.println(dto.getTitle() + " / " + dto.getType() 
			    + " / " + dto.getAmount() + " / " + dto.getDate() 
			    + " / " + dto.getMemo());
			}
			pw.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}		
		System.out.println("저장 완료");
	}
	
	public List<AccountDto> fileLoad() {
	List<AccountDto> list = new ArrayList<>();
	try (BufferedReader br = new BufferedReader(new FileReader(file))) {
	    String str;
	    while ((str = br.readLine()) != null && !str.isEmpty()) {
	        String[] data = str.split(" / ");
	        if (data.length < 5) continue; // 데이터가 이상하면 건너뜀
	        AccountDto dto = new AccountDto(data[0], data[1], 
	                Integer.parseInt(data[2]), data[3], data[4]);
	        list.add(dto);
	    }
	} catch (IOException | NumberFormatException e) {
	    e.printStackTrace();
	}
	return list;
	}	
}
