package entity;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DNAPC on 21.10.2017.
 */
public class Entity implements Printable {
    private List<Entity> children;
    private Entity parent = null;

    private String name = null;
    private String value = null;
    private Map<String, String> attributes;

    public Entity() {
        children = new ArrayList<>();
        attributes = new HashMap<>();
    }

    public List<Entity> getChildren() {
        return children;
    }

    public Entity getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setParent(Entity parent) {
        this.parent = parent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void print(PrintStream printStream, String tab) {
        if (!attributes.isEmpty()){
            for (Map.Entry attribute:attributes.entrySet()){
                printStream.print(tab+attribute.getKey()+" = "+attribute.getValue()+" ");
            }
            printStream.println();
        }
        if(!children.isEmpty()){
            tab+="   ";
            for (Entity child:children){
                child.print(printStream,tab);
            }
        }
        if(value!=null){
            printStream.println(tab+name+" = "+value);
        }
    }
}


