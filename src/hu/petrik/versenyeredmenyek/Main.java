package hu.petrik.versenyeredmenyek;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    private static Map<String, List<Eredmeny>> sportagEredmenyek = new HashMap<>();

    public static void main(String[] args) {
        String fajlNev = "eredmenyek.txt";
        try {
            beolvas(fajlNev);
            kiiras();
            System.out.printf("Az olimpián %d sportág van\n",getSportagakSzama());
            System.out.printf("Az olimpián %d versenyző vett részt\n",getversenyzokSzama());
        } catch (FileNotFoundException e) {
            System.out.printf("A(z) %s fájl név nem található",fajlNev);
        }
    }

    private static int getversenyzokSzama() {
        List<String> versenyzok = new ArrayList<>();
        for (Map.Entry<String, List<Eredmeny>> entry : sportagEredmenyek.entrySet()){
            List<Eredmeny> eredmenyek = entry.getValue();
            for (Eredmeny e : eredmenyek) {
                if (!versenyzok.contains(e.getNev())){
                    versenyzok.add(e.getNev());
                }
            }
        }
        return versenyzok.size();
    }

    private static int getSportagakSzama() {
        return sportagEredmenyek.keySet().size();
    }

    private static void kiiras(){
        for (Map.Entry<String, List<Eredmeny>> entry : sportagEredmenyek.entrySet()){
            String sportag = entry.getKey();
            List<Eredmeny> eredmenyek = entry.getValue();
            System.out.println(sportag);
            for (Eredmeny e : eredmenyek){
                System.out.println("\t" + e);
            }
        }
    }

    private static void beolvas(String fajlNev) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(fajlNev));
        while (sc.hasNext()){
            String[] sor = sc.nextLine().split(" ");
            String sportag = sor[0];
            String reszIdo = sor[1];
            String nev = sor[2] + " " + sor[3];
            Eredmeny e = new Eredmeny(reszIdo,nev);
            sportagEredmenyek.putIfAbsent(sportag, new ArrayList<>());
            sportagEredmenyek.get(sportag).add(e);
        }
        sc.close();
    }
}
