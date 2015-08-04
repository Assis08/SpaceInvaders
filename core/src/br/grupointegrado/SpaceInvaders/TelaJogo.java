package br.grupointegrado.SpaceInvaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FillViewport;


/**
 * Created by Willys on 03/08/2015.
 */
public class TelaJogo extends TelaBase {
    private OrthographicCamera camera; //camera 2d (cordenadas x y)
    private SpriteBatch batch; // desenha as imagens
    private Stage palco;
    private BitmapFont fonte;
    private Label lbpontucao;

    /**
     * Construtor padrão da tela de jogo
     * @param game Referência para a classe principal
     */
    public TelaJogo(MainGame game) {
        super(game);
    }

    /**
     * Chamado quando a tela é exibida
     */
    @Override
    public void show() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        //largura e altura da camera atual e passa a camera atual como referencia
        palco = new Stage(new FillViewport(camera.viewportWidth, camera.viewportHeight, camera));

        initFonte();
        initInformacoes();
    }

    private void initFonte(){
        fonte = new BitmapFont();
    }

    private void initInformacoes() {

        Label.LabelStyle lbEstilo = new Label.LabelStyle();
        lbEstilo.font = fonte;
        lbEstilo.fontColor = Color.WHITE;

        lbpontucao = new Label("0 pontos", lbEstilo);
        palco.addActor(lbpontucao);
    }

    /**
     * Chamado a todo quadro de atualização do jogo FPS (Frame ou quadros por segundo)
     * @param delta Tempo entre um quadro e outro em segundos
     */
    @Override
    public void render(float delta) {
        //Limpa tela
        Gdx.gl.glClearColor(.15f, .15f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        lbpontucao.setPosition(10,camera.viewportHeight-20 ); // X Y   viewportHeight-20 significa que ira seta na posição a 20 pixel abaixo da altura maxima da tela

        palco.act(delta); // avisa que rodou um novo quadro
        palco.draw();


    }

    /**
     * É chamado sempre que há uma alteração no tamanho da tela
     * @param width Novo valor de largura da tela
     * @param height Novo valor de altura da tela
     */
    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        camera.update();
    }

    /**
     * É chamado sempre que o jogo for minimizado
     */
    @Override
    public void pause() {

    }

    /**
     * É chamado sempre que  jogo voltar para o primeiro plano
     */
    @Override
    public void resume() {

    }

    /**
     * É chamado quando a tela for destruida
     */
    @Override
    public void dispose() {

        batch.dispose();
        palco.dispose();
        fonte.dispose();
    }
}
