package com.nipun.nipunbackend.repository.pmis;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nipun.nipunbackend.model.pmis.Contractor;

@Repository
public interface ContractorRepository extends JpaRepository<Contractor, String>{

    List<Contractor> findByEmailIdAndPhoneNumber(String emailId, String phoneNumber);

	boolean existsByPhoneNumber(String mobile);
	
	
	
	

}
