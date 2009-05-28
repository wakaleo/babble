/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sonatype.training.babble.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A babbler is a person that utters babble (@see Babble).
 * A babbler utters a list of babbles.
 * @author johnsmart
 */
public class Babbler {

    private String name;
    private List<Babble> babbles = new ArrayList<Babble>();

    /**
     * Commons Logging.
     */
    private static final Log log = LogFactory.getLog(Babbler.class);
    
    
    public Babbler() {
    }

    public Babbler(String name) {
        if ((name == null) || (name.trim().equals(""))) {
        	log.info("Attempt to create a babbler with an empty name");
            throw new IllegalArgumentException("Babbler name cannot be empty");
        }
    	log.debug("Creating new babbler: " + name);
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (obj.getClass() != this.getClass())) {
            return false;
        }
        Babbler babbler = (Babbler) obj;
        return (name == babbler.name || (name != null && name.equals(babbler.name)));
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

    public List<Babble> getBabbles() {
        return babbles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void utterBabble(String message) {
    	log.debug("Babble uttered for " + name + " :" + message);
        babbles.add(new Babble(this, message));
    }
}
