package com.dario.useraccounts.repository;

import com.dario.useraccounts.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
