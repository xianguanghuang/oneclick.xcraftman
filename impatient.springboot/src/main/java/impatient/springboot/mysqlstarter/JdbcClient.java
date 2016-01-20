package impatient.springboot.mysqlstarter;

/**
 * Created by Administrator on 2016/1/20.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class JdbcClient {


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcClient(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> fireQuery(){
        return this.jdbcTemplate.queryForList("SELECT * FROM SDK_CRASH_REPORT LIMIT 5");
    }
}
