//package mybatis.generator;
//
//import org.mybatis.generator.api.CommentGenerator;
//import org.mybatis.generator.api.IntrospectedColumn;
//import org.mybatis.generator.api.IntrospectedTable;
//import org.mybatis.generator.api.MyBatisGenerator;
//import org.mybatis.generator.api.dom.java.*;
//import org.mybatis.generator.api.dom.kotlin.KotlinFile;
//import org.mybatis.generator.api.dom.kotlin.KotlinFunction;
//import org.mybatis.generator.api.dom.kotlin.KotlinProperty;
//import org.mybatis.generator.api.dom.kotlin.KotlinType;
//import org.mybatis.generator.api.dom.xml.XmlElement;
//import org.mybatis.generator.config.Configuration;
//import org.mybatis.generator.config.xml.ConfigurationParser;
//import org.mybatis.generator.exception.InvalidConfigurationException;
//import org.mybatis.generator.exception.XMLParserException;
//import org.mybatis.generator.internal.DefaultShellCallback;
//
//import java.io.File;
//import java.io.IOException;
//import java.sql.SQLException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//public class SwaggerCommentGenerator implements CommentGenerator {
//
//
//    @Override
//    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
//        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
//        // 获取表注释
//        String remarks = introspectedTable.getRemarks();
//        topLevelClass.addImportedType("io.swagger.annotations.ApiModel");
//        topLevelClass.addImportedType("io.swagger.annotations.ApiModelProperty");
//        topLevelClass.addJavaDocLine("/**");
//        topLevelClass.addJavaDocLine(" * " + remarks);
//        topLevelClass.addJavaDocLine(" *");
//        topLevelClass.addJavaDocLine(" * @date " + dateFormatter.format(new Date()));
//        topLevelClass.addJavaDocLine(" */");
//        topLevelClass.addAnnotation("@ApiModel(\""+remarks+"\")");
//    }
//
//    @Override
//    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
//        // 获取列注释
//        String remarks = introspectedColumn.getRemarks();
//        field.addJavaDocLine("/**");
//        field.addJavaDocLine(" * " + remarks);
//        field.addJavaDocLine(" */");
//        field.addAnnotation("@ApiModelProperty(\""+remarks+"\")");
//    }
//    @Override
//    public void addModelClassComment(KotlinType modelClass, IntrospectedTable introspectedTable) {
//    }
//
//    @Override
//    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
//
//    }
//
//    @Override
//    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean b) {
//
//    }
//
//    @Override
//    public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
//
//    }
//
//    @Override
//    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
//
//    }
//
//    @Override
//    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
//
//    }
//
//    @Override
//    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
//
//    }
//
//    @Override
//    public void addJavaFileComment(CompilationUnit compilationUnit) {
//    }
//
//    @Override
//    public void addComment(XmlElement xmlElement) {
//    }
//
//    @Override
//    public void addRootComment(XmlElement xmlElement) {
//
//    }
//
//    @Override
//    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> set) {
//
//    }
//
//    @Override
//    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> set) {
//
//    }
//
//    @Override
//    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> set) {
//    }
//
//    @Override
//    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> set) {
//    }
//
//    @Override
//    public void addClassAnnotation(InnerClass innerClass, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> set) {
//    }
//
//    @Override
//    public void addFileComment(KotlinFile kotlinFile) {
//
//    }
//
//    @Override
//    public void addGeneralFunctionComment(KotlinFunction kf, IntrospectedTable introspectedTable, Set<String> imports) {
//
//    }
//
//    @Override
//    public void addGeneralPropertyComment(KotlinProperty property, IntrospectedTable introspectedTable, Set<String> imports) {
//
//    }
//
//    @Override
//    public void addConfigurationProperties(Properties properties) {
//
//    }
//
//
//
//    @Override
//    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
//    }
//}
