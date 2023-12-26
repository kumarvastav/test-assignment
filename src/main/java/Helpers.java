import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.entities.Comment;
import com.model.entities.Post;

import java.util.HashMap;
import java.util.Map;

public class Helpers {

    public Post createPostBody(String userId, String title, String body) {
        Post post = Post.builder()
                        .userId(userId)
                        .title(title)
                        .body(body)
                        .build();

        return post;
    }

    public Comment createCommentBody(String postId, String name, String email, String body) {
        Comment comment = Comment.builder()
                .postId(postId)
                .name(name)
                .email(email)
                .body(body)
                .build();

        return comment;
    }

    public Map<String, Object> createUserBody(String name, String username, String email, String phone, String website) {
        Map<String, Object> user = new HashMap<>();
        user.put("name",name);
        user.put("username",username);
        user.put("email",email);
        user.put("phone",phone);
        user.put("website",website);

        user.put("address",getAddress());
        user.put("company",getCompany());

        return user;
    }

    public Object getAddress() {
        Map<String, Object> address = new HashMap<>();
        address.put("street","Skiles Walk");
        address.put("suite","Suite 351");
        address.put("city","Roscoeview");
        address.put("zipcode","33263");

        Map<String, String> geo = new HashMap<>();
        geo.put("lat","-37.3159");
        geo.put("lng","81.1496");

        address.put("geo",geo);

        return address;
    }

    public Object getCompany() {
        Map<String, String> company = new HashMap<>();
        company.put("name","Keebler LLC");
        company.put("catchPhrase","User-centric fault-tolerant solution");
        company.put("bs","revolutionize end-to-end systems");

        return company;
    }
}
