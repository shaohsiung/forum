package cn.foobar.forum.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author Topic
 * @Date 2019/4/6 9:12
 * @Version 1.0.0
 * @Description
 **/
@Entity
@Table(name = "topics")
@ToString(callSuper = true)
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Topic extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id")
    private Long topicId;

    @Column(name = "topic_subject", nullable = false)
    private String topicSubject;

    @Column(name = "topic_date", updatable = false, nullable = false)
    @CreationTimestamp
    private Date topicDate;

    @ManyToOne
    @JoinColumn(name = "topic_cat", nullable = false)
    private Category topicCat;

    @ManyToOne
    @JoinColumn(name = "topic_by", nullable = false)
    private User topicBy;
}
