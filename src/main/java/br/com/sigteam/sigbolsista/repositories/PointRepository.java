package br.com.sigteam.sigbolsista.repositories;

import br.com.sigteam.sigbolsista.models.Point;
import org.springframework.data.repository.CrudRepository;

public interface PointRepository extends CrudRepository<Point, Long> {}
