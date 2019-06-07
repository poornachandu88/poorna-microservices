package com.poorna.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poorna.model.Quote;

import java.util.List;

public interface QuotesRepository extends JpaRepository<Quote, Integer> {
    List<Quote> findByUserName(String username);
    
    Quote findQuoteByUserName(String username);
}
