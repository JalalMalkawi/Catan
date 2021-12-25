import java.io.ObjectInputStream.GetField;

public class Tuile {
    private int numero;
    private int abscisse;
    private int ordonnee;
    private String nomTerrain;
    private boolean voleurPresent;

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
    public void Affiche(){
        String St=nomTerrain+" "+numero;
        while(St.length()<12){
            St+=" ";
        }
        System.out.print(St);
    }
    
    
}
