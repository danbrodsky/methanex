package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.model.NonTechnicalSkill;
import server.model.Portfolio;
import server.model.TechnicalSkill;
import server.repository.NonTechnicalSkillRepository;
import server.repository.TechnicalSkillRepository;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class SkillController {
    @Autowired
    private TechnicalSkillRepository repository;

    @Autowired
    private NonTechnicalSkillRepository nonTechnicalSkillRepository;

    @GetMapping("/technicalSkills")
    public @ResponseBody
    List<TechnicalSkill> getAllTechnicalSkills() {
        return repository.findAll();
    }

    @GetMapping("/nonTechnicalSkills")
    public @ResponseBody List<NonTechnicalSkill> getAllNonTechnicalSkills() {
        return nonTechnicalSkillRepository.findAll();
    }

    @GetMapping("/skills/{skillId}")
    public @ResponseBody
    TechnicalSkill getSkillById(@PathVariable(value = "skillId") Integer skillId) {
        return repository.findOne(skillId);
    }

    @PostMapping("/skills")
    public TechnicalSkill createSkill(@Valid @RequestBody TechnicalSkill skill) {
        return repository.save(skill);
    }

    @PutMapping("/skills/{skillId}")
    public ResponseEntity<TechnicalSkill> updateSkills(@PathVariable(value = "skillId") Integer skillId,
                                                       @Valid @RequestBody TechnicalSkill updateDetails) {
        TechnicalSkill beforeSkill = repository.findOne(skillId);
        if (beforeSkill != null) {

            beforeSkill.setName(updateDetails.getName());
            beforeSkill.setCategory(updateDetails.getCategory());
            TechnicalSkill updatedSkill = repository.save(beforeSkill);
            return ResponseEntity.ok(updatedSkill);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/skills/{skillId}")
    public ResponseEntity<Portfolio> deleteSkill(@PathVariable(value = "skillId") Integer skillId) {
        TechnicalSkill skill = repository.findOne(skillId);
        if (skill != null) {
            repository.delete(skill);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}