package catalytic.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by gardncl on 2/16/17.
 */

@Repository
@Transactional
public interface LogDao extends JpaRepository<Log,Long> {
}
