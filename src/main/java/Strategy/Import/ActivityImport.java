package Strategy.Import;

import Strategy.ExportDTO;
import exception.StrategyException;

public interface ActivityImport {
    void importDto(ExportDTO exportDTO) throws StrategyException;
}
