import java.util.Arrays;
import java.util.List;

public class StackOverFlowDemo {
    public static void main(String[] args) {
        StackOverFlow stackOverFlow = StackOverFlow.getInstance();

        //Create Users
        User priya = stackOverFlow.createUser("Priya", "priya@example.com");
        User janice = stackOverFlow.createUser("Janice", "janice@example.com");
        User ram = stackOverFlow.createUser("Ram", "ram@example.com");

        //Questions
        Question java = stackOverFlow.postQuestion("class", "What is a class?", priya.getId(), Arrays.asList("Java", "OOP"));

        //Answer
        Answer janiceAns = stackOverFlow.postAnswer(janice.getId(), java.getId(), "Class is a blueprint");

        //Comment
        stackOverFlow.addComment(ram.getId(), janiceAns, "Class is a template for creating objects.");
        
        stackOverFlow.addComment(priya.getId(), janiceAns, "Great Answer!");
        stackOverFlow.acceptAnswer(janiceAns.getAnswerId());
        
        stackOverFlow.vote(priya.getId(), janiceAns, VoteType.UPVOTE);
        stackOverFlow.vote(janice.getId(), java, VoteType.UPVOTE);
        
        stackOverFlow.vote(ram.getId(), janiceAns, VoteType.DOWNVOTE);

        System.out.println("Question: " + java.getTitle());
        System.out.println("Asked by: " + java.getUser().getName());
        System.out.println("Tags: " + java.getTags().stream().map(Tag::getName).reduce((a, b) -> a + ", " + b).orElse(""));
        System.out.println("Votes: " + java.getVoteCount());
        System.out.println("Comments: " + java.getComments().size());
        System.out.println("\nAnswer by " + janiceAns.getAuthor().getName() + ":");
        System.out.println(janiceAns.getContent());
        System.out.println("Votes: " + janiceAns.getVoteCount());
        System.out.println("Accepted: " + janiceAns.isAccepted());
        System.out.println("Comments: " + janiceAns.getComments().size());

        System.out.println("\nUser Reputations:");
        System.out.println("Priya: " + priya.getReputation());
        System.out.println("Janice: " + janice.getReputation());
        System.out.println("Ram: " + ram.getReputation());

        // Search questions by keyword
        System.out.println("\nSearch Results for 'java':");
        List<Question> searchResults = stackOverFlow.searchQuestions("java");
        for (Question q : searchResults) {
            System.out.println(q.getTitle());
        }

        // Search questions by user
        System.out.println("\nPriya's Questions:");
        List<Question> bobQuestions = stackOverFlow.getQuestionsByUser(priya.getId());
        for (Question q : bobQuestions) {
            System.out.println(q.getTitle());
        }
    }
}