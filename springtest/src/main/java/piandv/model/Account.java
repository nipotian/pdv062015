package piandv.model;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;




public class Account{
	
	@Min(100000000) @Max(999999999)
	private int id;
	
	@NotNull
	@Size(min = 3, max = 15, message = "Username must be between 3 and 20 characters long.")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Username must be alphanumeric with no spaces")
	private String fname;
	
	
	@NotNull
	@Size(min = 2, max = 15, message = "Username must be between 3 and 20 characters long.")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Username must be alphanumeric with no spaces")
	private String lname;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date birthday;

	public Account(){
		
	}

	public Account(int id, String fname, String lname){
		this();
		this.id=id;
		this.fname=fname;
		this.lname=lname;
	}
	
	public Account(int id, String fname, String lname, Date birthday){
		this(id,fname,lname);
		this.birthday=birthday;
		
	}
	
	public Account(Account m){
		this(m.getId(),m.getFname(),m.getLname(),m.getBirthday());
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public Date getBirthday() {
	
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	
	public String toString(){
		return id + fname+ lname+ birthday;
	}

	
	
}
