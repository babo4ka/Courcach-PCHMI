package sample;

import lombok.Getter;
import lombok.Setter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;


public class Patient {

    //ФИО
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String surname;
    //др
    @Getter @Setter
    private Date birthday;
    //id
    @Getter
    private long id;

    public Patient(String _name, String _surname, Date _birthday){
        this.name = _name;
        this.surname = _surname;
        this.birthday = _birthday;

        this.id = new Date().getTime();

    }

    private String toSaveString(){
        return this.getName() + "/" + this.getSurname() + "/" + this.getBirthday().getTime() + "/" + this.getId();
    }

    private void savePatient(){
        try(FileWriter writer = new FileWriter("./src/sample/database/patients.txt", true)){
            writer.write(this.toSaveString());
            writer.append('\n');
            writer.flush();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}
