package net.sekao.superFantasmio;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.*;
import com.badlogic.gdx.scenes.scene2d.*;
import static java.lang.Math.abs;
import static java.lang.Math.abs;
import java.util.ArrayList;

public class EndScreen implements Screen {
    Stage stage;
    TiledMap map;
    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera camera;
    Fantasma fantasma;
    int puntuacion;
    
    //para el movimiento especial de la camara
    int posicion = 0; //para el movimiento de la camara
    Boolean mueveCamara = false;
    
    //para cambiar de pantalla
    private Game game;
    
    public EndScreen(Game game){
        this.game = game;
    }
    
    

    public void show() {
        map = new TmxMapLoader().load("level3.tmx");
        final float pixelsPerTile = 16;
        renderer = new OrthogonalTiledMapRenderer(map, 1 / pixelsPerTile);
        camera = new OrthographicCamera();

        stage = new Stage();
        stage.getViewport().setCamera(camera);
        ///////
        ArrayList<Fantasma> fantasmas = new ArrayList();
        for(int i=0;i<20;i++){
            fantasmas.add(new Fantasma(10+i,20, game, camera));
            fantasmas.get(i).layer = (TiledMapTileLayer) map.getLayers().get("walls");
            fantasmas.get(i).setPosition(10+i, 20);
            stage.addActor(fantasmas.get(i));
            /*
            fantasma = new Fantasma(10,20, game);
            fantasma.layer = (TiledMapTileLayer) map.getLayers().get("walls");
            fantasma.setPosition(10, 20);
            stage.addActor(fantasma);
            */
        }
        
        
        
        camera.position.x = 10;
     
        
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f, 0.5f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

      
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
