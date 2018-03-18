package server.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import server.model.Portfolio;

public interface PortfolioRepository extends CrudRepository<Portfolio, Integer>, QueryDslPredicateExecutor<Portfolio>, PortfolioRepositoryCustom {
}
