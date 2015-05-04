package ch.swissonid.flow_sampel.view.helloworld;


import ch.swissonid.flow_sampel.view.Presenter;

/**
 * Created by pmueller on 3.5.15.
 */
public interface IHelloWorldPresnter extends Presenter<IHelloWorldView> {
    void onButtonClicked();
}
