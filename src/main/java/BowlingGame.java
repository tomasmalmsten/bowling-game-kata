import java.util.LinkedList;

class BowlingGame {
    private LinkedList<Frame> frames = new LinkedList<Frame>();
    private Frame current = new Frame();


    int getTotalScore() {
        int score = 0;
        for (Frame f : frames) {
            score += f.getScore();
        }
        return score;
    }

    void registerRoll(int score) {
        current.knockDown(score);
        if (current.completed()) {
            Frame newFrame = new Frame();
            current.nextFrame = newFrame;
            frames.add(current);
            current = newFrame;
        }

    }

    private class Frame {
        private boolean firstRoll = true;
        private boolean completed = false;
        private int pinsInRollOne;
        private int pinsInRollTwo;
        private Frame nextFrame;

        void knockDown(int pins) {
            if (firstRoll) {
                pinsInRollOne = pins;
                firstRoll = false;
                return;
            }
            pinsInRollTwo = pins;
            completed = true;
        }

        int getScore() {
            if (isStrike()) {
                if (nextFrame.isStrike()) {
                    return pinsInRollOne + nextFrame.pinsInRollOne + nextFrame.nextFrame.pinsInRollOne;
                }
                return pinsInRollOne + nextFrame.pinsInRollOne + nextFrame.pinsInRollTwo;
            }
            if (isSpare()) {
                return pinsInRollOne + pinsInRollTwo + nextFrame.pinsInRollOne;
            }
            return pinsInRollOne + pinsInRollTwo;
        }

        boolean isSpare() {
            return pinsInRollOne + pinsInRollTwo == 10;
        }

        boolean isStrike() {
            return pinsInRollOne == 10;
        }
        boolean completed() {
            return isSpare() || isStrike() || completed;
        }

    }

}
