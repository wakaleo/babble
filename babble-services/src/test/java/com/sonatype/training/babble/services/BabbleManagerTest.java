/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sonatype.training.babble.services;

import org.junit.Test;

import com.sonatype.training.babble.domain.Babble;
import com.sonatype.training.babble.domain.Babbler;

import java.util.List;
import org.junit.Before;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 *
 * @author johnsmart
 */
public class BabbleManagerTest {

    BabbleManager manager;

    @Before
    public void setup() {
        manager = BabbleManager.getInstance();
        manager.clear();
    }

    @Test
    public void shouldRegisterNewUserWithUniqueName() throws NameAlreadyExistsException {
        manager.register("joe");
        
        Babbler babbler = manager.findByName("joe");
        assertThat(babbler.getName(), is("joe"));
    }

    @Test(expected=NameAlreadyExistsException.class)
    public void shouldNotRegisterNewUserWithExistingName() throws NameAlreadyExistsException {
        manager.register("jake");
        manager.register("joe");
        assertThat(manager.findByName("joe").getName(), is("joe"));
        assertThat(manager.findByName("jake").getName(), is("jake"));

        manager.register("jake");
    }

    @Test
    public void shouldListAllBabblers() throws NameAlreadyExistsException {
        manager.register("jake");
        manager.register("joe");
        manager.register("jill");

        List<Babbler> babblers = manager.findAll();
        assertThat(babblers.size(), is(3));
        assertThat(babblers.contains(new Babbler("jake")),is(true));
        assertThat(babblers.contains(new Babbler("joe")),is(true));
        assertThat(babblers.contains(new Babbler("jill")),is(true));
    }

    @Test
    public void shoulddListAllBabblersInImmutableList() throws NameAlreadyExistsException {
        manager.register("jake");
        manager.register("joe");
        manager.register("jill");

        List<Babbler> babblers = manager.findAll();
        babblers.add(new Babbler("sneeky"));

        List<Babbler> newBabblers = manager.findAll();
        assertThat(newBabblers.size(), is(3));
        assertThat(newBabblers.contains(new Babbler("jake")),is(true));
        assertThat(newBabblers.contains(new Babbler("joe")),is(true));
        assertThat(newBabblers.contains(new Babbler("jill")),is(true));
        assertThat(newBabblers.contains(new Babbler("sneeky")),is(false));
    }

    @Test
    public void shouldListAllBabbles() throws NameAlreadyExistsException, InterruptedException {
        Babbler jake = manager.register("jake");
        Babbler joe = manager.register("joe");
        Babbler jill = manager.register("jill");

        jake.utterBabble("Hey dude!");
        Thread.sleep(5);
        joe.utterBabble("Hey");
        Thread.sleep(5);
        joe.utterBabble("How's it goin");
        Thread.sleep(5);
        jake.utterBabble("Had porridge for breakfast");
        Thread.sleep(5);
        joe.utterBabble("Awesome!");
        Thread.sleep(5);
        jill.utterBabble("Hi guys!");
        
        List<Babble> babbles = manager.findAllBabbles();
        assertThat(babbles.size(), is(6));
        assertThat(babbles.get(5).getUtterence(), is("Hi guys!"));
        assertThat(babbles.get(4).getUtterence(), is("Awesome!"));
        assertThat(babbles.get(3).getUtterence(), is("Had porridge for breakfast"));
        assertThat(babbles.get(2).getUtterence(), is("How's it goin"));
        assertThat(babbles.get(1).getUtterence(), is("Hey"));
        assertThat(babbles.get(0).getUtterence(), is("Hey dude!"));
    }    
}
