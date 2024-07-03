package com.example.demo.Repository;


import com.example.demo.entity.InvestmentPortfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface InvestmentPortfolioRepository extends JpaRepository<InvestmentPortfolio, Long> {
    List<InvestmentPortfolio> findByStockName(String stockName);
}