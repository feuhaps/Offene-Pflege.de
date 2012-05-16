package tablemodels;

import entity.verordnungen.*;
import op.tools.Pair;

import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tloehr
 * Date: 04.01.12
 * Time: 12:10
 * To change this template use File | Settings | File Templates.
 */
public class TMBestand extends AbstractTableModel{
    public static final int COL_NAME = 0;
    public static final int COL_MENGE = 1;
    protected List<Pair<MedBestand, BigDecimal>> data;

    public TMBestand(List<Pair<MedBestand, BigDecimal>> data) {
        this.data = data;
    }

    public List<Pair<MedBestand, BigDecimal>> getData() {
        return data;
    }

    public BigDecimal getBestandsMenge(int row) {
        return data.get(row).getSecond();
    }

    public MedBestand getBestand(int row) {
        return data.get(row).getFirst();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Object result = "";
        switch (col){
            case COL_NAME : {
                result = MedBestandTools.getBestandAsHTML(getBestand(row));
                break;
            }
            case COL_MENGE : {
                result = getBestandsMenge(row).setScale(2, BigDecimal.ROUND_HALF_UP) + " " + DarreichungTools.getPackungsEinheit(getBestand(row).getDarreichung());
                break;
            }
            default: {
                result = "";
            }
        }
        return result;
    }
}
