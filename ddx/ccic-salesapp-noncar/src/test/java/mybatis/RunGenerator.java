package mybatis;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RunGenerator {
    public static void main(String[] args) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        List<String> warnings = new ArrayList<>();
        String configPath = java.net.URLDecoder.decode(RunGenerator.class.getClassLoader().getResource("generatorConfig.xml").getPath().replaceAll("%20",""),"utf-8");//解决路径中含有空格的情况
        File configFile = new File(configPath);
        ConfigurationParser configParser = new ConfigurationParser(warnings);
        Configuration config = configParser.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(false);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
}
