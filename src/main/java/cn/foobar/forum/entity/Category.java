package cn.foobar.forum.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @Author Category
 * @Date 2019/4/6 9:11
 * @Version 1.0.0
 * @Description
 **/
@Entity
@Table(name = "categories")
@ToString(callSuper = true)
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id")
    private Long catId;

    @Column(name = "cat_name", nullable = false)
    private String catName;

    @Column(name = "cat_description", nullable = false)
    private String catDescription;
}
