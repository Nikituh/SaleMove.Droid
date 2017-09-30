package hackathon.com.salemovedroid.adapter;

/**
 * Created by John on 9/30/2017.
 */

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hackathon.com.salemovedroid.R;
import hackathon.com.salemovedroid.activity.OperatorsActivity;
import hackathon.com.salemovedroid.model.Operator;


public class OperatorListAdapter extends RecyclerView.Adapter<OperatorListAdapter.OperatorViewHolder> {

    private final Context context;
    private LayoutInflater inflater;
    List<Operator> operators;

    public OperatorListAdapter(Context context, List<Operator> operators) {
        this.operators = operators;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public OperatorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.include_operator_card, parent, false);
        return new OperatorViewHolder(itemView);
    }

    @Override

    public void onBindViewHolder(OperatorViewHolder holder, int position) {
        //TODO:Is the operator availabel?
        boolean status = operators.get(position).getStatus();

        holder.opName.setText(operators.get(position).getName());
        holder.opPhone.setText(operators.get(position).getPhone());
        String imageUrl = operators.get(position).getImageUrl();
        if (imageUrl.isEmpty()) {
            holder.opPhoto.setImageDrawable(context.getDrawable(R.drawable.ic_action_name));
        } else {
            Picasso.with(context).load(imageUrl).into(holder.opPhoto);
        }
        holder.context = context;
        holder.callIcon.setImageDrawable(context.getDrawable(R.drawable.ic_operator_status_available));
        holder.chatIcon.setImageDrawable(context.getDrawable(R.drawable.ic_chat_icon));
    }

    @Override
    public int getItemCount() {
        return operators.size();
    }

    public static class OperatorViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.operator_card_view)
        CardView cv;
        @BindView(R.id.person_name_val)
        TextView opName;
        @BindView(R.id.person_phone_val)
        TextView opPhone;
        @BindView(R.id.call_icon)
        ImageView callIcon;
        @BindView(R.id.person_photo)
        ImageView opPhoto;
        @BindView(R.id.chat_icon)
        ImageView chatIcon;
        public Context context;

        OperatorViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        @OnClick(R.id.chat_icon)
        public void loadOperatorView(ImageView card) {
            OperatorsActivity activity = (OperatorsActivity) context;
            activity.startWebActivity();
        }

        //TODO:ON CALL
        @OnClick(R.id.call_icon)
        public void startAcall(ImageView card) {
            String phone = opPhone.getText().toString();
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.fromParts("tel", phone, null));
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                context.startActivity(intent);
            }
        }
    }
}
