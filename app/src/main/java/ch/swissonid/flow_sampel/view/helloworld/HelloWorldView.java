package ch.swissonid.flow_sampel.view.helloworld;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import ch.swissonid.flow_sampel.R;

public class HelloWorldView extends LinearLayout implements IHelloWorldView{

    private final IHelloWorldPresnter mPresenter;

    private TextView mTextView;
    private Button mButton;
    public HelloWorldView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        mPresenter = new HelloWorldPresenter();
        mPresenter.onViewAttached(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initButton();
        mTextView = (TextView) findViewById(R.id.text);
        mPresenter.onViewReady();
    }

    private void initButton() {
        mButton = (Button)findViewById(R.id.next_button);
        mButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                mPresenter.onButtonClicked();
            }
        });
    }

    @Override
    public void setText(final String text) {
        mTextView.setText(text);
    }

    @Override
    public void setButtonText(final String text) {
        mButton.setText(text);
    }

    @Override
    public String getText() {
        return null;
    }
}
