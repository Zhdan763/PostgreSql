package Strategy.Export;


import Strategy.ExportDTO;
import exception.StrategyException;

public class Strategy {
    Activity activity;

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public ExportDTO export(int[] id) throws StrategyException {
        return activity.exportDto(id);
    }


}
