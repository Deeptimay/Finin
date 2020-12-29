package com.example.finin.adapters;

import android.widget.ImageView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.finin.models.Datum;

import androidx.databinding.BindingAdapter;

public class BindingAdapters {

    @BindingAdapter({"setImage"})
    public static void bindImage(ImageView imageView, Datum userData) {

        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
        int color = generator.getRandomColor();

        String name = String.valueOf(String.valueOf(userData.getFirstName().charAt(0)) + userData.getLastName().charAt(0)).toUpperCase();

        TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .withBorder(4) /* thickness in px */
                .endConfig()
                .buildRoundRect(name, color, 10);

        Glide.with(imageView.getContext())
                .load(userData.getAvatar())
                .apply(RequestOptions.placeholderOf(drawable).error(drawable))
                .into(imageView);
    }
}
