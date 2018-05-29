package com.abt.services.db.mapper;

import com.abt.services.api.Link;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author Abhishek Tyagi [abhishek.tyagi@zoomcar.com]
 * @since 1.0.8
 * Part of urlshortner
 * on 29/05/18 7:53 PM.
 */
public class LinkMapper implements ResultSetMapper<Link> {

    @Override
    public Link map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        int id = r.getInt("ID");
        String uuid = r.getString("UUID");
        String tenant = r.getString("TENANT");
        String code = r.getString("CODE");
        String url = r.getString("URL");
        Date expiry = r.getTimestamp("EXPIRY");
        Date createdAt = r.getTimestamp("CREATED_AT");

        return new Link(id, uuid, tenant, code, url, expiry, createdAt);
    }
}
