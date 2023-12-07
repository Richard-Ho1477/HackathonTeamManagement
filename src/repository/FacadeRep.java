package repository;

public class FacadeRep {
    private TeamRepository teamRep;
    private UserRepository userRep;
    public FacadeRep(){
        this.teamRep = new TeamRepository();
        this.userRep = new UserRepository();
    }
}
