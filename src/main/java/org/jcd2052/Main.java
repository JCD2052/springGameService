package org.jcd2052;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jcd2052.models.Genre;
import org.jcd2052.models.DeveloperStudio;
import org.jcd2052.models.GameInfo;
import org.jcd2052.models.Platform;


public class Main {
    public static void main(String[] args) {

        Configuration configuration = new Configuration()
                .addAnnotatedClass(Genre.class)
                .addAnnotatedClass(GameInfo.class)
                .addAnnotatedClass(Platform.class)
                .addAnnotatedClass(DeveloperStudio.class);

        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();


            GameInfo gameInfo = session.get(GameInfo.class, 32);
            System.out.println(gameInfo.getPlatforms());





//            DeveloperStudio developerStudio = session.get(DeveloperStudio.class, 1);
//            System.out.println(developerStudio);
//            System.out.println(developerStudio.getGames());

//            Category category = session.get(Category.class, 1);
//            System.out.println(category);
//            System.out.println(category.getGames());

////            System.out.println(gameInfo.getPlatforms());
////            System.out.println(gameInfo.getCategory());
////            System.out.println(gameInfo);
//
//            Platform platform = new Platform("Playstation 2");
//            platform.setGames(new ArrayList<>());
//            session.save(platform);
//            gameInfo.getPlatforms().add(platform);
//            platform.getGames().add(gameInfo);

            session.getTransaction().commit();
        }
    }
}
