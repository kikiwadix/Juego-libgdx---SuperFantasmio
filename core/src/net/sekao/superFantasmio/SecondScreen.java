/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sekao.superFantasmio;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 *
 * @author JuanPablo
 */
public class SecondScreen implements Screen{

    Stage stage;
    TiledMap map;
    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera camera;
    Fantasma fantasma;
    Malvado malvado;
    int puntuacion;
    
    //para el movimiento especial de la camara
    int posicion = 0; //para el movimiento de la camara
    Boolean mueveCamara = false;
    
    Vidas vidas;
    
    VidaExtraCodigo vidaExtra;
    
    
    //para cambiar de pantalla
    private Game game;
    
    public SecondScreen(Game game){
        this.game = game;
    }
    

   
    public void show() {
        map = new TmxMapLoader().load("level2.tmx");
        final float pixelsPerTile = 16;
        renderer = new OrthogonalTiledMapRenderer(map, 1 / pixelsPerTile);
        camera = new OrthographicCamera();

        stage = new Stage();
        stage.getViewport().setCamera(camera);
        ///////
        fantasma = new Fantasma(10, 20, game, camera);
        fantasma.layer = (TiledMapTileLayer) map.getLayers().get("walls");
        fantasma.setPosition(2, 2);
        stage.addActor(fantasma);
        ///////////
        malvado = new Malvado(71,9);
        malvado.layer = (TiledMapTileLayer) map.getLayers().get("walls");
        malvado.setPosition(71, 9);
       
        stage.addActor(malvado);
        ////////////
        ////////////
        vidas = new Vidas();
        //vidas.layer = (TiledMapTileLayer) map.getLayers().get("walls");
        vidas.setPosition(camera.position.x+10,17);
        stage.addActor(vidas);
        //////
        
        ////////////
        vidaExtra = new VidaExtraCodigo(82,14);
        vidaExtra.layer = (TiledMapTileLayer) map.getLayers().get("walls");
        vidaExtra.setPosition(82, 14);
        stage.addActor(vidaExtra);
        ////////
        
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
        if(fantasma.getX() > 17 && fantasma.getX()<212-17){
            camera.position.x = fantasma.getX();
        }
        
        vidas.setPosition(camera.position.x+12,18);
        
        //para colision entre fantasma y malvado
        if(fantasma.getRectangle().overlaps(malvado.getRectangle())){
            fantasma.muere(vidas);
            camera.position.x = fantasma.getX() +11;
        }
        
        //para que muera con los pinchos
        if(fantasma.getY() < 2){
            fantasma.muere(vidas);
        }
        
        //para cambiar de pantalla
        if(fantasma.getX()>=209){
            game.setScreen(new CreditsScreen(game));
        }
        
       
         //para colision entre fantasma y vidaExtra
        if(fantasma.getRectangle().overlaps(vidaExtra.getRectangle())){
            fantasma.sumaVidas(vidas, vidaExtra);
            vidaExtra.remove();
            //camera.position.x = fantasma.getX() +11;
        }
        
        
        /*para que no se vean los margenes (pantalla pequeña)
        if(fantasma.getX() > 13 && fantasma.getX()<212-13){
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
