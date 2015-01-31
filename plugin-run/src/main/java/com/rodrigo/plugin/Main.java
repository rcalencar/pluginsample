package com.rodrigo.plugin;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.rodrigo.pluginsample.SampleServiceFactory;

public class Main {

    // to run got to ./pluginsample/plugin-run/target
    //java -cp plugin-run-0.0.1-SNAPSHOT-jar-with-dependencies.jar com.rodrigo.plugin.Main
    
    public static void main(String[] args) {
        System.out.println("running...");

        try {
            InputStream is = new Main().getClass().getClassLoader().getResourceAsStream("services.properties");
            Properties p = new Properties(System.getProperties());
            p.load(is);
            System.setProperties(p);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SampleServiceFactory.getService("A").query("5");
        SampleServiceFactory.getService("B").query("142142");
    }
}
