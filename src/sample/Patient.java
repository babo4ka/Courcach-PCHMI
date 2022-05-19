package sample;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Patient {

    private static final String separator = "#";

    //ФИО
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String surname;
    //др
    @Getter @Setter
    private Date birthday;
    //район
    @Getter @Setter
    private Districts district;
    //id
    @Getter @Setter
    private long id;

    @Getter
    private static List<Patient> patients = new ArrayList<>();

    public Patient(String _name, String _surname, Date _birthday, Districts _district){
        this.setName(_name);
        this.setSurname(_surname);
        this.setBirthday(_birthday);
        this.setDistrict(_district);
        this.setId(new Date().getTime());

        patients.add(this);

        this.savePatient();
    }

    private Patient(String _name, String _surname, Date _birthday, Districts _district, long _id){
        this.setName(_name);
        this.setSurname(_surname);
        this.setBirthday(_birthday);
        this.setDistrict(_district);

        this.setId(_id);
    }

    public static void LoadPatients(){
        try{
            File patientsFile = new File("./src/sample/database/patients.txt");
            FileReader fileReader = new FileReader(patientsFile);
            BufferedReader reader = new BufferedReader(fileReader);

            String line = reader.readLine();

            while(line != null){
                String [] data = line.split(separator);
                long id = Long.parseLong(data[4]);
                Date bd = new Date(TimeUnit.SECONDS.toMillis(Long.parseLong(data[2])));
                patients.add(new Patient(data[0], data[1], bd, Districts.fromString(data[3]), id));
                line = reader.readLine();
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public String toSaveString(){
        return this.getName() + separator + this.getSurname() + separator + this.getBirthday().getTime() + separator + this.getDistrict().toString() + separator + this.getId();
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
