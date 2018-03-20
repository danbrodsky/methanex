package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.model.Portfolio;
import server.model.Project;
import server.repository.PortfolioRepository;
import server.repository.ProjectRepository;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class PortfolioController {
    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @PostMapping("/portfolios")
    public Portfolio createPortfolio(@Valid @RequestBody Portfolio portfolio) {
        return portfolioRepository.save(portfolio);
    }

    @GetMapping("/portfolios")
    public @ResponseBody
    Iterable<Portfolio> getAllPortfolios() {
        return this.portfolioRepository.findAll();
    }

    @GetMapping("/portfolios/{portfolioId}")
    public @ResponseBody
    ResponseEntity<Portfolio> getPortfolio(@PathVariable(value = "portfolioId") Integer portfolioId) {
        Portfolio portfolio = portfolioRepository.findOne(portfolioId);
        if (portfolio != null) {
            return ResponseEntity.ok(portfolio);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/portfolios/{portfolioId}/projects")
    public @ResponseBody ResponseEntity<List<Project>> getProjectsByPortfolio(@PathVariable(value = "portfolioId") Integer portfolioId) {
        Portfolio portfolio = portfolioRepository.findOne(portfolioId);
        if (portfolioId != null) {
            return ResponseEntity.ok(portfolio.getProjects());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/portfolios/{portfolioId}")
    public ResponseEntity<Portfolio> updatePortfolio(@PathVariable(value = "portfolioId") Integer portfolioId,
                                                 @Valid @RequestBody Portfolio updateDetails) {
        Portfolio beforePortfolio = portfolioRepository.findOne(portfolioId);
        if (beforePortfolio != null) {
            beforePortfolio.setClassification(updateDetails.getClassification());
            beforePortfolio.setProjects(updateDetails.getProjects());
            beforePortfolio.setResourceBreakdown(updateDetails.getResourceBreakdown());
            Portfolio updatedPortfolio = portfolioRepository.save(beforePortfolio);
            return ResponseEntity.ok(updatedPortfolio);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/portfolios/{portfolioId}")
    public ResponseEntity<Portfolio> deletePortfolio(@PathVariable(value = "portfolioId") Integer portfolioId) {
        Portfolio portfolio = portfolioRepository.findOne(portfolioId);
        if (portfolio != null) {
            portfolioRepository.delete(portfolio);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

//    @PostMapping("/portfolios/addProjects")
//    public ResponseEntity addProjectsToPortfolio(@RequestParam(value = "portfolioId", required = false) Integer portfolioId,
//                                                 @RequestBody String projectIds) {
//        Portfolio portfolio = portfolioId == null ? new Portfolio() : portfolioRepository.findOne(portfolioId);
//
//        List<Project> projects = portfolio.getProjects();
//        if (portfolio != null) {
//            projectIds.forEach(id -> {
//                Project project = projectRepository.findOne(id);
//                if (project != null) {
//                    projects.add(project);
//                }
//            });
//            portfolioRepository.save(portfolio);
//            return ResponseEntity.ok().build();
//        }
//        return ResponseEntity.notFound().build();
//    }

    @PostMapping("/portfolios/{portfolioId}/projects")
    public ResponseEntity addProjectsToPortfolio(@PathVariable(value = "portfolioId") Integer portfolioId,
                                                 @Valid @RequestBody Project project) {
        Portfolio portfolio = portfolioRepository.findOne(portfolioId);
        if (portfolio != null) {
            portfolio.getProjects().add(project);
            projectRepository.save(project);
            portfolioRepository.save(portfolio);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}