package med.voll.api.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.direccion.DatosDireccion;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    public MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    @Operation(summary = "Registra un nuevo médico")
    public ResponseEntity<DatosRespuestaMedico> registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico,
                                                               UriComponentsBuilder uriComponentsBuilder) {
        Medico medico = medicoRepository.save(new Medico(datosRegistroMedico));
        return ResponseEntity.created(buildLocationUri(uriComponentsBuilder, medico.getId()))
                             .body(buildDatosRespuestaMedico(medico));
    }

    private URI buildLocationUri(UriComponentsBuilder uriComponentsBuilder, Long id) {
        return uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(id).toUri();
    }

    private DatosRespuestaMedico buildDatosRespuestaMedico(Medico medico) {
        return new DatosRespuestaMedico(
                medico.getId(),
                medico.getNombre(),
                medico.getEmail(),
                medico.getTelefono(),
                medico.getEspecialidad().toString(),
                new DatosDireccion(
                        medico.getDireccion().getCalle(),
                        medico.getDireccion().getDistrito(),
                        medico.getDireccion().getCiudad(),
                        medico.getDireccion().getNumero(),
                        medico.getDireccion().getComplemento()
                )
        );
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoMedico>> listadoMedicos(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedico::new));
    }

    

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaMedico> retornaDatosMedico(@PathVariable Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        var datosMedico = new DatosRespuestaMedico(
                medico.getId(),
                medico.getNombre(),
                medico.getEmail(),
                medico.getTelefono(),
                medico.getEspecialidad().toString(),
                new DatosDireccion(
                        medico.getDireccion().getCalle(),
                        medico.getDireccion().getDistrito(),
                        medico.getDireccion().getCiudad(),
                        medico.getDireccion().getNumero(),
                        medico.getDireccion().getComplemento()
                )
        );
        return ResponseEntity.ok(datosMedico);
    }
}
