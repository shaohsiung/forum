package cn.foobar.forum.controller.request;

import lombok.Builder;
import lombok.Getter;

/**
 * @Author Result
 * @Date 2019/4/6 10:53
 * @Version 1.0.0
 * @Description
 **/
@Getter
@Builder
public class Result {

    /**
     * 响应状态码
     */
    private String status;

    /**
     * 处理结果提示
     */
    private String message;

    /**
     * 处理结果数据
     */
    private Object entity;
}
