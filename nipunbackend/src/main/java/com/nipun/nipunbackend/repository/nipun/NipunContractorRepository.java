package com.nipun.nipunbackend.repository.nipun;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nipun.nipunbackend.model.nipun.ContractorReg;

@Repository
public interface NipunContractorRepository extends JpaRepository<ContractorReg, Integer>{

	boolean existsByEmailAndMobile(String email, String mobile);

	boolean existsByMobile(String mobile);

	
	

}
