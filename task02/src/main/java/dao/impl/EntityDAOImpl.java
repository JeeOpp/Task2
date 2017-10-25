package dao.impl;

import dao.EntityDAO;
import entity.Entity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Arrays.asList;


/**
 * Created by DNAPC on 21.10.2017.
 */
public class EntityDAOImpl implements EntityDAO {
    public static final String PATH = "src/main/resources/misc.xml";

    public EntityDAOImpl(){}

    public Entity parseXML(){
        Entity entity;
        String lineXML;
        List<String> linesXML;
        if((lineXML = readXML()).isEmpty()){
            return null;
        }else{
            linesXML = splitLineXML(lineXML);
            entity = createTreeStructure(linesXML);
        }
        return entity;
    }

    private Entity createTreeStructure(List<String> linesXML){
        Entity root = null;
        Entity parent = null;
        Entity current = null;

        for(String lineXML:linesXML) {
            if (isOpeningTag(lineXML)) {
                if (root == null) {
                    root = new Entity();
                    current = root;
                    fillWithNameAndAttributes(current, lineXML);
                } else {
                    parent = current;
                    current = new Entity();
                    current.setParent(parent);
                    current.getParent().getChildren().add(current);
                    fillWithNameAndAttributes(current, lineXML);
                }
            }else{
                if (!isClosingTag(lineXML)) {
                    fillValues(current, lineXML);
                }
                current = parent;
                if (parent!=null && parent.getParent()!=null)
                    parent = parent.getParent();
            }
        }
        return root;
    }
    private boolean isOpeningTag(String lineXML){
        return (lineXML.charAt(0)=='<' && lineXML.charAt(1)!='/');
    }

    private boolean isClosingTag(String lineXML){
        return (lineXML.charAt(0)=='<' && lineXML.charAt(1)=='/');
    }

    private void fillWithNameAndAttributes(Entity current, String lineXML) {
        lineXML = lineXML.substring(1,lineXML.length()-1);
        List<String> splittedLineXML = new ArrayList<String>(asList(lineXML.split(" ")));
        current.setName(splittedLineXML.get(0));
        if (splittedLineXML.size() > 1) {
            splittedLineXML.remove(0);
            for (String attributes : splittedLineXML) {
                String[] splittedAttributes = attributes.split("=");
                String key = splittedAttributes[0];
                String value = splittedAttributes[1].substring(1,splittedAttributes[1].length()-1);
                current.getAttributes().put(key,value);
            }
        }
    }

    private void fillValues(Entity current, String lineXML){
        current.setValue(lineXML.split("<")[0]);
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
}
