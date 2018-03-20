package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.model.PeerGroup;

public interface PeerGroupRepository extends JpaRepository<PeerGroup, Integer> {
}
