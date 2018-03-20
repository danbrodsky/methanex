package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.model.Skill;

public interface SkillRepository extends JpaRepository<Skill, Integer> {
}
