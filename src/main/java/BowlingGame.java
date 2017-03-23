public class BowlingGame {
    private int totalScore = 0;
    private int currentFrame = 1;
    private short currentRollInFrame = 0;

    public int getTotalScore() {
        return totalScore;
    }

    public void registerRoll(int score) {
        addScoreForRoll(score);
        currentRollInFrame++;

        if (score == 10) {
            goToNextFrame();
        }
        if (currentRollInFrame == 2) {
            goToNextFrame();
        }
    }

    private void goToNextFrame() {
        currentRollInFrame = 0;
        currentFrame++;
    }

    private void addScoreForRoll(int score) {
        totalScore += score;
    }

    public int currentFrame() {
        return currentFrame;
    }
}
