import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by tomasmalmsten on 2017-03-21.
 */
public class BowlingGameTest {

    private BowlingGame underTest;

    @Before
    public void setUpUnderTest() {
        underTest = new BowlingGame();
    }

    @Test
    public void completeFailure_GivesZeroResults() {
        assertThat(underTest.getTotalScore(), is(0));
    }

    @Test
    public void singlePinOnAllRolls_gives20() {
        for (int i = 0; i < 20; i++) {
            underTest.registerRoll(1);
        }
        assertThat(underTest.getTotalScore(), is(20));
    }

    @Test
    public void firstRoll_fillsFirstFrame() {
        underTest.registerRoll(1);
        assertThat(underTest.currentFrame(), is(1));
    }

    @Test
    public void firstRollIsAStrike_movesToSecondFrame() {
        underTest.registerRoll(10);
        assertThat(underTest.currentFrame(), is(2));
    }

    @Test
    public void twoRolls_movesToSecondFrame() {
        underTest.registerRoll(1);
        underTest.registerRoll(1);
        assertThat(underTest.currentFrame(), is(2));
    }


    /*
    @Test
    public void allStrikes_Gives300() {
        BowlingGame underTest = new BowlingGame();
        underTest.addScore();
        int totalScore = underTest.getTotalScore();
        assertThat(totalScore, is(300));
    }
    */
}
