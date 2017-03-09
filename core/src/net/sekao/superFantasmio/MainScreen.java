package net.sekao.superFantasmio;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.*;
import com.badlogic.gdx.scenes.scene2d.*;
import static java.lang.Math.abs;
import static java.lang.Math.abs;

public class MainScreen implements Screen {
    Stage stage;
    TiledMap map;
    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera camera;
    Fantasma fantasma;
    Malvado malvado;
    int puntuacion;
    
    SpriteBatch batch = new SpriteBatch();
    BitmapFont mostrarVida = new BitmapFont();
    
    Vidas vidas;
    Fuego fuego;
    Fuego fuego2;
    VidaExtraCodigo vidaExtra;
    
    
    
    
    //para el movimiento especial de la camara
    int posicion = 0; //para el movimiento de la camara
    Boolean mueveCamara = false;
    
    //para cambiar de pantalla
    private Game game;
    
    public MainScreen(Game game){
        this.game = game;
        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
    }
    
    

    public void show() {
        map = new TmxMapLoader().load("level1.tmx");
        final float pixelsPerTile = 16;
        renderer = new OrthogonalTiledMapRenderer(map, 1 / pixelsPerTile);
        camera = new OrthographicCamera();

        stage = new Stage();
        stage.getViewport().setCamera(camera);
        ///////
        fantasma = new Fantasma(10, 20, game, camera);
        fantasma.layer = (TiledMapTileLayer) map.getLayers().get("walls");
        fantasma.setPosition(10, 20);
        stage.addActor(fantasma);
        
        ///////////
        malvado = new Malvado(71, 9);
        malvado.layer = (TiledMapTileLayer) map.getLayers().get("walls");
        malvado.setPosition(71, 9);
        stage.addActor(malvado);
        
        ////////////
        vidas = new Vidas();
        //vidas.layer = (TiledMapTileLayer) map.getLayers().get("walls");
        vidas.setPosition(camera.position.x+10,17);
        stage.addActor(vidas);
        
        
        //////////////////
        fuego = new Fuego();
        fuego.layer  = (TiledMapTileLayer) map.getLayers().get("walls");
        fuego.setPosition(50, 9);
        stage.addActor(fuego);

        //////////////////
        fuego2 = new Fuego();
        fuego2.layer  = (TiledMapTileLayer) map.getLayers().get("walls");
        fuego2.setPosition(46, 9);
        stage.addActor(fuego2);

        ////////////
        vidaExtra = new VidaExtraCodigo(82,14);
        vidaExtra.layer = (TiledMapTileLayer) map.getLayers().get("walls");
        vidaExtra.setPosition(82, 14);
        stage.addActor(vidaExtra);
        
        
        /*
        MapLayer vidas = map.getLayers().get("VidaExtra");
        for(MapObject vida : vidas.getObjects()){
            VidaExtra vid = new VidaExtra();
            vid.layer = (TiledMapTileLayer) map.getLayers().get("walls");
           
            float x = Float.parseFloat(vida.getProperties().get("x").toString());
            x = x/16;
            float y = Float.parseFloat(vida.getProperties().get("y").toString());
            y = y/16;
            vid.setPosition(x, y);
            stage.addActor(vid);
           
            
        }
        */
        
        
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f, 0.5f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        
        
        //para que no se vean los margenes (pantalla completa)
        if(fantasma.getX() > 20 && fantasma.getX()<212-20){
            camera.position.x = fantasma.getX();
        }
        
        vidas.setPosition(camera.position.x+12,18);
        
        //para colision entre fantasma y malvado
        if(fantasma.getRectangle().overlaps(malvado.getRectangle())){
            fantasma.muere(vidas);
            camera.position.x = fantasma.getX() +11;
        }
        
        
         //para colision entre fantasma y vidaExtra
        if(fantasma.getRectangle().overlaps(vidaExtra.getRectangle())){
            fantasma.sumaVidas(vidas, vidaExtra);
            vidaExtra.remove();
            //camera.position.x = fantasma.getX() +11;
        }
        
        
        
        //imprimir vida
        batch = new SpriteBatch();
        batch.begin();
        
        mostrarVida.draw(batch, "VIDA", Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight() -2);
        
        
        
        
        //para que muera con los pinchos
        if(fantasma.getY() < 2){
            fantasma.muere(vidas);
        }
        
        
        
        
        //para cambiar de pantalla
        if(fantasma.getX()>=210){
            game.setScreen(new SecondScreen(game));
        }
        
        
        
       
        
        
        /*para que no se vean los margenes (pantalla pequeña)
        if(fantasma.getX() > 13 && fantasma.getX()<212-13){
            camera.position.x = fantasma.getX();
        }
        */
        
        
        System.out.println("x=" + fantasma.getX());
        System.out.println("y=" + fantasma.getY());
      
        
        /*
        if(fantasma.getX() <= fantasma.getX()-18 || fantasma.getX() >= fantasma.getX()+20 ){
            
        }else{
            camera.position.x = fantasma.getX();
        }
        */
        
        camera.update();

        renderer.setView(camera);
        renderer.render();
        
        

        stage.act(delta);
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
        map.dispose();
        renderer.dispose();
        
    }

    public void hide() {
    }

    public void pause() {
    }

    public void resize(int width, int height) {
        camera.setToOrtho(false, 20 * width / height, 20);
    }

    public void resume() {
    }

    
}
