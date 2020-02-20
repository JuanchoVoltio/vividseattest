package vividseat.hlgo.model;

public class Person {
    private String name;
    private Boolean aCelebrity;
    private Person knownCelebrity;

    public Person(String name, Boolean isACelebrity){
        this.name = name;
        this.aCelebrity = isACelebrity;
    }

    public Person(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isACelebrity() {
        return aCelebrity;
    }

    public void setaCelebrity(Boolean aCelebrity) {
        this.aCelebrity = aCelebrity;
    }

    public Person getKnownCelebrity() {
        return knownCelebrity;
    }

    public void setKnownCelebrity(Person knownCelebrity) {
        this.knownCelebrity = knownCelebrity;
    }
}
