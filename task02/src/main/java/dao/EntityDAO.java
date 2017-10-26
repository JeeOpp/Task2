package dao;

import entity.Entity;

import java.io.FileNotFoundException;

/**
 * Created by DNAPC on 21.10.2017.
 */
public interface EntityDAO {
    Entity parseXML(String PATH) throws FileNotFoundException;
}
