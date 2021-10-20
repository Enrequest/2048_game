package j2048frontend;
import j2048backend.Estado;
import java.util.Observer;


public interface IObservador {
    //public void update(Estado event);
    void update(Estado estado);
}
