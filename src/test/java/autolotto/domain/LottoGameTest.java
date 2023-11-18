package autolotto.domain;

import autolotto.strategy.LottoNumberGeneratorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LottoGameTest {

    @Test
    @DisplayName("로또 구매 건수에 맞게 생성되는가")
    void getLottoTickets_발매갯수확인() {
        LottoGame lottoGame = new LottoGame(3, new LottoNumberGeneratorImpl());
        List<Lotto> lottoTickets = lottoGame.getLottoTickets();
        assertThat(lottoTickets).hasSize(3);
    }

    @Test
    @DisplayName("로또 하나에 숫자 6개 확인")
    void getLottoTickets_로또숫자확인() {
        LottoGame lottoGame = new LottoGame(3, new LottoNumberGeneratorImpl());
        List<Lotto> lottoTickets = lottoGame.getLottoTickets();
        for(Lotto lotto: lottoTickets){
            assertThat(lotto.lottoNumbers()).hasSize(6);
        }
    }
    @Test
    @DisplayName("로또 생성된 숫자 1~45 범위 내 인지 확인")
    void getLottoTickets_로또숫자범위확인() {
        LottoGame lottoGame = new LottoGame(3, new LottoNumberGeneratorImpl());
        List<Lotto> lottoTickets = lottoGame.getLottoTickets();
        for(Lotto lotto: lottoTickets){
            assertTrue(lotto.lottoNumbers().stream().allMatch(n-> n >= 1 && n <=45));
        }
    }
    @Test
    @DisplayName("로또 생성된 숫자 중복여부 확인")
    void getLottoTickets_로또숫자중복확인() {
        LottoGame lottoGame = new LottoGame(3, new LottoNumberGeneratorImpl());
        List<Lotto> lottoTickets = lottoGame.getLottoTickets();
        for(Lotto lotto: lottoTickets){
            Set<Integer> uniqueNumber = new HashSet<>(lotto.lottoNumbers());
            assertEquals(lotto.lottoNumbers().size(), uniqueNumber.size());
        }
    }
}