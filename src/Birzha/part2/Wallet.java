package Birzha.part2;

import java.util.Random;

public class Wallet {

    private String ID;
    public String getID(){return this.ID;}

    private Account owner;
    public Account getOwner(){return  this.owner;}

    private Double money;
    public Double getMoney(){return this.money;}
    public void addMoney(Double money){this.money += money;}
    public void spendMoney(Double money){this.money -= money;}

    public Wallet(Account owner){
        this.owner = owner;
        this.money = 0.0;
        createID();
    }

    public Wallet(Account owner, Double money){
        this.owner = owner;
        this.money = money;
        createID();
    }


    private void createID(){
        String ID = "";
        Random rand = new Random();
        for(int i=0; i<5; i++){
            ID = String.join("", ID, Integer.toString(rand.nextInt(10)));
        }
        System.out.println(ID);
        this.ID = ID;
    }
}
