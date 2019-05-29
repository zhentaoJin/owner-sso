package com.jzt.sso.security;

import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;

public class UrlCache {

    public static String url;

    public static RequestCache requestCache = new HttpSessionRequestCache();

}
