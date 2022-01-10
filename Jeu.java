import java.util.Random;
import java.util.Scanner;

public class Jeu {
    // private Joueur[] participants;
    // private Plateau plato;

    // public Jeu(Joueur[] j, Plateau p) {
    //     this.participants = j;
    //     this.plato = p;
    // }
    private Joueur joueur;
    private Plateau plato;
    int nbredeChevalierjoué;
    
    public Jeu(Joueur j, Plateau p){
        joueur=j;
        plato=p;
        nbredeChevalierjoué=0;
    } 

    public void jeu(){
        System.out.println("(1) Lancer les dès");
        System.out.println("(2) Construire une route");
        System.out.println("(3) Construire une ville");
        System.out.println("(4) Construire une Colonie");
        System.out.println("(5) jouer une carte Chevalier");
        System.out.println("(6) acheter une carte developpement");
        System.out.println("(7) faire un échange");
        while(joueur.veutJouer()){
            int x= joueur.demanderAction();
            switch(x){
                case 1:
                    int y=joueur.LancerDe();
                    System.out.println(y);
                    if(y!=7){
                    plato.getRoussource(y);
                    }else{
                        int []tab=joueur.demadeCordoneesVoleur();
                        while(plato.getTuiles()[tab[0]][tab[1]].getVoleurPresent()){
                            System.out.println("impossible :le voleur est déja present sur cette tuille \n ");
                            tab=joueur.demadeCordoneesVoleur();
                        }
                        plato.getTuiles()[tab[0]][tab[1]].setVoleurPresent(true);
                        //tu dois coder la methode pour voler une carte d'un joueur proche de la tuile ou tu vient de placer le volleur

                    }
                break;
                case 2:
                    if(joueur.peutConstruireRoute()){   // verifie si le joueur a les ressource nécéssaire pour construire une route
                       int [] tab=joueur.demanderCordonneesRoute(); // on démande les coordonné de la route
                       if(plato.peutConstruireRoute(tab[0], tab[1], joueur)){ //on vérifier s'il es possible de construire une route au coordonné donné
                               plato.ajouteRoute(tab[0], tab[1], tab[3],joueur); //construction de la route
                               joueur.suprimerCarte(new Carte("bois"),1);   //supression des ressource utilisé 
                               joueur.suprimerCarte(new Carte("argile"),1);   //pour la construction d'une      
                       }else{
                        System.out.println("Vous ne pouvez pas construire une route au coordonné indiqué");
                       }
                    }else{
                        System.out.println("Déolé! vous n'avez pas les ressource pour construire une route une Route au coordonné indiqué ");
                    }
                break;
                case 3:
                    if(joueur.peutConstruireVille()){// verifie si l'utilisateur à les ressource pour construire une ville
                        int []tab=joueur.demanderCoordonneesBatiment();//reçoit les coordonné
                        if(plato.peutConstruireVille(tab[0], tab[1], joueur)){//vérifie s'il est possible de construire une ville
                            plato.ajouteVille(tab[0], tab[1], joueur);// construcion de la ville
                            joueur.suprimerCarte(new Carte("ble"),2);// supression des ressource utiliser 
                            joueur.suprimerCarte(new Carte("minerai"),3);//par le  joueur pour la construction
                           joueur.setNbpoints(joueur.getNbpoints()+1);//incrementer le nmbr de point de victoire
                        }else{
                            System.out.println("Vous ne pouvez pas construire une ville au coordonné indiqué");
                        }
                    }else{
                        System.out.println("Impossible, vous n'avez pas assez de ressource pour construire une ville");
                    }
                break;
                case 4:
                    if(joueur.peutConstruireColonie()){ //verifie que l'utilisateur à les ressource nécessaire pour la construction d'une colonie
                        int []tab=joueur.demanderCoordonneesBatiment();//demande les coordonné de la colonie
                        if(plato.peutConstruireColonie(tab[0], tab[1], joueur)){ //verifie qu'on peut placer une colonie au coordonne indiqué
                            plato.ajouteColonie(tab[0], tab[1], joueur);//place la colonie
                            joueur.suprimerCarte(new Carte("bois"), 1); //suprime les ressource nutilisé
                            joueur.suprimerCarte(new Carte("argile"), 1);//pour la construction
                            joueur.suprimerCarte(new Carte("ble"), 1);
                            joueur.suprimerCarte(new Carte("laine"), 1);
                            joueur.setNbpoints(joueur.getNbpoints()+1);//incrementer le nmbr de point de victoire
                        }else{
                            System.out.println("Vous ne pouvez pas construire une colonie au coordonné indiqué");
                        }
                    }else{
                        System.out.println("Impossible,vous n'avez pas assez de ressource pour de construire une ville");
                    }
                break;
                case 5: //jouer la carte chevalier;
                    //deplacer le voleur 
                    int []gre=joueur.demadeCordoneesVoleur();
                        while(plato.getTuiles()[gre[0]][gre[1]].getVoleurPresent()){
                            System.out.println("impossible :le voleur est déja present sur cette tuille \n ");
                            gre=joueur.demadeCordoneesVoleur();
                        }
                        plato.getTuiles()[gre[0]][gre[1]].setVoleurPresent(true);
                    nbredeChevalierjoué++; 
                    //voler la carte d'un joueur
                    

                break;
                case 6:// acheter une carte devellopement
                        if(joueur.peutAcheterCarteDevelloppement()){
                            String [] dev={"chevalier","Construire 2 route","invention","Monopole","bibiotheque","place du marché","parlement","université"};
                            Random r=new Random();
                            int a=r.nextInt(dev.length);
                            if(a==0){
                                joueur.ajouteCarteDeve(new Carte("chevalier"));
                            }else if(a==1){
                                int b=2;
                                while(b>0){
                                    int [] tab=joueur.demanderCordonneesRoute(); // on démande les coordonné de la route
                                    if(plato.peutConstruireRoute(tab[0], tab[1], joueur)){ //on vérifier s'il es possible de construire une route au coordonné donné
                                        plato.ajouteRoute(tab[0], tab[1], tab[3],joueur); 
                                        b--;
                                    }
                                }
                            }else if(a==2){
                               int c=2;
                                while(c>0){
                                   joueur.invention();
                                  c--;
                                }
                            }else if(a==3){

                            }else{
                                joueur.ajouteCarteDeve(new CarteDeDeveloppement(dev[a], 1));
                                joueur.setNbpoints(joueur.getNbpoints()+1);
                            }
                        }else{
                            System.out.println("Desolé vous n'avez pas assez de ressource pour acheter une carte develloppement");
                        }
                break;
                case 7: //faire un echange 
                break;

            }
        }
      

    }
   
}