import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Answer implements Votable, Commentable{

    private final String answerId;
    private final User author;
    private final String content;
    private final Question question;
    private boolean isAccepted;
    private final Date creationDate;
    private final List<Comment> comments;
    private final List<Vote> votes;

    public Answer(User author, String content, Question question) {
        this.answerId = UUID.randomUUID().toString();;
        this.author = author;
        this.content = content;
        this.question = question;
        this.creationDate = new Date();
        this.comments = new ArrayList<>();
        this.votes = new ArrayList<>();
    }

    @Override
    public void vote(User voter, VoteType voteType) {
        for(Vote vote: votes){
            if(vote.getUser().equals(voter)){
                votes.remove(vote);
                break;
            }
        }
        votes.add(new Vote(voter, voteType));
        author.updateReputation(voteType == VoteType.UPVOTE ? ReputationType.ANSWER_UPVOTE.getPoints() : ReputationType.ANSWER_DOWNVOTE.getPoints());
    }

    @Override
    public int getVoteCount() {
        int sum = 0;
        for(Vote v: votes){
            sum += v.getType().getValue();
        }
        return sum;
    }

    @Override
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    public void markAsAccepted(){
        if (isAccepted) {
            throw new IllegalStateException("This answer is already accepted");
        }
        this.isAccepted = true;
        author.updateReputation(ReputationType.ANSWER_ACCEPTED.getPoints());
    }
    public Question getQuestion() {
        return question;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public String getAnswerId() {
        return answerId;
    }

    public User getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}
