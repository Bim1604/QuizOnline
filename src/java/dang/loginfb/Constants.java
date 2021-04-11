/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dang.loginfb;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class Constants implements Serializable{

    public static String FACEBOOK_APP_ID = "133206675390895";
    public static String FACEBOOK_APP_SECRET = "303babc548b1074c7e3d6906721b09eb";
    public static String FACEBOOK_LINK_GET_TOKEN = "https://graph.facebook.com/oauth/access_token?client_id=%s&client_secret=%s&redirect_uri=%s&code=%s";
}
