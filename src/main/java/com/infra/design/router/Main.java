package com.infra.design.router;

import java.io.InputStream;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.infra.design.router.model.Courser;

import org.yaml.snakeyaml.Yaml;

public class Main {

  private void readFileAsBean()
  {
    InputStream is = this.getClass()
    .getClassLoader()
    .getResourceAsStream("router.yaml");

    Yaml yaml = new Yaml(new Constructor(RouteFilter.class));
    RouteFilter router = yaml.load(is);
    System.out.println(router);  
  }
  public static void main(String[] args) {

    try{

      InputStream inputStream = new FileInputStream(new File("./src/main/resources/router.yaml"));
      Yaml yaml = new Yaml();
      Map<String, Object> data = yaml.load(inputStream);
      System.out.println(data.size());  
      System.out.println(data);  
    }
    catch(FileNotFoundException e) {
      e.printStackTrace();
    }

      Main myMain = new Main();
      //myMain.ReadYamlAsBeanWithNestedClass();
      myMain.readFileAsBean();

      //catch(IOException e) {
    //  System.out.println(e);
    //}
  }

  public void ReadYamlAsBeanWithNestedClass(){
    InputStream inputStream = this.getClass()
            .getClassLoader()
            .getResourceAsStream("just_courses.yml");
    Yaml yaml = new Yaml(new Constructor(Courser.class));
    Courser data = yaml.load(inputStream);
    System.out.println(data);
}

}