package vividseat.hlgo.model;

import org.json.JSONObject;

import javax.validation.constraints.NotEmpty;

public class Store {

    private String id;
    @NotEmpty(groups = Existing.class, message = "Name required.")
    private String name;
    @NotEmpty(groups = Existing.class, message = "NIT required.")
    private String nit;
    @NotEmpty(groups = Existing.class, message = "email required.")
    private String mail;

    public Store(){

    }

    public Store(String id, String name, String nit, String mail){
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.nit = nit;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o){
        return (o instanceof Store
                 && ((Store) o).id.equals(id)
                 && ((Store) o).mail.equals(mail)
                 && ((Store) o).name.equals(name)
                 && ((Store) o).nit.equals(nit));
    }

    public JSONObject toJson(){
        JSONObject json = new JSONObject();

        json.put("id", this.id);
        json.put("name", this.name);
        json.put("nit", this.nit);
        json.put("mail", this.mail);

        return json;
    }

    public interface Existing {
    }
}
