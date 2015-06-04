package piandv.service;

import java.util.List;

import piandv.model.Account;

public interface AccSrv {
	public static String NAME1 = "accSrv1";
	public List<Account> getAccList(int page);
	public Account getAcc(int id);
	public boolean udtAcc(Account acc);
	public int offsetNum();
	public boolean addAcc(Account acc);
	public boolean delAcc(int id);
}
