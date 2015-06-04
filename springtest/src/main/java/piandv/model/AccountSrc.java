package piandv.model;

public class AccountSrc {

	private Account acc;
	private int status;
	public AccountSrc(Account acc){
		this.acc=acc;
	}
	public AccountSrc(Account acc, int status){
		this(acc);
		this.status=status;
	}
	public Account getAcc() {
		return acc;
	}
	public void setAcc(Account acc) {
		this.acc = acc;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
