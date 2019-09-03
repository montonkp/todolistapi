package todolistapi.service;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import todolistapi.entity.Task;

@Service
@NoArgsConstructor
public class TaskService {

    public String fillNotMsg(Task task) {
        String msg = task.getDescription();
        if (msg == null || msg.isBlank() || msg.isEmpty())
            return "-";
        else
            return msg;
    }
}
