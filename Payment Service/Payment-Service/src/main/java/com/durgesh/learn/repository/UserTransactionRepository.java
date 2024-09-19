package com.durgesh.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.durgesh.learn.entity.UserTransaction;

public interface UserTransactionRepository extends JpaRepository<UserTransaction,Integer> {
}
