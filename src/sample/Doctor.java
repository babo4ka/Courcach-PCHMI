package sample;

import lombok.Getter;
import lombok.Setter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Doctor {

    @Getter @Setter
    private String name;
    @Getter @Setter
    private String surname;

    @Getter
    private long id;

    public Doctor(String _name, String _surname){
        this.name = _name;
        this.surname = _surname;

        this.id = new Date().getTime();

        this.saveDoctor();
    }

    private String toSaveString(){
        return this.getName() + "/" + this.getSurname() + "/" + this.getId();
    }


    private void saveDoctor(){
        try(FileWriter writer = new FileWriter("./src/sample/database/doctors.txt", true)){
            writer.write(this.toSaveString());
            writer.append('\n');
            writer.flush();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}
