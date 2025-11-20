/**
 * Excepción personalizada que se lanza cuando se intenta crear un animal
 * avícola con menos de 1kg de peso.
 */
public class PesoInsuficienteException extends Exception {

  public PesoInsuficienteException(String mensaje) {
    super(mensaje);
  }

  public PesoInsuficienteException() {
    super("Error: Un animal avícola debe tener al menos 1kg de peso");
  }
}