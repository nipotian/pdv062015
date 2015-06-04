package piandv.service;
import java.util.List;

import piandv.model.Account;
import piandv.model.AccountSrc;
public interface DataSrv {
	public List<AccountSrc> ds=null;
	public List<AccountSrc> getAccList(int page);
	public Account getAcc(int id);
	public boolean udtAcc(Account acc);
	public int offsetNum();
	public boolean addAcc(Account acc);
	public boolean delAcc(int id);

}
