package com.abt.services.util;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

/**
 * @author Abhishek Tyagi [abhishek.tyagi@zoomcar.com]
 * @since 1.0.8
 * Part of urlshortner
 * on 23/05/18 11:48 AM.
 */
public class StringTest {
    @Test
    public void testStringHasCode(){
        String url = "http://blog.xebia.com/how-to-dockerize-your-dropwizard-application/";

        try {
            boolean urlSafe = true;
            Base64 base64 = new Base64(urlSafe);
            String u = new String (base64.encode(url.getBytes("UTF-8")),"UTF-8");
            System.out.println("Direct: "+u);

            String str = new  String(base64.encode(BigInteger.valueOf(url.hashCode()).toByteArray()),"UTF-8");
            System.out.println(String.format(" Url: %s\t Hash:\t %s",url, str));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
