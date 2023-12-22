import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.entities.Comment;
import com.model.entities.Post;

public class Helpers {

    ObjectMapper objectMapper;
    public String createPostBody(String id, String title, String body) throws JsonProcessingException {
        Post post = Post.builder()
                        .id(id)
                        .title(title)
                        .body(body)
                        .build();

        return objectMapper.writeValueAsString(post);
    }

    public String createCommentBody(String postId, String name, String email, String body)
            throws JsonProcessingException {
        Comment comment = Comment.builder()
                .postId(postId)
                .name(name)
                .email(email)
                .body(body)
                .build();

        return objectMapper.writeValueAsString(comment);
    }

    public Helpers(){
        this.objectMapper = new ObjectMapper();
    }
}
