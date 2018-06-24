package josim74.github.com.retrofitjson.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import josim74.github.com.retrofitjson.model.Item;
import josim74.github.com.retrofitjson.R;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private ArrayList<Item> items;
    private Context mContext;

    public DataAdapter(ArrayList<Item> items, Context context) {
        this.items = items;
        mContext = context;

    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DataAdapter.ViewHolder holder, final int position) {
        holder.title.setText(items.get(position).getTitle());
        holder.date.setText(items.get(position).getPublished());
        holder.author.setText(items.get(position).getAuthor());
        //load image to the imageview...
        loadImage(position, holder);
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView title;
        private TextView date;
        private TextView author;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            date = itemView.findViewById(R.id.date);
            author = itemView.findViewById(R.id.author);

        }
    }

    //method: load image to the imagview
    private void loadImage(final int position, final DataAdapter.ViewHolder holder) {
        Picasso.with(mContext).load(items.get(position).getMedia().getM()).networkPolicy(NetworkPolicy.OFFLINE).placeholder(R.drawable.ic_place_holder).into(holder.imageView, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {
                Picasso.with(mContext).load(items.get(position).getMedia().getM()).placeholder(R.drawable.ic_place_holder).into(holder.imageView);
            }
        });
    }
}