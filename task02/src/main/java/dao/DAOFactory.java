package dao;

import dao.impl.EntityDAOImpl;

/**
 * Created by DNAPC on 21.10.2017.
 */
public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private final EntityDAO entityDAO = new EntityDAOImpl();

    private DAOFactory(){}
    public EntityDAO getEntityDAO(){
        return entityDAO;
    }
    public static DAOFactory getInstance(){
        return instance;
    }
}
