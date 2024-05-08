package com.jing.generator;

import com.jing.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class MainGenerator {
    /**
     *生成
     * @param model
     * @throws TemplateException
     * @throws IOException
     */
    public static void doGenerator(Object model) throws TemplateException, IOException {
        String inputRootPath = "D:\\ideaProject\\code-generator\\code-generator-demo-project\\acm-template-pro";
        String outputRootPath = "D:\\ideaProject\\code-generator";

        String inputPath;
        String outputPath;

        inputPath = new File(inputRootPath,"src/com/jing/acm/MainTemplate.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath,"src/com/jing/acm/MainTemplate.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath,outputPath,model);

        inputPath = new File(inputRootPath,".gitignore").getAbsolutePath();
        outputPath = new File(outputRootPath,".gitignore").getAbsolutePath();
        StaticGenerator.copyFilesByHutool(inputPath,outputPath);

        inputPath = new File(inputRootPath,"README.md").getAbsolutePath();
        outputPath = new File(outputRootPath,"README.md").getAbsolutePath();
        StaticGenerator.copyFilesByHutool(inputPath,outputPath);

    }

    public static void main(String[] args) throws TemplateException, IOException {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("jing");
        mainTemplateConfig.setOutputText("输出结果");
        mainTemplateConfig.setLoop(false);
        doGenerator(mainTemplateConfig);
    }
}
