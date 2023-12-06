package models;

public class User extends Model{
    private String nim;

    public User(String nama, Integer id, String nim){
        super(nama,id);
        this.nim = nim;
    }

    public void setNim(String nim){
        this.nim = nim;
    }

    public String getNim(){
        return nim;
    }
}
