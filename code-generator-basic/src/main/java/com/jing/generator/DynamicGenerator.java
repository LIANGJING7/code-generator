package com.jing.generator;


import com.jing.model.MainTemplateConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class DynamicGenerator {
    public static void main(String[] args) throws IOException, TemplateException {

        String projectPath = System.getProperty("user.dir") +File.separator +"code-generator-basic";
        String inputPath = projectPath + File.separator +"src/main/resources/templates/MainTemplate.java.ftl";
        String outputPath = projectPath + File.separator + "MainTemplate.java";
        // 数据模型
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("jing");
        mainTemplateConfig.setOutputText("输出结果");
        mainTemplateConfig.setLoop(false);
        doGenerate(inputPath,outputPath,mainTemplateConfig);
    }

    public static void doGenerate(String inputPath,String outputPath,Object model) throws IOException, TemplateException {
        // new 出 configuration对象 ,参数为freemarker 版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);
        System.out.println(inputPath);
        File templateDir = new File(inputPath).getParentFile();
        // 指定模板文件所在的路径
        configuration.setDirectoryForTemplateLoading(templateDir);
        // 设置模板文件使用的字符集
        configuration.setDefaultEncoding("UTF-8");
        configuration.setNumberFormat("0.######");
        // 创建模板对象,加载指定模板
        String templateName = new File(inputPath).getName();
        Template template = configuration.getTemplate(templateName);


        Writer out = new FileWriter(outputPath);

        template.process(model,out);

        // 生成文件后要关闭流
        out.close();
    }
}
