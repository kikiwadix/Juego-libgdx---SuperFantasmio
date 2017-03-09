package net.sekao.superFantasmio;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
        import com.badlogic.gdx.Game;
        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
        import com.badlogic.gdx.graphics.GL20;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.SpriteBatch;
        import com.badlogic.gdx.scenes.scene2d.InputEvent;
        import com.badlogic.gdx.scenes.scene2d.InputListener;
        import com.badlogic.gdx.scenes.scene2d.Stage;
        import com.badlogic.gdx.scenes.scene2d.ui.Label;
        import com.badlogic.gdx.scenes.scene2d.ui.Skin;
        import com.badlogic.gdx.scenes.scene2d.ui.Table;
        import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 *
 * @author grep
 */
public class MenuScreen implements Screen {
    Stage stage = new Stage();
    Skin skin;
    Game game;
    private Texture menuBg;

    private SpriteBatch batch;

    public MenuScreen(Game game) {
        this.game = game;
        menuBg = new Texture(Gdx.files.internal("bg-menu.jpg"));

        batch = new SpriteBatch();
        batch.getProjectionMatrix().setToOrtho2D(0, 0, 514, 448);

        Sound mp3Sound = Gdx.audio.newSound(Gdx.files.internal("cancion.mp3"));
        long id = mp3Sound.play();
        mp3Sound.setVolume(id,0.5f);

    }

    @Override
    public void show() {
        // CREO UNA TABLA
        Table table = new Table();
        table.setPosition(1024 / 5, 450);
        table.setFillParent(true);
        table.setHeight(400);
        stage.addActor(table);

        // ETIKETA
        Label label = new Label("BIENVENIDO A SUPERFANTASMIO", getSkin());
        table.addActor(label);

        // BOTONES
        TextButton botonPlay = new TextButton("JUGAR", getSkin());
        botonPlay.setPosition(label.getOriginX(), label.getOriginY() -120);
        botonPlay.setWidth(200);
        botonPlay.setHeight(40);
        botonPlay.addListener(new InputListener() {
            public boolean touchDown(InputEvent e, float x, float y, int pointer, int button) {
                game.setScreen(new MainScreen(game));

                return false;
            }
        });
        table.addActor(botonPlay);

        TextButton botonSalir = new TextButton("SALIR", getSkin());
        botonSalir.setPosition(label.getOriginX(), label.getOriginY() -170);
        botonSalir.setWidth(200);
        botonSalir.setHeight(40);
        botonSalir.addListener(new InputListener() {
            public boolean touchDown(InputEvent e, float x, float y, int pointer, int button) {
                System.exit(0);
                return false;
            }
        });
        table.addActor(botonSalir);
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f, 0.5f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(menuBg, 0, 0);
        batch.end();

        // PINTER EL MENU
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(int width, int height) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void pause() {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void resume() {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void hide() {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dispose() {
        this.hide();
        //To change body of generated methods, choose Tools | Templates.
    }

    protected Skin getSkin() {
        if (skin == null) {
            skin = new Skin(Gdx.files.internal("uiskin.json"));
        }
        return skin;
    }
}