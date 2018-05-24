package uet.k59t.model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Long laptop on 4/20/2018.
 */
public class BaoKimList<E> extends ArrayList<E> {

    public boolean contains(Record o) {
        for (int i = 0; i < this.size(); i++) {
            Record checkRecord = (Record) this.get(i);
            if(checkRecord.getMaDonHang().equals(o.getMaDonHang()) )
                return true;
        }
        return false;
    }
}
