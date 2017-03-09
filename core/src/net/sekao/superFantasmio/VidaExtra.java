/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sekao.superFantasmio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 *
 * @author JuanPablo
 */
public class VidaExtra extends Actor{
    

    Texture vidaExtra;
    TiledMapTileLayer layer;

    

    public VidaExtra() {
        final float width = 16;
        final float height = 16;
        this.setSize(1, height / width);

        vidaExtra = new Texture("VidaExtra.png");

    }

 

    public void draw(Batch batch, float parentAlpha) {
        
       batch.draw(vidaExtra, this.getX(), this.getY(), this.getWidth(), this.getHeight());
       
        
    }

    
}
