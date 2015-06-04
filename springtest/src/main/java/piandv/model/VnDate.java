package piandv.model;
import java.util.Date;
import java.text.SimpleDateFormat;
public class VnDate extends Date{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
	private boolean er=false;
	public VnDate(){
		super();
	}
	public VnDate(long date){
		super(date);
	}
	public VnDate(Date date){
		if(date!=null)
			this.setTime(date.getTime());
		else er=true;
	}
	public VnDate(String s){
		try{
			Date date=df.parse(s);
			this.setTime(date.getTime());
			er=false;
			
		}catch (Exception ex){
			this.setTime(0);
			er=true;
		}
	}
	public boolean hasError(){
		return er;
	}
	@Override
	public String toString(){
		if(er) return "";
		return df.format(this);
	}
}
