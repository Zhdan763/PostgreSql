package domain;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class User {
    private long id;
    private String login;
    private String password;


    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }


}
