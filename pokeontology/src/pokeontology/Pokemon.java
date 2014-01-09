/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pokeontology;

import java.util.HashMap;

/**
 *
 * @author nicolas
 */
public class Pokemon {
    
    private Integer number;
    private HashMap<String, String> names;
    
    public Pokemon() {
        names = new HashMap<>();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public HashMap<String, String> getNames() {
        return names;
    }
    
    public void addName(String language, String name) {
        names.put(language, name);
    }
    
}
