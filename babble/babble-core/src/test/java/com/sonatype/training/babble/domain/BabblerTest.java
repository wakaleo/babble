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
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasItem;

/**
 *
 * @author johnsmart
 */
public class BabblerTest {

    @Test
    public void babblerShouldHaveAUniqueName() {
        Babbler babbler = new Babbler("joe");
        assertThat(babbler.getName(), is("joe"));
    }

    @Test(expected=IllegalArgumentException.class)
    public void babblerShouldHaveANonEmptyName() {
        new Babbler("");
    }

    @Test(expected=IllegalArgumentException.class)
    public void babblerShouldHaveANonNullName() {
        new Babbler(null);
    }

    @Test
    public void babblersWithSameNameAreEqual() {
        Babbler babbler1 = new Babbler("joe");
        Babbler babbler2 = new Babbler("joe");
        assertThat(babbler1.equals(babbler2),is(true));
    }

    @Test
    public void babblersWithDifferentNamesAreNotEqual() {
        Babbler babbler1 = new Babbler("joe");
        Babbler babbler2 = new Babbler("jake");
        assertThat(babbler1.equals(babbler2),is(false));
    }

    @Test
    public void anyBabblerIsNotEqualToNull() {
        Babbler babbler = new Babbler("joe");
        assertThat(babbler.equals(null),is(false));
    }

    @Test
    public void babblerShouldUtterSingleBabble() {
        Babbler babbler = new Babbler("joe");
        babbler.utterBabble("Yo!");
        Matcher<Babble> yoBabble = hasProperty("utterence", is("Yo!"));
        assertThat(babbler.getBabbles(), hasItem(yoBabble));
    }

    @Test
    public void babblerShouldUtterMultipleBabbles() {
        Babbler babbler = new Babbler("joe");
        babbler.utterBabble("Yo!");
        babbler.utterBabble("Dude!");
        Matcher<Babble> yoBabble = hasProperty("utterence", is("Yo!"));
        Matcher<Babble> dudeBabble = hasProperty("utterence", is("Dude!"));
        assertThat(babbler.getBabbles(), hasItem(yoBabble));
        assertThat(babbler.getBabbles(), hasItem(dudeBabble));
    }

    @Test
    public void babblesShouldBeInChronologicalOrder() throws InterruptedException {
        Babbler babbler = new Babbler("joe");
        babbler.utterBabble("Yo!");
        Thread.sleep(10);
        babbler.utterBabble("Dude!");
        List<Babble> babbles = babbler.getBabbles();
        Babble babble1 = babbles.get(0);
        Babble babble2 = babbles.get(1);
        assertThat(babble1.getTime().getTime(), lessThan(babble2.getTime().getTime()));
    }

}
