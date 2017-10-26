package service;

import entity.Entity;

import java.io.IOException;

/**
 * Created by DNAPC on 21.10.2017.
 */
public interface ParserService {
    Entity parseXML(String path) throws IOException;
}
