/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pokeontology;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author dodelien
 */
public class PokemonWeakness {
    
    public class TypeWeakness {
        private final String weakType;
        private final String strongType;

        public TypeWeakness(String weakType, String strongType) {
            this.weakType = weakType;
            this.strongType = strongType;
        }

        public String getWeakType() {
            return weakType;
        }

        public String getStrongType() {
            return strongType;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 47 * hash + Objects.hashCode(this.weakType);
            hash = 47 * hash + Objects.hashCode(this.strongType);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final TypeWeakness other = (TypeWeakness) obj;
            if (!Objects.equals(this.weakType, other.weakType)) {
                return false;
            }
            if (!Objects.equals(this.strongType, other.strongType)) {
                return false;
            }
            return true;
        }
        
    }
    
    private Pokemon pokemon;
    private final Set<TypeWeakness> weaknesses;

    public PokemonWeakness() {
        weaknesses = new HashSet<>();
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }
    
    public void addTypes(String weakType, String strongType) {
        weaknesses.add(new TypeWeakness(weakType, strongType));
    }

    public Set<TypeWeakness> getTypes() {
        return weaknesses;
    }
    
}
