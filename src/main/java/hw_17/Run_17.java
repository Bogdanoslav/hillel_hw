package hw_17;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.yaml.JacksonYAMLParseException;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import javax.imageio.IIOException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Run_17 {
    public static void main(String[] args) {
        BufferedWriter writer;
        String yToJStr =  convertYamlToJson("src/main/java/hw_17/" + args[0] + ".yaml");
        String jToY1Str = convertJsonToYaml1("src/main/java/hw_17/" + args[1] + ".json");
        String jToY2Str = convertJsonToYaml2("src/main/java/hw_17/" + args[1] + ".json");
        File yToJFile = new File("src/main/java/hw_17/" + args[0] + ".json");
        File jToY1File = new File("src/main/java/hw_17/" + args[1] + "1.yaml");
        File jToY2File = new File("src/main/java/hw_17/" + args[1] + "2.yaml");
        String str = "Hello";
        try {
            writer = new BufferedWriter(new FileWriter(yToJFile));
            writer.write(yToJStr);
            writer.close();
            writer = new BufferedWriter(new FileWriter(jToY1File));
            writer.write(jToY1Str);
            writer.close();
            writer = new BufferedWriter(new FileWriter(jToY2File));
            writer.write(jToY2Str);
            writer.close();
        } catch(IOException e){
            System.out.println("Ошибка при записи файла");
        }

    }
    public static String convertYamlToJson(String path) {
        ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
        ObjectMapper jsonWriter = new ObjectMapper();
        try {
            Object obj = yamlReader.readValue(new File(path), Object.class);
            return jsonWriter.writeValueAsString(obj);
        } catch (JacksonYAMLParseException e){
            System.out.println("Невалидный формат YAML\n" + e);
        } catch (IOException e){
            System.out.println("Такого файла нет");
        }
        return  "Произошла ошибка при конвертации";
    }
    public static String convertJsonToYaml1(String path){
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get(path)));;
            JsonNode jsonNodeTree = new ObjectMapper().readTree(jsonString);
            String jsonAsYaml = new YAMLMapper().writeValueAsString(jsonNodeTree);
            return jsonAsYaml;
        } catch (JsonProcessingException e){
            System.out.println("Невалидный формат JSON\n" + e);
        } catch (IOException e){
            System.out.println("Такого файла нет");
        }
        return  "Произошла ошибка при конвертации";
    }
    public static String convertJsonToYaml2(String path)  {
        ObjectMapper jsonReader = new ObjectMapper(new JsonFactory());
        ObjectMapper yamlWriter = new ObjectMapper(new YAMLFactory());
        try{
            Object obj = jsonReader.readValue(new File(path), Object.class);
            return  yamlWriter.writeValueAsString(obj);
        }  catch (JsonProcessingException e){
            System.out.println("Невалидный формат JSON\n" + e);
        } catch (IOException e){
            System.out.println("Такого файла нет");
        }
        return  "Произошла ошибка при конвертации";
    }

}

