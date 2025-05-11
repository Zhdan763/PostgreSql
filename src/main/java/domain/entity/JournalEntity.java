package domain.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "journal")
public class JournalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private Date date;

    public JournalEntity() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public JournalEntity(long id, String name, String description, Date date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public static JournalEntityBuilder builder() {
        return new JournalEntityBuilder();
    }

    public static class JournalEntityBuilder {
        private long id;
        private String name;
        private String description;
        private Date date;

        public JournalEntityBuilder id(long id) {
            this.id = id;
            return this;
        }

        public JournalEntityBuilder name(String name) {
            this.name = name;
            return this;
        }

        public JournalEntityBuilder description(String description) {
            this.description = description;
            return this;
        }

        public JournalEntityBuilder date(Date date) {
            this.date = date;
            return this;
        }

        public JournalEntity build() {
            return new JournalEntity(id, name, description, date);
        }

    }

    @Override
    public String toString() {
        return "JournalEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}

