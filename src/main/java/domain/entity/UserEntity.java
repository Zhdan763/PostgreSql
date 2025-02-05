package domain.entity;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class UserEntity {
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
