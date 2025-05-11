package Strategy.Import;

import Strategy.ExportDTO;
import controller.JournalController;
import controller.TaskController;
import domain.entity.JournalEntity;
import domain.entity.TaskEntity;
import exception.ControllerException;
import exception.StrategyException;

public class Update implements ActivityImport {
    @Override
    public void importDto(ExportDTO exportDTO) throws StrategyException {
        if (exportDTO.getJournals() != null) {
            for (JournalEntity journal : exportDTO.getJournals()
            ) {
                boolean check = true;
                try {
                    check = JournalController.getInstance().checkJournal(journal);
                } catch (ControllerException e) {
                    throw new StrategyException("Couldn't check the journal");
                }
                if (check) {
                    try {
                        JournalController.getInstance().updateEntity(journal);
                    } catch (ControllerException e) {
                        System.out.println("Error! Class: " + Update.class.getName() + ". Date: " +
                                new java.util.Date() + ". Message: " + e);
                        throw new StrategyException("Couldn't update the journal");
                    }
                } else {
                    try {
                        JournalController.getInstance().createJournalEntity(journal);
                    } catch (ControllerException e) {
                        System.out.println("Error! Class: " + Update.class.getName() + ". Date: " +
                                new java.util.Date() + ". Message: " + e);
                        throw new StrategyException("Couldn't add the journal");
                    }
                }

            }
        }

        if (exportDTO.getTasks() != null) {
            for (TaskEntity taskEntity : exportDTO.getTasks()
            ) {
                boolean check = true;
                try {
                    check = TaskController.getInstance().checkTask(taskEntity);
                } catch (ControllerException e) {
                    throw new StrategyException("Couldn't check the task");
                }
                if (check) {
                    try {
                        TaskController.getInstance().updateEntity(taskEntity);
                    } catch (ControllerException e) {
                        System.out.println("Error! Class: " + Update.class.getName() + ". Date: " +
                                new java.util.Date() + ". Message: " + e);
                        throw new StrategyException("Couldn't update the task");
                    }
                } else {
                    try {
                        TaskController.getInstance().createTaskEntity(taskEntity);
                    } catch (ControllerException e) {
                        throw new StrategyException("Couldn't add the task");
                    }
                }
            }
        }

    }
}
