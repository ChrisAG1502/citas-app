package azc.uam.app.service;

import azc.uam.app.repository.CitaRepository;
import azc.uam.app.repository.DisponibilidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CitaService {
    @Autowired
    private CitaRepository citaRepo;

    @Autowired
    private DisponibilidadRepository disponibilidadRepo;
}
