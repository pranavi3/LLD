import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class StackOverFlow {
    private static StackOverFlow INSTANCE ;
    private Map<String, User> users;
    private Map<String, Question> questions;
    private Map<String, Answer> answers;
    private Map<String, Tag> tags;

    private StackOverFlow(){
        users = new ConcurrentHashMap<>();
        questions = new ConcurrentHashMap<>();
        answers = new ConcurrentHashMap<>();
        tags = new ConcurrentHashMap<>();
    }
    public static synchronized StackOverFlow getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new StackOverFlow();
        }
        return INSTANCE;
    }

    public User createUser(String name, String email){
        User user = new User(name, email);
        users.put(user.getId(), user);
        return user;
    }

    public Question postQuestion(String title, String content, String userId, List<String> questionTags){
        User user = users.get(userId);
        List<Tag> tagList = new ArrayList<>();
        for(String name: questionTags){
            Tag tag = new Tag(name);
            tagList.add(tag);
            tags.put(tag.getId(), tag);
        }
        Question question = new Question(title, content, user, tagList);
        questions.put(question.getId(), question);
        return question;
    }

    public Answer postAnswer(String userId, String questionId, String content){
        User author = users.get(userId);
        Question question = questions.get(questionId);
        Answer answer = new Answer(author, content, question);
        answers.put(answer.getAnswerId(), answer);
        return answer;
    }

    public Comment addComment(String userId, Commentable commentable, String content){
        User author = users.get(userId);
        Comment comment = new Comment(content, author);
        commentable.addComment(comment);
        return comment;
    }
    public void vote(String userId, Votable votable, VoteType type){
        User user = users.get(userId);
        votable.vote(user, type);
    }
    public void acceptAnswer(String answerId){
        Answer answer = answers.get(answerId);
        Question question = answer.getQuestion();
        answer.markAsAccepted();
        question.acceptAnswer(answer);
    }
    public List<Question> searchQuestions(String query) {
        List<Question> out = new ArrayList<>();
        for(Question question: questions.values()){
            if(question.getTitle().toLowerCase().contains(query.toLowerCase()) || question.getContent().toLowerCase().contains(query.toLowerCase())){
                out.add(question);
            }else{
                for(Tag t: question.getTags()){
                    if(t.getName().equalsIgnoreCase(query))
                        out.add(question);
                }
            }
        }
        return out;
    }
    public List<Question> getQuestionsByUser(String userId) {
        User user = users.get(userId);
        List<Question> out = new ArrayList<>();
        for(Question question: questions.values()){
            if(question.getUser().equals(user)){
                out.add(question);
            }
        }
        return out;
    }
}
