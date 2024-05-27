package com.example.notification.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.notification.entities.Notification;

public interface NotificationRepository extends CrudRepository<Notification,Long> {
    Optional<Notification> findById(Integer id);

    @Query(value = "SELECT * FROM applymedia.notification f WHERE f.msisdn=:msisdn", nativeQuery = true)
    List<Notification> findByMsisdn(String msisdn);
}
