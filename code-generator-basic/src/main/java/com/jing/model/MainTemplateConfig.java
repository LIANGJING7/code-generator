package com.jing.model;

import lombok.Data;

@Data
public class MainTemplateConfig {

    /**
     * 是否生成循环
     */
    private boolean loop ;

    /**
     * 作者注释
     */
    private String author="jing";
    /**
     * 输出信息
     */
    private String outputText = "sum = ";
}
