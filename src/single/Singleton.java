package single;

import java.util.ArrayList;
import java.util.List;

import dto.AccountDto;

public class Singleton {
	private static Singleton sc = null;
	public List<AccountDto> list = null;
	
	private Singleton() {
		list = new ArrayList<AccountDto>();
	}
	public static Singleton getInstance() {
		if(sc == null) {
			sc = new Singleton();
		}
		return sc;
	}
}