package j2048frontend.ui;
import j2048backend.Estado;

public interface Observador {
    void actualizar(Estado estado);
}
