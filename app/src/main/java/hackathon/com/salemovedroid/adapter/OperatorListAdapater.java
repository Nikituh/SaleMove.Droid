package hackathon.com.salemovedroid.adapter;

/**
 * Created by John on 9/30/2017.
 */

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hackathon.com.salemovedroid.R;
import hackathon.com.salemovedroid.model.Operator;


public class OperatorListAdapater extends RecyclerView.Adapter<OperatorListAdapater.OperatorViewHolder> {

    private final Context context;
    private LayoutInflater inflater;
    List<Operator> operators;

    public OperatorListAdapater(Context context, List<Operator> operators) {
        this.operators = operators;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public OperatorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.include_operator_card, parent, false);
        return new OperatorViewHolder(itemView);
    }

    @Override

    public void onBindViewHolder(OperatorViewHolder holder, int position) {
        holder.opName.setText(operators.get(position).getName());
        boolean status = operators.get(position).getStatus();
        holder.opStatus.setText(String.valueOf(status));
        holder.opPhone.setText(operators.get(position).getPhone());
        holder.opPhoto.setImageDrawable(context.getDrawable(R.drawable.ic_action_name));


        if(status){
            holder.opStatusIcon.setImageDrawable(context.getDrawable(R.drawable.ic_operator_status_available));
        }
        else{
            double rnd=Math.random();
            if(rnd>0.5){holder.opStatusIcon.setImageDrawable(context.getDrawable(R.drawable.ic_operator_status_off));}
            else{holder.opStatusIcon.setImageDrawable(context.getDrawable(R.drawable.ic_operator_status_on_call));}

        }


        //TODO:onclickListener

    }

    @Override
    public int getItemCount() {
        return operators.size();
    }


    public static class OperatorViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView opName;
        //TextView opEmail;
        TextView opPhone;
        TextView opStatus;
        ImageView opPhoto;
        ImageView opStatusIcon;

        OperatorViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.operator_card_view);
            opName = (TextView) itemView.findViewById(R.id.person_name_val);
            opPhone = (TextView) itemView.findViewById(R.id.person_phone_val);
            opPhoto = (ImageView) itemView.findViewById(R.id.person_photo);
            opStatusIcon = (ImageView) itemView.findViewById(R.id.status_icon);
            opStatus = (TextView) itemView.findViewById(R.id.person_status_val);
        }
    }
}
