package com.junfly.water.utils;

import com.junfly.water.entity.sys.Column;
import com.junfly.water.entity.sys.Table;
import com.junfly.water.entity.vo.gen.GenCodeVO;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器   工具类
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月19日 下午11:40:24
 */
public class GenUtils {

    public static List<String> getTemplates() {
        List<String> templates = new ArrayList<String>();
        templates.add("Entity.java.ftl");
        templates.add("Mapper.java.ftl");
        templates.add("Mapper.xml.ftl");
        templates.add("Service.java.ftl");
        templates.add("ServiceImpl.java.ftl");
        templates.add("Rest.java.ftl");
        templates.add("list.vue.ftl");
        templates.add("menu.sql.ftl");
        return templates;
    }

    /**
     * 生成代码
     */
    public static void generatorCode(Map<String, String> table,
                                     List<Map<String, String>> columns, ZipOutputStream zip, freemarker.template.Configuration configuration, GenCodeVO genCodeVO) {
        //配置信息
        Configuration config = getConfig();

        //表信息
        Table tableEntity = new Table();
        tableEntity.setTableName(table.get("tableName"));
        tableEntity.setComments(table.get("tableComment" +
                ""));
        //表名转换成Java类名
        String className = tableToJava(tableEntity.getTableName(), config.getString("tablePrefix"));
        tableEntity.setClassName(className);
        tableEntity.setClassname(StringUtils.uncapitalize(className));

        //列信息
        List<Column> columsList = new ArrayList<>();
        for (Map<String, String> column : columns) {
            Column columnEntity = new Column();
            columnEntity.setColumnName(column.get("columnName"));
            columnEntity.setDataType(column.get("dataType"));
            columnEntity.setComments(column.get("columnComment"));
            columnEntity.setExtra(column.get("extra"));

            //列名转换成Java属性名
            String attrName = columnToJava(columnEntity.getColumnName());
            columnEntity.setAttrName(attrName);
            columnEntity.setAttrname(StringUtils.uncapitalize(attrName));

            //列的数据类型，转换成Java类型
            String attrType = config.getString(columnEntity.getDataType(), "unknowType");
            columnEntity.setAttrType(attrType);

            //是否主键
            if ("PRI".equalsIgnoreCase(column.get("columnKey")) && tableEntity.getPk() == null) {
                tableEntity.setPk(columnEntity);
            }

            columsList.add(columnEntity);
        }
        tableEntity.setColumns(columsList);

        //没主键，则第一个字段为主键
        if (tableEntity.getPk() == null) {
            tableEntity.setPk(tableEntity.getColumns().get(0));
        }

        //封装模板数据
        Map<String, Object> map = new HashMap<>();
        map.put("tableName", tableEntity.getTableName());
        map.put("comments", tableEntity.getComments());
        map.put("pk", tableEntity.getPk());
        map.put("className", tableEntity.getClassName());
        map.put("classname", tableEntity.getClassname());
        map.put("pathName", tableEntity.getClassname().toLowerCase());
        map.put("columns", tableEntity.getColumns());
        map.put("package", config.getString("package"));
        map.put("author", config.getString("author"));
        map.put("email", config.getString("email"));
        map.put("datetime", DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
        if (StringUtils.isNotEmpty(genCodeVO.getPackageName())) {
            map.put("packageName", genCodeVO.getPackageName());
        }

        //获取模板列表
        List<String> templates = getTemplates();
        for (String template : templates) {
            //渲染模板
            StringWriter sw = new StringWriter();
            try {
                Template t = configuration.getTemplate(template);
                t.process(map, sw);

                //添加到zip
                zip.putNextEntry(new ZipEntry(getFileName(template, tableEntity.getClassName(), config.getString("package"), genCodeVO)));
                IOUtils.write(sw.toString(), zip, "UTF-8");
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            } catch (IOException e) {
                throw new RRException("渲染模板失败，表名：" + tableEntity.getTableName(), e);
            } catch (TemplateException e) {
                throw new RRException("渲染模板失败，表名：" + tableEntity.getTableName(), e);
            }
        }
    }


    /**
     * 列名转换成Java属性名
     */
    public static String columnToJava(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
    }

    /**
     * 表名转换成Java类名
     */
    public static String tableToJava(String tableName, String tablePrefix) {
        if (StringUtils.isNotBlank(tablePrefix)) {
            tableName = tableName.replace(tablePrefix, "");
        }
        return columnToJava(tableName);
    }

    /**
     * 获取配置信息
     */
    public static Configuration getConfig() {
        try {
            return new PropertiesConfiguration("generator.properties");
        } catch (ConfigurationException e) {
            throw new RRException("获取配置文件失败，", e);
        }
    }

    /**
     * 获取文件名
     */
    public static String getFileName(String template, String className, String packageName, GenCodeVO genCodeVO) {
        String packagePath = "main" + File.separator + "java" + File.separator;
        if (StringUtils.isNotBlank(packageName)) {
            packagePath += packageName.replace(".", File.separator) + File.separator;
        }

        String selfPackageName = "";
        if (StringUtils.isNotBlank(genCodeVO.getPackageName())) {
            selfPackageName += genCodeVO.getPackageName() + File.separator;
        }

        if (template.contains("Entity.java.ftl")) {
            return packagePath + "entity" + File.separator + selfPackageName + className + ".java";
        }

        if (template.contains("Mapper.java.ftl")) {
            return packagePath + "mapper" + File.separator + selfPackageName + className + "Mapper.java";
        }

        if (template.contains("Service.java.ftl")) {
            return packagePath + "service" + File.separator + selfPackageName + className + "Service.java";
        }

        if (template.contains("ServiceImpl.java.ftl")) {
            return packagePath + "service" + File.separator + selfPackageName + "impl" + File.separator + className + "ServiceImpl.java";
        }

        if (template.contains("Rest.java.ftl")) {
            return packagePath + "adminapi" + File.separator + selfPackageName + className + "Rest.java";
        }

        if (template.contains("Mapper.xml.ftl")) {
            return "main" + File.separator + "resources" + File.separator + "mappers" + File.separator + selfPackageName + className + "Mapper.xml";
        }

        if (template.contains("list.vue.ftl")) {
            return "main" + File.separator + "resources" + File.separator + "static" + File.separator
                    + "generator" + File.separator + selfPackageName + className.toLowerCase() + ".vue";
        }

        if (template.contains("menu.sql.ftl")) {
            return className.toLowerCase() + "_menu.sql";
        }

        return null;
    }
}
