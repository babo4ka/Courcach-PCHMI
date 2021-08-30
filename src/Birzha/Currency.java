package Birzha;

public class Currency {

    public Currency(String name){
        this.currencyName = name;
    }

    private String currencyName;
    public void setCurrencyName(String name){this.currencyName = name;}
    public String getCurrencyName(){return this.currencyName;}

    private Double price;
    public void setPrice(Double price){this.price = price;}
    public Double getPrice(){return this.price;}

    private Double[] lastMonthRate = new Double[30];
    public void setLastMonthRate(Double[] rate){
        if(rate.length== 30){
            int iterator = 0;
            for(Double d:rate){
                this.lastMonthRate[iterator] = d;
                iterator++;
            }
        }else{
            System.out.println("В рейтинге должно быть 30 значений");
        }
    }

}
