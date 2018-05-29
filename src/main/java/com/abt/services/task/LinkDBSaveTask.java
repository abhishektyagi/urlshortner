package com.abt.services.task;

import com.abt.services.api.Link;
import com.abt.services.db.DAOProvider;
import com.abt.services.db.LinkDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Abhishek Tyagi [abhishek.tyagi@zoomcar.com]
 * @since 1.0.8
 * Part of urlshortner
 * on 29/05/18 8:11 PM.
 */
public class LinkDBSaveTask implements Runnable{
    private static final Logger LOGGER = LoggerFactory.getLogger(LinkDBSaveTask.class);
    private final Link link;

    public LinkDBSaveTask(Link link) {
        this.link = link;
    }

    /**
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        LinkDAO linkDAO = DAOProvider.getInstance().getLinkDAO();
        try{
            int id = linkDAO.save(link);
            LOGGER.info("created in db: {}", linkDAO.get(id));

        } catch (Exception e){
            LOGGER.error("Error while saving LINK in db. link: {}\t",link,e);
        }
    }
}
