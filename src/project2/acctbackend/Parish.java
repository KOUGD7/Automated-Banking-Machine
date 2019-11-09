package src.project2.acctbackend;

public enum Parish {
    KingstonStAndrew("Kingston and St. Andrew"),
    StThomas("St. Thomas"),
    Portland("Portland"),
    StMary("St. Mary"),
    StAnn("St. Ann"),
    Trelawny("Trelawny"),
    StJames("St. James"),
    Hanover("Hanover"),
    Westmoreland("Westmoreland"),
    StElizabeth("St. Elizabeth"),
    Manchester("Manchester"),
    Clarendon("Clarendon"),
    StCatherine("St. Catherine");

    private final String fullname;
    
    Parish(String name){
        this.fullname = name;
    }
    
    String fullName(){
        return this.fullname;
    }
    public static Parish findByName(String name){
        for (Parish pq: Parish.values()){
            if (name.equalsIgnoreCase(pq.fullName())){
                return pq;
            }
        }
        return Parish.KingstonStAndrew;
    }
}