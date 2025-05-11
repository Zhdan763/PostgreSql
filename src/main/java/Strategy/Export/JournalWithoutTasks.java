package Strategy.Export;

import Strategy.ExportDTO;
import controller.JournalController;
import exception.ControllerException;
import exception.StrategyException;

public class JournalWithoutTasks implements Activity {


    @Override
    public ExportDTO exportDto(int[] journalId) throws StrategyException {
        ExportDTO exportDTO = new ExportDTO();

        try {
            exportDTO.setJournals(JournalController.getInstance().getJournalEntityArray(journalId));
        } catch (ControllerException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new StrategyException("It is impossible to create an exportDTO, " +
                    "because journal controller cannot be initialized ");
        }

        return exportDTO;
    }

}



