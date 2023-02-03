package org.jcd2052.api.service.games;

import org.jcd2052.api.entities.GameInfo;
import org.jcd2052.api.repositories.games.GameInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class GameInfoService extends BaseService<GameInfo> {

    @Autowired
    public GameInfoService(GameInfoRepository repository) {
        super(repository);
    }

    public GameInfo findGameInfoByName(String gameName) {
        return ((GameInfoRepository) repository).findGameInfoByGameName(gameName)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Couldn't find a game with name: " + gameName));
    }

}
