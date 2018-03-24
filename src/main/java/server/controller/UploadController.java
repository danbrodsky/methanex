package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import server.model.GanttChart;
import server.repository.GanttChartRepository;

import java.time.LocalDate;

@RestController
public class UploadController {
    @Autowired
    private GanttChartRepository repository;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadFile(@RequestParam("filename") String filename,
                                     @RequestBody MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                byte[] imageAsBytes = file.getBytes();
                GanttChart chart = new GanttChart();
                chart.setFileName(filename);
                chart.setUploadDate(LocalDate.now());
                chart.setData(imageAsBytes);
            }
            catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.noContent().build();
    }
}
