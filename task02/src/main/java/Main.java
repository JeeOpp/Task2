import entity.Entity;
import service.ParserService;
import service.ServiceFactory;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        final String PATH = "locator.xml";

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ParserService parserService;
        try {
            parserService = serviceFactory.getParserService();
            Entity entity = parserService.parseXML(PATH);
            if(entity!=null) {
                entity.print(System.out, "");
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }

    }
}
