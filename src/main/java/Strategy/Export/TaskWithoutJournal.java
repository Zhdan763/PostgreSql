package Strategy.Export;

import Strategy.ExportDTO;
import controller.TaskController;
import exception.ControllerException;
import exception.StrategyException;

public class TaskWithoutJournal implements Activity {


    @Override
    public ExportDTO exportDto(int[] taskId) throws StrategyException {
        ExportDTO exportDto = new ExportDTO();
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




