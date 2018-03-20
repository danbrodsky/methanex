package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.model.NonTechnicalSkill;
import server.model.Portfolio;
import server.model.Skill;
import server.repository.NonTechnicalSkillRepository;
import server.repository.SkillRepository;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class SkillController {
    @Autowired
    private SkillRepository repository;

    @Autowired
    private NonTechnicalSkillRepository nonTechnicalSkillRepository;

    @GetMapping("/technicalSkills")
    public @ResponseBody
    List<Skill> getAllTechnicalSkills() {
        return repository.findAll();
    }

    @GetMapping("/nonTechnicalSkills")
    public @ResponseBody List<NonTechnicalSkill> getAllNonTechnicalSkills() {
        return nonTechnicalSkillRepository.findAll();
    }

    @GetMapping("/skills/{skillId}")
    public @ResponseBody
    Skill getSkillById(@PathVariable(value = "skillId") Integer skillId) {
        return repository.findOne(skillId);
    }

    @PostMapping("/skills")
    public Skill createSkill(@Valid @RequestBody Skill skill) {

        return repository.save(skill);
    }

    @PutMapping("/skills/{skillId}")
    public ResponseEntity<Skill> updateSkills(@PathVariable(value = "skillId") Integer skillId,
                                              @Valid @RequestBody Skill updateDetails) {
        Skill beforeSkill = repository.findOne(skillId);
        if (beforeSkill != null) {

            beforeSkill.setName(updateDetails.getName());
            beforeSkill.setCategory(updateDetails.getCategory());
            Skill updatedSkill = repository.save(beforeSkill);
            return ResponseEntity.ok(updatedSkill);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/skills/{skillId}")
    public ResponseEntity<Portfolio> deleteSkill(@PathVariable(value = "skillId") Integer skillId) {
        Skill skill = repository.findOne(skillId);
        if (skill != null) {
            repository.delete(skill);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}