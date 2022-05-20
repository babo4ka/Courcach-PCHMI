package sample;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.*;


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
    private List<Date> recieps = new ArrayList<>();


    public void addReciepe(Date _reciepe){
        try{
            File source = new File("./src/sample/database/patients.txt");

            BufferedReader reader = new BufferedReader(new FileReader(source));

            String line;
            String lineToRewrite = this.toSaveString();

            List<String> lines = new ArrayList<>();

            while((line = reader.readLine()) != null){
                if(!line.equals(lineToRewrite)){
                    lines.add(line);
                }
            }

            BufferedWriter fake = new BufferedWriter(new FileWriter(source, false));
            fake.write("");
            fake.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(source, true));

            for(String l:lines){
                writer.write(l);
                writer.newLine();
            }

            this.recieps.add(_reciepe);
            writer.write(this.toSaveString());

            reader.close();
            writer.close();

        }catch (IOException err){
            err.printStackTrace();
        }
    }

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

    private Patient(String _name, String _surname, Date _birthday, Districts _district, long _id, String... _recs){
        this.setName(_name);
        this.setSurname(_surname);
        this.setBirthday(_birthday);
        this.setDistrict(_district);

        this.setId(_id);

        for(String r:_recs){
            this.recieps.add(new Date(Long.parseLong(r)));
        }
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
                Date bd = new Date(Long.parseLong(data[2]));

                if(data.length > 5){
                    String[] recs = Arrays.copyOfRange(data, 5, data.length);
                    patients.add(new Patient(data[0], data[1], bd, Districts.fromString(data[3]), id, recs));
                }else{
                    patients.add(new Patient(data[0], data[1], bd, Districts.fromString(data[3]), id));
                }

                line = reader.readLine();
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public String toNameString(){
        return (this.getName() + " " + this.getSurname()).toLowerCase(Locale.ROOT);
    }

    public String toNameStringReverse(){
        return (this.getSurname() + " " + this.getName()).toLowerCase(Locale.ROOT);
    }

    public String toSaveString(){
        String recs = "";
        if(recieps.size() != 0){
            for(Date r:recieps){
                if(recs.equals(""))recs = Long.toString(r.getTime());
                else recs = String.join(separator, recs, Long.toString(r.getTime()));
            }
        }
        System.out.println(recs);
        return this.getName() + separator + this.getSurname() + separator + this.getBirthday().getTime() + separator + this.getDistrict().toString() + separator + this.getId() + separator + recs;
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
