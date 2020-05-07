package com.esiea.projetmobile.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.esiea.projetmobile.R;
import com.esiea.projetmobile.model.Giphy;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<Giphy> values;
    //private Context context;
    public SimpleDraweeView mSimpleDraweeView;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        //TextView txtHeader;
        //TextView txtFooter;
        View layout;
       // public ImageView imageView;


        ViewHolder(View v) {
            super(v);
            layout = v;
           // imageView = (ImageView) v.findViewById(R.id.image_view);
//            txtHeader = (TextView) v.findViewById(R.id.firstLine);
           // txtFooter = (TextView) v.findViewById(R.id.secondLine);
            mSimpleDraweeView = v.findViewById(R.id.my_image_view);


        }
    }

    public void add(int position, Giphy item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ListAdapter(List<Giphy> myDataset) {
        values = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }



    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Giphy currentGiphy = values.get(position);

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(currentGiphy.getImages().getDownsizedMedium().getUrl())
                .setAutoPlayAnimations(true)
                .build();
        mSimpleDraweeView.setController(controller);
        //holder.txtHeader.setText(currentGiphy.getTitle());
        //holder.txtFooter.setText(currentGiphy.getTitle());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}

