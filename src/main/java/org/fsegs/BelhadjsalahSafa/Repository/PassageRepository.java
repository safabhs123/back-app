package org.fsegs.BelhadjsalahSafa.Repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PassageRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> verifyPassages(String startDate, String endDate, String agency, String agency2, String agency3, String agency4) {
        String query = "SELECT agsa, count(distinct dateh) as nombre_passages " +
                "FROM ( " +
                "   SELECT * FROM bkheve WHERE dateh >= ? AND dateh <= ? AND ope = '160' AND eta = 'VA' " +
                "   AND agsa NOT IN ('00099','00199','00299','00399','00499','00599','00699','00799','00200','00217') " +
                "   AND agep = ? " +
                "UNION " +
                "   SELECT * FROM bkheve WHERE dateh >= ? AND dateh <= ? AND ope IN ('253','456') AND eta = 'VA' AND nat = 'VERESP' " +
                "   AND agsa NOT IN ('00099','00199','00299','00399','00499','00599','00699','00799','00200','00217') " +
                "   AND age2 = ? AND ncp2 IN ('1017200000', '1017200200', '1017204100') " +
                "UNION " +
                "   SELECT * FROM bkheve WHERE dateh >= ? AND dateh <= ? AND eta = 'VA' AND nat = 'VERESP' " +
                "   AND agsa NOT IN ('00099','00199','00299','00399','00499','00599','00699','00799','00200','00217') " +
                "   AND age2 = ? AND ncp2 IN ('1017400000', '1017400200', '1017404100') " +
                "UNION " +
                "   SELECT * FROM bkheve WHERE dateh >= ? AND dateh <= ? AND ope = '255' AND eta = 'VA' AND nat = 'RETESP' " +
                "   AND agsa NOT IN ('00099','00199','00299','00399','00499','00599','00699','00799','00200','00217') " +
                "   AND age1 = ? AND ncp1 IN ('1017300000', '1017300200', '1017304100') " +
                ") AS subquery " +
                "GROUP BY agsa";

        return jdbcTemplate.queryForList(query, startDate, endDate, agency, startDate, endDate, agency2, startDate, endDate, agency3, startDate, endDate, agency4);
    }
}

