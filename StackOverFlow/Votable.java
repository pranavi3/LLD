public interface Votable {
    void vote(User user, VoteType voteType);
    int getVoteCount();
}
