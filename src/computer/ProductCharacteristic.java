package computer;

public class ProductCharacteristic {

    public ProductCharacteristic(String name){
        this.name = name;
    }

    private String name;
    public void setName(String name){this.name = name;}
    public String getName(){return this.name;}

    private String value;
    public void setValue(String value){this.value = value;}
    public String getValue(){return this.value;}
}
