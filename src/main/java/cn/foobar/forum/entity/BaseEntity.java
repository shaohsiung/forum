package cn.foobar.forum.entity;

import lombok.*;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @Author BaseEntity
 * @Date 2019/4/6 9:16
 * @Version 1.0.0
 * @Description
 **/
@MappedSuperclass
@Data
@AllArgsConstructor
public class BaseEntity implements Serializable {
}
