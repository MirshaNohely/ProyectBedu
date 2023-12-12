package med.voll.api.domain.direccion;


import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Direccion {

    @NotBlank
    private String calle;

    @NotBlank
    private String distrito;

    @NotBlank
    private String ciudad;

    @NotBlank
    private String numero;

    private String complemento;

    // Constructor to create an instance of Direccion from DatosDireccion
    public Direccion(DatosDireccion datosDireccion) {
        this.calle = datosDireccion.calle();
        this.distrito = datosDireccion.distrito();
        this.ciudad = datosDireccion.ciudad();
        this.numero = datosDireccion.numero();
        this.complemento = datosDireccion.complemento();
    }

    // Method to update the fields of the address
    public Direccion actualizarDireccion(DatosDireccion datosDireccion) {
        this.calle = datosDireccion.calle();
        this.distrito = datosDireccion.distrito();
        this.ciudad = datosDireccion.ciudad();
        this.numero = datosDireccion.numero();
        this.complemento = datosDireccion.complemento();
        return this;
    }
    
}
