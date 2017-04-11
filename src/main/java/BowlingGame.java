import java.util.LinkedList;

class BowlingGame {
    private LinkedList<Frame> frames = new LinkedList<Frame>();
    private Frame current = new Frame();


    int getTotalScore() {
        int score = 0;
        for (int i = 0; i < frames.size(); i++) {
            Frame f = frames.get(i);
            score += f.getScore();
            if (f.isStrike()) {
                if (frames.size() > i + 1) {
                    Frame nextFrame = frames.get(i + 1);
                    score += nextFrame.getScore();
                }
            }
        }
        return score;
    }

    void registerRoll(int score) {
        current.addScore(score);

        if (current.completed()) {
            frames.add(current);
            current = new Frame();
        }

    }

    private class Frame {
        private int rollInFrame = 0;
        private int score;

        void addScore(int score) {
            this.score += score;
            rollInFrame++;
        }

        boolean isSpare() {
            return score == 10 && rollInFrame == 2;
        }

        boolean isStrike() {
            return score == 10 && rollInFrame == 1;
        }
        boolean completed() {
            return isSpare() || isStrike() || rollInFrame == 2;
        }
        int getScore() {
            return score;
        }
    }

}
