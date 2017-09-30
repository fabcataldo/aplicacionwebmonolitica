package com.fabiocompany.supermercadosdeltaplus.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

import com.fabiocompany.supermercadosdeltaplus.app.config.ContextConfig;
import com.fabiocompany.supermercadosdeltaplus.app.config.PersistenceConfig;
import com.fabiocompany.supermercadosdeltaplus.app.setup.MyApplicationContextInitializer;

/**
 * 
 * @author magm
 *
 */
@SpringBootApplication
@Import({ 
    ContextConfig.class,
    PersistenceConfig.class
}) 
public class Application extends SpringBootServletInitializer {
    

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
    

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).initializers(new MyApplicationContextInitializer()).run(args);
    }
    
}
