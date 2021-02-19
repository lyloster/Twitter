package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.media.Image;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

//Start by extending RecyclerView.ViewHolder
// Then extend RecyclerView.Adapter<VH>
//Parametrize it with the just defined ViewHolder

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder>{

    Context context;
    List<Tweet> tweets;

    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    //Pass in context and list of tweets
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(view);
    }

    //For each row inflate the layout for a tweet
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Get the data at a position
        Tweet tweet = tweets.get(position);
        //Bind the tweet with the viewHolder
        holder.bind(tweet);
    }

    //Bind values based on the position of the element
    @Override
    public int getItemCount() {
        return tweets.size();
    }

    //SwipeLayout helper method
    //Clean all elements of the recycler
    public void clear(){
        tweets.clear(); //modifying the existing reference, not creating a new one
        notifyDataSetChanged();
    }

    //SwipeLayout helper method
    //Add a list of items to our dataset
    public void addAll(List<Tweet> tweetList) {
        tweets.addAll(tweetList);
        notifyDataSetChanged();
    }

    // Define a viewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvBody;
        TextView tvScreenName;
        ImageView ivProfileImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
        }

        public void bind(Tweet tweet) {
            //Take the different attributes of a tweet and bnd them into views
            tvScreenName.setText(tweet.user.screenName);
            tvBody.setText(tweet.body);
            Glide.with(context).load(tweet.user.profileImageUrl).into(ivProfileImage);
        }
    }
}
