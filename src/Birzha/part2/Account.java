package Birzha.part2;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private String ID;
    public String getID(){return this.ID;}


    public Account(){
        this.ID = "123334";

        for(int i =0; i<5; i++){
            createWallet(15000.0 * (i+2));
        }
    }


    private List<Wallet> wallets = new ArrayList<>();
    public List<Wallet> getWallets(){return this.wallets;}
    public void createWallet(Double money){
        if(money>0.0){
            this.wallets.add(new Wallet(this, money));
        }else{
            this.wallets.add(new Wallet(this));
        }
    }
    public Wallet getWalletByID(String ID){
        for(Wallet w: wallets){
            if(w.getID().equals(ID)){
                return w;
            }
        }
        System.out.println("Такого кошелька нет");
        return null;
    }

    private List<Security> securities = new ArrayList<>();
    public List<Security> getSecurities(){return this.securities;}

    public void addSecurity(Security security){
        this.securities.add(security);
    }
    public void sellSecutity(Security security){
        this.securities.remove(security);
    }
}
