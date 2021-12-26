import java.io.ObjectInputStream.GetField;

public class Tuile {
    private int numero;
    private int abscisse;
    private int ordonnee;
    private String nomTerrain;
    private boolean voleurPresent;
    private Carte ressource;
    //CONSTRUCTEUR
    public Tuile(int num, int ab,int ord,String nom,boolean p){
        numero=num;
        abscisse=ab;
        ordonnee=ord;
        nomTerrain=nom;
        voleurPresent=p;
    }
    public void setAbscisse(int abscisse) {
        this.abscisse = abscisse;
    }
    public void setNomTerrain(String nomTerrain) {
        this.nomTerrain = nomTerrain;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public void setOrdonnee(int ordonnee) {
        this.ordonnee = ordonnee;
    }public void setVoleurPresent(boolean voleurPresent) {
        this.voleurPresent = voleurPresent;
    }
    public int getAbscisse() {
        return abscisse;
    }
    public String getNomTerrain() {
        return nomTerrain;
    }
    public int getNumero() {
        return numero;
    }
    public int getOrdonnee() {
        return ordonnee;
    }
    public boolean getVoleurPresent(){
        return voleurPresent;
    }
    public void AfficheTuille(){
        String St=nomTerrain+" "+numero;
        while(St.length()<12){
            St+=" ";
        }
        System.out.print(St);
    }
    public void poseVoleur(){
        if(voleurPresent){
            System.out.println("le voleur est déja sur cette tuille");
        }else{
            voleurPresent=true;
        }
    }
    
    public Carte getRessourceD() {
        return ressource;
    } 
    public Carte getRessource(){
        String a="";
        if(nomTerrain.equalsIgnoreCase("Foret")){
            a="bois";
        }else if(nomTerrain.equalsIgnoreCase("Coline")){
            a="argile";
        }else if(nomTerrain.equalsIgnoreCase("pres")){
            a="laine";
        }else if(nomTerrain.equalsIgnoreCase("Champs")){
            a="ble";
        }else if(nomTerrain.equalsIgnoreCase("montagnes")){
            a="minerai";
        }
        return new Carte(a);
    }    
}
