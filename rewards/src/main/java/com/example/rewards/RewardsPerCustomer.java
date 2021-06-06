package com.example.rewards;

import java.util.Map;

public class RewardsPerCustomer {

    private Map<Integer, Long> rewardsEarnedPerMonthMap;
    private long totalRewardsEarned;

    public void setTotalRewardsEarned(long totalRewardsEarned) {
        this.totalRewardsEarned = totalRewardsEarned;
    }

    public void setRewardsEarnedPerMonthMap(Map<Integer, Long> rewardsEarnedPerMonthMap) {
        this.rewardsEarnedPerMonthMap = rewardsEarnedPerMonthMap;
    }

    public Map<Integer, Long> getRewardsPerMonthMap() {
        return rewardsEarnedPerMonthMap;
    }

    public Long getTotalRewardsEarned() {
        return totalRewardsEarned;
    }

    @Override
    public String toString() {
        return "rewardsPerMonth: " + this.rewardsEarnedPerMonthMap + " totalRewardsEarned: " + this.totalRewardsEarned;
    }
}
