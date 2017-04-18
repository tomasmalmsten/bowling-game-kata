import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BowlingGameTest {

    private BowlingGame underTest;

    @Before
    public void setUpUnderTest() {
        underTest = new BowlingGame();
    }

    @Test
    public void completeFailure_GivesZeroAsResults() {

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
    public void whenFrameIsStrike_nextFrameDefinesScore() {
        underTest.registerRoll(10);
        underTest.registerRoll(1);
        underTest.registerRoll(1);
        assertThat(underTest.getTotalScore(), is(14));
    }

    @Test
    public void allStrikesButLast_Gives270PlusTwoLastRoles() {
        underTest.registerRoll(10);
        underTest.registerRoll(10);
        underTest.registerRoll(10);
        underTest.registerRoll(10);
        underTest.registerRoll(10);
        underTest.registerRoll(10);
        underTest.registerRoll(10);
        underTest.registerRoll(10);
        underTest.registerRoll(10);
        underTest.registerRoll(5);
        underTest.registerRoll(4);

        int totalScore = underTest.getTotalScore();
        assertThat(totalScore, is(263));
    }

    @Test
    public void allSparesButLast_givesSomething() {
        underTest.registerRoll(5);
        underTest.registerRoll(5);

        underTest.registerRoll(5);
        underTest.registerRoll(5);

        underTest.registerRoll(5);
        underTest.registerRoll(5);

        underTest.registerRoll(5);
        underTest.registerRoll(5);

        underTest.registerRoll(5);
        underTest.registerRoll(5);

        underTest.registerRoll(5);
        underTest.registerRoll(5);

        underTest.registerRoll(5);
        underTest.registerRoll(5);

        underTest.registerRoll(5);
        underTest.registerRoll(5);

        underTest.registerRoll(5);
        underTest.registerRoll(5);

        underTest.registerRoll(4);
        underTest.registerRoll(4);

        int totalScore = underTest.getTotalScore();
        assertThat(totalScore, is(142));
    }

    @Test
    public void allStrikes() {
        underTest.registerRoll(10);

        underTest.registerRoll(10);

        underTest.registerRoll(10);

        underTest.registerRoll(10);

        underTest.registerRoll(10);

        underTest.registerRoll(10);

        underTest.registerRoll(10);

        underTest.registerRoll(10);

        underTest.registerRoll(10);

        underTest.registerRoll(10);
        underTest.registerRoll(10);
        underTest.registerRoll(10);

        int totalScore = underTest.getTotalScore();
        assertThat(totalScore, is(300));
    }

}
