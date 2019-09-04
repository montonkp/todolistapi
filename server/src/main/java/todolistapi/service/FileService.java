package todolistapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@NoArgsConstructor
public class FileService {
    public void saveLogFileAsJson (Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        try {
        // Java object to JSON
            String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            Logger logger = LoggerFactory.getLogger("jsonLogger");
            logger.debug(jsonInString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
