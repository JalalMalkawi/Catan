import java.util.ArrayList;
import java.util.Scanner;

public class Joueur {
    private String name;
    private String type;
    private int nbpoints;
    private ArrayList<Carte> deck;
    private boolean armeeLaPlusPuissante;
    private boolean routeLaPlusLongue;
    private Scanner scanReponse;
    private char route;//le caractére avec le quelle on va representer ca route
    private char batiments;//le caractére avec le quelle on va répresenter ses batiment;

    public Joueur(ArrayList<Carte> deck){
        name="Anonyme";
        this.type="IA";
        this.deck=deck;
        armeeLaPlusPuissante=false;
        routeLaPlusLongue=false;
        scanReponse=new Scanner(System.in);
    }
    public char getRoute() {
       return route;
    }
    public char getBatiments() {
       return batiments;
    }
    public int getNbpoints() {
        return nbpoints;
    }
    public ArrayList<Carte> getDeck() {
        return deck;
    }
    public String getName() {
        return name;
    }public Scanner getScanReponse() {
        return scanReponse;
    }
    public void setNom(String nom){
        this.name=nom;
    }
    public void finish(){
        scanReponse.close();
    }
    private String demanderStr(String q){
        System.out.println(q);
        return System.console().readLine();
    }
    private int demanderInt(String q){
        System.out.println(q);
        return scanReponse.nextInt();
    }
    public boolean veutJouer(){
        if(demanderStr("Voulez vous jouer oui/non").equals("oui")){
            return true;
        }else{
            return false;
        }
    }
    public String demanderNom(){
        return demanderStr("Donnez votre nom");
    }


}
