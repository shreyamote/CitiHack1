package com.example.demo.Service;

import com.example.demo.entity.InvestmentPortfolio;
import com.example.demo.Repository.InvestmentPortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvestmentSer implements InvestmentPortfolioService {

    @Autowired
    private InvestmentPortfolioRepository repository;

    @Override
    public List<InvestmentPortfolio> getAllPortfolios() {
        return repository.findAll();
    }

    @Override
    public Optional<InvestmentPortfolio> getPortfolioById(Long id) {
        return repository.findById(id);
    }

    @Override
    public InvestmentPortfolio createPortfolio(InvestmentPortfolio portfolio) {
        return repository.save(portfolio);
    }

    @Override
    public Optional<InvestmentPortfolio> updatePortfolio(Long id, InvestmentPortfolio updatedPortfolio) {
        return repository.findById(id).map(portfolio -> {
            portfolio.setStockName(updatedPortfolio.getStockName());
            portfolio.setInvestmentAmount(updatedPortfolio.getInvestmentAmount());
            portfolio.setCurrentValue(updatedPortfolio.getCurrentValue());
            portfolio.setReturns(updatedPortfolio.getReturns());
            return repository.save(portfolio);
        });
    }

    @Override
    public void deletePortfolio(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<InvestmentPortfolio> searchPortfolios(String stockName) {
        return repository.findByStockName(stockName);
    }
}