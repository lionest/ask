package com.ah3nong.wd.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreeMarkerUtil {
    public static void createHtml(ServletContext context, Map<String, Object> data,String templatePath,String targetHtmlPath,String ftl,String fileName) {  
        Configuration cf = new Configuration();  
        cf.setServletContextForTemplateLoading(context, templatePath);  
        cf.setEncoding(Locale.getDefault(), "utf-8");  
        Template template = null;  
        try {  
            template = cf.getTemplate(ftl);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        template.setEncoding("utf-8");  
        String path = context.getRealPath(targetHtmlPath);  
        String htmlFilePath = path +"/"+ fileName;  
        File file = new File(htmlFilePath);  
        
        try {  
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"utf-8"));  
            template.process(data, out);  
        } catch (Exception e) {
            e.printStackTrace();  
        }  
    }  
}
