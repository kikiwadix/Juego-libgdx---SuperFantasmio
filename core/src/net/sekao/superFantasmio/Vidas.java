/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sekao.superFantasmio;




import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


public class Vidas extends Image {
    TextureRegion stand, jump;
    Animation walk;

    float time = 0;
    float xVelocity = 0;
    float yVelocity = 0;
    boolean canJump = false;
    boolean isFacingRight = true;
    TiledMapTileLayer layer;

    final float GRAVITY = -2.5f;
    final float MAX_VELOCITY = 10f;
    final float DAMPING = 0.87f;
    
    public Texture corazones3, corazones2, corazones1, corazones0;
    
    public Texture frame;

    public Vidas() {
        final float width = 210;
        final float height = 52;
        this.setSize(4, height*2 / width*2);

        corazones3 = new Texture("cor1.png");
        corazones2 = new Texture("cor2.png");
        corazones1 = new Texture("cor3.png");
        corazones0 = new Texture("cor4.png");
        
        frame = this.corazones3;
       
    }

       public void act(float delta) {
        time = time + delta;

      
    }
       
    public void setCorazones(int vidas){
        
        if(vidas == 3){
            frame =this.corazones3;
        }
        if(vidas == 2){
            frame =this.corazones2;
        }
        if(vidas == 1){
            frame =this.corazones1;
        }
        if(vidas == 0){
            frame =this.corazones0;
        }
    }   
       

    public void draw(Batch batch, float parentAlpha) {
        
        
       

        if (isFacingRight) {
            batch.draw(frame, this.getX(), this.getY(), this.getWidth(), this.getHeight());
        } else {
            batch.draw(frame, this.getX() + this.getWidth(), this.getY(), -1 * this.getWidth(), this.getHeight());
        }
    }

    
}