package com.jing.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.jing.generator.MainGenerator;
import com.jing.model.MainTemplateConfig;
import freemarker.template.TemplateException;
import lombok.Data;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.IOException;
import java.util.concurrent.Callable;

@Command(name = "generate" , description = "生成代码",mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Callable<Integer> {
    @Option(names = {"-l" , "--loop"},arity = "0..1",description = "是否循环",interactive = true,echo = true)
    private boolean loop;

    @Option(names = {"-a" , "--author"},arity = "0..1",description = "作者",interactive = true,echo = true)
    private String author = "jing";

    @Option(names = {"-o" , "--outputText"},arity = "0..1",description = "输出文本",interactive = true,echo = true)
    private String outputText = "sum = ";

    @Override
    public Integer call() throws TemplateException, IOException {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        BeanUtil.copyProperties(this,mainTemplateConfig);
        MainGenerator.doGenerator(mainTemplateConfig);
        return null;
    }
}
