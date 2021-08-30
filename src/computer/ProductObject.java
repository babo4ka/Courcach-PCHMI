package computer;

import java.util.List;

public class ProductObject {

    public ProductObject(String name, List<ProductCharacteristic> characteristics){
        this.name = name;
        this.characteristics = characteristics;
    }

    public String toString(){
        return this.name + this.characteristics.size();
    }

    private String name;
    public void setName(String name){this.name = name;}
    public String getName(){return this.name;}

    private String description;
    public void setDescription(String desc){this.description = desc;}
    public String getDescription(){return this.description;}

    private List<ProductCharacteristic> characteristics;
    public void addCharacteristic(ProductCharacteristic сharacteristic){
        this.characteristics.add(сharacteristic);
    }
    public void removeCharacteristic(ProductCharacteristic сharacteristic){
        this.characteristics.remove(сharacteristic);
    }

    private Double price;
    public void setPrice(Double price){this.price = price;}
    public Double getPrice(){return this.price;}


}
