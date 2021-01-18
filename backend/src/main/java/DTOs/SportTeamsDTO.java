package DTOs;

import entities.SportTeam;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author baske
 */
public class SportTeamsDTO {
    private List<SportTeamDTO> all = new ArrayList();

    public SportTeamsDTO(List<SportTeam> sportTeamEntities) {
        sportTeamEntities.forEach((st) -> {
            all.add(new SportTeamDTO(st));
        });
    }

    public SportTeamsDTO() {
    }

    public List<SportTeamDTO> getAll() {
        return all;
    }
    
}
