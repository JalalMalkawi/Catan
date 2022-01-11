import java.util.Random;
import java.util.Scanner;

public class Jeu {
    //private Joueur[] participants;
    //private Plateau plato;

    //public Jeu(Joueur[] j, Plateau p){
    //    this.participants = j;
    //    this.plato = p;
    //}
    private Joueur joueur;
    private Plateau plato;
    int nbredeChevalierjoué;
    
    public Jeu(Joueur j, Plateau p){
        joueur=j;
        plato=p;
        nbredeChevalierjoué=0;
    } 

    public void tour(){
        while(joueur.veutJouer()){
            int action= joueur.demanderAction();
            switch(action){
                case 1:
                    int y=joueur.lancerDe();
                    System.out.println("Vous avez obtenu aux dés le nombre :"+y);
                    if(y!=7){
                        System.out.println("Distribution des ressources...");
                        plato.distribueRessources(y);
                    }else{
                        int[] coordonnesVoleur  = joueur.demandeCoordonneesVoleur();
                        int absVoleur = coordonnesVoleur[0];
                        int ordVoleur = coordonnesVoleur[1];
                        Tuile voleur = plato.getTuiles()[absVoleur][ordVoleur];
                        while( voleur.getVoleurPresent() || plato.tuileHorsLimite(absVoleur, ordVoleur) ){
                            System.out.println("Veuillez saisir des coordonnées valides : des coordonnees dans les limites du plateau, d'une tuile sans voleur. \n ");
                            coordonnesVoleur = joueur.demandeCoordonneesVoleur();
                        }
                        voleur.setVoleurPresent(true);
                        //tu dois coder la méthode pour voler une carte d'un joueur proche de la tuile où tu vient de placer le voleur
                        //une méthode qui ressemble à : ( exemple ) joueur.voleRessources(Tuile t)
                    }   
                break;
                case 2:
                    if(joueur.peutConstruireRoute()){   // verifie si le joueur a les ressource nécéssaire pour construire une route
                       int [] coordonneesRoute =joueur.demanderCordonneesRoute(); // on demande les coordonné de la route
                       if(plato.peutConstruireRoute(coordonneesRoute[0], coordonneesRoute[1], coordonneesRoute[2], joueur)){ //on vérifier s'il es possible de construire une route au coordonné donné
                               plato.ajouteRoute(coordonneesRoute [0], coordonneesRoute[1], coordonneesRoute[3],joueur); //construction de la route
                               joueur.suprimerCarte(new Carte("bois"),1);   //supression des ressource utilisé 
                               joueur.suprimerCarte(new Carte("argile"),1);   //pour la construction d'une      
                       }
                       else{
                        System.out.println("Vous ne pouvez pas construire une route aux coordonnées indiquées");
                       }
                    }else{
                        System.out.println("Désolé! Vous n'avez pas les ressources pour construire une route aux coordonnées indiquées");
                    }
                break;
                case 3:
                    if(joueur.peutConstruireVille()){// verifie si l'utilisateur a les ressources pour construire une ville
                        int []coordonneesVille =joueur.demanderCoordonneesBatiment();
                        if(plato.peutConstruireVille(coordonneesVille[0], coordonneesVille[1], joueur)){
                            plato.ajouteVille(coordonneesVille[0], coordonneesVille[1], joueur);
                            joueur.suprimerCarte(new Carte("ble"),2);// suppression des ressource utiliser 
                            joueur.suprimerCarte(new Carte("minerai"),3);//par le  joueur pour la construction
                            joueur.ajoutePoints(2);//incrementer le nmbr de point de victoire
                        }else{
                            System.out.println("Vous ne pouvez pas construire une ville aux coordonnées indiqué");
                        }
                    }else{
                        System.out.println("Impossible, vous n'avez pas assez de ressources pour construire une ville");
                    }
                break;
                case 4:
                    if(joueur.peutConstruireColonie()){ //vérifie que l'utilisateur a les ressources nécessaires pour la construction d'une colonie
                        int []coordonneesColonie = joueur.demanderCoordonneesBatiment();
                        if(plato.peutConstruireColonie(coordonneesColonie[0], coordonneesColonie[1], joueur)){ //On vérifie qu'on peut placer une colonie au coordonnées indiquées ...
                            plato.ajouteColonie(coordonneesColonie[0], coordonneesColonie[1], joueur);//...place la colonie,
                            joueur.suprimerCarte(new Carte("bois"), 1); //Et on supprime les ressources utilisées pour la construction,...
                            joueur.suprimerCarte(new Carte("argile"), 1);
                            joueur.suprimerCarte(new Carte("ble"), 1);
                            joueur.suprimerCarte(new Carte("laine"), 1);
                            joueur.setNbpoints(joueur.getNbpoints()+1);//...pour à la fin incrémenter le nombre de points de victoire du joueur.
                        }else{
                            System.out.println("Vous ne pouvez pas construire une colonie au coordonné indiqué");
                        }
                    }else{
                        System.out.println("Impossible,vous n'avez pas assez de ressource pour de construire une ville");
                    }
                break;
                case 5: //jouer la carte chevalier;
                    //deplacer le voleur 
                    int []gre=joueur.demandeCoordonneesVoleur();
                    while(plato.getTuiles()[gre[0]][gre[1]].getVoleurPresent()){
                        System.out.println("impossible :le voleur est déja present sur cette tuille \n ");
                        gre=joueur.demandeCoordonneesVoleur();
                    }
                    plato.getTuiles()[gre[0]][gre[1]].setVoleurPresent(true);
                    nbredeChevalierjoué++; 
                    //voler la carte d'un joueur
                break;
                case 6:// acheter une carte développement
                        if(joueur.peutAcheterCarteDeveloppement()){
                            String [] dev={"chevalier","Construire 2 route","invention","Monopole","bibiotheque","place du marché","parlement","université"};
                            Random r=new Random();
                            int a=r.nextInt(dev.length);
                            if(a==0){
                                joueur.ajouteCarteDeve(new CarteDeDeveloppement("chevalier",2));
                            }else if(a==1){
                                int b=2;
                                while(b>0){
                                    int [] coordonnesVoleur =joueur.demanderCordonneesRoute(); // on démande les coordonné de la route
                                    if(plato.peutConstruireRoute(coordonnesVoleur [0], coordonnesVoleur [1], coordonnesVoleur [2], joueur)){ //on vérifier s'il es possible de construire une route au coordonné donné
                                        plato.ajouteRoute(coordonnesVoleur [0], coordonnesVoleur [1], coordonnesVoleur [3],joueur); 
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

                            }
                            else{
                                joueur.ajouteCarteDeve(new CarteDeDeveloppement(dev[a], 1));
                                joueur.setNbpoints(joueur.getNbpoints()+1);
                            }
                        }else{
                            System.out.println("Désolé vous n'avez pas assez de ressources pour acheter une carte développement");
                        }
                break;
                case 7: //faire un échange 
                break;

            }
        }
      

    }
   
}