package piandv.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import piandv.model.Account;
import piandv.model.VnDate;
import piandv.service.AccSrv;


@Service(AccSrv.NAME1)
public class AccSrvImpl implements AccSrv{

	private static ApplicationContext context= new ClassPathXmlApplicationContext("demoDS.xml");
    private static DriverManagerDataSource ds= (DriverManagerDataSource) context.getBean("dataSource");
	@Override
	public List<Account> getAccList(int page) {				
		List<Account> als=new ArrayList<Account>();
		Account acc=new Account();
		try{
			
		Connection con=ds.getConnection();
		
		Statement stm=con.createStatement();
		ResultSet rs=stm.executeQuery("Select * from account order by fname,lname limit 3 offset " + ((page-1)*3));
		while (rs.next()){
			acc=new Account();
			acc.setId(rs.getInt(1));
			acc.setFname(rs.getString(2));
			acc.setLname(rs.getString(3));
			acc.setBirthday(new VnDate(rs.getDate(4)));
			als.add(acc);
		}
		
		
		}catch (Exception ex){
			System.out.println("loi: " + ex.toString());
		}
	
		return als;
	}

	@Override
	public boolean addAcc(Account acc) {
		boolean b=false;		
		String s;
		if(acc.getBirthday()==null)
			s="insert into account(id,fname,lname) values("+acc.getId()
					+",'"+acc.getFname()+"','"+acc.getLname() + "')";
		else 
			s="insert into account values("+acc.getId()
					+",'"+acc.getFname()+"','"+acc.getLname()
					+"',?)";
		try{
			
			Connection con=ds.getConnection();
			
			PreparedStatement stm=con.prepareStatement(s);
			if(acc.getBirthday()!=null)
				stm.setDate(1, new Date(acc.getBirthday().getTime()));
			int i=stm.executeUpdate();
			b=(i==1);
			}catch (Exception ex){
				//System.out.println("loi: " + ex.getMessage());
				
			}
		return b;
	}

	@Override
	public int offsetNum() {
		// TODO Auto-generated method stub
		int p=0;
		try{
			
			Connection con=ds.getConnection();
			
			Statement stm=con.createStatement();
			ResultSet rs=stm.executeQuery("Select count(*) from account");
			if(rs.next())
				p=rs.getInt(1);
		}catch (Exception ex){
			System.out.println("loi: " + ex.toString());
		}
		if(p%3==0)
			return p/3;
		return p/3+1;
	}

	@Override
	public Account getAcc(int id) {
		Account acc=null;
		try{
			
		Connection con=ds.getConnection();
		
		Statement stm=con.createStatement();
		ResultSet rs=stm.executeQuery("Select * from account where id=" +id);
		if (rs.next()){
			acc=new Account();
			acc.setId(rs.getInt(1));
			acc.setFname(rs.getString(2));
			acc.setLname(rs.getString(3));
			acc.setBirthday(new VnDate(rs.getDate(4)));
			
		}
		
		
		}catch (Exception ex){
			System.out.println("loi: " + ex.toString());
		}
		return acc;
	}

	@Override
	public boolean udtAcc(Account acc) {
		boolean b=false;		
		String s="update account set fname=?, lname=?, birthday=";
		if(acc.getBirthday()==null)
			s=s+"null";
		else 
			s=s+"?";
		s=s+ " where id=" + acc.getId();
		try{
			
			Connection con=ds.getConnection();
			
			PreparedStatement stm=con.prepareStatement(s);
			
			stm.setString(1, acc.getFname());
			stm.setString(2, acc.getLname());
			if(acc.getBirthday()!=null)
				stm.setDate(3, new Date(acc.getBirthday().getTime()));
			int i=stm.executeUpdate();
			b=(i==1);
			}catch (Exception ex){
				System.out.println("loi: " + ex.getMessage());
				
			}
		return b;
	}

	@Override
	public boolean delAcc(int id) {
		boolean b=false;
		try{
			
			Connection con=ds.getConnection();
			
			Statement stm=con.createStatement();
			int i=stm.executeUpdate("Delete from account where id=" +id);
			b=(i==1);
			
			
			}catch (Exception ex){
				System.out.println("loi: " + ex.toString());
			}
		return b;
	}
}
