package sample;

public enum Districts {
    VAH("Вахитовский"),
    AVI("Авиастроительный"),
    KIROV("Кировский"),
    MSK("Московский"),
    NOVSAV("Ново-Савиноский"),
    SOVIET("Советский"),
    PRIV("Приволжский");


    private final String name;
    Districts(String _name) {
        this.name = _name;
    }

    public static Districts fromString(String _name){
        for(Districts d: Districts.values()){
            if(d.name.equals(_name))return d;
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
