package domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String login;
    private String password;


    public UserEntity() {
    }

    public UserEntity(long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;

    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
