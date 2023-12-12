package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.direccion.DatosDireccion;
import med.voll.api.domain.direccion.Direccion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.Valid;

@Table(name = "medicos")
@Entity(name = "medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;
    private Boolean activo;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;

    public Medico(DatosRegistroMedico datosRegistroMedico) {
        this.activo = true;
        this.nombre = datosRegistroMedico.nombre();
        this.email = datosRegistroMedico.email();
        this.documento = datosRegistroMedico.documento();
        this.telefono = datosRegistroMedico.telefono();
        this.especialidad = datosRegistroMedico.especialidad();
        this.direccion = new Direccion(datosRegistroMedico.direccion());
    }

    public record DatosActualizarMedico(@NotBlank String nombre, @NotBlank String documento, DatosDireccion direccion) {
    }

    public void actualizarDatos(@Valid Medico.DatosActualizarMedico datosActualizarMedico) {
        if (datosActualizarMedico.getNombre() != null) {
            this.nombre = datosActualizarMedico.getNombre();
        }
        if (datosActualizarMedico.getDocumento() != null) {
            this.documento = datosActualizarMedico.getDocumento();
        }
        if (datosActualizarMedico.getDireccion() != null) {
            this.direccion = this.direccion.actualizarDireccion(datosActualizarMedico.getDireccion());
        }
    }


    public void desactivaMedico() {
        this.activo = false;
    }
}
