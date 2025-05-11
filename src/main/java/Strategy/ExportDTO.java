package Strategy;

import com.fasterxml.jackson.annotation.JsonInclude;
import domain.entity.JournalEntity;
import domain.entity.TaskEntity;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExportDTO {
    private List<JournalEntity> journals;
    private List<TaskEntity> tasks;


}
