package br.com.sigteam.sigbolsista.services;

import br.com.sigteam.sigbolsista.models.Point;
import br.com.sigteam.sigbolsista.models.User;
import br.com.sigteam.sigbolsista.repositories.PointRepository;
import org.springframework.stereotype.Service;

/**
 * @author Gabrielle Duarte (gabrielleduartee3@gmail.com)
 * @since 15/07/2021
 */

@Service
public class PointService {

    private final PointRepository pointRepository;

    public PointService(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    public void save(Point point, User user){
        point.setUser(user);
        pointRepository.save(point);
    }
}
