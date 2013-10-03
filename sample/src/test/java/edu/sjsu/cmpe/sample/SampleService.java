package edu.sjsu.cmpe.sample;

import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.sjsu.cmpe.sample.resource.SampleObject;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class SampleService {

  public static void main(String[] args) {

    // Configuration
    Writer file = null;
    Configuration cfg = new Configuration();

    try {
      // Set Directory for templates
      cfg.setDirectoryForTemplateLoading(new File("templates"));
      // load template
      Template template = cfg.getTemplate("hello.ftl");

      // data-model
      Map<String, Object> input = new HashMap<String, Object>();
      input.put("message", "vogella example");
      input.put("container", "test");

      // create list
      List<SampleObject> systems = new ArrayList<SampleObject>();

      systems.add(new SampleObject("Android"));
      systems.add(new SampleObject("iOS States"));
      systems.add(new SampleObject("Ubuntu"));
      systems.add(new SampleObject("Windows7"));
      systems.add(new SampleObject("OS/2"));

      input.put("systems", systems);

      SampleObject exampleObject = new SampleObject("Java object");
      input.put("exampleObject", exampleObject);

      // File output
      file = new FileWriter(new File("output.html"));
      template.process(input, file);
      file.flush();

      // Also write output to console
      Writer out = new OutputStreamWriter(System.out);
      template.process(input, out);
      out.flush();

    } catch (Exception e) {
      System.out.println(e.getMessage());

    } finally {
      if (file != null) {
        try {
          file.close();
        } catch (Exception e2) {
        }
      }
    }

  }
} 