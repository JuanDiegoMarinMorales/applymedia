package com.example.subscription.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.subscription.entity.FirstStep;

public interface FirstStepRepository extends CrudRepository<FirstStep, Long> {
	
    Optional<FirstStep> findByClickId(String clickId);   
    
    @Query(value = "SELECT * FROM applymedia.firststep f WHERE f.msisdn=:msisdn", nativeQuery = true)
    List<FirstStep> findByMsisdn(String msisdn);
    
   
    // @Query(value = "SELECT * FROM step2.firststep f WHERE f.msisdn=:msisdn AND f.subscription_id<>'' AND f.subscription_id IS NOT null ORDER BY f.timestamp desc", nativeQuery = true)
    // List<FirstStep> findByMsisdnOrderByTimestampDesc( String msisdn);

}
