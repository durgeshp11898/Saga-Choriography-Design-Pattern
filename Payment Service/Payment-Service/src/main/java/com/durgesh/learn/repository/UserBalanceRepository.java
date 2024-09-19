package com.durgesh.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.durgesh.learn.entity.UserBalance;

@Repository
public interface UserBalanceRepository extends JpaRepository<UserBalance,Integer> {
}