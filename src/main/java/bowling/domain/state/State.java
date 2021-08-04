package bowling.domain.state;

import bowling.domain.pin.DownedPins;
import bowling.domain.score.InCalculableScore;
import bowling.domain.score.Score;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class State {

    public State downPins(DownedPins downedPins) {
        validate(downedPins);

        return nextState(downedPins);
    }

    private void validate(DownedPins downedPins) {
        if (Objects.isNull(downedPins)) {
            throw new IllegalArgumentException("DownedPin can't be null");
        }
    }

    protected abstract State nextState(DownedPins downedPins);

    public Score score() {
        return InCalculableScore.init();
    }

    public Score addScore(Score score) {
        if (score.isCalculable()) {
            return score;
        }

        return addBonusScore(score);
    }

    protected Score addBonusScore(Score score) {
        return score;
    }

    public boolean isEnd() {
        return false;
    }

    protected boolean isMiss() {
        return false;
    }

    protected boolean isClean() {
        return false;
    }

    public List<State> getState() {
        return Collections.singletonList(this);
    }

    public abstract List<Integer> getDownedPins();

    public boolean isStart() {
        return false;
    }

}