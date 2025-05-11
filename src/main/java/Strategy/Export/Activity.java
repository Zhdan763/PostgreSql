package Strategy.Export;

import Strategy.ExportDTO;
import exception.StrategyException;

public interface Activity {

    ExportDTO exportDto(int[] id) throws StrategyException;

}
