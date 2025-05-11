package Strategy.Export;

import Strategy.ExportDTO;
import controller.JournalController;
import controller.TaskController;
import exception.ControllerException;
import exception.StrategyException;

public class JournalWithTasks implements Activity {

    @Override
    public ExportDTO exportDto(int[] journalId) throws StrategyException {
        ExportDTO exportDto = new ExportDTO();
        try {
            exportDto.setJournals(JournalController.getInstance().getJournalEntityArray(journalId));
        } catch (ControllerException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new StrategyException("It is impossible to create an exportDTO, " +
                    "because journal controller cannot be initialized");
        }
        try {
            exportDto.setTasks(TaskController.getInstance().getTasksEntityByJournalId(journalId));
        } catch (ControllerException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new StrategyException("It is impossible to create an exportDTO, " +
                    "because task controller cannot be initialized");
        }
        return exportDto;
    }


}
