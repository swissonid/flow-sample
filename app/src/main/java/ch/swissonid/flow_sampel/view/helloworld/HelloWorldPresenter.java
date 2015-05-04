package ch.swissonid.flow_sampel.view.helloworld;



import android.view.View;

import ch.swissonid.flow_sampel.view.Presenter;
import flow.Flow;
import flow.path.Path;

/**
 * Created by pmueller on 3.5.15.
 */
public class HelloWorldPresenter implements IHelloWorldPresnter {

    private IHelloWorldView mView;


    @Override
    public void onButtonClicked() {
        Flow.get(((HelloWorldView) mView)).set(new HelloWorldScreen(getScreenText(), getScreenNr()));
    }

    public String getScreenText(){
        HelloWorldScreen screen = Path.get(((View) mView).getContext());
        return screen.pathText;

    }


    @Override
    public void onViewAttached(final IHelloWorldView view) {
        mView = view;
    }

    @Override
    public void onViewReady() {
        mView.setText(getScreenText()+""+getScreenNr());
    }

    @Override
    public void onDetach() {

    }

    public int getScreenNr() {
        HelloWorldScreen screen = Path.get(((View) mView).getContext());
        return screen.screenNumber+1;
    }
}
