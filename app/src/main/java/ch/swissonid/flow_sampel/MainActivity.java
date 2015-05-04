package ch.swissonid.flow_sampel;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.google.gson.Gson;

import ch.swissonid.flow_sampel.view.helloworld.HelloWorldScreen;
import flow.Flow;
import flow.FlowDelegate;
import flow.History;
import flow.path.Path;
import flow.path.PathContainerView;


public class MainActivity extends AppCompatActivity implements Flow.Dispatcher {
    private PathContainerView mPathContainerView;
    private FlowDelegate mFlowDelegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPathContainerView = (PathContainerView) findViewById(R.id.container);
        GsonParceler parceler = new GsonParceler(new Gson());
        FlowDelegate.NonConfigurationInstance noConfig =
                ((FlowDelegate.NonConfigurationInstance) getLastNonConfigurationInstance());

        mFlowDelegate = FlowDelegate.onCreate(noConfig
                , getIntent()
                , savedInstanceState
                , parceler
                , History.single(new HelloWorldScreen())
                , this);
    }

    @Override
    public void dispatch(final Flow.Traversal traversal, final Flow.TraversalCallback callback) {
        Path path = traversal.destination.top();
        setTitle(path.getClass().getSimpleName());
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        boolean canGoBack = traversal.destination.size() > 1;
        actionBar.setDisplayHomeAsUpEnabled(canGoBack);
        actionBar.setHomeButtonEnabled(canGoBack);
        mPathContainerView.dispatch(traversal, new Flow.TraversalCallback() {
            @Override public void onTraversalCompleted() {
                invalidateOptionsMenu();
                callback.onTraversalCompleted();
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mFlowDelegate.onNewIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFlowDelegate.onResume();
    }

    @Override
    protected void onPause() {
        mFlowDelegate.onPause();
        super.onPause();
    }

    //@SuppressWarnings("deprecation") // https://code.google.com/p/android/issues/detail?id=151346
    //public Object onRetainNonConfigurationInstance() { // maybe it won't work
    //@SuppressWarnings("deprecation")
    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return mFlowDelegate.onRetainNonConfigurationInstance();
    }

    @Override
    public Object getSystemService(String name) {
        Object service = null;
        if (mFlowDelegate != null) {
            service = mFlowDelegate.getSystemService(name);
        }
        return service != null ? service : super.getSystemService(name);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mFlowDelegate.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        //if (containerAsBackTarget.onBackPressed()) return;
        if (mFlowDelegate.onBackPressed()) return;
        super.onBackPressed();
    }
}
