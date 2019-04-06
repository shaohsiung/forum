package cn.foobar.forum.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author Post
 * @Date 2019/4/6 9:12
 * @Version 1.0.0
 * @Description
 **/
@Entity
@Table(name = "posts")
@ToString(callSuper = true)
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column(name = "post_content", nullable = false, columnDefinition = "TEXT")
    private String postContent;

    @Column(name = "post_date", updatable = false, nullable = false)
    @CreationTimestamp
    private Date postDate;

    @ManyToOne
    @JoinColumn(name = "post_topic", nullable = false)
    private Topic postTopic;

    @ManyToOne
    @JoinColumn(name = "topic_by", nullable = false)
    private User topicBy;
}
