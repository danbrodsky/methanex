package server;

import com.querydsl.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PortfolioRepositoryImpl implements PortfolioRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long countProjects(int portfolioId) {
        JPAQuery<Notification> query = new JPAQuery<Notification>(entityManager);
        return query
                .select(QPortfolio.portfolio.id)
                .from(QPortfolio.portfolio)
                .where(QPortfolio
                        .portfolio
                        .id
                        .eq(portfolioId))
                .fetchCount();
    }

    //
    @Override
    public long countResourcesInAllProjects(int portfolioId) {
        QProject project = QProject.project;
        QResource resource = QResource.resource;
        return new JPAQuery<>(entityManager)
                .from(project)
                .where(project.portfolio.id.eq(portfolioId))
                .innerJoin(project.resources, resource)
                .fetchCount();
    }

    @Override
    public long countResourcesInPeerGroup(int portfolioId, int peerGroupId) {
        return 0;
    }

    @Override
    public long countResourcesInGroup(int portfolioId, int groupId) {
        return 0;
    }

    @Override
    public long countResourcesInLocation(int portfolioId, String location) {
        return 0;
    }

    @Override
    public long countResourcesWithSkill(int portfolioId, int skillId) {
        return 0;
    }
}