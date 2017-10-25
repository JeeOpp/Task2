package dao;

import entity.Entity;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by DNAPC on 25.10.2017.
 */
public class TreeCreator {
    private static final TreeCreator instance = new TreeCreator();

    private TreeCreator(){}

    public static TreeCreator getInstance(){
        return instance;
    }

    public Entity createTreeStructure(List<String> linesXML){
        Entity root = null;
        Entity parent = null;
        Entity current = null;

        root = new Entity();
        current = root;
        fillWithNameAndAttributes(current,linesXML.get(0));
        linesXML.remove(0);

        for(String lineXML:linesXML) {
            if (isOpeningTag(lineXML)){
                parent = current;
                current = new Entity();
                current.setParent(parent);
                current.getParent().getChildren().add(current);
                fillWithNameAndAttributes(current, lineXML);
                if (isSingleTag(lineXML)){
                    current = parent;
                    parent = changeParent(parent);
                }
            }else{
                if (!isClosingTag(lineXML)) {
                    fillValues(current, lineXML);
                }
                current = parent;
                parent = changeParent(parent);
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

    private boolean isSingleTag(String lineXML){
        return (lineXML.charAt(0)=='<' && lineXML.charAt(lineXML.length()-2)=='/');
    }

    private void fillWithNameAndAttributes(Entity current, String lineXML) {
        int margin = isSingleTag(lineXML)? 2:1;
        lineXML = lineXML.substring(1, lineXML.length() - margin);  //2
        List<String> splittedLineXML = new ArrayList<String>(asList(lineXML.split(" ")));
        current.setName(splittedLineXML.get(0));
        if (splittedLineXML.size() > 1) {
            splittedLineXML.remove(0);
            String[] splittedAttributes;
            for (String attributes : splittedLineXML) {
                splittedAttributes = attributes.split("=");
                String key = splittedAttributes[0];
                String value = splittedAttributes[1].substring(1,splittedAttributes[1].length()-1);
                current.getAttributes().put(key,value);
            }
        }
    }

    private void fillValues(Entity current, String lineXML){
        current.setValue(lineXML.split("<")[0]);
    }

    private Entity changeParent(Entity parent){
        if (parent!=null && parent.getParent()!=null) {
            parent = parent.getParent();
        }
        return parent;
    }
}
