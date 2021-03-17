package com.beauty.shopping.mbg;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wuzhenxian
 * @date 2021/03/03
 * 用于自动生成xml的代码
 */
@Slf4j
public class Generator {

    public static void generateMapper() {
        try {
            //MBG 执行过程中的警告信息
            List<String> warnings = new ArrayList<>();
            //读取MBG的配置文件
            InputStream inputStream = Generator.class.getResourceAsStream("/generatorConfig.xml");
            ConfigurationParser configurationParser = new ConfigurationParser(warnings);
            Configuration configuration = configurationParser.parseConfiguration(inputStream);
            inputStream.close();

            //当生成的代码重复时，覆盖原代码
            DefaultShellCallback callback = new DefaultShellCallback(true);
            //创建MBG
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration, callback, warnings);
            myBatisGenerator.generate(null);
        } catch (Exception e) {
            log.error("自动生成MBG失败", e);
        }
    }
}
