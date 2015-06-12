package piandv.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import piandv.model.Account;
//import com.mysql.jdbc.Buffer;
import piandv.model.VnDate;

public class AccRepositoryImpl implements AccRepositoryCustom{

	@PersistenceContext
	EntityManager em;
	
	@Override
	public PageImpl<Account> findByFnameAndLnameContains(
			String fname, String lname, Pageable pageable) {
		StringBuffer query = new StringBuffer(String.format(" From Account a WHERE lastName LIKE %s", "%"+lname+"%"));
		if(StringUtils.isNotBlank(fname)){
			query.append(String.format(" AND a.fistName = '%s'", fname));
		}
		
		
		Sort sort = pageable.getSort();
		@SuppressWarnings("unchecked")
		List<Order> oders = IteratorUtils.toList(sort.iterator());
		if(CollectionUtils.isNotEmpty(oders)){
			query.append(" ODERBY");
			for(Order oder : oders){
				query.append(String.format(" a.%s %s,", oder.getProperty(), oder.getDirection()));
			}
		}
		StringBuffer queryAccounts = new StringBuffer("Select a ");
		StringBuffer queryCountItems = new StringBuffer("Select COUNT(a) ");
		
		 Query  accounts = em.createQuery(queryAccounts.append(query.toString()).toString(),Account.class);
		 Query toTalItem = em.createQuery(queryCountItems.append(query.toString()).toString(),Long.class);
		 
		 @SuppressWarnings("unchecked")
		List<Account> lstAccount = accounts.setFirstResult(pageable.getPageNumber())
		 .setMaxResults(pageable.getPageSize())
		 .getResultList();
		 
		 Long total = (long) toTalItem.getFirstResult();
		 
		 PageImpl<Account> pageAcount = new PageImpl<Account>(lstAccount, pageable, total);
		return pageAcount;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> findAll(int page, int limit) {
		Query query = em.createQuery( "Select acc from Account acc order by fname,lname", Account.class);
		query.setFirstResult((page-1)*limit);
		query.setMaxResults(limit);
		List<Account> als=(List<Account>)query.getResultList();
		
		for(Account acc:als){
			acc.setBirthday(new VnDate(acc.getBirthday()));
		}
		return als;
	}

	@Override
	public long offsetNum() {
		long p=0;
		
		Query query = em.createQuery("Select count(acc) from Account acc");
		p=(long)query.getSingleResult();
		
	
	if(p%3==0)
		return p/3;
	return p/3+1;
	}

	
	
}
