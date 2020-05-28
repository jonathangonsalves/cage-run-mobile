package com.example.flappycage.data.model;

import android.widget.ImageView;

public class GameObject {

    private ImageView image;
    private int width, height;
    private int x, y;

    public GameObject(ImageView img, int w, int h){
        this.image = img;
        this.width = w;
        this.height = h;
        this.x = (int) img.getX();
        this.y = (int) img.getY();
    }

    public ImageView getImage() {
        return image;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setImage(ImageView img) {
        this.image = img;
    }

    public void setWidth(int width) {
        this.width = width;
        this.image.getLayoutParams().width = this.width;
        this.image.requestLayout();
        this.image.invalidate();
    }

    public void setHeight(int height) {
        this.height = height;
        this.image.getLayoutParams().height = this.height;
        this.image.requestLayout();
        this.image.invalidate();
    }

    public void setX(int x) {
        this.x = x;
        this.image.setX(this.x);
    }

    public void setY(int y) {
        this.y = y;
        this.image.setY(this.y);
    }

    static public boolean isColliding(GameObject a, GameObject b, char optPos){
        if(optPos == 'b') {                                                                         // bottom
            if (a.x > (b.x + b.width)) {
                return false;
            } else if ((a.x + a.width) < b.x) {
                return false;
            } else if (a.y < (b.y + b.height)) {
                return false;
            } else if ((a.y - a.height) > b.y) {
                return false;
            } else {
                return true;
            }
        }else{                                                                                      // top
            if (a.x > (b.x + b.width)) {
                return false;
            } else if ((a.x + a.width) < b.x) {
                return false;
            } else if (a.y > (b.y + b.height)) {
                return false;
            } else if ((a.y + a.height) < b.y) {
                return false;
            } else {
                return true;
            }
        }
    }
}
