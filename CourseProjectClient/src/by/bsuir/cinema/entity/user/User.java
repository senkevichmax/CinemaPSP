package by.bsuir.cinema.entity.user;

import by.bsuir.cinema.entity.Entity;
import java.util.Objects;

public class User extends Entity {
    private String login;
    private TypeUser type;

    public User(){super();}

    public User(int id, String login, TypeUser type){
        super(id);
        this.login = login;
        this.type = type;
    }

    public User(int insertedId, String login) {
        super(insertedId);
        this.login = login;
        type = TypeUser.CLIENT;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public TypeUser getType() {
        return type;
    }

    public void setType(TypeUser type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(getLogin(), user.getLogin()) &&
                getType() == user.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getLogin(), getType());
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", type=" + type +
                '}';
    }
}
