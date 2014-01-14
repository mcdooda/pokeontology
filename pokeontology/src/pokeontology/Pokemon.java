/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pokeontology;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author nicolas
 */
public class Pokemon {
    
    private Integer number;
    private final Map<String, String> names;
    private final Map<String, String> familyNames;
    private Float weight;
    private Float height;
    private final Set<String> types;
    
    public Pokemon() {
        names = new HashMap<>();
        familyNames = new HashMap<>();
        types = new HashSet<>();
    }

    public Integer getNumber() {
        return number;
    }

    public Map<String, String> getNames() {
        return names;
    }

    public Map<String, String> getFamilyNames() {
        return familyNames;
    }

    public Float getWeight() {
        return weight;
    }

    public Float getHeight() {
        return height;
    }

    public Set<String> getTypes() {
        return types;
    }
    
    public void setNumber(Integer number) {
        this.number = number;
    }
    
    public void addName(String language, String name) {
        names.put(language, name);
    }
    
    public void addFamilyName(String language, String name) {
        familyNames.put(language, name);
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public void setHeight(Float height) {
        this.height = height;
    }
    
    public void addType(String type) {
        types.add(type);
    }
    
}
