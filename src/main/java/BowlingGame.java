import java.util.LinkedList;

class BowlingGame {
    private LinkedList<Frame> frames = new LinkedList<Frame>();
    private Frame current = new Frame();

    int getFrameCount() {
        return frames.size();
    }

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
            Frame newFrame;
            frames.add(current);
            if (frames.size() != 9) {
                newFrame = new Frame();
            } else {
                newFrame = new LastFrame();
                frames.add(newFrame);
            }
            current.nextFrame = newFrame;
            current = newFrame;
        }
    }

    private class Frame {
        private boolean firstRoll = true;
        private boolean completed = false;
        protected int pinsInRollOne;
        protected int pinsInRollTwo;
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
                if (nextFrame.isStrike() && nextFrame.nextFrame != null) {
                    return pinsInRollOne
                            + nextFrame.pinsInRollOne
                            + nextFrame.nextFrame.pinsInRollOne;
                }
                return pinsInRollOne
                        + nextFrame.pinsInRollOne
                        + nextFrame.pinsInRollTwo;
            }
            if (isSpare() && nextFrame != null) {
                return pinsInRollOne
                        + pinsInRollTwo
                        + nextFrame.pinsInRollOne;
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

    class LastFrame extends Frame {
        private int pinsInRollThree;
        private boolean firstRoll = true;
        private boolean secondRoll = false;

        @Override
        boolean completed() {
            return false;
        }

        @Override
        void knockDown(int pins) {
            if (firstRoll) {
                pinsInRollOne = pins;
                firstRoll = false;
                secondRoll = true;
            } else if (secondRoll) {
                pinsInRollTwo = pins;
                secondRoll = false;
            } else {
                pinsInRollThree = pins;
            }
        }

        @Override
        int getScore() {
            return pinsInRollOne + pinsInRollTwo + pinsInRollThree;
        }
    }


}
