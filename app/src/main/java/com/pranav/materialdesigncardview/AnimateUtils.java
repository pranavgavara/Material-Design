package com.pranav.materialdesigncardview;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

/**
 * Created by Pranav on 9/8/2016.
 */
public class AnimateUtils {




    public static void animate(Adapter_mostpopularlist.ViewHolderMostPopular holder,boolean goesdown) {
//        AnimatorSet animaterset=new AnimatorSet();
//        ObjectAnimator animatorTranslateY=new ObjectAnimator().ofFloat(holder.itemView,"translationY",goesdown==true?100:-100,0);
//        ObjectAnimator animatorTranslateX=new ObjectAnimator().ofFloat(holder.itemView,"translationX",-50,50,-40,40,-30,30,-20,20,-10,10,0);
//        animatorTranslateY.setDuration(1000);
//        animatorTranslateX.setDuration(1000);
//        animaterset.playTogether(animatorTranslateX,animatorTranslateY);
//        animaterset.start();
        YoYo.with(Techniques.ZoomIn)
                .duration(1000)
                .playOn(holder.itemView);

    }
    public static void animateToolbar(View containerToolbar){

    }
}
