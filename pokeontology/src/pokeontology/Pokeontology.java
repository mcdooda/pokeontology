package pokeontology;

import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

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
            System.out.println("1. Tous les pokémons");
            System.out.println("2. Les pokémons légendaires");
            System.out.println("3. Les pokémons de base (non évolués)");
            System.out.println("4. Les évolutions d'un pokémon");
            System.out.println("5. Les types de pokémons");
            System.out.println("6. Les pokémons d'un type");
            System.out.println("7. Pokémons forts contre un pokémon donné");
            System.out.println("8. Tout sur un pokémon donné");
            System.out.println("0. Quitter");

            try {
                int commande = sc.nextInt();

                switch (commande) {
                    case 1:
                        printPokemons(pm.getAllPokemons());
                        break;

                    case 2:
                        printPokemons(pm.getLegendaryPokemons());
                        break;

                    case 3:
                        printPokemons(pm.getBasePokemons());
                        break;

                    case 4: {
                        System.out.println("Nom du pokémon :");
                        sc.nextLine();
                        String pokemonName = sc.nextLine();
                        System.out.println("Evolutions de " + pokemonName + " :");
                        printPokemons(pm.getPokemonEvolutions(pokemonName));
                    }
                    break;

                    case 5:
                        printPokemonTypes(pm.getPokemonTypes());
                        break;

                    case 6: {
                        System.out.println("Nom du type (sans le préfixe http://www.semanticweb.org/pokemon#) :");
                        sc.nextLine();
                        String pokemonType = sc.nextLine();
                        printPokemons(pm.getPokemonsByType(pokemonType));
                    }
                    break;

                    case 7: {
                        System.out.println("Nom du pokémon :");
                        sc.nextLine();
                        String pokemonName = sc.nextLine();
                        System.out.println("Pokémons forts contre " + pokemonName + " :");
                        printPokemonWeaknesses(pm.getPokemonWeaknesses(pokemonName));
                    }
                    break;

                    case 8: {
                        System.out.println("Nom du pokémon :");
                        sc.nextLine();
                        String pokemonName = sc.nextLine();
                        printPokemon(pm.getPokemon(pokemonName));
                    }
                    break;

                    case 0:
                        quitter = true;
                        break;
                }
            } catch (Exception ex) {
                while (!sc.hasNextInt()) {
                    sc.next();
                }
            }
        }
    }

    private static void printPokemons(List<Pokemon> pokemons) {
        if (pokemons.isEmpty()) {
            System.out.println("Aucun résultat");
        } else {
            sortPokemons(pokemons);
            System.out.println(pokemons.size() + " pokémons :");
            for (Pokemon pokemon : pokemons) {
                System.out.print("\t#" + pokemon.getNumber());
                for (Entry<String, String> name : pokemon.getNames().entrySet()) {
                    System.out.print(" [" + name.getKey() + "] " + name.getValue());
                }
                System.out.println();
            }
        }
        System.out.println();
    }
    
    private static void printPokemonWeaknesses(List<PokemonWeakness> pokemonWeaknesses) {
        if (pokemonWeaknesses.isEmpty()) {
            System.out.println("Aucun résultat");
        } else {
            sortPokemonWeaknesses(pokemonWeaknesses);
            System.out.println(pokemonWeaknesses.size() + " pokémons :");
            for (PokemonWeakness pokemonWeakness : pokemonWeaknesses) {
                System.out.print("\t#" + pokemonWeakness.getPokemon().getNumber());
                for (Entry<String, String> name : pokemonWeakness.getPokemon().getNames().entrySet()) {
                    System.out.print(" [" + name.getKey() + "] " + name.getValue());
                }
                Set<PokemonWeakness.TypeWeakness> typeWeaknesses = pokemonWeakness.getTypes();
                System.out.print(" (" + typeWeaknesses.size() + " faiblesse" + (typeWeaknesses.size() > 1 ? "s" : "") + " : ");
                int i = 0;
                for (PokemonWeakness.TypeWeakness typeWeakness : typeWeaknesses) {
                    System.out.print(typeWeakness.getWeakType() + " < " + typeWeakness.getStrongType());
                    i++;
                    if (i < typeWeaknesses.size()) {
                        System.out.print(", ");
                    }
                }
                System.out.println(")");
            }
        }
        System.out.println();
    }

    private static void sortPokemons(List<Pokemon> pokemons) {
        Collections.sort(pokemons, new Comparator<Pokemon>() {

            @Override
            public int compare(Pokemon o1, Pokemon o2) {
                return o1.getNumber() - o2.getNumber();
            }
        });
    }
    
    private static void sortPokemonWeaknesses(List<PokemonWeakness> pokemonWeaknesses) {
        Collections.sort(pokemonWeaknesses, new Comparator<PokemonWeakness>() {

            @Override
            public int compare(PokemonWeakness o1, PokemonWeakness o2) {
                int i = o2.getTypes().size() - o1.getTypes().size();
                if (i == 0) {
                    i = o1.getPokemon().getNumber() - o2.getPokemon().getNumber();
                }
                return i;
            }
        });
    }

    private static void printPokemonTypes(List<String> types) {
        if (types.isEmpty()) {
            System.out.println("Aucun résultat");
        } else {
            System.out.println(types.size() + " types :");
            for (String type : types) {
                System.out.println("\t" + type);
            }
        }
        System.out.println();
    }
    
    private static void printPokemon(Pokemon pokemon) {
        System.out.print("#" + pokemon.getNumber());
        for (Entry<String, String> name : pokemon.getNames().entrySet()) {
            System.out.print(" [" + name.getKey() + "] " + name.getValue());
        }
        System.out.println();
        
        System.out.print("Famille :");
        for (Entry<String, String> familyName : pokemon.getFamilyNames().entrySet()) {
            System.out.print(" [" + familyName.getKey() + "] " + familyName.getValue());
        }
        System.out.println();
        
        System.out.println("Types :");
        for (String type : pokemon.getTypes()) {
            System.out.println(" * " + type);
        }
        
        System.out.println("Poids : " + pokemon.getWeight());
        
        System.out.println("Taille : " + pokemon.getHeight());
        
        System.out.println();
    }

}
