package pokeontology;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author dodelien
 */
public class PokemonModel extends AbstractModel {

    private final String prefixes
            = "PREFIX p: <http://www.semanticweb.org/pokemon#>\n"
            + "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"
            + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
            + "PREFIX xml: <http://www.w3.org/XML/1998/namespace>\n"
            + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
            + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
            + "\n";

    public PokemonModel(String fileName) {
        super(fileName);
    }

    private List<Pokemon> getPokemons(String query) {
        HashMap<Integer, Pokemon> pokemons = new HashMap<>();

        QueryExecution qexec = QueryExecutionFactory.create(prefixes + query, model);
        ResultSet rs = qexec.execSelect();
        while (rs.hasNext()) {
            QuerySolution qs = rs.nextSolution();

            Integer pokemonNumber = Integer.parseInt(qs.getLiteral("pokemonNumber").getString());

            String pokemonName = qs.getLiteral("pokemonName").getString();
            String pokemonNameLanguage = qs.getLiteral("pokemonName").getLanguage();

            Pokemon pokemon;
            if (pokemons.containsKey(pokemonNumber)) {
                pokemon = pokemons.get(pokemonNumber);
                pokemon.addName(pokemonNameLanguage, pokemonName);
            } else {
                pokemon = new Pokemon();
                pokemon.setNumber(pokemonNumber);
                pokemon.addName(pokemonNameLanguage, pokemonName);

                pokemons.put(pokemonNumber, pokemon);
            }
        }

        return new ArrayList<>(pokemons.values());
    }

    private List<String> getTypes(String query) {
        ArrayList<String> types = new ArrayList<>();

        QueryExecution qexec = QueryExecutionFactory.create(prefixes + query, model);
        ResultSet rs = qexec.execSelect();
        while (rs.hasNext()) {
            QuerySolution qs = rs.nextSolution();
            String pokemonType = qs.get("pokemonType").toString();
            types.add(pokemonType);
        }

        return types;
    }

    public List<Pokemon> getAllPokemons() {
        return getPokemons(
                "SELECT ?pokemonNumber ?pokemonName \n"
                + "WHERE \n"
                + "{ \n"
                + "?pokemon a p:Pokemon ; \n"
                + "         p:numero ?pokemonNumber ; \n"
                + "         p:nom ?pokemonName . \n"
                + "} \n"
        );
    }

    public List<Pokemon> getLegendaryPokemons() {
        return getPokemons(
                "SELECT ?pokemonNumber ?pokemonName \n"
                + "WHERE \n"
                + "{ \n"
                + "?pokemon a p:PokemonLegendaire ; \n"
                + "         p:numero ?pokemonNumber ; \n"
                + "         p:nom ?pokemonName . \n"
                + "} \n"
        );
    }

    public List<Pokemon> getBasePokemons() {
        return getPokemons(
                "SELECT ?pokemonNumber ?pokemonName \n"
                + "WHERE \n"
                + "{ \n"
                + "?pokemon a p:PokemonBase ; \n"
                + "         p:numero ?pokemonNumber ; \n"
                + "         p:nom ?pokemonName . \n"
                + "} \n"
        );
    }

    public List<Pokemon> getPokemonEvolutions(String pokemonName) {
        return getPokemons(
                "SELECT ?pokemonNumber ?pokemonName \n"
                + "WHERE \n"
                + "{ \n"
                + "?pokemonEvolution a p:PokemonEvolution ; \n"
                + "                  p:numero ?pokemonNumber ; \n"
                + "                  p:nom ?pokemonName . \n"
                + "?pokemonBase a p:Pokemon ; \n"
                + "             p:nom \"" + pokemonName + "\"@fr ; \n"
                + "             p:hasEvolution ?pokemonEvolution . \n"
                + "} \n"
        );
    }

    public List<String> getPokemonTypes() {
        return getTypes(
                "SELECT ?pokemonType \n"
                + "WHERE \n"
                + "{ \n"
                + "?pokemonType a p:PType"
                + "} \n"
        );
    }

    public List<Pokemon> getPokemonsByType(String type) {
        return getPokemons(
                "SELECT ?pokemonNumber ?pokemonName \n"
                + "WHERE \n"
                + "{ \n"
                + "?pokemon a p:Pokemon ; \n"
                + "         p:numero ?pokemonNumber ; \n"
                + "         p:nom ?pokemonName ; \n"
                + "         p:hasType p:" + type + " . \n"
                + "} \n"
        );
    }

    public List<Pokemon> getStrongPokemonsAgainst(String pokemonName) {
        return getPokemons(
                "SELECT ?pokemonNumber ?pokemonName \n"
                + "WHERE \n"
                + "{ \n"
                + "?strongPokemon a p:Pokemon ; \n"
                + "               p:numero ?pokemonNumber ; \n"
                + "               p:nom ?pokemonName ; \n"
                + "               p:hasType ?strongType . \n"
                + "?weakPokemon a p:Pokemon ; \n"
                + "             p:nom \"" + pokemonName + "\"@fr ; \n"
                + "             p:hasType ?weakType . \n"
                + "?strongType a p:PType ; \n"
                + "            p:efficaceContre ?weakType . \n"
                + "} \n"
        );
    }

}
