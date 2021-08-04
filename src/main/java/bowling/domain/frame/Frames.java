package bowling.domain.frame;

import bowling.domain.pin.DownedPins;
import bowling.domain.score.Score;
import bowling.dto.ScoreDto;
import bowling.dto.StateDtos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {
    private static final int LIMIT_SIZE_OF_FRAMES = 10;

    private final List<Frame> frames;

    private Frames() {
        frames = new ArrayList<>(LIMIT_SIZE_OF_FRAMES);
        frames.add(GeneralFrame.init());
    }

    public static Frames init() {
        return new Frames();
    }

    public void downPins(DownedPins downedPins) {
        Frame currentFrame = currentFrame();

        currentFrame.downPins(downedPins);
        currentFrame.appendFrame(frames);
    }

    public boolean isBowlingEnd() {
        return currentFrame().isBowlingEnd();
    }

    public boolean isTurnOver() {
        return currentFrame().isStartState();
    }

    private Frame currentFrame() {
        return frames.get(frames.size() - 1);
    }

    public List<StateDtos> getTotalStates() {
        return frames.stream()
                .map(Frame::getFrameStates)
                .collect(Collectors.toList());
    }

    public List<ScoreDto> getScores() {
        return frames.stream()
                .map(Frame::getScore)
                .filter(Score::isCalculable)
                .map(ScoreDto::from)
                .collect(Collectors.toList());
    }
}