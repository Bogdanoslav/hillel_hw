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
        String yToJStr =  convertYamlToJson(args[0]);
        String jToY1Str = convertJsonToYaml1(args[1]);
        String jToY2Str = convertJsonToYaml2(args[1]);
        try {
            writer = new BufferedWriter(new FileWriter("converted_yaml.json"));
            writer.write(yToJStr);
            writer.close();
            writer = new BufferedWriter(new FileWriter("converted_json1.yaml"));
            writer.write(jToY1Str);
            writer.close();
            writer = new BufferedWriter(new FileWriter("converted_json2.yaml"));
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

