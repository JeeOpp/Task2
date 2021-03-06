package dao.impl;

import dao.EntityDAO;
import dao.TreeCreator;
import entity.Entity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by DNAPC on 21.10.2017.
 */
public class EntityDAOImpl implements EntityDAO {
    public EntityDAOImpl(){}

    public Entity parseXML(String path){
        Entity entity;
        String lineXML;
        List<String> linesXML;
        TreeCreator treeCreator = TreeCreator.getInstance();
        if((lineXML = readXML(path)).isEmpty()){
            return null;
        }else{
            linesXML = splitLineXML(lineXML);
            entity = treeCreator.createTreeStructure(linesXML);
        }
        return entity;
    }

    private String readXML(String path) {
        BufferedReader bufferedReader;
        String line="";
        String currentLine="";
        try {
            bufferedReader = new BufferedReader(new FileReader(new File(path)));
            while ((currentLine = bufferedReader.readLine()) != null) {
                line+=currentLine.trim();
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return line;
    }

    private List<String> splitLineXML(String lineXML){
        String patternLine = "<?[^>]*>";
        String xmlDeclaration = "<?xml";

        Pattern pattern = Pattern.compile(patternLine);
        Matcher matcher = pattern.matcher(lineXML);

        List<String> linesXML = new ArrayList<>();
        while (matcher.find()) {
            linesXML.add(lineXML.substring(matcher.start(), matcher.end()));
        }
        if(linesXML.get(0).contains(xmlDeclaration)) {
            linesXML.remove(0);
        }
        return linesXML;
    }
}
