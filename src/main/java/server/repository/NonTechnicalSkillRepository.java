package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.model.NonTechnicalSkill;

public interface NonTechnicalSkillRepository extends JpaRepository<NonTechnicalSkill, Integer> {
}
