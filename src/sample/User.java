package sample;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class User {

    private static final String separator = "#";

    @Getter @Setter
    private String login;
    @Getter @Setter
    private String password;

    public User(String _login, String _password){
        this.setLogin(_login);
        this.setPassword(_password);
    }

    private static List<User> users = new ArrayList<>();

    public static void loadUsers(){
        try{
            File usersFile = new File("./src/sample/database/users.txt");
            FileReader fileReader = new FileReader(usersFile);
            BufferedReader reader = new BufferedReader(fileReader);

            String line = reader.readLine();

            while(line != null){
                String [] data = line.split(separator);
                users.add(new User(data[0], data[1]));
                line = reader.readLine();
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static boolean checkUser(String _login, String _password){
        boolean found = false;
        User foundUser = null;
        for(User u : users){
            if(u.getLogin().equals(_login)){
                found = true;
                foundUser = u;
                break;
            }
        }
        if(!found)return false;

        return foundUser.getPassword().equals(_password);
    }
}
