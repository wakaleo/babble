/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sonatype.training.babble.domain;

import java.util.List;
import org.hamcrest.Matcher;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.greaterThan;

/**
 *
 * @author johnsmart
 */
public class BabbleTest {

    @Test
    public void compareToShouldOrderByDate() throws InterruptedException {
        Babbler babbler = new Babbler("joe");
        Babble babble1 = new Babble(babbler,"Hi there");
        Thread.sleep(5);
        Babble babble2 = new Babble(babbler,"Hey");
               
        assertThat(babble1.compareTo(babble2), lessThan(0));
    }
    @Test
    public void compareToShouldOrderByAscendingDate() throws InterruptedException {
        Babbler babbler = new Babbler("joe");
        Babble babble1 = new Babble(babbler,"Hi there");
        Thread.sleep(5);
        Babble babble2 = new Babble(babbler,"Hey");
               
        assertThat(babble2.compareTo(babble1), greaterThan(0));
    }
    @Test
    public void compareToShouldBeEqualWithSameDate() {
        Babbler babbler = new Babbler("joe");
        Babble babble1 = new Babble(babbler,"Hi there");
        Babble babble2 = new Babble(babbler,"Hi there");
        babble2.setTime(babble1.getTime());
               
        assertThat(babble2.compareTo(babble1), is(0));
    }
    
}
