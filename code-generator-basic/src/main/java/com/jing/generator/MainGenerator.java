package com.jing.generator;

import com.jing.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class MainGenerator {
    public static void doGenerator(Object model) throws TemplateException, IOException {
        String projectPath = System.getProperty("user.dir");
        // 整个项目根路径
        File parentFile = new File(projectPath).getParentFile();
        // 输入路径
        String inputPath = new File(parentFile,"code-generator-demo-project/acm-template").getAbsolutePath();
        String outputPath = projectPath;
        // 生成静态文件
        StaticGenerator.copyFilesByRecursive(inputPath,outputPath);
        // 生成动态文件
        String inputDynamicFilePath = projectPath + File.separator + "MainTemplate.java.ftl";
        System.out.println(inputDynamicFilePath);
        String outputDynamicFilePath = outputPath + File.separator + "acm-template/src/com/code/acm/MainTemplate.java";
        DynamicGenerator.doGenerate(inputDynamicFilePath,outputDynamicFilePath,model);
    }

    public static void main(String[] args) throws TemplateException, IOException {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("jing");
        mainTemplateConfig.setOutputText("输出结果");
        mainTemplateConfig.setLoop(false);
        doGenerator(mainTemplateConfig);
    }
}
