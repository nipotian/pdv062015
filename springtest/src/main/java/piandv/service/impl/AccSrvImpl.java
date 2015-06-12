package piandv.service.impl;

import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
//import javax.persistence.TypedQuery;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import piandv.model.Account;
import piandv.model.VnDate;
import piandv.service.AccSrv;


@Service(AccSrv.NAME1)
@Transactional(propagation=Propagation.SUPPORTS)
public class AccSrvImpl implements AccSrv{
	@PersistenceContext
	private EntityManager em;
	private static ApplicationContext context= new ClassPathXmlApplicationContext("demoDS.xml");
    private static DriverManagerDataSource ds= (DriverManagerDataSource) context.getBean("dataSource");
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Account> getAccList(int page) {				
		List<Account> als=new ArrayList<Account>();
		List<Object> ls=null;
		Account acc=new Account();
		try{
			
			Query query = em.createQuery( "SELECT acc.id FROM Account AS acc");
			ls=(List<Object>)query.getResultList();
			System.out.println(ls);
			
		//Connection con=ds.getConnection();
		
		//Statement stm=con.createStatement();
		//ResultSet rs=stm.executeQuery("Select * from account order by fname,lname limit 3 offset " + ((page-1)*3));
		/*while (rs.next()){
			acc=new Account();
			acc.setId(rs.getInt(1));
			acc.setFname(rs.getString(2));
			acc.setLname(rs.getString(3));
			acc.setBirthday(new VnDate(rs.getDate(4)));
			als.add(acc);
		}*/
		
		//als=query.getResultList();
		}catch (Exception ex){
			System.out.println("loi: " + ex.toString());
		}
	
		return als;
	}

	@Override
	@Transactional
	public boolean addAcc(Account acc) {
		boolean b=false;		
		/*String s;
		if(acc.getBirthday()==null)
			s="insert into account(id,fname,lname) values("+acc.getId()
					+",'"+acc.getFname()+"','"+acc.getLname() + "')";
		else 
			s="insert into account values("+acc.getId()
					+",'"+acc.getFname()+"','"+acc.getLname()
					+"',?)";*/
		try{
			em.persist(acc);
			b=true;
			/*Connection con=ds.getConnection();
			
			PreparedStatement stm=con.prepareStatement(s);
			if(acc.getBirthday()!=null)
				stm.setDate(1, new Date(acc.getBirthday().getTime()));
			int i=stm.executeUpdate();
			b=(i==1);*/
			}catch (Exception ex){
				//System.out.println("loi: " + ex.getMessage());
				b=false;
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
			
			acc=em.find(Account.class, id);
			acc.setBirthday(new VnDate(acc.getBirthday()));
		/*Connection con=ds.getConnection();
		
		Statement stm=con.createStatement();
		ResultSet rs=stm.executeQuery("Select * from account where id=" +id);
		if (rs.next()){
			acc=new Account();
			acc.setId(rs.getInt(1));
			acc.setFname(rs.getString(2));
			acc.setLname(rs.getString(3));
			acc.setBirthday(new VnDate(rs.getDate(4)));
			
		}*/
		
		
		}catch (Exception ex){
			System.out.println("loi: " + ex.toString());
		}
		return acc;
	}

	@Override
	@Transactional
	public boolean udtAcc(Account acc) {
		boolean b=false;
		Account ac=new Account();
		/*String s="update account set fname=?, lname=?, birthday=";
		if(acc.getBirthday()==null)
			s=s+"null";
		else 
			s=s+"?";
		s=s+ " where id=" + acc.getId();*/
		try{
			ac=em.find(Account.class, acc.getId());
			ac.setFname(acc.getFname());
			ac.setLname(acc.getLname());
			if(acc.getBirthday()!=null)
				ac.setBirthday(new Date(acc.getBirthday().getTime()));
			else ac.setBirthday(null);
			/*Connection con=ds.getConnection();
			
			PreparedStatement stm=con.prepareStatement(s);
			
			stm.setString(1, acc.getFname());
			stm.setString(2, acc.getLname());
			if(acc.getBirthday()!=null)
				stm.setDate(3, new Date(acc.getBirthday().getTime()));
			int i=stm.executeUpdate();
			b=(i==1);*/
			}catch (Exception ex){
				System.out.println("loi: " + ex.getMessage());
				
			}
		return b;
	}

	@Override
	@Transactional
	public boolean delAcc(int id) {
		boolean b=false;
		Account ac=new Account();
		try{
			ac=em.find(Account.class, id);
			em.remove(ac);
			/*Connection con=ds.getConnection();
			
			Statement stm=con.createStatement();
			int i=stm.executeUpdate("Delete from account where id=" +id);
			b=(i==1);*/
			
			
			}catch (Exception ex){
				System.out.println("loi: " + ex.toString());
			}
		return b;
	}
}
