package czujniki;

import kupa.Observator;

public interface Subject {
    void register_Observer(Observator observator);
    void remove_Observer(Observator observator);
    void notify_Observers();
}
