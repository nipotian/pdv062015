package piandv.repository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import piandv.model.Account;



public interface AccRepository extends JpaRepository<Account, Integer>, AccRepositoryCustom {
	public List<Account> findByFname(String fname, Pageable pagable);
}