package uet.k59t.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uet.k59t.model.Record;

/**
 * Created by Long laptop on 4/20/2018.
 */
@Repository
public interface RecordRepository extends CrudRepository<Record,Long>{
}
