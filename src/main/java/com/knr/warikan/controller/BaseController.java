package com.knr.warikan.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.knr.warikan.config.Settings;

@Controller
public class BaseController {
	
	@Autowired
	private Settings settings;

    public boolean originHeaderCheck(HttpServletRequest request) {
    	//originヘッダが存在しているかどうかのチェック
    	boolean isOriginHeaderExist = false;
    	Enumeration<?> headerNames = request.getHeaderNames();
    	while (headerNames.hasMoreElements()) {
    	    String headerName = (String)headerNames.nextElement();
    	    if (headerName.equals("origin")) {
    	    	isOriginHeaderExist = true;
    	    	break;
    	    }
    	}
    	//Originヘッダが存在しており、かつホスト名と不一致の場合はエラー
    	System.out.println(request.getHeader("origin"));
    	System.out.println(settings.getHost());
    	if(isOriginHeaderExist && !(request.getHeader("origin").equals(settings.getHost()))) {
    		return false;
    	}
    	return true;
    }

}
