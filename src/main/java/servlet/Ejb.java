package servlet;


import Strategy.*;
import Strategy.Export.*;
import Strategy.Import.Ignore;
import Strategy.Import.ImportError;
import Strategy.Import.ImportStrategy;
import Strategy.Import.Update;
import exception.EjbException;
import exception.StrategyException;
import jakarta.ejb.Stateless;
import util.Constants;


@Stateless
public class Ejb {


    public ExportDTO exportDTO(String type, int[] id) throws EjbException {
        Strategy strategy = new Strategy();
        switch (type) {
            case Constants.JOURNAL_WITH_TASKS:
                strategy.setActivity(new JournalWithTasks());
                break;
            case Constants.JOURNAL_WITHOUT_TASKS:
                strategy.setActivity(new JournalWithoutTasks());
                break;
            case Constants.TASK_WITH_JOURNAL:
                strategy.setActivity(new TaskWithJournal());
                break;
            case Constants.TASK_WITHOUT_JOURNAL:
                strategy.setActivity(new TaskWithoutJournal());
                break;

        }
        try {
            return strategy.export(id);
        } catch (StrategyException e) {
            throw new EjbException("Export is not possible");
        }
    }


    public void importDto(String type, ExportDTO exportDTO) throws EjbException {
        ImportStrategy importStrategy = new ImportStrategy();
        switch (type) {
            case (Constants.IGNORE):
                importStrategy.setActivity(new Ignore());
                break;
            case (Constants.ERROR):
                importStrategy.setActivity(new ImportError());
                break;
            case (Constants.UPDATE):
                importStrategy.setActivity(new Update());
                break;
        }

        try {
            importStrategy.importDto(exportDTO);
        } catch (StrategyException e) {
            throw new EjbException("Import is not possible");
        }

    }


}
