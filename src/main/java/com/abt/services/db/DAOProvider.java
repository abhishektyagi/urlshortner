package com.abt.services.db;

import com.abt.services.UrlShortnerConfiguration;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Abhishek Tyagi [abhishek.tyagi@zoomcar.com]
 * @since 1.0.8
 * Part of urlshortner
 * on 29/05/18 6:48 PM.
 */
public class DAOProvider {
    private Environment environment;
    private DBI dbi;
    private UrlShortnerConfiguration configuration;
    private static DAOProvider ourInstance = new DAOProvider();

    public static DAOProvider getInstance() {
        return ourInstance;
    }
    private Map<String, Object> daoMap;

    private DAOProvider() {

    }

    public void init(Environment env, UrlShortnerConfiguration configuration) {
        if (this.configuration == null && environment == null) {
            this.configuration = configuration;
            this.environment = env;
            DBIFactory dbiFactory = new DBIFactory();
            dbi = dbiFactory.build(env, configuration.getDataSourceFactory(), "URL_SHORT_DB");

            daoMap = Collections.synchronizedMap(new HashMap<>());
            populateDAOMap();

        }
    }

    private void populateDAOMap() {
        if(daoMap!=null){
            daoMap.put(LinkDAO.class.getCanonicalName(), dbi.onDemand(LinkDAO.class));
        }
    }

    public LinkDAO getLinkDAO(){
        return (LinkDAO) daoMap.get(LinkDAO.class.getCanonicalName());
    }
}
