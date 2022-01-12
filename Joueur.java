import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;




public class Joueur {
    public static String alphabetI="-ACEGIKMOQSUWY"; // lettres aux positions impaires dans l'alphabet
    public static String alphabetP="-BDFHJLNPRTVXZ"; // lettres aux positions paires
    private String name;
    private String type;
    private int nbpoints;
    private ArrayList<ArrayList<Carte>> deck;
    private boolean armeeLaPlusPuissante;
    private boolean routeLaPlusLongue;
    private static Scanner scanReponse = new Scanner(System.in);
    private String route;//le caractère avec lequel on va représenter les routes du joueur
    private String ville;// idem mais pour les villes
    private String colonie;
    private int nbreChevalierJouer;
    private boolean desLances;
    //couleurs pour l'affichage des routes,colonies... du joueur
    
    public static final String RESET = "\u001B[0m";
    public static final String BLUE = "\033[0;34m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";

    public static final String[] COLORS = {RESET,BLUE,RED,GREEN};







    
    
    public Joueur(){
        name="Anonyme";
        this.type="IA";
        deck=new ArrayList<>();
        ArrayList<Carte> CarteDeveloppement=new ArrayList<Carte>();
        ArrayList<Carte> CarteRessource=new ArrayList<Carte>();
        deck.add(CarteDeveloppement);
        deck.add(CarteRessource);
    }

    public Joueur(String nom){
        name = nom;
        type = "humain";
        deck=new ArrayList<>();
        ArrayList<Carte> CarteDeveloppement=new ArrayList<Carte>();
        ArrayList<Carte> CarteRessource=new ArrayList<Carte>();
        deck.add(CarteDeveloppement);
        deck.add(CarteRessource);
    }


    //---------------Getters et setters-------------------
    public int getNbreChevalierJouer() {
        return nbreChevalierJouer;
    }
    public void setNbreChevalierJouer() {
        nbreChevalierJouer +=1;
    }
    public String getRoute() {
       return route;
    }
    
    public String getColonie() {
        return colonie;
    }

    public String getVille() {
        return ville;
    }

    public String getType() {
        return type;
    }

    public void setDesLances(boolean desLances) {
        this.desLances = desLances;
    }

    public static void initialiseJoueurs(ArrayList<Joueur> joueurs){
        for (int i = 0; i < 3; i++) {
            joueurs.add(new Joueur(demanderNom()));
        }
        for (int i = 0; i < joueurs.size(); i++) {
            joueurs.get(i).setRoute(COLORS[i+1] + "*" + COLORS[0]);
            joueurs.get(i).setColonie(COLORS[i+1] + "C" + COLORS[0]);
            joueurs.get(i).setVille(COLORS[i+1] + "V" + COLORS[0]);
        }
    }

    

    

    public int getNbpoints() {
        return nbpoints;
    }

    public ArrayList<ArrayList<Carte>> getDeck() {
        return deck;
    }

    public String getName() {
        return name;
    }

    public Scanner getScanReponse() {
        return scanReponse;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public void setColonie(String colonie) {
        this.colonie = colonie;
    }

    public void setRouteLaPlusLongue(boolean routeLaPlusLongue) {
        this.routeLaPlusLongue = routeLaPlusLongue;
    }

    public void setArmeeLaPlusPuissante(boolean armeeLaPlusPuissante) {
        this.armeeLaPlusPuissante = armeeLaPlusPuissante;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNbpoints(int nbpoints) {
        this.nbpoints = nbpoints;
    }

    public void ajoutePoints(int points){
        this.nbpoints += points;
    }

    public void setDeck(ArrayList<ArrayList<Carte>> deck) {
        this.deck = deck;
    }

    public boolean getDesLances(){
        return desLances;
    }
    //--------------------


    public void finish(){
        scanReponse.close();
    }
    public static String demanderStr(String q){
        System.out.println(q);
        return scanReponse.next();
    }
    private int demanderInt(String q){
        System.out.println(q);
        return scanReponse.nextInt();
    }
    private char demanderChar(String q){
        System.out.println(q);
        return scanReponse.next().charAt(0);
    }
    public boolean veutJouer(){
        return (demanderStr("Voulez vous jouer? (oui/non)").equalsIgnoreCase("oui"));
    }
    public static String demanderNom(){
        return demanderStr("Donnez votre nom");
    }
    public int demanderDim(){
        return demanderInt("Donner la dimension du plateau");
    }
    public int lancerDe(){
        Random r = new Random(); 
        int i=r.nextInt(6)+1;
        int j=r.nextInt(6)+1;
        return i+j;
    }

    public int[] getDimension(String str){ 
        String hauteur="";
        String largeur="";
        int[] dim = new int[2];
        for (int i = 0; i < str.indexOf("x"); i++) {
            if(Character.isDigit(str.charAt(i))){
                hauteur+=str.charAt(i);
            }
        }
        int midindex = str.toLowerCase().indexOf("x"); // On repère l'index du "x" dans la réponse de l'utilisateur de la forme largeurXhauteur
        for (int i = midindex+1; i < str.length(); i++) {
                largeur+=str.charAt(i);
            }
        dim[0] = Integer.parseInt(hauteur);
        dim[1] = Integer.parseInt(largeur);
        return dim;
        
    }

    public int demanderAction(){
        System.out.println("Veuillez saisir le numéro correspondant à l'action que vous voulez effectuer");
        System.out.println("(1) Lancer les dès");
        System.out.println("(2) Construire une route");
        System.out.println("(3) Construire une ville");
        System.out.println("(4) Construire une Colonie");
        System.out.println("(5) jouer une carte Chevalier");
        System.out.println("(6) acheter une carte developpement");
        System.out.println("(7) Consultez vos ressources");
        System.out.println("(8) faire un échange");
        System.out.println("(9) passer son tour");
        return scanReponse.nextInt();
    }
 
    public int[] demanderCoordonneesBatiment() { // sortie : tableau int[] de la forme : [abscisse du batiment dans batiments[][]] [ordonnee dans batiments[][]]
        String coordonnees = demanderStr("Saisir les coordonnées d'un batiment (Ligne.Colonne. Exemple : AA ou CC)");
        int[] coord = new int[2];
        while(!(Plateau.alphabet.indexOf(coordonnees.charAt(0))%2==1 && Plateau.alphabet.indexOf(coordonnees.charAt(1))%2==1)){
            coordonnees = demanderStr("Veuillez saisir des coordonnees valides pour un batiment. Rappel du format : (Ligne.Colonne : Exemple : AA ou CC)");
        } 
        coord[0] = alphabetI.indexOf(coordonnees.charAt(0)); 
        coord[1] = alphabetI.indexOf(coordonnees.charAt(1)); 
        return coord;
    }


    //renvoie les coordonné d'une route en fonction qu'elle soit verticale ou horizontale
    //sortie :[abs dans routesV ou routesH][ord dans routesV ou routesH][nbr qui indique si c'est routesV ou routesH "ex: 1 pour routesV et 0 pr routesH"]
    public int [] demanderCoordonneesRoute(){
        String coordonnees = demanderStr("Saisir les coordonnées d'une route (Ligne.Colonne : Exemple : AB ou BA ou bien FC )");
        int[] coord = new int[3];
        coord[0] =(Plateau.alphabet.indexOf(coordonnees.charAt(0))%2==0) ? alphabetP.indexOf(coordonnees.charAt(0)) : alphabetI.indexOf(coordonnees.charAt(0)); 
        coord[1] = (Plateau.alphabet.indexOf(coordonnees.charAt(1))%2==0) ? alphabetP.indexOf(coordonnees.charAt(1)) : alphabetI.indexOf(coordonnees.charAt(1));
        coord[2] = (Plateau.alphabet.indexOf(coordonnees.charAt(0))%2==0) ? 0 : 1 ; // si coord[0] est pair, i.e si c'est une route verticale qu'on pose, alors on mets comme code le chiffre 0 (comme 0 modulo 2...). Autrement on mets 1 (route horizontale)
        return coord;
    }
    public int [] demandeCoordonneesVoleur(){
        String coordonnees = demanderStr("Saisir les coordonnées de la tuile où vous voulez placer le voleur (Ligne.Colonne : Exemple : BB ou bien HD )");
        int[] coord = new int[2];
        coord[0] = alphabetP.indexOf(coordonnees.charAt(0)); 
        coord[1]=alphabetP.indexOf(coordonnees.charAt(1));
        return coord;
    }

    public void ajouteCarteRessoure(Carte c){
        deck.get(1).add(c);    
    }
    
    public void ajouteCarteDeve(Carte c){
        deck.get(0).add(c);
    }
    public int nombreMinerai(){
        int n=0;
        for(int i=0;i<deck.get(1).size();i++){
            if(deck.get(1).get(i).getNom().equalsIgnoreCase("minerai")){
                n++;
            }
        }
        return n;
    }
    public int nombreBois(){
        int n=0;
        for(int i=0;i<deck.get(1).size();i++){
            if(deck.get(1).get(i).getNom().equalsIgnoreCase("bois")){
                n++;
            }
        }
        return n;
    }
    public int nombreLaine(){
        int n=0;
        for(int i=0;i<deck.get(1).size();i++){
            if(deck.get(1).get(i).getNom().equalsIgnoreCase("laine")){
                n++;
            }
        }
        return n;
    }
    public int nombreBle(){
        int n=0;
        for(int i=0;i<deck.get(1).size();i++){
            if(deck.get(1).get(i).getNom().equalsIgnoreCase("ble")){
                n++;
            }
        }
        return n;
    }
    public int nombreArgile(){
        int n=0;
        for(int i=0;i<deck.get(1).size();i++){
            if(deck.get(1).get(i).getNom().equalsIgnoreCase("argile")){
                n++;
            }
        }
        return n;
    }

    public boolean coordonneesValides(String coord){ // que ce soit une route ou un bâtiment, les coordonnees doivent être sous la forme suivante : <lettre de l'alphabet><lettre de l'alphabet>
        return Plateau.alphabet.contains(coord.charAt(0)+"") && Plateau.alphabet.contains(coord.charAt(1)+"");
    }

    //permet de savoir le nombre si le joueur a des resource nécessaire pour construire une route
    public boolean peutConstruireRoute(){
        return (nombreBois()>0 && nombreArgile()>0);
    }
    //permet de savoir le nombre si le joueur a des resource nécessaire pour construire une Colonie
    public boolean peutConstruireColonie(){
        return (nombreBois()>0 && nombreArgile()>0 && nombreBle()>0 && nombreLaine()>0);
    }
    //permet de savoir le nombre si le joueur a des ressources nécessaires pour construire une ville

    public boolean peutConstruireVille(){
        return (nombreBle()>1 && nombreMinerai()>2);
    }

    public boolean peutAcheterCarteDeveloppement(){
        return (nombreMinerai()>0 && nombreBle()>0 && nombreLaine()>0);
    }
    public void suprimerCarte(Carte c, int n){
        for(int i=0;i<n;i++){
            deck.get(1).remove(c);
        }   
    }
    public void invention(){
        String [] tab={"bois","argile","laine","ble","minerai"};
        int x = demanderInt("1-bois \n2-argile\n3-laine\n4-ble\5-minerai");
        ajouteCarteRessoure(new Carte(tab[x]));
    }

    public void volercarte(Joueur j){
        Random r=new Random();
        int x=r.nextInt(j.getDeck().get(1).size());
        Carte c=j.getDeck().get(1).get(x);
        j.getDeck().get(1).remove(j.getDeck().get(1).get(x));
        this.ajouteCarteRessoure(c);
    }
    public boolean cartePresent(Carte c){
        for(int i=0;i<deck.get(1).size();i++){
            if(deck.get(1).get(i).getClass().getName().equals(c.getNom())){
                return true;
            }
        }
        return false;
    }
    public void perdrecarte(ArrayList<Joueur> participants){
        for(int i=0;i<participants.size();i++){
            if(participants.get(i).deck.get(1).size()>7){
                int n=(participants.get(i).deck.get(1).size())/2;
                while(n>0){
                    String st=demanderStr("Donnez le nom de la carte que vous voulez laisser au voleur");
                    Carte c=new Carte(st.toLowerCase());
                    if(cartePresent(c)){
                        suprimerCarte(c, 1);
                    }
                    n--;
                }
            }
        }
    }
    public void monopole(Carte c, ArrayList<Joueur> participants){
        for(int i=0;i<participants.size();i++){
            if(!participants.get(i).getName().equals(this.name) && participants.get(i).cartePresent(c)){
                for(int j=0;j<participants.get(i).deck.get(1).size();i++){
                    this.ajouteCarteRessoure(c);
                    participants.get(i).suprimerCarte(c, 1);
                }
            }
        }
    }
    
    public void volercarte(Plateau plato, int []gre){
        String st=demanderStr("Donner le nom du jouer que vous voulez volez");
        if(plato.joueurPresent(st)){
            int z=1;
            while(z>0){// cette boucle me permet de m'assurer que le joueur a voler une cart
                for (int k = gre[0]-1; k <= gre[0]; k++) { 
                    for (int l = gre[1] - 1; l <= gre[1]; l++) {
                       if(plato.getBatiments()[k][l].getProprietaire().getName().equalsIgnoreCase(st)) {
                            volercarte(plato.getBatiments()[k][l].getProprietaire());
                            z--;
                        }
                    }
                }
            }
        }else{
            System.out.println("désoler ce joueur n'est pas présent sur le plateau ");
        }   
    }
    public void afficheRessoure(){
        System.out.println("*******************************************");
        System.out.println("Voici les ressources dont vous disposez");
        System.out.println(" Bois     :"+nombreBois());
        System.out.println(" Argile   :"+nombreArgile());
        System.out.println(" Laine    :"+nombreLaine());
        System.out.println(" Ble      :"+nombreBle());
        System.out.println(" Minerai  :"+nombreMinerai());
    }
   
    public int nombrechevalier(){
        int n=0;
        for(int i=0;i<deck.get(0).size();i++){
            if(deck.get(1).get(i).getNom().equalsIgnoreCase("chevalier")){
                n++;
            }
        }
        return n;
    }
    //Verifier qu'aucun joueur ne dispose de l'étiquette Armée la plus puissante
    public boolean ArmePPuissante(ArrayList<Joueur> tab ){
        for( int i=0;i<tab.size();i++){
            if(tab.get(i).armeeLaPlusPuissante){
                return true;
            }
        }
        return false;
    }
    //affecte l'étiquette de l'armé la plus puissante au premier joueur qui a 3 carte chevalier
    public void armeeLaplusPuissante(ArrayList<Joueur> participants ){
        if(!ArmePPuissante(participants)){
            for( int i=0;i<participants.size();i++){
                if(participants.get(i).nombrechevalier()>=3){
                    participants.get(i).armeeLaPlusPuissante=true;
                    nbpoints+=2;
                    return ;
                }
            }
        }
    }
}
