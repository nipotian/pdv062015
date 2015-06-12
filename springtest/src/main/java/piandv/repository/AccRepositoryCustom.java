package piandv.repository;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import piandv.model.Account;



public interface AccRepositoryCustom {
	public PageImpl<Account> findByFnameAndLnameContains(String fName,
			String lName, Pageable pageable);
	public List<Account> findAll(int page, int limit);
	public long offsetNum();
	
}