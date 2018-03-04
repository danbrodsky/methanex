package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class PorfolioController {
    @Autowired
    private BusinessService businessService;

    @GetMapping(value = "/portfolios")
    public Iterable<Portfolio> getPortfolios(@RequestParam Integer id) {
        if (id != null) {
            return this.businessService.getPortfoliosByGivenProject(id);
        }
        return this.businessService.getPortfolios();
    }
}
