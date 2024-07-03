package com.example.demo.Controller;

import com.example.demo.Service.InvestmentSer;
import com.example.demo.entity.InvestmentPortfolio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/portfolios")
public class InvestmentPortfolioController {

    @Autowired
    private InvestmentSer portfolioService;

    @GetMapping
    public List<InvestmentPortfolio> getAllPortfolios() {
        return portfolioService.getAllPortfolios();
    }

    @GetMapping("/{id}")
    public InvestmentPortfolio getPortfolioById(@PathVariable Long id) {
        return portfolioService.getPortfolioById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Portfolio not found"));
    }

    @PostMapping
    public InvestmentPortfolio createPortfolio(@RequestBody InvestmentPortfolio portfolio) {
        return portfolioService.createPortfolio(portfolio);
    }

    @PutMapping("/{id}")
    public InvestmentPortfolio updatePortfolio(@PathVariable Long id, @RequestBody InvestmentPortfolio updatedPortfolio) {
        return portfolioService.updatePortfolio(id, updatedPortfolio)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Portfolio not found"));
    }

    @DeleteMapping("/{id}")
    public void deletePortfolio(@PathVariable Long id) {
        try {
            portfolioService.deletePortfolio(id);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Portfolio not found");
        }
    }

    @GetMapping("/search")
    public List<InvestmentPortfolio> searchPortfolios(@RequestParam String stockName) {
        return portfolioService.searchPortfolios(stockName);
    }
}