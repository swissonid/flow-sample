package ch.swissonid.flow_sampel.view.helloworld;

import ch.swissonid.flow_sampel.R;
import ch.swissonid.flow_sampel.pathview.Layout;
import flow.path.Path;


@Layout(R.layout.helloworld_view)
public class HelloWorldScreen extends Path {
    public final String pathText;
    public final int screenNumber;

    public HelloWorldScreen(String text, int screenNumber){
        pathText = text;
        this.screenNumber =screenNumber;
    }

    public HelloWorldScreen(){
        this("Screen ", 0);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof HelloWorldScreen)) return false;

        HelloWorldScreen that = (HelloWorldScreen) o;

        if (screenNumber != that.screenNumber) return false;
        return !(pathText != null ? !pathText.equals(that.pathText) : that.pathText != null);

    }

    @Override
    public int hashCode() {
        int result = pathText != null ? pathText.hashCode() : 0;
        result = 31 * result + screenNumber;
        return result;
    }
}
