package com.example.landing.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.landing.entity.FirstStep;


public interface FirstStepRepository extends CrudRepository<FirstStep,Long>{
     Optional<FirstStep> findByClickId(String clickId);
     Optional<FirstStep> findByMsisdn(Long msisdn);
}
