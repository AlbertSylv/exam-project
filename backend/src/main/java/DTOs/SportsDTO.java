package DTOs;

import entities.Sport;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author baske
 */
public class SportsDTO {
    private List<SportDTO> all = new ArrayList();

    public SportsDTO(List<Sport> sportEntities) {
        sportEntities.forEach((s) -> {
            all.add(new SportDTO(s));
        });
    }


    public List<SportDTO> getAll() {
        return all;
    }
    
    

    public class toString {


    }
    
}