package br.com.sigteam.sigbolsista.services;

import br.com.sigteam.sigbolsista.models.Point;
import br.com.sigteam.sigbolsista.repositories.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PointService {
    @Autowired
    private PointRepository pointRepository;

    public Iterable<Point> all() {
        return pointRepository.findAll();
    }

    public Optional<Point> getById(Long id) {
        return pointRepository.findById(id);
    }

    public void save(Point point) {
        pointRepository.save(point);
    }

    public void delete(Point point) {
        pointRepository.delete(point);
    }
}
