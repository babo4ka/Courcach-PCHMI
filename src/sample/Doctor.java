package sample;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Doctor {

    private static final String separator = "#";

    @Getter @Setter
    private String name;
    @Getter @Setter
    private String surname;

    @Getter @Setter
    private long id;

    @Getter
    private static List<Doctor> doctors = new ArrayList<>();

    public Doctor(String _name, String _surname){
        this.setName(_name);
        this.setSurname(_surname);

        this.setId(new Date().getTime());
        doctors.add(this);
        this.saveDoctor();
    }

    private Doctor(String _name, String _surname, long _id){
        this.setName(_name);
        this.setSurname(_surname);

        this.setId(_id);
    }

    public static void LoadDoctors(){
        try{
            File doctorsFile = new File("./src/sample/database/doctors.txt");
            FileReader fileReader = new FileReader(doctorsFile);
            BufferedReader reader = new BufferedReader(fileReader);

            String line = reader.readLine();

            while(line != null){
                String [] data = line.split(separator);
                doctors.add(new Doctor(data[0], data[1], Long.parseLong(data[2])));
                line = reader.readLine();
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

    }


    private String toSaveString(){
        return this.getName() + separator + this.getSurname() + separator + this.getId();
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
