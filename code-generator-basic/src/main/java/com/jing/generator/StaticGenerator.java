package com.jing.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * 静态文件生成器
 */
public class StaticGenerator {
    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        //  输入路径
        String inputPath = projectPath + File.separator + "code-generator-demo-project" + File.separator + "acm-template";
        // 输出路径
        String outputPath = projectPath;
        copyFilesByHutool(inputPath, outputPath);


    }

    /**
     * 拷贝文件实现会将输入的目录完整拷贝到输出目录下(Hutool实现)
     *
     * @param inputPath
     * @param outputPath
     */
    public static void copyFilesByHutool(String inputPath, String outputPath) {
        FileUtil.copy(inputPath, outputPath, false);
    }

    /**
     * 递归拷贝文件(递归实现,会将输入的完整目录拷贝到输出目录下)
     *
     * @param inputPath
     * @param outputPath
     */
    public static void copyFilesByRecursive(String inputPath, String outputPath) {
        File inputFile = new File(inputPath);
        File outputFile = new File(outputPath);
        try {
            copyFileByRecursive(inputFile, outputFile);
        } catch (IOException e) {
            System.err.println("文件复制失败");
            throw new RuntimeException(e);
        }
    }

    /**
     * 核心思路: 先创建目录,然后遍历目录内的文件,依次复制
     *
     * @param inputFile
     * @param outputFile
     * @throws IOException
     */
    public static void copyFileByRecursive(File inputFile, File outputFile) throws IOException {
        // 区分是文件还是目录
        if (inputFile.isDirectory()) {
            File destOutputFile = new File(outputFile, inputFile.getName());
            // 如果是目录,且不存在,首先创建目标目录
            if (!destOutputFile.exists()) {
                destOutputFile.mkdirs();
            }
            // 获取目录下所有的文件和子目录
            File[] files = inputFile.listFiles();
            // 无子文件,直接结束
            if (ArrayUtil.isEmpty(files)) {
                return;
            }
            for (File file : files) {
                // 递归拷贝下一层文件
                copyFileByRecursive(file, destOutputFile);
            }
        } else {
            // 是文件,直接复制到目标目录下
            Path destPath = outputFile.toPath().resolve(inputFile.getName());
            Files.copy(inputFile.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
