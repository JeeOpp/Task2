package service.impl;

import dao.DAOFactory;
import dao.EntityDAO;
import dao.impl.EntityDAOImpl;
import entity.Entity;
import service.ParserService;

import java.io.IOException;
import java.util.List;

/**
 * Created by DNAPC on 21.10.2017.
 */
public class ParserServiceImpl implements ParserService{
    public Entity parseXML() throws IOException{
        DAOFactory daoFactory = DAOFactory.getInstance();
        EntityDAO entityDAO = daoFactory.getEntityDAO();
        return entityDAO.parseXML();
    }
}
