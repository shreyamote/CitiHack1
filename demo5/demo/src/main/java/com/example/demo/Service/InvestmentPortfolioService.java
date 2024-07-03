package com.example.demo.Service;

import com.example.demo.entity.InvestmentPortfolio;

import java.util.List;
import java.util.Optional;

public interface InvestmentPortfolioService {
    List<InvestmentPortfolio> getAllPortfolios();
    Optional<InvestmentPortfolio> getPortfolioById(Long id);
    InvestmentPortfolio createPortfolio(InvestmentPortfolio portfolio);
    Optional<InvestmentPortfolio> updatePortfolio(Long id, InvestmentPortfolio updatedPortfolio);
    void deletePortfolio(Long id);
    List<InvestmentPortfolio> searchPortfolios(String stockName);
}