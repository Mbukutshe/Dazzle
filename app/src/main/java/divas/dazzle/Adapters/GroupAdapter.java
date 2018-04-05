package divas.dazzle.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import divas.dazzle.Functions.Functions;
import divas.dazzle.Holders.GroupHolder;
import divas.dazzle.Objects.Groups;
import divas.dazzle.R;

/**
 * Created by Wiseman on 2017-10-16.
 */

public class GroupAdapter extends RecyclerView.Adapter<GroupHolder> {
    List<Groups> mDataset;
    Context context,c;
    public GroupAdapter(Context context, List<Groups> mDataset) {
        this.mDataset = mDataset;
        this.context=context;
    }
    @Override
    public GroupHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        c = parent.getContext();
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.group_item, parent, false);
        // set the view's size, margins, paddings and layout parameters

        GroupHolder vh = new GroupHolder(view);
        return vh;
    }
    @Override
    public void onBindViewHolder(final GroupHolder holder, final int position) {
       holder.group_name.setText(mDataset.get(position).getName());
        holder.itemView.setOnClickListener(new Functions(context));
    }

    @Override
    public int getItemCount() {
        return this.mDataset.size();
    }
}
