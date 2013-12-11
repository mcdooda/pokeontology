package pokeontology;

import java.util.Scanner;

/**
 *
 * @author dodelien
 */
public class Pokeontology {


    public static void main(String[] args) {
        PokemonModel pm = new PokemonModel("pokemon_inferred.owl");
        boolean quitter = false;
        Scanner sc = new Scanner(System.in);
        while (!quitter) {
            System.out.println("Choisir une action :");
            System.out.println("1. Les pokémons");
            System.out.println("2. Les pokémons légendaires");
            System.out.println("3. Les pokémons de base (non évolués)");
            System.out.println("4. Les évolutions d'un pokémon");
            System.out.println("5. Les types de pokémons");
            System.out.println("6. Les pokémons d'un type");
            System.out.println("7. Pokémons forts contre un pokémon donné");
            System.out.println("0. Quitter");
            
            int commande = sc.nextInt();
            
            switch (commande) {
                case 1:
                    break;
                    
                case 2:
                    break;
                    
                case 3:
                    break;
                    
                case 4:
                    break;
                    
                case 5:
                    break;
                    
                case 6:
                    break;
                    
                case 7:
                    break;
                    
                case 0:
                    quitter = true;
                    break;
            }
        }
    }
    
}
