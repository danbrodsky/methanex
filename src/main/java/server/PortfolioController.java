package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class PortfolioController {

    @Autowired
    private PortfolioRepository repository;

    @PostMapping("/portfolios")
    public Portfolio createPortfolio(@Valid @RequestBody Portfolio portfolio) {
        return repository.save(portfolio);
    }

    @GetMapping("/portfolios")
    public @ResponseBody
    Iterable<Portfolio> getAllPortfolios() {
        return this.repository.findAll();
    }

    @GetMapping("/portfolios/{portfolioId}")
    public @ResponseBody
    ResponseEntity<Portfolio> getPortfolio(@PathVariable(value = "portfolioId") Integer portfolioId) {
        Portfolio portfolio = repository.findOne(portfolioId);
        if (portfolio != null) {
            return ResponseEntity.ok(portfolio);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/portfolios/{portfolioId}")
    public ResponseEntity<Portfolio> updatePortfolio(@PathVariable(value = "portfolioId") Integer portfolioId,
                                                 @Valid @RequestBody Portfolio updateDetails) {
        Portfolio beforePortfolio = repository.findOne(portfolioId);
        if (beforePortfolio != null) {

            beforePortfolio.setBusinessOwner(updateDetails.getBusinessOwner());
            beforePortfolio.setClassification(updateDetails.getClassification());
            beforePortfolio.setProjects(updateDetails.getProjects());
            beforePortfolio.setResourceBreakdown(updateDetails.getResourceBreakdown());
            beforePortfolio.setStatus(updateDetails.getStatus());
            Portfolio updatedPortfolio = repository.save(beforePortfolio);
            return ResponseEntity.ok(updatedPortfolio);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/portfolios/{portfolioId}")
    public ResponseEntity<Portfolio> deletePortfolio(@PathVariable(value = "portfolioId") Integer portfolioId) {
        Portfolio portfolio = repository.findOne(portfolioId);
        if (portfolio != null) {
            repository.delete(portfolio);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}