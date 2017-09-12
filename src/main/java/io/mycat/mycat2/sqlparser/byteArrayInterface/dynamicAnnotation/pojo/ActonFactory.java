package io.mycat.mycat2.sqlparser.byteArrayInterface.dynamicAnnotation.pojo;

import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/**
 * Created by jamie on 2017/9/10.
 */
public class ActonFactory {
 public static Acton  get(String name){
return null;
 }

 public static void main(String[] args) throws Exception{
//  Constructor constructor = new Constructor(Matches.class);
//  TypeDescription annotationsDescription = new TypeDescription(Matches.class);
//  annotationsDescription.putListPropertyType("actions", Object.class);
//  constructor.addTypeDescription(annotationsDescription);
  Yaml yaml = new Yaml();
  Object object = yaml.load(new FileInputStream(new File("D:\\SQLparserNew\\src\\main\\resources\\actions.yaml")));
  System.out.println(object.toString());
 }
}
