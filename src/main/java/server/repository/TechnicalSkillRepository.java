package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import server.model.TechnicalSkill;

public interface TechnicalSkillRepository extends JpaRepository<TechnicalSkill, Integer> {
}
