package net.sekao.superFantasmio;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;

public class Fantasma extends Actor {
    TextureRegion stand, jump;
    Animation walk;
    
    int vidas = 3;

    float time = 0;
    float xVelocity = 0;
    float yVelocity = 0;
    boolean canJump = false;
    boolean isFacingRight = true;
    TiledMapTileLayer layer;

    final float GRAVITY = -2.5f;
    final float MAX_VELOCITY = 10f;
    final float DAMPING = 0.87f;
    
    Rectangle fantasmaRectangle;
    
    //para cambiar de pantalla
    private Game game;
    OrthographicCamera camera;
    
    int recanguloX = 0;
    int rectanguloY = 0;
    Sound mp3Sound;
   

    public Fantasma() {
        final float width = 18;
        final float height = 26;
        this.setSize(1, height / width);

        Texture koalaTexture = new Texture("fantasmio.png");
        TextureRegion[][] grid = TextureRegion.split(koalaTexture, (int) width, (int) height);

        stand = grid[0][0];
        jump = grid[0][1];
        walk = new Animation(0.15f, grid[0][2], grid[0][3], grid[0][4]);
        walk.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        

    }
    
    public Fantasma(int x, int y, Game game, OrthographicCamera camera) {
        final float width = 18;
        final float height = 26;
        this.setSize(1, height / width);

        Texture koalaTexture = new Texture("fantasmio.png");
        TextureRegion[][] grid = TextureRegion.split(koalaTexture, (int) width, (int) height);

        stand = grid[0][0];
        jump = grid[0][1];
        walk = new Animation(0.15f, grid[0][2], grid[0][3], grid[0][4]);
        walk.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        
        fantasmaRectangle = new Rectangle(x , y ,(int) this.getWidth(), (int)this.getHeight());
        
        this.game = game;
        
        this.camera = camera;
        
        
        System.out.println("fantasmaX:" + fantasmaRectangle.getX());
        System.out.println("fantasmaY:" + fantasmaRectangle.getY());
        
        mp3Sound = Gdx.audio.newSound(Gdx.files.internal("muerte2.mp3"));
        
        
       
        
        
    }

    public void act(float delta) {
        
        fantasmaRectangle.setX(this.getX());
        fantasmaRectangle.setY(this.getY());
        
       /*
        if( this.tocaPinchos(this.getX(), this.getY())){
            this.muere();
        }
        */
        
        System.out.println("fantasmaX:" + fantasmaRectangle.getX());
        System.out.println("fantasmaY:" + fantasmaRectangle.getY());
        
        time = time + delta;

        boolean upTouched = Gdx.input.isTouched() && Gdx.input.getY() < Gdx.graphics.getHeight() / 2;
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || upTouched) {
            if (canJump) {
                yVelocity = yVelocity + MAX_VELOCITY * 4;
            }
            canJump = false;
        }

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
        
        
        //para reiniciar el juego
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            vidas = 3;
            game.setScreen(new MainScreen(game));
            
        }
        ////////////
        
        
        
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
    }

    public void draw(Batch batch, float parentAlpha) {
        TextureRegion frame;
        
        
        fantasmaRectangle.setX(this.getX());
        fantasmaRectangle.setY(this.getY());
        
        System.out.println("fantasmaX:" + fantasmaRectangle.getX());
        System.out.println("fantasmaY:" + fantasmaRectangle.getY());
        

        if (yVelocity != 0) {
            frame = jump;
        } else if (xVelocity != 0) {
            frame = walk.getKeyFrame(time);
        } else {
            frame = stand;
        }

        if (isFacingRight) {
            batch.draw(frame, this.getX(), this.getY(), this.getWidth(), this.getHeight());
        } else {
            batch.draw(frame, this.getX() + this.getWidth(), this.getY(), -1 * this.getWidth(), this.getHeight());
        }
    }
    
    private boolean tocaPinchos(float startX, float startY) {
        float endX = startX + this.getWidth();
        float endY = startY + this.getHeight();
        
        Boolean resultado = false;

        int x = (int) startX;
        int y = (int) startY;
        
        
        
        if(layer.getCell(x, y).getTile().getProperties().containsKey("tipo")){
            
            if (layer.getCell(x, y).getTile().getProperties().get("tipo").equals("pincho") ) {
            resultado = true;
            }
            
        }
        
        
       
        

        return resultado;
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
        return this.fantasmaRectangle;
    }
    
    public void muere(Vidas vida){
       
        mp3Sound.play();
        vidas--;
        
        if(vidas == 0){
            vida.setCorazones(vidas);
            game.setScreen(new EndScreen(game));
        }else{
            vida.setCorazones(vidas);
            this.setX(10);
            this.setY(20);
            camera.position.x = this.getX()+10;
           
        }
        
    }
    
    public void sumaVidas(Vidas vida, VidaExtraCodigo vidaExtra){
       
        if(vidas<3){
            vidas++;
            
                vida.setCorazones(vidas);
                //vidaExtra.setPosicionRectangulo();
                /*
                this.setX(10);
                this.setY(20);
                camera.position.x = this.getX()+10;
                */
            
        }
        
        
    }
    
}
