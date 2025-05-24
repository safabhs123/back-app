
package org.fsegs.BelhadjsalahSafa.Config;
import org.fsegs.BelhadjsalahSafa.Entity.LDAP;
import org.fsegs.BelhadjsalahSafa.Entity.Role;
import org.fsegs.BelhadjsalahSafa.Repository.LdapRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class LdapConfig {

    @Bean
    CommandLineRunner initData(LdapRepository repo) {
        return args -> {
            if (!repo.existsByMatricule("")) {
                repo.save(LDAP.builder()
                    .matricule("")
                    .password("")
                    .role(Role.	ADMIN_USER)
                    .build());
            }
           if (!repo.existsByMatricule("")) {
                repo.save(LDAP.builder()
                    .matricule("")
                    .password("")
                    .role(Role.ADMIN_FUNCTIONAL)
                    .build());
            }

            if (!repo.existsByMatricule("")) {
                repo.save(LDAP.builder()
                    .matricule("")
                    .password("")
                    .role(Role.EMPLOYEE)
                    .build());
            }
        };
    }
}