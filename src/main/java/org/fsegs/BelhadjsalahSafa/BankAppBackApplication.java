package org.fsegs.BelhadjsalahSafa;

import org.fsegs.BelhadjsalahSafa.Repository.CaisseRepository;
import org.fsegs.BelhadjsalahSafa.Repository.RegionRepository;
import org.fsegs.BelhadjsalahSafa.Repository.TransporteurRepository;
import org.fsegs.BelhadjsalahSafa.Entity.Caisse;
import org.fsegs.BelhadjsalahSafa.Entity.Region;
import org.fsegs.BelhadjsalahSafa.Entity.Transporteur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankAppBackApplication implements ApplicationRunner {

    @Autowired
    private CaisseRepository caisseRepo;

    @Autowired
    private RegionRepository regionRepo;

    @Autowired
    private TransporteurRepository transporteurRepo;

    public static void main(String[] args) {
        SpringApplication.run(BankAppBackApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(regionRepo.count() == 0) {
            Transporteur t1 = new Transporteur();
            t1.setNom("IBS");
            transporteurRepo.save(t1);

            Transporteur t2 = new Transporteur();
            t2.setNom("Yosr");
            transporteurRepo.save(t2);

            Transporteur t3 = new Transporteur();
            t3.setNom("Express"); 
            transporteurRepo.save(t3);
            
            Region tunis = new Region();
            tunis.setNom("Tunis");
            tunis.getTransporteurs().add(t1);
            tunis.getTransporteurs().add(t2);
            tunis.getTransporteurs().add(t3);
            regionRepo.save(tunis);

            Region sousse = new Region();
            sousse.setNom("Sousse");
            sousse.getTransporteurs().add(t3);
            regionRepo.save(sousse);

            Region sfax = new Region();
            sfax.setNom("Sfax");
            sfax.getTransporteurs().add(t2);
            regionRepo.save(sfax);

            Region nabeul = new Region();
            nabeul.setNom("Nabeul");
            nabeul.getTransporteurs().add(t2);
            regionRepo.save(nabeul);
            
            Region mednin= new Region();
            mednin.setNom("Mednin");
            mednin.getTransporteurs().add(t2);
            regionRepo.save(mednin);
            
            Region gafsa = new Region();
            gafsa.setNom("Gafsa");
            gafsa.getTransporteurs().add(t2);
            regionRepo.save(gafsa);
            
            Region jendouba = new Region();
            jendouba.setNom("Jendouba");
            jendouba.getTransporteurs().add(t2);
            regionRepo.save(jendouba);
            
            Region gabes = new Region();
            gabes.setNom("Gabes");
            gabes.getTransporteurs().add(t2);
            regionRepo.save(gabes);
            
            Caisse c1 = new Caisse();
            c1.setId(Long.valueOf("00099"));
            c1.setRegion(tunis);
            caisseRepo.save(c1);

            Caisse c2 = new Caisse();
            c2.setId(Long.valueOf("00199"));
            c2.setRegion(sousse);
            caisseRepo.save(c2);
            
            Caisse c3 = new Caisse();
            c3.setId(Long.valueOf("00299"));
            c3.setRegion(nabeul);
            caisseRepo.save(c3);
            
            Caisse c4 = new Caisse();
            c4.setId(Long.valueOf("00399"));
            c4.setRegion(sfax);
            caisseRepo.save(c4);
            
            Caisse c5 = new Caisse();
            c5.setId(Long.valueOf("00499"));
            c5.setRegion(gabes);
            caisseRepo.save(c5);
            
            Caisse c6 = new Caisse();
            c6.setId(Long.valueOf("00599"));
            c6.setRegion(gafsa);
            caisseRepo.save(c6);
            
            Caisse c7 = new Caisse();
            c7.setId(Long.valueOf("00699"));
            c7.setRegion(jendouba);
            caisseRepo.save(c7);
            
            Caisse c8 = new Caisse();
            c8.setId(Long.valueOf("00799"));
            c8.setRegion(mednin);
            caisseRepo.save(c8);

            System.out.println("Données initiales insérées.");
        } else {
            System.out.println("Données déjà présentes, pas d'insertion.");
        }
    }
}
