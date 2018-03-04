package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BusinessService {
    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public Iterable<Portfolio> getPortfolios() {
        return this.portfolioRepository.findAll();
    }

    public Iterable<Portfolio> getPortfoliosByGivenProject(Integer id) {
        Project project = projectRepository.findOne(id);
        return  project.getPortfolios();
    }
}
