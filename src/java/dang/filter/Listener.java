/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dang.filter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author Admin
 */
public class Listener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        FileReader f;
        BufferedReader br;
        String line;
        Map<String, String> map = new HashMap<>();
        ServletContext context = sce.getServletContext();
        String path = context.getRealPath("/WEB-INF/file");
        try {
            f = new FileReader(path);
            br = new BufferedReader(f);
            StringTokenizer str;
            while ((line = br.readLine()) != null) {
                str = new StringTokenizer(line, "=");
                String key = str.nextToken().trim();
                String value = str.nextToken();
                map.put(key, value);
            }
            context.setAttribute("MAP", map);
            br.close();
            f.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce
    ) {

    }
}
