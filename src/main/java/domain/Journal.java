package domain;

import lombok.Builder;
import lombok.ToString;

import java.sql.Date;

@Builder
@ToString
public class Journal {
    private long id;
    private String name;
    private String description;
    private Date date;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }


}
