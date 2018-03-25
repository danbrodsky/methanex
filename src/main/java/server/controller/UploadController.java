package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import server.model.GanttChart;
import server.repository.GanttChartRepository;
import java.time.LocalDate;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UploadController {
    @Autowired
    private GanttChartRepository repository;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                byte[] imageAsBytes = file.getBytes();
                GanttChart chart = new GanttChart();
                chart.setFileName(file.getName());
                chart.setUploadDate(LocalDate.now());
                chart.setData(imageAsBytes);
                repository.save(chart);
                ResponseEntity.ok().build();
            }
            catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/image")
    public ResponseEntity getImage(@RequestParam("id") Integer id) {
        GanttChart chart = repository.findOne(id);
        if (chart != null) {
            return ResponseEntity.ok(chart);
        }
        return ResponseEntity.notFound().build();
    }

}
