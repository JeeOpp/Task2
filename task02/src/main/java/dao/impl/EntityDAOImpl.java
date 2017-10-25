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
    //public static final String PATH = "misc.xml";
    public static final String PATH = "./out/production/task02/misc.xml";

    public EntityDAOImpl(){}

    public Entity parseXML(){
        Entity entity;
        String lineXML;
        List<String> linesXML;
        TreeCreator treeCreator = TreeCreator.getInstance();
        if((lineXML = readXML()).isEmpty()){
            return null;
        }else{
            linesXML = splitLineXML(lineXML);
            entity = treeCreator.createTreeStructure(linesXML);
        }
        return entity;
    }

    private String readXML() {
        BufferedReader bufferedReader;
        String line="";
        String currentLine="";
        try {
            bufferedReader = new BufferedReader(new FileReader(new File(PATH)));
            while ((currentLine = bufferedReader.readLine()) != null) {
                line+=currentLine.trim();
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return line;
    }

    private List<String> splitLineXML(String lineXML){
        Pattern pattern = Pattern.compile("(<?[^>]*>)");
        Matcher matcher = pattern.matcher(lineXML);

        List<String> linesXML = new ArrayList<>();
        while (matcher.find()) {
            System.out.println(lineXML.substring(matcher.start(), matcher.end()));
            linesXML.add(lineXML.substring(matcher.start(), matcher.end()));
        }
        if(linesXML.get(0).contains("<?xml")) {
            linesXML.remove(0);
        }
        return linesXML;
    }
}
