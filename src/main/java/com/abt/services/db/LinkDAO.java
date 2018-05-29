package com.abt.services.db;

import com.abt.services.api.Link;
import com.abt.services.db.mapper.LinkMapper;
import org.skife.jdbi.v2.exceptions.UnableToExecuteStatementException;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

/**
 * @author Abhishek Tyagi [abhishek.tyagi@zoomcar.com]
 * @since 1.0.0
 * Part of urlshortner
 * on 29/05/18 7:17 PM.
 */
@RegisterMapper(LinkMapper.class)
public interface LinkDAO {

    @SqlQuery("SELECT * FROM LINKS WHERE CODE=:code AND EXPIRY > NOW() AND STATUS =1 ")
    Link get(@Bind("code") String code);

    @SqlQuery("SELECT * FROM LINKS WHERE ID=:id ")
    Link get(@Bind("id") int id);

    @SqlUpdate("INSERT INTO LINKS (UUID, TENANT, CODE, URL, EXPIRY, STATUS) " +
            "VALUES (UUID(), :tenant, :code, :url, :expiry, :status)")
    int save(@BindBean Link link) throws UnableToExecuteStatementException;
}
