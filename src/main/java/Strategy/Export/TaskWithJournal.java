package Strategy.Export;

import Strategy.ExportDTO;
import controller.JournalController;
import controller.TaskController;
import exception.ControllerException;
import exception.StrategyException;

public class TaskWithJournal implements Activity {
    @Override
    public ExportDTO exportDto(int[] taskId) throws StrategyException {

        int journalId = 0;
        for (int i = 0; i < taskId.length; i++) {
            try {
                journalId = TaskController.getInstance().getJournalId(taskId[i]);
            } catch (ControllerException e) {
                System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + e);
                throw new StrategyException("it is impossible to get the journalId");

            }

        }
        ExportDTO exportDto = new ExportDTO();
        try {
            exportDto.setJournals(JournalController.getInstance().getJournalEntity(journalId));
        } catch (ControllerException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new StrategyException("It is impossible to create an exportDTO, " +
                    "because journal controller cannot be initialized");
        }
        try {
            exportDto.setTasks(TaskController.getInstance().getTaskEntityListArray(taskId));
        } catch (ControllerException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new StrategyException("It is impossible to create an exportDTO, " +
                    "because task controller cannot be initialized");
        }
        return exportDto;
    }

}
