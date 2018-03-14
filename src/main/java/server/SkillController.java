package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class SkillController {

    @Autowired
    private SkillRepository repository;

    @PostMapping("/skills")
    public Skill createSkill(@Valid @RequestBody Skill skill) {
        return repository.save(skill);
    }

    @GetMapping("/skills")
    public @ResponseBody Iterable<Skill> getAllSkills() {
        return repository.findAll();
    }

    @GetMapping("/skills/{skillId}")
    public @ResponseBody ResponseEntity<Skill> getSkill(@PathVariable(value = "skillId") Integer skillId) {
        Skill skill = repository.findOne(skillId);
        if (skill != null) {
            return ResponseEntity.ok(skill);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/skills/{skillId}")
    public ResponseEntity<Skill> deleteSkill(@PathVariable(value = "skillId") Integer skillId) {
        Skill skill = repository.findOne(skillId);
        if(skill != null) {
            repository.delete(skill);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
