package server;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface PortfolioRepository extends CrudRepository<Portfolio, Integer>, QueryDslPredicateExecutor<Portfolio>, PortfolioRepositoryCustom {
}
