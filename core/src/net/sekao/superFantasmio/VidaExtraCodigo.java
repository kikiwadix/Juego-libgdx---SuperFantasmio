package net.sekao.superFantasmio;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class VidaExtraCodigo extends Actor {
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
    
    Rectangle vidaExtraRectangle;
    
    int rectanguloX = 0;
    int rectanguloY = 0;
    
    Texture frame;

    public VidaExtraCodigo() {
        final float width = 30;
        final float height = 26;
        this.setSize(1, height / width);

        Texture vidaTexture = new Texture("vidaCorazon.png");
        
        frame = vidaTexture;
     
    }

    
    public VidaExtraCodigo(int x, int y) {
        final float width = 18;
        final float height = 26;
        this.setSize(1, height / width);

        Texture vidaTexture = new Texture("vidaCorazon.png");
        
        frame = vidaTexture;
       
        vidaExtraRectangle =  new Rectangle(x , y, (int)this.getWidth(), (int)this.getHeight());
       
        
       
    }

    public void act(float delta) {
        
        /*
        vidaExtraRectangle.setX(this.getX());
        vidaExtraRectangle.setY(this.getY());
        */
        
        //vidaExtraRectangle =  new Rectangle(this.getX() , this.getY(), (int)this.getWidth(), (int)this.getHeight());
        time = time + delta;


        /*
        boolean leftTouched = Gdx.input.isTouched() && Gdx.input.getX() < Gdx.graphics.getWidth() / 3;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || leftTouched) {
            xVelocity = -1 * MAX_VELOCITY;
            isFacingRight = false;
        }

        boolean rightTouched = Gdx.input.isTouched() && Gdx.input.getX() > Gdx.graphics.getWidth() * 2 / 3;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || rightTouched) {
            xVelocity = MAX_VELOCITY;
            isFacingRight = true;
        }
        
        */
        
        
        //para reiniciar el juego
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            this.setPosition(71, 9);
        }
        ////////////
        
        
        /*
        yVelocity = yVelocity + GRAVITY;

        float x = this.getX();
        float y = this.getY();
        float xChange = xVelocity * delta;
        float yChange = yVelocity * delta;

        if (canMoveTo(x + xChange, y, false) == false) {
            xVelocity = xChange = 0;
        }

        if (canMoveTo(x, y + yChange, yVelocity > 0) == false) {
            canJump = yVelocity < 0;
            yVelocity = yChange = 0;
        }

        this.setPosition(x + xChange, y + yChange);

        xVelocity = xVelocity * DAMPING;
        if (Math.abs(xVelocity) < 0.5f) {
            xVelocity = 0;
        }
        
        */
    }

    public void draw(Batch batch, float parentAlpha) {
        
        
        
        vidaExtraRectangle.setX(this.getX());
        vidaExtraRectangle.setY(this.getY());
        
       

        if (isFacingRight) {
            batch.draw(frame, this.getX(), this.getY(), this.getWidth(), this.getHeight());
        } else {
            batch.draw(frame, this.getX() + this.getWidth(), this.getY(), -1 * this.getWidth(), this.getHeight());
        }
    }

    private boolean canMoveTo(float startX, float startY, boolean shouldDestroy) {
        float endX = startX + this.getWidth();
        float endY = startY + this.getHeight();

        int x = (int) startX;
        while (x < endX) {

            int y = (int) startY;
            while (y < endY) {
                if (layer.getCell(x, y) != null) {
                    /*
                    if (shouldDestroy) {
                        layer.setCell(x, y, null);
                    }
                    */
                    return false;
                    
                }
                y = y + 1;
            }
            x = x + 1;
        }

        return true;
    }
    
    public Rectangle getRectangle(){
        return this.vidaExtraRectangle;
    }
    
    public void setPosicionRectangulo(){
        this.rectanguloX = -5;
        this.rectanguloY = -5;
    }
}
