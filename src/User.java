public class User {
    private int rank;
    private int progress;
    private static final int[] RANKS = {-8, -7, -6, -5, -4, -3, -2, -1, 1, 2, 3, 4, 5, 6, 7, 8};

    public User() {
        this.rank = -8;
        this.progress = 0;
    }

    public int getRank() {
        return rank;
    }

    public int getProgress() {
        return progress;
    }

    public void incProgress(int activityRank) {
        if (!isValidRank(activityRank)) {
            throw new IllegalArgumentException("The rank of an activity cannot be less than 8, 0, or greater than 8!");
        }

        int d = calculateRankDifference(activityRank);

        if (d == -2) return;

        int earnedProgress = 0;
        if (d == 0) {
            earnedProgress = 3;
        } else if (d == -1) {
            earnedProgress = 1;
        } else if (d > 0) {
            earnedProgress = 10 * d * d;
        }

        progress += earnedProgress;

        while (progress >= 100 && rank < 8) {
            progress -= 100;
            rank = getNextRank(rank);
        }

        if (rank == 8) {
            progress = 0;
        }
    }

    private boolean isValidRank(int rank) {
        for (int r : RANKS) {
            if (r == rank) return true;
        }
        return false;
    }

    private int calculateRankDifference(int activityRank) {
        int userIndex = getRankIndex(rank);
        int activityIndex = getRankIndex(activityRank);
        return activityIndex - userIndex;
    }

    private int getRankIndex(int r) {
        for (int i = 0; i < RANKS.length; i++) {
            if (RANKS[i] == r) return i;
        }
        return -1;
    }

    private int getNextRank(int currentRank) {
        int index = getRankIndex(currentRank);
        return index + 1 < RANKS.length ? RANKS[index + 1] : 8;
    }

    @Override
    public String toString() {
        return "User{" + "rank=" + rank + ", progress=" + progress + '}';
    }
}
