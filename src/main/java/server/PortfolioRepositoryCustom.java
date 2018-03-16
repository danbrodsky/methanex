package server;

public interface PortfolioRepositoryCustom {
    long countProjects(int portfolioId);

    long countResourcesInAllProjects(int portfolioId);

    long countResourcesInPeerGroup(int portfolioId, int peerGroupId);

    long countResourcesInGroup(int portfolioId, int groupId);

    long countResourcesInLocation(int portfolioId, String location);

    long countResourcesWithSkill(int portfolioId, int skillId);
}
