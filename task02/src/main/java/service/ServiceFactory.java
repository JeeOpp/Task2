package service;

import service.impl.ParserServiceImpl;

/**
 * Created by DNAPC on 21.10.2017.
 */
public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final ParserService parserService = new ParserServiceImpl();

    private ServiceFactory(){}

    public ParserService getParserService(){
        return parserService;
    }
    public static ServiceFactory getInstance(){
        return instance;
    }
}
