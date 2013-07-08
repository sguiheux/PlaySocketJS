package fr.soart.impl.loading;

import fr.soart.engine.db.RefBusiness;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Chargement de l'application.
 */
@Component
public class LoadingBean implements ApplicationListener {

    @Resource(name = "mongoTemplate")
    private MongoOperations mongoOperation;


    private void findClasses(File directory, String packageName) throws ClassNotFoundException {
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                findClasses(file, packageName + "." + file.getName());
            } else if (file.getName().endsWith(".class")) {
                String classe = file.getName().replaceAll(".class", "");
                Class c = Class.forName(packageName + "." + classe);
                for (Annotation a : c.getAnnotations()) {
                    if (a.toString().contains("Business")) {
                        classe = classe.replaceFirst(classe.substring(0, 1), classe.substring(0, 1).toLowerCase());

                        Query searchBusinessQuery = new Query(Criteria.where("springName").is(classe));
                        RefBusiness savedBusiness = mongoOperation.findOne(searchBusinessQuery, RefBusiness.class);
                        if (savedBusiness == null) {
                            RefBusiness rb = new RefBusiness();
                            rb.setSpringName(classe);
                            mongoOperation.save(rb);
                        }


                        System.out.println(classe);
                    }

                }


            }
        }
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String packageName = "fr.soart.impl.business";
        String path = packageName.replace('.', '/');
        try {
            Enumeration<URL> resources = classLoader.getResources(path);
            List<File> dirs = new ArrayList<File>();
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                dirs.add(new File(resource.getFile()));

            }
            for (File directory : dirs) {
                findClasses(directory, packageName);
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }


}
