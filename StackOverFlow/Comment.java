import java.util.Date;
import java.util.UUID;

public class Comment {
    private final String id;
    private final String content;
    private final User user;
    private final Date creationDate;

    public Comment(String content, User user) {
        this.id = UUID.randomUUID().toString();
        this.content = content;
        this.user = user;
        this.creationDate = new Date();
    }

    public String getId() { return id; }
    public User getUser() { return user; }
    public String getContent() { return content; }
}
