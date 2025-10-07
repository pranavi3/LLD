import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class Question implements Commentable, Votable{
    private final String id;
    private final String title;
    private final String content;
    private final User user;
    private final List<Answer> answerList;
    private final List<Comment> comments;
    private final Date creationDate;
    private final List<Vote> votes;
    private final List<Tag> tags;
    private Answer acceptedAnswer;

    public Question(String title, String content, User user, List<Tag> tags) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.content = content;
        this.user = user;
        this.answerList = new CopyOnWriteArrayList<>(); //thread safe
        this.comments = new CopyOnWriteArrayList<>();
        this.creationDate = new Date();
        this.votes = new CopyOnWriteArrayList<>();
        this.tags = tags;
    }

    public synchronized void addAnswer(Answer answer){
        answerList.add(answer);
    }
    public synchronized void acceptAnswer(Answer answer){
        this.acceptedAnswer = answer;
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
        user.updateReputation(voteType == VoteType.UPVOTE ? ReputationType.ANSWER_UPVOTE.getPoints() : ReputationType.ANSWER_DOWNVOTE.getPoints());
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


    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public User getUser() {
        return user;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public Answer getAcceptedAnswer() {
        return acceptedAnswer;
    }
}
