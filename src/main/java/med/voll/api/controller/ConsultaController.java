package med.voll.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.AgendaDeConsultaService;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import med.voll.api.domain.consulta.DatosDetalleConsulta;
import med.voll.api.infra.errores.ValidacionDeIntegridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


@Controller
@ResponseBody
@RequestMapping("/consulta")
@SecurityRequirement(name = "bearer-key")
@SuppressWarnings("all")
public class ConsultaController {
    @Autowired
    private AgendaDeConsultaService service;

    @PostMapping
    @Transactional
    @Operation(
         summary = "registra una consulta en la base de datos",
        description = "",
        tags = { "consulta", "post" })
    public ResponseEntity<DatosDetalleConsulta> agendar(@RequestBody @Valid DatosAgendarConsulta datos) {
    try {
        var response = service.agendar(datos);
        return ResponseEntity.ok(response);
    } catch (ValidacionDeIntegridad e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}

}
