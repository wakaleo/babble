/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sonatype.training.babble.services;

import com.sonatype.training.babble.domain.Babble;
import com.sonatype.training.babble.domain.Babbler;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * A service class that acts as a broker for babble messages.
 * @author johnsmart
 */
public class BabbleManager {

    private static BabbleManager singleton = new BabbleManager();

    private List<Babbler> registeredBabblers = new ArrayList<Babbler>();
    
    public static BabbleManager getInstance() {
        return singleton;       
    }

    public Babbler register(String name) throws NameAlreadyExistsException{

        Babbler babbler = new Babbler(name);
        if (registeredBabblers.contains(babbler)) {
            throw new NameAlreadyExistsException("Babbler already exists: " + name);
        }
        registeredBabblers.add(babbler);
        return babbler;
    }

    public Babbler findByName(String name) {
        Babbler foundBabbler = null;
        int index = registeredBabblers.indexOf(new Babbler(name));
        if (index >= 0) {
            foundBabbler = registeredBabblers.get(index);
        }
        return foundBabbler;
    }

    public final List<Babbler> findAll() {
        List<Babbler> results = new ArrayList<Babbler>();
        results.addAll(registeredBabblers);
        return results;
    }

    public void clear() {
        registeredBabblers.clear();
    }

    public final List<Babble> findAllBabbles() {
        List<Babble> results = new ArrayList<Babble>();
        for(Babbler babbler : registeredBabblers) {
        	results.addAll(babbler.getBabbles()); 
        }
        Collections.sort(results);        
        return results;
    }
    
}
