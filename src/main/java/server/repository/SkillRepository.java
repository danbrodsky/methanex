package server.repository;

import org.springframework.data.repository.CrudRepository;
import server.model.Skill;

public interface SkillRepository extends CrudRepository<Skill, Integer> {
}
