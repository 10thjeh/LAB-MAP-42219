package umn.ac.id.uts_lab;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SoundRecyclerViewAdapter extends RecyclerView.Adapter<SoundRecyclerViewAdapter.SoundAdapter>{

    ArrayList sounds;
    Context context;

    public SoundRecyclerViewAdapter(Context context, ArrayList sounds){
        this.context = context;
        this.sounds = sounds;
    }

    @NonNull
    @Override
    public SoundAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.sfx_card, parent, false);

        return new SoundAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SoundAdapter holder, int position) {
        Sound sound = (Sound) sounds.get(position);
        holder.text_title.setText(sound.getName());
        holder.text_description.setText(sound.getDescription());

        holder.itemView.setOnClickListener( v->{
            Intent intent = new Intent(v.getContext(), DetailActivity.class);
            intent.putExtra("SOUND", sound);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            v.getContext().startActivity(intent);
        });

        holder.remove_button.setOnClickListener(v->{
            sounds.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, sounds.size());
        });

    }

    @Override
    public int getItemCount() {
        return sounds.size();
    }

    public class SoundAdapter extends RecyclerView.ViewHolder{
        TextView text_title, text_description;
        ImageButton remove_button;

        public SoundAdapter(@NonNull View itemView) {
            super(itemView);
            text_title = itemView.findViewById(R.id.text_title);
            text_description = itemView.findViewById(R.id.text_description);
            remove_button = itemView.findViewById(R.id.remove_button);

            remove_button.setOnClickListener(v->{

            });
        }


    }
}
