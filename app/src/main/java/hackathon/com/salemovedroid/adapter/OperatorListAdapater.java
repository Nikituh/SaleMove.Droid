package hackathon.com.salemovedroid.adapter;

/**
 * Created by John on 9/30/2017.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;

import hackathon.com.salemovedroid.model.Operator;


public class OperatorListAdapater extends SimpleAdapter {

    List<Operator> operatorList;

    public OperatorListAdapater(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
    }

    @Override
    public int getCount() {
        return operatorList.size();
    }

    @Override
    public Operator getItem(int idx) {
        return operatorList.get(idx);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        return super.getView(position, view, parent);
    }
}
