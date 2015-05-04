package ch.swissonid.flow_sampel.view;

/**
 * Created by pmueller on 3.5.15.
 */
public interface Presenter<T extends View> {

    void onViewAttached(T view);
    void onViewReady();
    void onDetach();
}
