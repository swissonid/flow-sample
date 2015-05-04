package ch.swissonid.flow_sampel.view.helloworld;

import ch.swissonid.flow_sampel.view.View;

/**
 * Created by pmueller on 3.5.15.
 */
public interface IHelloWorldView extends View {

    void setText(String text);
    void setButtonText(String text);
    String getText();
}
