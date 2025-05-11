package Strategy.Import;

import Strategy.ExportDTO;
import controller.JournalController;
import controller.TaskController;
import domain.entity.JournalEntity;
import domain.entity.TaskEntity;
import exception.ControllerException;
import exception.StrategyException;

public class ImportError implements ActivityImport {

    @Override
    public void importDto(ExportDTO exportDTO) throws StrategyException {
        if (exportDTO.getJournals() != null) {
            for (JournalEntity journal : exportDTO.getJournals()
            ) {
                try {
                    JournalController.getInstance().createJournalEntity(journal);
                } catch (ControllerException e) {
                    System.out.println("Error! Class: " + ImportError.class.getName() + ". Date: " +
                            new java.util.Date() + ". Message: " + e);
                    throw new StrategyException("It is impossible to add a journal");
                }
            }
        }

        if (exportDTO.getTasks() != null) {
            for (TaskEntity taskEntity : exportDTO.getTasks()
            ) {
                try {
                    TaskController.getInstance().createTaskEntity(taskEntity);
                } catch (ControllerException e) {
                    System.out.println("Error! Class: " + ImportError.class.getName() + ". Date: " +
                            new java.util.Date() + ". Message: " + e);
                    throw new StrategyException("It is impossible to add a task ");
                }
            }
        }

    }
}
