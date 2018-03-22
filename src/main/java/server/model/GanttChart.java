package server.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "gantt_chart")
public class GanttChart implements Serializable {
    private static final long serialVersionUID = 8194877105433257008L;

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "upload_date")
    private LocalDate uploadDate;

    @Column(name = "data")
    @Lob
    private byte[] data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
