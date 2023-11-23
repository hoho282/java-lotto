package autolotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoStatistics {
    private static final int LOTTO_PRICE = 1000;
    private static final int ZERO = 0;
    private List<Lotto> lottoTickets;
    private WinLotto winLotto;

    public LottoStatistics(List<Lotto> lottoTickets, WinLotto winLotto) {
        this.lottoTickets = lottoTickets;
        this.winLotto = winLotto;
    }

    public Map<Rank, Integer> calcuratorRankCount() {
        Map<Rank, Integer> rankCount = new HashMap<>();

        for (Lotto ticket : lottoTickets) {
            boolean hasBonusNumber = ticket.containsBonusNumber(winLotto.getBonusNumber());
            Rank rank = Rank.of(ticket.matchCount(ticket, winLotto.getLastWeekWinNumber()), hasBonusNumber);
            rankCount.put(rank, rankCount.getOrDefault(rank, 0) + 1);
        }
        return rankCount;
    }

    public double calcuratorProfit(Map<Rank, Integer> rankCount, int ticketsCount) {
        double totalPrize = rankCount.entrySet().stream()
                .mapToDouble(entry -> (long) entry.getKey().getMoney() * entry.getValue())
                .sum();
        if (totalPrize == ZERO) {
            return ZERO;
        }
        return (double) totalPrize / (ticketsCount * LOTTO_PRICE);
    }
}
