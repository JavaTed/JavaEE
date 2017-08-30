package ua.kiev.prog.jpa.views;

import ua.kiev.prog.jpa.controllers.KeyboardReader;
import ua.kiev.prog.jpa.controllers.RestaurantMenuItemInputFactory;
import ua.kiev.prog.jpa.models.RestaurantMenuItem;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class MenuService {
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static CriteriaBuilder cb;
    private static CriteriaQuery<RestaurantMenuItem> cq;
    private static Root<RestaurantMenuItem> root;

    private static BufferedReader d = new BufferedReader(new InputStreamReader(System.in));

    private static void addMenuItem(){
        RestaurantMenuItem mi;
        if (RestaurantMenuItemInputFactory.isMenuItemEntered(d))
            mi = RestaurantMenuItemInputFactory.getEnteredMenuItem();
        else {
            System.out.println("You have refused menu item entering");
            return;
        }
        em.getTransaction().begin();
        em.persist(mi);
        System.out.println("new menu item ID "+mi.getId());
        em.getTransaction().commit();
    }

    private static void streamShow(Query query){
        query
                .getResultList()
                .stream()
                .forEach(System.out::println);
    }

    private static void showResults(CriteriaQuery<RestaurantMenuItem> cq){
        TypedQuery<RestaurantMenuItem> typedQuery = em.createQuery(cq);
        streamShow(typedQuery);
    }

    public static void viewMenuFilter1(){
        Double lb = KeyboardReader.readDoubleInfo(d,"Enter low boundary",false);
        Double hb = KeyboardReader.readDoubleInfo(d,"Enter high boundary",false);

        showResults(cq.where(cb.and(cb.greaterThanOrEqualTo(root.get("price"),lb),cb.lessThanOrEqualTo(root.get("price"),hb))));
    }

    public static void viewMenuFilter2(){
        showResults(cq.where(cb.greaterThan(root.get("discountPercent"),0.0)));
    }

    public static void viewMenuFilter3(){
        Random r = new Random(100);
        showResults(cq.orderBy(cb.asc(cb.function("RAND",Integer.class))));
        TypedQuery<RestaurantMenuItem> typedQuery = em.createQuery(cq);
        double[] x = {0};
        typedQuery
                .getResultList()
                .stream()
                .forEach(t->{if (x[0]+t.getWeight()<=1000)
                    System.out.println(t);
                    x[0] = x[0] +t.getWeight();}
                );
    }

    private static void viewMenu(){
        Query query = em.createNamedQuery("Item.AllItems",RestaurantMenuItem.class);
        streamShow(query);
    }

    private static String getMenuChoice(){
        return KeyboardReader.readStrInfo(d, getMenu(),"^(0|1|2|3|4|5|6)$");
    }

    private static String getMenu(){
        return new StringBuilder("1: add menu item\n")
                .append("2: select menu items by Price range\n")
                .append("3: select menu items with discounts\n")
                .append("4: select menu items set with total  weight  than less or equals 1 kilogramm\n")
                .append("5: view all menu items\n")
                .append("6: exit\n")
                .append("-> ")
                .toString();
    }

    public static void mainMenu(){
        try {
            // create connection
            emf = Persistence.createEntityManagerFactory("JPATask1");
            em = emf.createEntityManager();
            cb = em.getCriteriaBuilder();
            cq = cb.createQuery(RestaurantMenuItem.class);
            root = cq.from(RestaurantMenuItem.class);
            try {
                while (true) {
                    String s = getMenuChoice();
                    switch (s) {
                        case "1":
                            MenuService.addMenuItem();
                            break;
                        case "2":
                            MenuService.viewMenuFilter1();
                            break;
                        case "3":
                            MenuService.viewMenuFilter2();
                            break;
                        case "4":
                            MenuService.viewMenuFilter3();
                            break;
                        case "5":
                            viewMenu();
                            break;
                        case "6":
                            return;
                    }
                }
            } finally {
                d.close();
                em.close();
                emf.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
    }
}
