package service;

import entity.Entity;

import java.io.IOException;
import java.util.List;

/**
 * Created by DNAPC on 21.10.2017.
 */
public interface ParserService {
    Entity parseXML(String PATH) throws IOException;
}
