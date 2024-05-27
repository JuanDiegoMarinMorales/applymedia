/*
 * Copyright (C) IDB Mobile Technology S.L. - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */
package com.example.subscription.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.subscription.entity.Subscription;

/*
 * Subscription Repository to have a native queries
 * and default queries
 * I NEED TO SEE WHAT QUERIES I NEED AND WHAT NOT CAUSE
 * THIS IS COPY / PASTE THING 
 */
public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {

	// Optional<SubscriptionEntity> findByMsisdnAndServiceIdAndActiveTrue(final String msisdn, final String serviceId);

	// boolean existsByMsisdnAndServiceIdAndActiveTrue(final String msisdn, final String serviceId);
	@Query(nativeQuery = true, value = "SELECT * FROM applymedia.subscription WHERE msisdn = :msisdn AND state = 'ACTIVE'")
	Optional<Subscription> findByMsisdnStateActive(final String msisdn);

	@Query(nativeQuery = true, value = "SELECT * FROM applymedia.subscription WHERE msisdn = :msisdn")
	Optional<Subscription> findByMsisdn(final String msisdn);
	// @Query(nativeQuery = true, value = "SELECT * FROM subscription WHERE msisdn = :msisdn  AND service_id = :serviceId AND state = 'ACTIVE' AND password =:password" )
	// Optional<SubscriptionEntity> findByMsisdnActiveServiceIdStateActive(final String msisdn, final String serviceId,final String password);
	
	// @Query(nativeQuery = true, value = "SELECT * FROM subscription WHERE msisdn = :msisdn  AND service_id = :serviceId AND state = 'ACTIVE'" )
	// Optional<SubscriptionEntity> findByMsisdnActiveServiceIdStateActiveNoPassword(final String msisdn, final String serviceId);
	
	// @Query(nativeQuery = true, value = "SELECT * FROM subscription WHERE msisdn = :msisdn  AND service_id = :serviceId AND state = 'ACTIVE'" )
	// Optional<SubscriptionEntity> findByMsisdnActiveServiceIdStateActiveUsub(final String msisdn, final String serviceId);


	// Optional<SubscriptionEntity> findByClickId(final String clickId);
	
	// Optional<SubscriptionEntity> findBySubscriptionId(final String subscriptionId);
	
	// Stream<SubscriptionEntity> findByMsisdnAndActiveTrue(final String msisdn);

	// @Query(value = "SELECT * FROM subscription s WHERE s.state='ACTIVE' AND s.msisdn=:msisdn", nativeQuery = true)
	// Optional<SubscriptionEntity> findByMsisdnAndStateActive(final String msisdn);

	// @Query(value = "SELECT * FROM subscription s WHERE s.state='ACTIVE' AND s.msisdn=:msisdn AND s.service_id=:service", nativeQuery = true)
	// List<SubscriptionEntity> findByMsisdnAndStateActiveAndService(final String msisdn,final Integer service);
	
	// @Query(value = "SELECT * FROM subscription s WHERE s.state='ACTIVE' AND s.msisdn=:msisdn AND s.service_name=:service", nativeQuery = true)
	// List<SubscriptionEntity> findByMsisdnAndStateActiveAndServiceName(final String msisdn,final String service);
	
	// @Query(value = "SELECT * FROM subscription s WHERE s.state='ACTIVE' AND s.msisdn=:msisdn AND s.password=:password", nativeQuery = true)
	// List<SubscriptionEntity> findByMsisdnAndStateActiveAndPassword(final String msisdn,final String password);
	
	// Optional<SubscriptionEntity> findTop1ByMsisdnAndActiveAndTypeSignoffOrderByIdDesc(final String msisdn,
	// 		final boolean active, final TypeSignoff typeSignoff);

	// List<SubscriptionEntity> findByMsisdnAndCarrierId(final String msisdn, final Integer carrier);

	// @Query(value = "SELECT * FROM subscription s WHERE s.msisdn=:msisdn AND s.service_name=:service ORDER BY s.subscription_timestamp DESC", nativeQuery = true)
	// List<SubscriptionEntity> findByMsisdnAndServiceId(final String msisdn, final String service);
	
	// @Query(value = "SELECT * FROM subscription s WHERE s.msisdn=:msisdn AND s.service_name=:service AND s.active=1 ORDER BY s.subscription_timestamp DESC LIMIT 1", nativeQuery = true)
	// Optional<SubscriptionEntity> findByMsisdnAndServiceIdAndActive(final String msisdn, final String service);

	// Optional<SubscriptionEntity> findByMsisdn(final String msisdn);

	// @Query("SELECT s.id FROM SubscriptionEntity s WHERE s.active=TRUE AND s.countryId= :countryId")
	// List<Long> findAllIdByCountryIdAndActive(Integer countryId);

	// @Query(value = "SELECT s.id FROM subscription s WHERE s.active=TRUE AND s.country_id= :countryId AND (s.last_dr_success IS NULL OR date(s.last_dr_success) != curdate());", nativeQuery = true)
	// List<Long> findAllIdByCountryIdAndActiveAndLastBilledNotToday(Integer countryId);

	// @Query(value = "SELECT s.id FROM subscription s WHERE s.active=TRUE AND (s.last_dr_success IS NULL OR date(s.last_dr_success) != curdate());", nativeQuery = true)
	// List<Long> findAllActiveAndLastBilledNotToday();

}
