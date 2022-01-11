import java.util.Random;
import java.util.Scanner;

public class Jeu {
    
    private Joueur joueur;
    private Plateau plato;
    
    
    public Jeu(Joueur j, Plateau p){
        joueur=j;
        plato=p;
    } 

    
    public void jeu(){
        Joueur[] participants=plato.getJoueurs();
        while(joueur.veutJouer()){
            int x= joueur.demanderAction();
            switch(x){
                case 1:
                    int y=joueur.lancerDe();
                    System.out.println("Vous avez obtenu :"+y);
                    if(y!=7){
                        System.out.println( "Les joueur disposant de Batiment à proximité vont ");
                    plato.getRoussource(y);
                    }else{
                        System.out.println("vous allez deplacez le volleur");
                        int []tab=joueur.demadeCordoneesVoleur();
                        while(plato.getTuiles()[tab[0]][tab[1]].getVoleurPresent()){
                            System.out.println("impossible :le voleur est déja present sur cette tuille \n ");
                            tab=joueur.demadeCordoneesVoleur();
                        }
                        plato.getTuiles()[tab[0]][tab[1]].setVoleurPresent(true);
                        System.out.println("Tous les joueur qui ont plus de cette carte vont perdre la moitié de leur carte");
                        joueur.perdrecarte(participants);//tous les joueur qui on plus de 7 carte vont perdre la motié de leur carte   
                        System.out.println("Vous allez volez une carte à l'un des joueur que vous venez d'embeter par le voleur"); 
                        joueur.volercarte(plato, tab);//voler une carte d'un joueur proche de la tuile ou tu vient de placer le volleur
                    }
                break;
                case 2:
                    if(joueur.peutConstruireRoute()){   // verifie si le joueur a les ressource nécéssaire pour construire une route
                        System.out.println("Vous avez les ressource nécessaire pour construire une route");
                        boolean a =false;
                        while(!a){
                            int [] tab=joueur.demanderCordonneesRoute(); // on démande les coordonné de la route
                            if(plato.peutConstruireRoute(tab[0], tab[1], tab[2], joueur)){ //on vérifier s'il es possible de construire une route au coordonné donné
                                plato.ajouteRoute(tab[0], tab[1], tab[3],joueur); //construction de la route
                                joueur.suprimerCarte(new Carte("bois"),1);   //supression des ressource utilisé 
                                joueur.suprimerCarte(new Carte("argile"),1);   //pour la construction d'une   
                                a=true;   //pour m'assurez que le travaille a bien été fait
                            }else{
                             System.out.println("Vous ne pouvez pas construire une route au coordonné indiqué");
                            }
                        }
                       
                    }else{
                        System.out.println("Déolé! vous n'avez pas les ressource pour construire une route une Route au coordonné indiqué ");
                    }
                break;
                case 3:
                    if(joueur.peutConstruireVille()){// verifie si l'utilisateur à les ressource pour construire une ville
                        System.out.println("Vous avez les ressource nécessaire pour construire une Ville");
                        boolean b=false;
                        while(!b){
                            int []tab=joueur.demanderCoordonneesBatiment();//reçoit les coordonné
                            if(plato.peutConstruireVille(tab[0], tab[1], joueur)){//vérifie s'il est possible de construire une ville
                                plato.ajouteVille(tab[0], tab[1], joueur);// construcion de la ville
                                joueur.suprimerCarte(new Carte("ble"),2);// supression des ressource utiliser 
                                joueur.suprimerCarte(new Carte("minerai"),3);//par le  joueur pour la construction
                                b=true;
                            }else{
                                System.out.println("Vous ne pouvez pas construire une ville au coordonné indiqué");
                            }
                        } 
                    }else{
                        System.out.println("Impossible, vous n'avez pas assez de ressource pour construire une ville");
                    }
                break;
                case 4:
                    if(joueur.peutConstruireColonie()){ //verifie que l'utilisateur à les ressource nécessaire pour la construction d'une colonie
                        System.out.println("Vous avez les ressource nécessaire pour construire une Colonie");
                        boolean c=false;
                        while(!c){
                            int []tab=joueur.demanderCoordonneesBatiment();//demande les coordonné de la colonie
                            if(plato.peutConstruireColonie(tab[0], tab[1], joueur)){ //verifie qu'on peut placer une colonie au coordonne indiqué
                                plato.ajouteColonie(tab[0], tab[1], joueur);//place la colonie
                                joueur.suprimerCarte(new Carte("bois"), 1); //suprime les ressource nutilisé
                                joueur.suprimerCarte(new Carte("argile"), 1);//pour la construction
                                joueur.suprimerCarte(new Carte("ble"), 1);
                                joueur.suprimerCarte(new Carte("laine"), 1);
                                c=true;
                            }else{
                                System.out.println("Vous ne pouvez pas construire une colonie au coordonné indiqué");
                        }    
                        }
                        
                    }else{
                        System.out.println("Impossible,vous n'avez pas assez de ressource pour de construire une ville");
                    }
                break;
                case 5: //jouer la carte chevalier;
                    if(joueur.getNbreChevalierJouer()<joueur.nombrechevalier()){
                        System.out.println("Vous avez joué une carte chevalier vous allez déplacez le voleur");
                        //deplacer le voleur 
                        int []gre=joueur.demadeCordoneesVoleur();
                        while(plato.getTuiles()[gre[0]][gre[1]].getVoleurPresent()){
                            System.out.println("impossible :le voleur est déja present sur cette tuille \n ");
                            gre=joueur.demadeCordoneesVoleur();
                        }
                        plato.getTuiles()[gre[0]][gre[1]].setVoleurPresent(true);
                        //voler la carte d'un joueur
                        System.out.println("Vous allez volez une carte à l'un des joueur que vous venez d'embeter par le voleur"); 
                        joueur.volercarte(plato, gre);   
                        joueur.setNbreChevalierJouer(); 
                    }else{
                        System.out.println("Désolé vous avez jouez tous vos carte chevalier");
                    }
                break;
                case 6:// acheter une carte devellopement
                        if(joueur.peutAcheterCarteDevelloppement()){
                            System.out.println("Vous avez les ressource nécessaire pour acheter une carte devellopement");
                            String [] dev={"chevalier","Construire 2 route","invention","Monopole","bibiotheque","place du marché","parlement","université"};
                            Random r=new Random();
                            int a=r.nextInt(dev.length);
                            if(a==0){
                                System.out.println("Bravo vous venez de gagné une carte chevalier");
                                joueur.ajouteCarteDeve(new Carte("chevalier"));
                            }else if(a==1){
                                int b=2;
                                while(b>0){
                                    System.out.println("Bravo vous venez de gagné une carte progée qui vous donne la possibilité de construire deux route gratuitement\n");
                                    int [] tab=joueur.demanderCordonneesRoute(); // on démande les coordonné de la route
                                    if(plato.peutConstruireRoute(tab[0], tab[1], tab[2], joueur)){ //on vérifier s'il es possible de construire une route au coordonné donné
                                        plato.ajouteRoute(tab[0], tab[1], tab[3],joueur); 
                                        b--;
                                    }
                                }
                            }else if(a==2){
                                System.out.println("Bravo! vous avez gagné une carte invention;vous avez la possibilité de choisir deux ressource de votre choix qui seront ajouté aux votre\n");
                               int c=2;
                                while(c>0){
                                   joueur.invention();
                                  c--;
                                }
                            }else if(a==3){
                                System.out.println("Bravo vous venez de gagné le droit de monopoliser une ressource \n");
                                String carte=joueur.demanderStr("Donner le nom de la ressource dont vous vooulez avoir le monopole");
                                joueur.monopole(new Carte(carte.toLowerCase()), participants);
                            }else{
                                System.out.println("bravo vous venz de gané une carte devellopemment qui vous rapporte un point de victoire");
                                joueur.ajouteCarteDeve(new CarteDeDeveloppement(dev[a], 1));
                                joueur.setNbpoints(joueur.getNbpoints()+1);
                            }
                        }else{
                            System.out.println("Desolé vous n'avez pas assez de ressource pour acheter une carte développement");
                        }
                break;
                case 7: 
                    joueur.afficheRessoure();
                break;
                // case 8: //faire un echange 
                // break;
            }
            joueur.ArmeLaplusPuissante(participants);
            plato.afficheTabR();
        }     
       
    }
   
}