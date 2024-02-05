package com.jing.cli.command;

import cn.hutool.core.util.ReflectUtil;
import com.jing.model.MainTemplateConfig;
import picocli.CommandLine.Command;

import java.lang.reflect.Field;

@Command(name = "config" , description = "查看参数信息",mixinStandardHelpOptions = true)
public class ConfigCommand implements Runnable{
    @Override
    public void run() {
        // 获取要打印属性信息的类
        Class<MainTemplateConfig>myClass = MainTemplateConfig.class;
        // 获取类的所有字段
        Field[] fields = ReflectUtil.getFields(MainTemplateConfig.class);
        //遍历打印每个字段信息
        for(Field field: fields ){
            System.out.println("字段名称" + field.getName());
            System.out.println("字段类型" + field.getType());
            System.out.println("---");
        }

    }
}
