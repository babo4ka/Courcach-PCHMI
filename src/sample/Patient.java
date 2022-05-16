package sample;

import lombok.Getter;
import lombok.Setter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;


public class Patient {

    private final char separator = '*';

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
        this.setName(_name);
        this.setSurname(_surname);
        this.setBirthday(_birthday);

        this.id = new Date().getTime();

        this.savePatient();
    }

    private String toSaveString(){
        return this.getName() + separator + this.getSurname() + separator + this.getBirthday().getTime() + separator + this.getId();
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
