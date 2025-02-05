package domain;

import lombok.Builder;
import lombok.ToString;

import java.util.Date;

@Builder
@ToString
public class Task {
    private int id;
    private String name;
    private String description;
    private Date date;

    private Status taskStatus;
    private int journalId;


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public Status getTaskStatus() {
        return taskStatus;
    }

    public int getJournalId() {
        return journalId;
    }

}
