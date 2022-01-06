
public class Tuile {
    private int numero;
    private int abscisse;
    private int ordonnee;
    private String nomTerrain;
    private boolean voleurPresent;
    //CONSTRUCTEURS
    public Tuile(int num, int x,int y,String nomTerrain){
        numero=num;
        abscisse=x;
        ordonnee=y;
        this.nomTerrain=nomTerrain;
    }

    public Tuile(int x, int y){
        abscisse = x;
        ordonnee = y;
        nomTerrain = "";
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
    }
    public void setVoleurPresent(boolean voleurPresent) {
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
    @Override
    public String toString(){
        String st=" "+nomTerrain+" "+numero;
        while(st.length()<12){
            st+=" ";
        }
        return st;
    }
    public void poseVoleur(){
        if(voleurPresent){
            System.out.println("le voleur est déja sur cette tuille");
        }else{
            voleurPresent=true;
        }
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
