package domain.entity;
import lombok.Builder;
import lombok.ToString;
import java.sql.Date;
import java.sql.Timestamp;

@Builder
@ToString
public class TaskEntity {
    private long id;
    private String name;
    private String description;
    private Timestamp date;
    private String status;
    private long journalId;

    public TaskEntity() {
    }

    public TaskEntity(long id, String name, String description, Timestamp date, String status, long journalId) {
        this.id = id;
        this.name = name;
        this.description=description;
        this.date = date;
        this.status = status;
        this.journalId = journalId;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public long getJournalId() {
        return journalId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setJournalId(long journalId) {
        this.journalId = journalId;
    }
}
