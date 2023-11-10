package com.coder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coder.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
