package bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NormalRoundTest {
    private NormalRound normalRound;

    @BeforeEach
    public void setUp() {
        normalRound = new NormalRound();
    }

    @Test
    public void 스트라이크일_경우_바로_다음_라운드는_스킵한다() {
        //when
        boolean isSkip = normalRound.isSkipNextRound(1, BowlingResult.STRIKE, false);

        //then
        assertTrue(isSkip);
    }

    @Test
    public void 마지막_라운드_이전_라운드를_마치면_final_round를_생성한다() {
        //when
        Round next1 = normalRound.next(BowlingResult.STRIKE, 9, 1);
        //then
        assertTrue(next1 instanceof FinalRound);

        //when
        Round next2 = normalRound.next(BowlingResult.SPARE, 9, 1);
        //then
        assertTrue(next2 instanceof NormalRound);

        //when
        Round next3 = normalRound.next(BowlingResult.SPARE, 9, 2);
        //then
        assertTrue(next3 instanceof FinalRound);
    }

}
