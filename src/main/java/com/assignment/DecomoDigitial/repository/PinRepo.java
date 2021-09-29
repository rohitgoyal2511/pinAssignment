package com.assignment.decomodigitial.repository;

import com.assignment.decomodigitial.entity.PIN;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PinRepo extends CrudRepository<PIN, String> {

    List<PIN> findByMsisdnAndVerifiedOrderBySentAtDesc(String recipient, boolean verified);

    Optional<PIN> findByPinIdAndVerified(Long pinId, boolean verified);
}
