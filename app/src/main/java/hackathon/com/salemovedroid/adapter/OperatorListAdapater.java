package hackathon.com.salemovedroid.adapter;

/**
 * Created by John on 9/30/2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import hackathon.com.salemovedroid.R;
import hackathon.com.salemovedroid.model.Operator;


public class OperatorListAdapater extends BaseAdapter  {

    private final Context context;
    private LayoutInflater inflater;
    List<Operator> operators;

    public OperatorListAdapater(Context context,List<Operator> operators) {
        this.operators = operators;
        this.context=context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return operators.size();
    }

    @Override
    public Operator getItem(int idx) {
        return operators.get(idx);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View rowView = inflater.inflate(R.layout.layout_operatorlist, parent, false);
        return rowView;
    }


}
