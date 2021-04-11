/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dang.logingoogle;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class Constants implements Serializable{
  public static String GOOGLE_CLIENT_ID = "166893396430-l76pm1f6cudof18ri04fp2os0tsvk0ch.apps.googleusercontent.com";
  public static String GOOGLE_CLIENT_SECRET = "pWHR41U3qFKHDmcqmLtXogRM";
  public static String GOOGLE_REDIRECT_URI = "http://localhost:8084/QuizOnline/LoginGoogleServlet";
  public static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";
  public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
  public static String GOOGLE_GRANT_TYPE = "authorization_code";
}
