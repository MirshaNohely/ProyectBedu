package med.voll.api.domain.medico;

import jakarta.validation.constraints.NotBlank;
import med.voll.api.domain.direccion.DatosDireccion;

public interface DatosActualizarMedico {
    
    @NotBlank
    Long id();

    String getNombre();

    String getDocumento();

    DatosDireccion getDireccion();
}


