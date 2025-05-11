package Strategy.Import;

import Strategy.ExportDTO;
import exception.StrategyException;

public class ImportStrategy {
    ActivityImport activityImport;

    public void setActivity(ActivityImport activityImport) {
        this.activityImport = activityImport;
    }

    public void importDto(ExportDTO exportDTO) throws StrategyException {
        activityImport.importDto(exportDTO);
    }
}
