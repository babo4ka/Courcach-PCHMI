package Birzha.part2;

import java.util.ArrayList;
import java.util.List;

public class Security {

    private String name;
    public void setName(String name){this.name = name;}
    public String getName(){return this.name;}

    private Double price;
    public void setPrice(Double price){this.price = price;}
    public Double getPrice(){return this.price;}

    public Security(String name, Double EPrice){
        if(!checkSecurities(name)){
            this.name = name;
            this.price = EPrice;
            Security.securities.add(this);
            System.out.println("создана бумага " + name);
        }else{
            System.out.println("Такая бумага уже есть");
        }

    }

    public static List<Security> securities = new ArrayList<>();
    public boolean checkSecurities(String name){
        for(Security s : Security.securities){
            if(name.equals(s.getName())){
                return true;
            }
        }
        return false;
    }
}
