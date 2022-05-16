package sample;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class User {

    @Getter @Setter
    private String login;
    @Getter @Setter
    private String password;

    public User(String _login, String _password){
        this.setLogin(_login);
        this.setPassword(_password);
    }

    private List<User> users = new ArrayList<>();

    public static void loadUsers(){

    }
}
