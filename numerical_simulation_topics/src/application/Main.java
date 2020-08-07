package application;
	
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Translate;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
	
	boolean startcheck1 = false;
	boolean stopcheck1 = true;
	boolean timeline1check = false;
	Timeline timeline1;
	int phasecheck1 = 0;
	Label t1label = new Label("");
	Label Nlabel = new Label("");
	double t1 = 0;
    double N1 = 0;
    double N2 = 0;
    double populationmaxdouble = 0;
    double populationzerodouble = 0;
    double regionwidth = 0;
    double gammadouble = 0;
    TextField birthratetext = new TextField();
    TextField deathratetext = new TextField();
    Canvas populationcanvas = new Canvas( 1000, 800 );
    GraphicsContext populationgc = populationcanvas.getGraphicsContext2D();
    Canvas populationformulacanvas = new Canvas(900, 720);
    GraphicsContext populationformulagc = populationformulacanvas.getGraphicsContext2D();
    Canvas diffcanvas = new Canvas( 1000, 800 );
    GraphicsContext diffgc = diffcanvas.getGraphicsContext2D();
	Canvas diffplaycanvas = new Canvas( 1000, 800 );
	GraphicsContext diffplaygc = diffplaycanvas.getGraphicsContext2D();
    double t2 = 0;
    double diffomegadouble = 0;
    double diffcoefdouble = 0;
    double diffdeltatdouble = 0;
    boolean diffCzerocheck = false;
    double C1[][][] = new double[100][100][2];
    boolean startcheck2 = false;
	boolean stopcheck2 = true;
	boolean timeline2check = false;
	Timeline timeline2;
	int phasecheck2 = 0;
	Label t2label = new Label("");
	Canvas diffformulacanvas = new Canvas(900, 720);
    GraphicsContext diffformulagc = diffformulacanvas.getGraphicsContext2D();
    Canvas wavecanvas = new Canvas( 1000, 800 );
    GraphicsContext wavegc = wavecanvas.getGraphicsContext2D();
    Canvas waveplaycanvas = new Canvas( 1000, 800 );
	GraphicsContext waveplaygc = waveplaycanvas.getGraphicsContext2D();
	Label t3label = new Label("");
	double t3 = 0;
    double wavecoefdouble = 0;
    double wavedeltatdouble = 0;
    boolean waveuzerocheck = false;
    double u1[][][] = new double[100][100][3];
    boolean startcheck3 = false;
	boolean stopcheck3 = true;
	boolean timeline3check = false;
	Timeline timeline3;
	int phasecheck3 = 0;
	boolean arrange1 = false;
	Canvas waveformulacanvas = new Canvas(900, 720);
    GraphicsContext waveformulagc = waveformulacanvas.getGraphicsContext2D();
	
    
	@SuppressWarnings("exports")
	@Override
	public void start(Stage stage) {
		//buttonの番号対応
		//1->スタート画面
		//2->説明書き
		//3->Verhulstプレイ
		//4->Verhulst開始／一時停止
		//5->Verhulstリセット
		//6->Verhulst戻る
		//7->Verhulst解説
		//8->Verfulstから2次元拡散方程式
		//9->2次元拡散方程式プレイ
		//10->2次元拡散方程式からVerfulst
		//11->2次元拡散方程式開始／一時停止
		//12->2次元拡散方程式リセット
		//13->2次元拡散方程式戻る
		//14->2次元拡散方程式解説
		//15->2次元拡散方程式csv
		//16->2次元拡散方程式から2次元波動方程式
		//17->2次元波動方程式プレイ
		//18->2次元波動方程式から2次元拡散方程式
		//19->2次元波動方程式開始／一時停止
		//20->2次元波動方程式リセット
		//21->2次元波動方程式戻る
		//22->2次元波動方程式解説
		//23->2次元波動方程式csv
		//24->2次元波動方程式アレンジありなし
		
		//tの番号対応
		//1->Verhulst
		//2->2次元拡散方程式
		//3->2次元波動方程式
		
		
		//スタート画面ここから
        Label label = new Label("数値実験の話題");
        label.setFont(new Font(50));
        Button button1 = new Button("Start");
        button1.setMinSize(200, 80);
        button1.setFont(new Font(40));
        GridPane startlabelpanel = new GridPane();
        startlabelpanel.setPadding(new Insets(80, 0, 0, 330));
        startlabelpanel.add(label, 0, 0);
        BorderPane titlescene = new BorderPane();
        titlescene.setTop(startlabelpanel);
        GridPane startbuttonpanel = new GridPane();
        startbuttonpanel.setPadding(new Insets(0, 0, 100, 400));
        startbuttonpanel.add(button1, 0, 0);
        titlescene.setBottom(startbuttonpanel);
        Image image = new Image( "title.png" );
        ImageView imgView = new ImageView( image );
        GridPane imagepanel = new GridPane();
        imagepanel.setPadding(new Insets(100, 0, 0, 400));
        imagepanel.add(imgView, 0, 0);
        titlescene.setCenter(imagepanel);
        stage.setScene(new Scene(titlescene, 1000, 800));
        stage.setTitle("数値実験の話題");
        stage.show();
        //スタート画面ここまで
        
        Button button2 = new Button("次へ進む");
        Label introlabel = new Label("このアプリについて");
        GridPane intropanel = new GridPane();
        Label introlabel2 = new Label("有名な数値実験の例をアニメーション付きで紹介するアプリです．"
        		+ "\n数値実験の例を作成するにあたって，下記の書籍やWebサイトを参考にしました．"
        		+ "\n　*デヴィッド・バージェス／モラグ・ボリー著，垣田高夫／大町比佐栄訳，1990，"
        		+ "\n　 微分方程式で数学モデルを作ろう，日本評論社"
        		+ "\n　*数値計算入門 その１，2020年7月30日アクセス"
        		+ "\n　　　http://www.aoni.waseda.jp/ykagawa/chap1html/chap1html.html"
        		+ "\n   *波動方程式の数値解法，2020年8月6日アクセス"
        		+ "\n           https://qiita.com/Ushio/items/0249fd7a5363ccd914dd");
        GridPane intropanel2 = new GridPane();
        GridPane button2panel = new GridPane();
        
        button1.setOnAction((ActionEvent e) -> {
        	//説明書きここから
            startlabelpanel.setManaged(false);
            startlabelpanel.setVisible(false);
            startbuttonpanel.setManaged(false);
            startbuttonpanel.setVisible(false);
            imagepanel.setManaged(false);
            imagepanel.setVisible(false);
            introlabel.setFont(new Font(50));
            intropanel.add(introlabel, 0, 0);
            intropanel.setPadding(new Insets(40, 0, 0, 330));
            introlabel2.setFont(new Font(24));
            intropanel2.add(introlabel2, 0, 0);
            intropanel2.setPadding(new Insets(0, 0, 0, 30));
            button2.setMinSize(200, 80);
            button2.setFont(new Font(40));
            button2panel.setPadding(new Insets(0, 0, 100, 400));
            button2panel.add(button2, 0, 0);
            BorderPane introscene = new BorderPane();
            introscene.setTop(intropanel);
            introscene.setCenter(intropanel2);
            introscene.setBottom(button2panel);
            stage.setScene(new Scene(introscene, 1000, 800));
            //説明書きここまで
        });
        
        
        
        Label populationlabel = new Label("Verhulstの人口増加モデル");
        GridPane populationlabelpanel = new GridPane();
        Button button3 = new Button("プレイする");
        GridPane button3panel = new GridPane();
        Image verfulstimage = new Image( "population.png" );
        ImageView verfulstimgView = new ImageView( verfulstimage );
        Button button8 = new Button("≫");
        GridPane button8panel = new GridPane();
        
        button2.setOnAction((ActionEvent e) -> {
        	//説明書きからのVerhulstモデル
        	intropanel.setManaged(false);
            intropanel.setVisible(false);
            intropanel2.setManaged(false);
            intropanel2.setVisible(false);
            button2panel.setManaged(false);
            button2panel.setVisible(false);
            populationlabel.setFont(new Font(50));
            populationlabelpanel.add(populationlabel, 0, 0);
            populationlabelpanel.setPadding(new Insets(40, 0, 0, 225));
            button3.setMinSize(200, 80);
            button3.setFont(new Font(40));
            button3panel.setPadding(new Insets(0, 0, 100, 400));
            button3panel.add(button3, 0, 0);
            button8.setMinSize(20, 20);
            button8.setFont(new Font(32));
            button8.setStyle("-fx-base: #f4f162");
            button8panel.setPadding(new Insets(200, 10, 0, 0));
            button8panel.add(button8, 0, 0);
            BorderPane populationscene = new BorderPane();
            populationscene.setTop(populationlabelpanel);
            populationscene.setBottom(button3panel);
            populationscene.setCenter(verfulstimgView);
            populationscene.setRight(button8panel);
            stage.setScene(new Scene(populationscene, 1000, 800));
        });
        
        
        Label populationzero = new Label(" 初期人口");
        Label populationmax = new Label(" 人口の上限");
        Label birthrate = new Label(" 出生率");
        Label deathrate = new Label(" 死亡率");
        Label populationexplanation = new Label(" ※初期人口と人口の上限はいずれも1以上100以下，出生率は死亡率以上の値としてください");
        TextField populationzerotext = new TextField();
        TextField populationmaxtext = new TextField();
        Button button4 = new Button("開始／一時停止");
        Button button5 = new Button("リセット");
        Button button6 = new Button("数値実験の選択に戻る");
        Button button7 = new Button("解説を見る");
        
        populationgc.setFill(Color.rgb(244, 244, 244));
        populationgc.fillRect(0, 0, populationcanvas.getWidth(), populationcanvas.getHeight());
        populationzero.setFont(new Font(24));
        populationzero.getTransforms().add(new Translate(10, 8));
        populationzerotext.getTransforms().add(new Translate(120, 5));
        populationzerotext.setFont(new Font(18));
        populationzerotext.setMaxSize(80, 10);
        populationmax.setFont(new Font(24));
        populationmax.getTransforms().add(new Translate(210, 8));
        populationmaxtext.getTransforms().add(new Translate(340, 5));
        populationmaxtext.setFont(new Font(18));
        populationmaxtext.setMaxSize(80, 10);
        birthrate.setFont(new Font(24));
        birthrate.getTransforms().add(new Translate(430, 8));
        birthratetext.getTransforms().add(new Translate(515, 5));
        birthratetext.setFont(new Font(18));
        birthratetext.setMaxSize(80, 10);
        deathrate.setFont(new Font(24));
        deathrate.getTransforms().add(new Translate(605, 8));
        deathratetext.getTransforms().add(new Translate(690, 5));
        deathratetext.setFont(new Font(18));
        deathratetext.setMaxSize(80, 10);
        populationexplanation.setFont(new Font(24));
        populationexplanation.getTransforms().add(new Translate(10, 50));
        button4.setFont(new Font(20));
        button4.getTransforms().add(new Translate(10, 100));
        button5.setFont(new Font(20));
        button5.getTransforms().add(new Translate(200, 100));
        button6.setFont(new Font(20));
        button6.getTransforms().add(new Translate(10, 750));
        button7.setFont(new Font(20));
        button7.getTransforms().add(new Translate(870, 100));
        t1label.setFont(new Font(24));
        t1label.getTransforms().add(new Translate(650, 700));
        Nlabel.setFont(new Font(24));
        Nlabel.getTransforms().add(new Translate(650, 730));
        
        button3.setOnAction((ActionEvent e) -> {
        	//Verhulstプレイ画面
        	populationlabelpanel.setManaged(false);
            populationlabelpanel.setVisible(false);
            button3panel.setManaged(false);
            button3panel.setVisible(false);
            Group population = new Group();
            population.getChildren().add(populationcanvas);
            population.getChildren().add(populationzero);
            population.getChildren().add(populationzerotext);
            population.getChildren().add(populationmax);
            population.getChildren().add(populationmaxtext);
            population.getChildren().add(birthrate);
            population.getChildren().add(birthratetext);
            population.getChildren().add(deathrate);
            population.getChildren().add(deathratetext);
            population.getChildren().add(populationexplanation);
            population.getChildren().add(button4);
            population.getChildren().add(button5);
            population.getChildren().add(button6);
            population.getChildren().add(button7);
            population.getChildren().add(t1label);
            population.getChildren().add(Nlabel);
            Scene populationplayscene = new Scene(population);
            stage.setScene(populationplayscene);
        });
        
        
        
        
        
        
        button4.setOnAction((ActionEvent e) -> {
        	//Verhulst計算開始
        	
        	if (startcheck1 == true) {
        		phasecheck1 = phasecheck1 + 1;
        	}
        	
        	
        	
        	
        	
        	if(startcheck1 == false) {
        		if(populationmaxtext.getLength() > 0) {
        			if(populationzerotext.getLength() > 0) {
        				if(birthratetext.getLength() > 0) {
        					if(deathratetext.getLength() > 0) {
        					    populationmaxdouble = Double.parseDouble(populationmaxtext.getText());
        						populationzerodouble = Double.parseDouble(populationzerotext.getText());
        						if(populationmaxdouble < 101) {
        							if(populationzerodouble < 101) {
        								if(populationmaxdouble >= populationzerodouble) {
        									populationgc.setStroke(Color.rgb(0, 0, 0));
            		        				populationgc.setLineWidth(10);
            		                		regionwidth = Math.sqrt(populationmaxdouble)*50;
            		                		populationgc.strokeRect(500 - regionwidth/2, 450 - regionwidth/2, regionwidth, regionwidth);
            		                		int populationzeroint = (int)populationzerodouble;
            		                		populationgc.setFill(Color.BLUE);
            		                		for (int humanfirstdrawcounter = 0; humanfirstdrawcounter < populationzeroint; humanfirstdrawcounter++) {
            		                			double humanx = Math.random();
            		                			double humany = Math.random();
            		                			populationgc.fillOval(500 - regionwidth/2 + 10 + humanx*(regionwidth - 50), 450 - regionwidth/2 + 10 + humany*(regionwidth - 50), 20, 20);	
            		                		}
            		                		t1label.setText("時刻：0");
        		                			Nlabel.setText("人口：" + populationzerotext.getText() + "（人）");
            		                		N1 = populationzerodouble;
            		                		startcheck1 = true;
            		                		stopcheck1 = false;
            		                		phasecheck1 = phasecheck1 + 1;
        								}
        							}
        						}
        					}
        				}
        			}
        			
        		}
        	}
        	
        	
        	
        	if(timeline1check == false) {
        		timeline1 = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
            		@Override
            		public void handle(ActionEvent event) {
            	if(startcheck1 == true)	{
            		if(stopcheck1 == false) {
            			gammadouble = (Double.parseDouble(birthratetext.getText()) - Double.parseDouble(deathratetext.getText()));
                		N2 = populationmaxdouble/(1 + (populationmaxdouble/populationzerodouble - 1)*Math.exp(-gammadouble*t1));
                		int deltaN = (int)N2 - (int)N1;
                		if(deltaN >= 1) {
                			for (int humandrawcounter = 0; humandrawcounter < deltaN; humandrawcounter++) {
                    			double humanx = Math.random();
                    			double humany = Math.random();
                    			populationgc.fillOval(500 - regionwidth/2 + 10 + humanx*(regionwidth - 50), 450 - regionwidth/2 + 10 + humany*(regionwidth - 50), 20, 20);	
                    		}
                			Nlabel.setText("人口：" + String.valueOf((int)N2) + "（人）");
                		}
                		t1 = t1 + 1;
            			t1label.setText("時刻：" + String.valueOf((int)t1));
                		N1 = N2;
            		}
            	}
            	
            	
            		}
            		
            		
            	}));
            	
            	
                timeline1.setCycleCount(Timeline.INDEFINITE);
                timeline1check = true;
        	}
        	
            
        	
            if(startcheck1 == true) {
        		if(stopcheck1 == false) {
        			if(phasecheck1 < 2) {
            			timeline1.play();
        			}
        		}
        	}
            
            if(startcheck1 == true) {
        		if(stopcheck1 == false) {
        			if(phasecheck1 > 1) {
        				stopcheck1 = true;
            			timeline1.stop();
        			}
        		}
        		else {
        			if(stopcheck1 == true) {
        				if(phasecheck1 > 1) {
            				stopcheck1 = false;
                			timeline1.play();
            			}	
        			}
        		}	
        	}

            
            
        });
        
        button5.setOnAction((ActionEvent e) -> {
        	//Verhulst計算のリセット
        	timeline1.stop();
            startcheck1 = false;
        	stopcheck1 = true;
        	timeline1check = false;
        	phasecheck1 = 0;
        	t1label.setText("");
        	Nlabel.setText("");
        	t1 = 0;
        	populationgc.setFill(Color.rgb(244, 244, 244));
        	populationgc.fillRect(245, 195, 520, 520);
        });
        
        button6.setOnAction((ActionEvent e) -> {
        	//Verhulstプレイ画面から戻る
            populationlabelpanel.setManaged(true);
            populationlabelpanel.setVisible(true);
            button3panel.setManaged(true);
            button3panel.setVisible(true);
            BorderPane populationscene = new BorderPane();
            populationscene.setTop(populationlabelpanel);
            populationscene.setBottom(button3panel);
            populationscene.setCenter(verfulstimgView);
            populationscene.setRight(button8panel);
            stage.setScene(new Scene(populationscene, 1000, 800));
            timeline1.stop();
            startcheck1 = false;
        	stopcheck1 = true;
        	timeline1check = false;
        	phasecheck1 = 0;
        	t1label.setText("");
        	Nlabel.setText("");
        	t1 = 0;
        	populationgc.setFill(Color.rgb(244, 244, 244));
        	populationgc.fillRect(0, 0, populationcanvas.getWidth(), populationcanvas.getHeight());
        });
        
        Label populationformulalabel1 = new Label("Verhulstの人口増加モデル");
    	populationformulalabel1.setFont(new Font(34));
    	populationformulalabel1.getTransforms().add(new Translate(270, 5));
    	Label populationformulalabel2 = new Label("ベルギーの数学者フェルフルストは1838年に人口増加のモデルを提案しました．");
    	populationformulalabel2.setFont(new Font(24));
    	populationformulalabel2.getTransforms().add(new Translate(10, 80));
    	Image populationformulaimage = new Image( "Verhulst.png" );
        ImageView populationformulaimgView = new ImageView( populationformulaimage );
        populationformulaimgView.getTransforms().add(new Translate(10, 200));
        

        
        button7.setOnAction((ActionEvent e) -> {
        	//Verflust解説画面
        	Stage populationformulastage = new Stage();
        	populationformulastage.initModality(Modality.APPLICATION_MODAL);
        	populationformulastage.initOwner(stage);
        	populationformulagc.setFill(Color.rgb(244, 244, 244));
            populationformulagc.fillRect(0, 0, populationformulacanvas.getWidth(), populationformulacanvas.getHeight());
            Group populationformula = new Group();
            populationformula.getChildren().add(populationformulacanvas);
            populationformula.getChildren().add(populationformulalabel1);
            populationformula.getChildren().add(populationformulalabel2);
            populationformula.getChildren().add(populationformulaimgView);
            Scene populationformulascene = new Scene(populationformula);
            populationformulastage.setTitle("数値実験の話題");
            populationformulastage.setScene(populationformulascene);
            populationformulastage.show();
        });
        
        
        Label difflabel1 = new Label("2次元拡散方程式のアレンジ");
    	difflabel1.setFont(new Font(50));
    	difflabel1.getTransforms().add(new Translate(225, 43));
    	Button button9 = new Button("プレイする");
    	button9.setMinSize(200, 80);
        button9.setFont(new Font(40));
        button9.getTransforms().add(new Translate(400, 614));
        Button button10 = new Button("≪");
        button10.setMinSize(20, 20);
        button10.setFont(new Font(32));
        button10.setStyle("-fx-base: #f4f162");
    	button10.getTransforms().add(new Translate(10, 313));
    	Button button16 = new Button("≫");
    	button16.setMinSize(20, 20);
        button16.setFont(new Font(32));
        button16.setStyle("-fx-base: #f4f162");
    	button16.getTransforms().add(new Translate(914, 313));
    	Image diffimage3 = new Image( "diff3.png" );
        ImageView diffimgView3 = new ImageView( diffimage3 );
        diffimgView3.getTransforms().add(new Translate(300, 150));
        
        button8.setOnAction((ActionEvent e) -> {
        	//Verflustから2次元拡散方程式のアレンジ
        	Group diff = new Group();
        	diffgc.setFill(Color.rgb(244, 244, 244));
        	diffgc.fillRect(0, 0, diffcanvas.getWidth(), diffcanvas.getHeight());
        	diff.getChildren().add(diffcanvas);
            diff.getChildren().add(difflabel1);
            diff.getChildren().add(button9);
            diff.getChildren().add(button10);
            diff.getChildren().add(diffimgView3);
            diff.getChildren().add(button16);
            Scene diffscene = new Scene(diff);
            stage.setScene(diffscene);
            stage.show();
        });
        
        Label diffcoef = new Label(" 拡散係数");
        Label diffomega = new Label("ω");
        Label diffCzero = new Label("初期濃度");
        Label diffdeltat = new Label("Δt");
        Label diffexplanation = new Label(" ※いずれも正の値としてください．初期濃度はcsvファイルを選択して与えてください．");
        TextField diffcoeftext = new TextField();
        TextField diffomegatext = new TextField();
        TextField diffdeltattext = new TextField();
        Button button11 = new Button("開始／一時停止");
        Button button12 = new Button("リセット");
        Button button13 = new Button("数値実験の選択に戻る");
        Button button14 = new Button("解説を見る");
        Button button15 = new Button("csv");
        
        diffplaygc.setFill(Color.rgb(244, 244, 244));
        diffplaygc.fillRect(0, 0, diffplaycanvas.getWidth(), diffplaycanvas.getHeight());
        diffcoef.setFont(new Font(24));
        diffcoef.getTransforms().add(new Translate(10, 8));
        diffcoeftext.getTransforms().add(new Translate(120, 5));
        diffcoeftext.setFont(new Font(18));
        diffcoeftext.setMaxSize(80, 10);
        diffomega.setFont(new Font(24));
        diffomega.getTransforms().add(new Translate(230, 8));
        diffomegatext.getTransforms().add(new Translate(255, 5));
        diffomegatext.setFont(new Font(18));
        diffomegatext.setMaxSize(80, 10);
        diffCzero.setFont(new Font(24));
        diffCzero.getTransforms().add(new Translate(365, 8));
        button15.getTransforms().add(new Translate(470, 5));
        button15.setFont(new Font(18));
        button15.setMaxSize(80, 10);
        diffdeltat.setFont(new Font(24));
        diffdeltat.getTransforms().add(new Translate(550, 8));
        diffdeltattext.getTransforms().add(new Translate(580, 5));
        diffdeltattext.setFont(new Font(18));
        diffdeltattext.setMaxSize(80, 10);
        diffexplanation.setFont(new Font(24));
        diffexplanation.getTransforms().add(new Translate(10, 50));
        button11.setFont(new Font(20));
        button11.getTransforms().add(new Translate(10, 100));
        button12.setFont(new Font(20));
        button12.getTransforms().add(new Translate(200, 100));
        button13.setFont(new Font(20));
        button13.getTransforms().add(new Translate(10, 750));
        button14.setFont(new Font(20));
        button14.getTransforms().add(new Translate(870, 100));
        t2label.setFont(new Font(24));
        t2label.getTransforms().add(new Translate(670, 710));
        
        button9.setOnAction((ActionEvent e) -> {
        	//2次元拡散方程式のアレンジプレイ画面
        	Group diffplay = new Group();
            diffplay.getChildren().add(diffplaycanvas);
            diffplay.getChildren().add(diffcoef);
            diffplay.getChildren().add(diffcoeftext);
            diffplay.getChildren().add(diffomega);
            diffplay.getChildren().add(diffomegatext);
            diffplay.getChildren().add(diffdeltat);
            diffplay.getChildren().add(diffdeltattext);
            diffplay.getChildren().add(diffCzero);
            diffplay.getChildren().add(button15);
            diffplay.getChildren().add(diffexplanation);
            diffplay.getChildren().add(button11);
            diffplay.getChildren().add(button12);
            diffplay.getChildren().add(button13);
            diffplay.getChildren().add(button14);
            diffplay.getChildren().add(t2label);
            Scene diffplayscene = new Scene(diffplay);
            stage.setScene(diffplayscene);
        });
        
        button10.setOnAction((ActionEvent e) -> {
        	//2次元拡散方程式のアレンジからVerflust
        	populationlabelpanel.setManaged(true);
            populationlabelpanel.setVisible(true);
            button3panel.setManaged(true);
            button3panel.setVisible(true);
            BorderPane populationscene = new BorderPane();
            populationscene.setTop(populationlabelpanel);
            populationscene.setBottom(button3panel);
            populationscene.setCenter(verfulstimgView);
            populationscene.setRight(button8panel);
            stage.setScene(new Scene(populationscene, 1000, 800));
        });
        
        button11.setOnAction((ActionEvent e) -> {
        	//2次元拡散方程式のアレンジ計算開始
        	
        	if (startcheck2 == true) {
        		phasecheck2 = phasecheck2 + 1;
        	}
        	
        	
        	if(startcheck2 == false) {
        		if(diffcoeftext.getLength() > 0) {
        			if(diffdeltattext.getLength() > 0) {
        				if(diffomegatext.getLength() > 0) {
        					if(diffCzerocheck == true) {
        					    diffcoefdouble = Double.parseDouble(diffcoeftext.getText());
        						diffdeltatdouble = Double.parseDouble(diffdeltattext.getText());
        						diffomegadouble = Double.parseDouble(diffomegatext.getText());
        						if(diffcoefdouble > 0) {
        							if(diffdeltatdouble > 0) {
        								if(diffomegadouble > 0) {
        									for (int C1firstdrawcounterx = 0; C1firstdrawcounterx < 100; C1firstdrawcounterx++) {
            		                			for (int C1firstdrawcountery = 0; C1firstdrawcountery < 100; C1firstdrawcountery++) {
            		                				double brightness = 1 / (1 + Math.exp(-(5*C1[C1firstdrawcounterx][C1firstdrawcountery][0])));
            		                				diffplaygc.setFill(Color.hsb(0, 0, brightness));
            		                				diffplaygc.fillRect(260 + C1firstdrawcounterx*5, 210 + C1firstdrawcountery*5, 5, 5);
            		                			}	
            		                		}
            		                		t2label.setText("時刻：0");
        		                			startcheck2 = true;
            		                		stopcheck2 = false;
            		                		phasecheck2 = phasecheck2 + 1;
        								}
        							}
        						}
        					}
        				}
        			}
        			
        		}
        	}
        	
        	
        	if(timeline2check == false) {
        		timeline2 = new Timeline(new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {
            		@Override
            		public void handle(ActionEvent event) {
            	if(startcheck2 == true)	{
            		if(stopcheck2 == false) {
            			
            			C1[0][99][1] = C1[0][99][0] + diffcoefdouble*Math.sin(diffomegadouble*t2)*((C1[1][99][0] - 2*C1[0][99][0] + C1[0][99][0])/1 + (C1[0][99][0] - 2*C1[0][99][0] + C1[0][98][0])/1)*diffdeltatdouble;
            			C1[99][99][1] = C1[99][99][0] + diffcoefdouble*Math.sin(diffomegadouble*t2)*((C1[99][99][0] - 2*C1[99][99][0] + C1[98][99][0])/1 + (C1[99][99][0] - 2*C1[99][99][0] + C1[99][98][0])/1)*diffdeltatdouble;
            			C1[99][0][1] = C1[99][0][0] + diffcoefdouble*Math.sin(diffomegadouble*t2)*((C1[99][0][0] - 2*C1[99][0][0] + C1[98][0][0])/1 + (C1[99][1][0] - 2*C1[99][0][0] + C1[99][0][0])/1)*diffdeltatdouble;
            			C1[0][0][1] = C1[0][0][0] + diffcoefdouble*Math.sin(diffomegadouble*t2)*((C1[1][0][0] - 2*C1[0][0][0] + C1[0][0][0])/1 + (C1[0][1][0] - 2*C1[0][0][0] + C1[0][0][0])/1)*diffdeltatdouble;
            			
            			
            			for (int C1calupcounter = 1; C1calupcounter < 99; C1calupcounter++) {
            				C1[C1calupcounter][99][1] = C1[C1calupcounter][99][0] + diffcoefdouble*Math.sin(diffomegadouble*t2)*((C1[C1calupcounter + 1][99][0] - 2*C1[C1calupcounter][99][0] + C1[C1calupcounter - 1][99][0])/1 + (C1[C1calupcounter][99][0] - 2*C1[C1calupcounter][99][0] + C1[C1calupcounter][98][0])/1)*diffdeltatdouble;
            			}
            			
            			for (int C1calrightcounter = 1; C1calrightcounter < 99; C1calrightcounter++) {
            				C1[99][C1calrightcounter][1] = C1[99][C1calrightcounter][0] + diffcoefdouble*Math.sin(diffomegadouble*t2)*((C1[99][C1calrightcounter][0] - 2*C1[99][C1calrightcounter][0] + C1[98][C1calrightcounter][0])/1 + (C1[99][C1calrightcounter + 1][0] - 2*C1[99][C1calrightcounter][0] + C1[99][C1calrightcounter - 1][0])/1)*diffdeltatdouble;
            			}
            			
            			for (int C1calleftcounter = 1; C1calleftcounter < 99; C1calleftcounter++) {
            				C1[0][C1calleftcounter][1] = C1[0][C1calleftcounter][0] + diffcoefdouble*Math.sin(diffomegadouble*t2)*((C1[1][C1calleftcounter][0] - 2*C1[0][C1calleftcounter][0] + C1[0][C1calleftcounter][0])/1 + (C1[0][C1calleftcounter + 1][0] - 2*C1[0][C1calleftcounter][0] + C1[0][C1calleftcounter - 1][0])/1)*diffdeltatdouble;
            			}
            			
            			for (int C1callowcounter = 1; C1callowcounter < 99; C1callowcounter++) {
            				C1[C1callowcounter][0][1] = C1[C1callowcounter][0][0] + diffcoefdouble*Math.sin(diffomegadouble*t2)*((C1[C1callowcounter + 1][0][0] - 2*C1[C1callowcounter][0][0] + C1[C1callowcounter - 1][0][0])/1 + (C1[C1callowcounter][1][0] - 2*C1[C1callowcounter][0][0] + C1[C1callowcounter][0][0])/1)*diffdeltatdouble;
            			}
            			
            			for (int C1calcounterx = 1; C1calcounterx < 99; C1calcounterx++) {
                    		for (int C1calcountery = 1; C1calcountery < 99; C1calcountery++) {
                    			C1[C1calcounterx][C1calcountery][1] = C1[C1calcounterx][C1calcountery][0] + diffcoefdouble*Math.sin(diffomegadouble*t2)*((C1[C1calcounterx + 1][C1calcountery][0] - 2*C1[C1calcounterx][C1calcountery][0] + C1[C1calcounterx - 1][C1calcountery][0])/1 + (C1[C1calcounterx][C1calcountery + 1][0] - 2*C1[C1calcounterx][C1calcountery][0] + C1[C1calcounterx][C1calcountery - 1][0])/1)*diffdeltatdouble;
                    		}
                    	}
            			
            			for (int C1drawcounterx = 0; C1drawcounterx < 100; C1drawcounterx++) {
                    		for (int C1drawcountery = 0; C1drawcountery < 100; C1drawcountery++) {
                    			double brightness = 1 / (1 + Math.exp(-(5*C1[C1drawcounterx][C1drawcountery][1])));
                				diffplaygc.setFill(Color.hsb(0, 0, brightness));
                				diffplaygc.fillRect(260 + C1drawcounterx*5, 210 + C1drawcountery*5, 5, 5);
                    		}
                    	}
            			
            			for (int C1exchcounterx = 0; C1exchcounterx < 100; C1exchcounterx++) {
                    		for (int C1exchcountery = 0; C1exchcountery < 100; C1exchcountery++) {
                    			C1[C1exchcounterx][C1exchcountery][0] = C1[C1exchcounterx][C1exchcountery][1];
                    		}
                    	}
                			
                		
                		t2 = t2 + diffdeltatdouble;
            			t2label.setText("時刻：" + String.format("%.1f", t2));
            		}
            	}
            	
            	
            		}
            		
            		
            	}));
            	
            	
                timeline2.setCycleCount(Timeline.INDEFINITE);
                timeline2check = true;
        	}
        	
            
        	
            if(startcheck2 == true) {
        		if(stopcheck2 == false) {
        			if(phasecheck2 < 2) {
            			timeline2.play();
        			}
        		}
        	}
            
            if(startcheck2 == true) {
        		if(stopcheck2 == false) {
        			if(phasecheck2 > 1) {
        				stopcheck2 = true;
            			timeline2.stop();
        			}
        		}
        		else {
        			if(stopcheck2 == true) {
        				if(phasecheck2 > 1) {
            				stopcheck2 = false;
                			timeline2.play();
            			}	
        			}
        		}	
        	}
        	
        });
        
        button12.setOnAction((ActionEvent e) -> {
        	//2次元拡散方程式のアレンジ計算のリセット
        	timeline2.stop();
            startcheck2 = false;
        	stopcheck2 = true;
        	timeline2check = false;
        	phasecheck2 = 0;
        	diffCzerocheck = false;
        	t2label.setText("");
        	t2 = 0;
        	diffplaygc.setFill(Color.rgb(244, 244, 244));
        	diffplaygc.fillRect(260, 210, 520, 520);
        });
        
        
        button13.setOnAction((ActionEvent e) -> {
        	//2次元拡散方程式のアレンジプレイ画面から戻る
        	Group diff = new Group();
        	diffgc.setFill(Color.rgb(244, 244, 244));
        	diffgc.fillRect(0, 0, diffcanvas.getWidth(), diffcanvas.getHeight());
        	diff.getChildren().add(diffcanvas);
            diff.getChildren().add(difflabel1);
            diff.getChildren().add(button9);
            diff.getChildren().add(button10);
            diff.getChildren().add(diffimgView3);
            diff.getChildren().add(button16);
            Scene diffscene = new Scene(diff);
            stage.setScene(diffscene);
            timeline2.stop();
            startcheck2 = false;
        	stopcheck2 = true;
        	timeline2check = false;
        	phasecheck2 = 0;
        	diffCzerocheck = false;
        	t2label.setText("");
        	t2 = 0;
        	diffplaygc.setFill(Color.rgb(244, 244, 244));
        	diffplaygc.fillRect(0, 0, diffplaycanvas.getWidth(), diffplaycanvas.getHeight());
        });
        
        
        Label diffformulalabel1 = new Label("2次元拡散方程式のアレンジ");
    	diffformulalabel1.setFont(new Font(34));
    	diffformulalabel1.getTransforms().add(new Translate(270, 5));
    	Label diffformulalabel2 = new Label("2次元拡散方程式にsin関数を付けてみました．微分方程式と境界条件は以下の通りです．"
    			+ "\n陽解法を用いて解を求めています．");
    	diffformulalabel2.setFont(new Font(24));
    	diffformulalabel2.getTransforms().add(new Translate(10, 80));
    	Label diffformulalabel3 = new Label("また，濃度をシグモイド関数でHSBの輝度に変換して表示しています．");
    	diffformulalabel3.setFont(new Font(24));
    	diffformulalabel3.getTransforms().add(new Translate(10, 600));
    	Image diffformulaimage1 = new Image( "diff1.png" );
        ImageView diffformulaimgView1 = new ImageView( diffformulaimage1 );
        diffformulaimgView1.getTransforms().add(new Translate(10, 200));
        Image diffformulaimage2 = new Image( "diff2.png" );
        ImageView diffformulaimgView2 = new ImageView( diffformulaimage2 );
        diffformulaimgView2.getTransforms().add(new Translate(10, 630));
        button14.setOnAction((ActionEvent e) -> {
        	//2次元拡散方程式のアレンジ解説画面
        	Stage diffformulastage = new Stage();
        	diffformulastage.initModality(Modality.APPLICATION_MODAL);
        	diffformulastage.initOwner(stage);
        	diffformulagc.setFill(Color.rgb(244, 244, 244));
            diffformulagc.fillRect(0, 0, diffformulacanvas.getWidth(), diffformulacanvas.getHeight());
            Group diffformula = new Group();
            diffformula.getChildren().add(diffformulacanvas);
            diffformula.getChildren().add(diffformulalabel1);
            diffformula.getChildren().add(diffformulalabel2);
            diffformula.getChildren().add(diffformulalabel3);
            diffformula.getChildren().add(diffformulaimgView1);
            diffformula.getChildren().add(diffformulaimgView2);
            Scene diffformulascene = new Scene(diffformula);
            diffformulastage.setTitle("数値実験の話題");
            diffformulastage.setScene(diffformulascene);
            diffformulastage.show();
        });
        
        
        button15.setOnAction((ActionEvent e) -> {
        	//2次元拡散方程式のアレンジcsv
        	
        	FileInputStream difffis = null;
        	InputStreamReader diffisr = null;
        	BufferedReader diffbr = null;
        	
        	FileChooser fc = new FileChooser();
        	fc.setTitle("csvファイル選択");
        	
        	fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("csvファイル", "*.csv*"));
        	
        	File file = fc.showOpenDialog(null);
        	String filename = null;
        	
        	if(file != null) {
        		  filename = file.getPath();
        		  try {
              		difffis = new FileInputStream(filename);
              		diffisr = new InputStreamReader(difffis);
              		diffbr = new BufferedReader(diffisr);
              		
              		String line;
              		
              		int i = 0;
              		
              		while ((line = diffbr.readLine()) != null) {
              			String[] arr = null;
              			arr = line.split(",");
              			int arrleng = arr.length;
              			for (int arrcounter = 0; arrcounter < arrleng; arrcounter++) {
              				C1[arrcounter][99 - i][0] = Double.parseDouble(arr[arrcounter]);	
              			}
              			i++;
              		}
              		
              	} catch (Exception e1) {
              	    e1.printStackTrace();
              	  } finally {
              	    try {
              	      diffbr.close();
              	    } catch (Exception e1) {
              	      e1.printStackTrace();
              	    }
              	  }
        		  diffCzerocheck = true;
        		}
        	
        	
        	
        	
        });
        
        
        Label wavelabel1 = new Label("2次元波動方程式のアレンジ");
    	wavelabel1.setFont(new Font(50));
    	wavelabel1.getTransforms().add(new Translate(225, 43));
    	Button button17 = new Button("プレイする");
    	button17.setMinSize(200, 80);
        button17.setFont(new Font(40));
        button17.getTransforms().add(new Translate(400, 614));
        Button button18 = new Button("≪");
        button18.setMinSize(20, 20);
        button18.setFont(new Font(32));
        button18.setStyle("-fx-base: #f4f162");
    	button18.getTransforms().add(new Translate(10, 313));
    	/*Button button16 = new Button("≫");
    	button16.setMinSize(20, 20);
        button16.setFont(new Font(32));
        button16.setStyle("-fx-base: #f4f162");
    	button16.getTransforms().add(new Translate(914, 313));*/
    	Image waveimage3 = new Image( "wave3.png" );
        ImageView waveimgView3 = new ImageView( waveimage3 );
        waveimgView3.getTransforms().add(new Translate(300, 150));
        
        button16.setOnAction((ActionEvent e) -> {
        	//2次元拡散方程式のアレンジから2次元波動方程式のアレンジ
        	Group wave = new Group();
        	wavegc.setFill(Color.rgb(244, 244, 244));
        	wavegc.fillRect(0, 0, wavecanvas.getWidth(), wavecanvas.getHeight());
        	wave.getChildren().add(wavecanvas);
            wave.getChildren().add(wavelabel1);
            wave.getChildren().add(button17);
            wave.getChildren().add(button18);
            wave.getChildren().add(waveimgView3);
            //wave.getChildren().add(button16);
            Scene wavescene = new Scene(wave);
            stage.setScene(wavescene);
            stage.show();
        });
        
        
        Label wavecoef = new Label(" 位相速度");
        Label waveuzero = new Label("初期変位");
        Label wavedeltat = new Label("Δt");
        Label waveexplanation = new Label(" ※いずれも正の値としてください．初期変位はcsvファイルを選択して与えてください．");
        TextField wavecoeftext = new TextField();
        TextField wavedeltattext = new TextField();
        Button button19 = new Button("開始／一時停止");
        Button button20 = new Button("リセット");
        Button button21 = new Button("数値実験の選択に戻る");
        Button button22 = new Button("解説を見る");
        Button button23 = new Button("csv");
        Button button24 = new Button("アレンジなし");
        
        waveplaygc.setFill(Color.rgb(244, 244, 244));
        waveplaygc.fillRect(0, 0, waveplaycanvas.getWidth(), waveplaycanvas.getHeight());
        wavecoef.setFont(new Font(24));
        wavecoef.getTransforms().add(new Translate(10, 8));
        wavecoeftext.getTransforms().add(new Translate(120, 5));
        wavecoeftext.setFont(new Font(18));
        wavecoeftext.setMaxSize(80, 10);
        waveuzero.setFont(new Font(24));
        waveuzero.getTransforms().add(new Translate(220, 8));
        button23.getTransforms().add(new Translate(320, 5));
        button23.setFont(new Font(18));
        button23.setMaxSize(80, 10);
        wavedeltat.setFont(new Font(24));
        wavedeltat.getTransforms().add(new Translate(390, 8));
        wavedeltattext.getTransforms().add(new Translate(420, 5));
        wavedeltattext.setFont(new Font(18));
        wavedeltattext.setMaxSize(80, 10);
        waveexplanation.setFont(new Font(24));
        waveexplanation.getTransforms().add(new Translate(10, 50));
        button19.setFont(new Font(20));
        button19.getTransforms().add(new Translate(10, 100));
        button20.setFont(new Font(20));
        button20.getTransforms().add(new Translate(200, 100));
        button21.setFont(new Font(20));
        button21.getTransforms().add(new Translate(10, 750));
        button22.setFont(new Font(20));
        button22.getTransforms().add(new Translate(870, 100));
        t3label.setFont(new Font(24));
        t3label.getTransforms().add(new Translate(670, 710));
        button24.setFont(new Font(20));
        button24.getTransforms().add(new Translate(300, 100));
        
        
        button17.setOnAction((ActionEvent e) -> {
        	//2次元波動方程式のアレンジプレイ画面
        	Group waveplay = new Group();
            waveplay.getChildren().add(waveplaycanvas);
            waveplay.getChildren().add(wavecoef);
            waveplay.getChildren().add(wavecoeftext);
            waveplay.getChildren().add(wavedeltat);
            waveplay.getChildren().add(wavedeltattext);
            waveplay.getChildren().add(waveuzero);
            waveplay.getChildren().add(button23);
            waveplay.getChildren().add(waveexplanation);
            waveplay.getChildren().add(button19);
            waveplay.getChildren().add(button20);
            waveplay.getChildren().add(button21);
            waveplay.getChildren().add(button22);
            waveplay.getChildren().add(t3label);
            waveplay.getChildren().add(button24);
            Scene waveplayscene = new Scene(waveplay);
            stage.setScene(waveplayscene);
        });
        
        
        button18.setOnAction((ActionEvent e) -> {
        	//2次元波動方程式のアレンジから2次元拡散方程式のアレンジ
        	Group diff = new Group();
        	diffgc.setFill(Color.rgb(244, 244, 244));
        	diffgc.fillRect(0, 0, diffcanvas.getWidth(), diffcanvas.getHeight());
        	diff.getChildren().add(diffcanvas);
            diff.getChildren().add(difflabel1);
            diff.getChildren().add(button9);
            diff.getChildren().add(button10);
            diff.getChildren().add(diffimgView3);
            diff.getChildren().add(button16);
            Scene diffscene = new Scene(diff);
            stage.setScene(diffscene);
            stage.show();
        });
        
        
        button19.setOnAction((ActionEvent e) -> {
        	//2次元波動方程式のアレンジ計算開始
        	
        	if (startcheck3 == true) {
        		phasecheck3 = phasecheck3 + 1;
        	}
        	
        	
        	if(startcheck3 == false) {
        		if(wavecoeftext.getLength() > 0) {
        			if(wavedeltattext.getLength() > 0) {
        				if(waveuzerocheck == true) {
        					wavecoefdouble = Double.parseDouble(wavecoeftext.getText());
        					wavedeltatdouble = Double.parseDouble(wavedeltattext.getText());
        					if(wavecoefdouble > 0) {
        						if(wavedeltatdouble > 0) {
        							for (int u1firstdrawcounterx = 0; u1firstdrawcounterx < 100; u1firstdrawcounterx++) {
        								for (int u1firstdrawcountery = 0; u1firstdrawcountery < 100; u1firstdrawcountery++) {
        									double brightness = 1 / (1 + Math.exp(-(5*u1[u1firstdrawcounterx][u1firstdrawcountery][0])));
            		                		waveplaygc.setFill(Color.hsb(0, 0, brightness));
            		                		waveplaygc.fillRect(260 + u1firstdrawcounterx*5, 210 + u1firstdrawcountery*5, 5, 5);
            		                		}	
        								}
        							t3label.setText("時刻：0");
        		                	startcheck3 = true;
            		                stopcheck3 = false;
            		                phasecheck3 = phasecheck3 + 1;
            		                }
        						}
        						
        					}
        				}
        			}
        			
        		}
        	
        	
        	
        	if(timeline3check == false) {
        		timeline3 = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
            		@Override
            		public void handle(ActionEvent event) {
            	if(startcheck3 == true)	{
            		if(stopcheck3 == false) {
            			
            			if (arrange1 == false) {
            				u1[0][99][2] = 2*u1[0][99][1] - u1[0][99][0] + wavecoefdouble*wavecoefdouble*((u1[1][99][1] - 2*u1[0][99][1] + u1[0][99][1])/1 + (u1[0][99][1] - 2*u1[0][99][1] + u1[0][98][1])/1)*wavedeltatdouble*wavedeltatdouble;
                			u1[99][99][2] = 2*u1[99][99][1] - u1[99][99][0] + wavecoefdouble*wavecoefdouble*((u1[99][99][1] - 2*u1[99][99][1] + u1[98][99][1])/1 + (u1[99][99][1] - 2*u1[99][99][1] + u1[99][98][1])/1)*wavedeltatdouble*wavedeltatdouble;
                			u1[99][0][2] = 2*u1[99][0][1] - u1[99][0][0] + wavecoefdouble*wavecoefdouble*((u1[99][0][1] - 2*u1[99][0][1] + u1[98][0][1])/1 + (u1[99][1][1] - 2*u1[99][0][1] + u1[99][0][1])/1)*wavedeltatdouble*wavedeltatdouble;
                			u1[0][0][2] = 2*u1[0][0][1] - u1[0][0][0] + wavecoefdouble*wavecoefdouble*((u1[1][0][1] - 2*u1[0][0][1] + u1[0][0][1])/1 + (u1[0][1][1] - 2*u1[0][0][1] + u1[0][0][1])/1)*wavedeltatdouble*wavedeltatdouble;
                			
                			
                			for (int u1calupcounter = 1; u1calupcounter < 99; u1calupcounter++) {
                				u1[u1calupcounter][99][2] = 2*u1[u1calupcounter][99][1] - u1[u1calupcounter][99][0] + wavecoefdouble*wavecoefdouble*((u1[u1calupcounter + 1][99][1] - 2*u1[u1calupcounter][99][1] + u1[u1calupcounter - 1][99][1])/1 + (u1[u1calupcounter][99][1] - 2*u1[u1calupcounter][99][1] + u1[u1calupcounter][98][1])/1)*wavedeltatdouble*wavedeltatdouble;
                			}
                			
                			for (int u1calrightcounter = 1; u1calrightcounter < 99; u1calrightcounter++) {
                				u1[99][u1calrightcounter][2] = 2*u1[99][u1calrightcounter][1] - u1[99][u1calrightcounter][0] + wavecoefdouble*wavecoefdouble*((u1[99][u1calrightcounter][1] - 2*u1[99][u1calrightcounter][1] + u1[98][u1calrightcounter][1])/1 + (u1[99][u1calrightcounter + 1][1] - 2*u1[99][u1calrightcounter][1] + u1[99][u1calrightcounter - 1][1])/1)*wavedeltatdouble*wavedeltatdouble;
                			}
                			
                			for (int u1calleftcounter = 1; u1calleftcounter < 99; u1calleftcounter++) {
                				u1[0][u1calleftcounter][2] = 2*u1[0][u1calleftcounter][1] - u1[0][u1calleftcounter][0] + wavecoefdouble*wavecoefdouble*((u1[1][u1calleftcounter][1] - 2*u1[0][u1calleftcounter][1] + u1[0][u1calleftcounter][1])/1 + (u1[0][u1calleftcounter + 1][1] - 2*u1[0][u1calleftcounter][1] + u1[0][u1calleftcounter - 1][1])/1)*wavedeltatdouble*wavedeltatdouble;
                			}
                			
                			for (int u1callowcounter = 1; u1callowcounter < 99; u1callowcounter++) {
                				u1[u1callowcounter][0][2] = 2*u1[u1callowcounter][0][1] - u1[u1callowcounter][0][0] + wavecoefdouble*wavecoefdouble*((u1[u1callowcounter + 1][0][1] - 2*u1[u1callowcounter][0][1] + u1[u1callowcounter - 1][0][1])/1 + (u1[u1callowcounter][1][1] - 2*u1[u1callowcounter][0][1] + u1[u1callowcounter][0][1])/1)*wavedeltatdouble*wavedeltatdouble;
                			}
                			
                			for (int u1calcounterx = 1; u1calcounterx < 99; u1calcounterx++) {
                        		for (int u1calcountery = 1; u1calcountery < 99; u1calcountery++) {
                        			u1[u1calcounterx][u1calcountery][2] = 2*u1[u1calcounterx][u1calcountery][1] - u1[u1calcounterx][u1calcountery][0] + wavecoefdouble*wavecoefdouble*((u1[u1calcounterx + 1][u1calcountery][1] - 2*u1[u1calcounterx][u1calcountery][1] + u1[u1calcounterx - 1][u1calcountery][1])/1 + (u1[u1calcounterx][u1calcountery + 1][1] - 2*u1[u1calcounterx][u1calcountery][1] + u1[u1calcounterx][u1calcountery - 1][1])/1)*wavedeltatdouble*wavedeltatdouble;
                        		}
                        	}
                			
                			for (int u1drawcounterx = 0; u1drawcounterx < 100; u1drawcounterx++) {
                        		for (int u1drawcountery = 0; u1drawcountery < 100; u1drawcountery++) {
                        			double brightness = 1 / (1 + Math.exp(-(5*u1[u1drawcounterx][u1drawcountery][2])));
                    				waveplaygc.setFill(Color.hsb(0, 0, brightness));
                    				waveplaygc.fillRect(260 + u1drawcounterx*5, 210 + u1drawcountery*5, 5, 5);
                        		}
                        	}
                			
                			for (int u1exchcounterx = 0; u1exchcounterx < 100; u1exchcounterx++) {
                        		for (int u1exchcountery = 0; u1exchcountery < 100; u1exchcountery++) {
                        			u1[u1exchcounterx][u1exchcountery][0] = u1[u1exchcounterx][u1exchcountery][1];
                        			u1[u1exchcounterx][u1exchcountery][1] = u1[u1exchcounterx][u1exchcountery][2];
                        		}
                        	}
            			}
            			
            			
            			if (arrange1 == true) {
            				u1[0][99][2] = 2*u1[0][99][1] - u1[0][99][0] + wavecoefdouble*wavecoefdouble*((u1[1][99][1] - 2*u1[0][99][1] + u1[0][99][1])/1 - (u1[0][99][1] - 2*u1[0][99][1] + u1[0][98][1])/1)*wavedeltatdouble*wavedeltatdouble;
                			u1[99][99][2] = 2*u1[99][99][1] - u1[99][99][0] + wavecoefdouble*wavecoefdouble*((u1[99][99][1] - 2*u1[99][99][1] + u1[98][99][1])/1 - (u1[99][99][1] - 2*u1[99][99][1] + u1[99][98][1])/1)*wavedeltatdouble*wavedeltatdouble;
                			u1[99][0][2] = 2*u1[99][0][1] - u1[99][0][0] + wavecoefdouble*wavecoefdouble*((u1[99][0][1] - 2*u1[99][0][1] + u1[98][0][1])/1 - (u1[99][1][1] - 2*u1[99][0][1] + u1[99][0][1])/1)*wavedeltatdouble*wavedeltatdouble;
                			u1[0][0][2] = 2*u1[0][0][1] - u1[0][0][0] + wavecoefdouble*wavecoefdouble*((u1[1][0][1] - 2*u1[0][0][1] + u1[0][0][1])/1 - (u1[0][1][1] - 2*u1[0][0][1] + u1[0][0][1])/1)*wavedeltatdouble*wavedeltatdouble;
                			
                			
                			for (int u1calupcounter = 1; u1calupcounter < 99; u1calupcounter++) {
                				u1[u1calupcounter][99][2] = 2*u1[u1calupcounter][99][1] - u1[u1calupcounter][99][0] + wavecoefdouble*wavecoefdouble*((u1[u1calupcounter + 1][99][1] - 2*u1[u1calupcounter][99][1] + u1[u1calupcounter - 1][99][1])/1 - (u1[u1calupcounter][99][1] - 2*u1[u1calupcounter][99][1] + u1[u1calupcounter][98][1])/1)*wavedeltatdouble*wavedeltatdouble;
                			}
                			
                			for (int u1calrightcounter = 1; u1calrightcounter < 99; u1calrightcounter++) {
                				u1[99][u1calrightcounter][2] = 2*u1[99][u1calrightcounter][1] - u1[99][u1calrightcounter][0] + wavecoefdouble*wavecoefdouble*((u1[99][u1calrightcounter][1] - 2*u1[99][u1calrightcounter][1] + u1[98][u1calrightcounter][1])/1 - (u1[99][u1calrightcounter + 1][1] - 2*u1[99][u1calrightcounter][1] + u1[99][u1calrightcounter - 1][1])/1)*wavedeltatdouble*wavedeltatdouble;
                			}
                			
                			for (int u1calleftcounter = 1; u1calleftcounter < 99; u1calleftcounter++) {
                				u1[0][u1calleftcounter][2] = 2*u1[0][u1calleftcounter][1] - u1[0][u1calleftcounter][0] + wavecoefdouble*wavecoefdouble*((u1[1][u1calleftcounter][1] - 2*u1[0][u1calleftcounter][1] + u1[0][u1calleftcounter][1])/1 - (u1[0][u1calleftcounter + 1][1] - 2*u1[0][u1calleftcounter][1] + u1[0][u1calleftcounter - 1][1])/1)*wavedeltatdouble*wavedeltatdouble;
                			}
                			
                			for (int u1callowcounter = 1; u1callowcounter < 99; u1callowcounter++) {
                				u1[u1callowcounter][0][2] = 2*u1[u1callowcounter][0][1] - u1[u1callowcounter][0][0] + wavecoefdouble*wavecoefdouble*((u1[u1callowcounter + 1][0][1] - 2*u1[u1callowcounter][0][1] + u1[u1callowcounter - 1][0][1])/1 - (u1[u1callowcounter][1][1] - 2*u1[u1callowcounter][0][1] + u1[u1callowcounter][0][1])/1)*wavedeltatdouble*wavedeltatdouble;
                			}
                			
                			for (int u1calcounterx = 1; u1calcounterx < 99; u1calcounterx++) {
                        		for (int u1calcountery = 1; u1calcountery < 99; u1calcountery++) {
                        			u1[u1calcounterx][u1calcountery][2] = 2*u1[u1calcounterx][u1calcountery][1] - u1[u1calcounterx][u1calcountery][0] + wavecoefdouble*wavecoefdouble*((u1[u1calcounterx + 1][u1calcountery][1] - 2*u1[u1calcounterx][u1calcountery][1] + u1[u1calcounterx - 1][u1calcountery][1])/1 - (u1[u1calcounterx][u1calcountery + 1][1] - 2*u1[u1calcounterx][u1calcountery][1] + u1[u1calcounterx][u1calcountery - 1][1])/1)*wavedeltatdouble*wavedeltatdouble;
                        		}
                        	}
                			
                			for (int u1drawcounterx = 0; u1drawcounterx < 100; u1drawcounterx++) {
                        		for (int u1drawcountery = 0; u1drawcountery < 100; u1drawcountery++) {
                        			double brightness = 1 / (1 + Math.exp(-(5*u1[u1drawcounterx][u1drawcountery][2])));
                    				waveplaygc.setFill(Color.hsb(0, 0, brightness));
                    				waveplaygc.fillRect(260 + u1drawcounterx*5, 210 + u1drawcountery*5, 5, 5);
                        		}
                        	}
                			
                			for (int u1exchcounterx = 0; u1exchcounterx < 100; u1exchcounterx++) {
                        		for (int u1exchcountery = 0; u1exchcountery < 100; u1exchcountery++) {
                        			u1[u1exchcounterx][u1exchcountery][0] = u1[u1exchcounterx][u1exchcountery][1];
                        			u1[u1exchcounterx][u1exchcountery][1] = u1[u1exchcounterx][u1exchcountery][2];
                        		}
                        	}
            			}
            			
            			
                			
                		
                		t3 = t3 + wavedeltatdouble;
            			t3label.setText("時刻：" + String.format("%.1f", t3));
            		}
            	}
            	
            	
            		}
            		
            		
            	}));
            	
            	
                timeline3.setCycleCount(Timeline.INDEFINITE);
                timeline3check = true;
        	}
        	
            
        	
            if(startcheck3 == true) {
        		if(stopcheck3 == false) {
        			if(phasecheck3 < 2) {
            			timeline3.play();
        			}
        		}
        	}
            
            if(startcheck3 == true) {
        		if(stopcheck3 == false) {
        			if(phasecheck3 > 1) {
        				stopcheck3 = true;
            			timeline3.stop();
        			}
        		}
        		else {
        			if(stopcheck3 == true) {
        				if(phasecheck3 > 1) {
            				stopcheck3 = false;
                			timeline3.play();
            			}	
        			}
        		}	
        	}
        });
        
        
        button20.setOnAction((ActionEvent e) -> {
        	//2次元波動方程式のアレンジ計算のリセット
        	timeline3.stop();
            startcheck3 = false;
        	stopcheck3 = true;
        	timeline3check = false;
        	phasecheck3 = 0;
        	waveuzerocheck = false;
        	t3label.setText("");
        	t3 = 0;
        	waveplaygc.setFill(Color.rgb(244, 244, 244));
        	waveplaygc.fillRect(260, 210, 520, 520);
        });
        
        
        button21.setOnAction((ActionEvent e) -> {
        	//2次元波動方程式のアレンジプレイ画面から戻る
        	Group wave = new Group();
        	wavegc.setFill(Color.rgb(244, 244, 244));
        	wavegc.fillRect(0, 0, wavecanvas.getWidth(), wavecanvas.getHeight());
        	wave.getChildren().add(wavecanvas);
            wave.getChildren().add(wavelabel1);
            wave.getChildren().add(button17);
            wave.getChildren().add(button18);
            wave.getChildren().add(waveimgView3);
            //wave.getChildren().add(button16);
            Scene wavescene = new Scene(wave);
            stage.setScene(wavescene);
            timeline3.stop();
            startcheck3 = false;
        	stopcheck3 = true;
        	timeline3check = false;
        	phasecheck3 = 0;
        	waveuzerocheck = false;
        	t3label.setText("");
        	t3 = 0;
        	waveplaygc.setFill(Color.rgb(244, 244, 244));
        	waveplaygc.fillRect(0, 0, waveplaycanvas.getWidth(), waveplaycanvas.getHeight());
        });
        
        
        Label waveformulalabel1 = new Label("2次元波動方程式のアレンジ");
    	waveformulalabel1.setFont(new Font(34));
    	waveformulalabel1.getTransforms().add(new Translate(270, 5));
    	Label waveformulalabel2 = new Label("2次元波動方程式の右辺第二項の符号をマイナスにしてみました．"
    			+ "\n微分方程式と境界条件は以下の通りです．"
    			+ "\n陽解法を用いて解を求めています．");
    	waveformulalabel2.setFont(new Font(24));
    	waveformulalabel2.getTransforms().add(new Translate(10, 80));
    	Label waveformulalabel3 = new Label("また，濃度をシグモイド関数でHSBの輝度に変換して表示しています．");
    	waveformulalabel3.setFont(new Font(24));
    	waveformulalabel3.getTransforms().add(new Translate(10, 600));
    	Image waveformulaimage1 = new Image( "wave1.png" );
        ImageView waveformulaimgView1 = new ImageView( waveformulaimage1 );
        waveformulaimgView1.getTransforms().add(new Translate(10, 200));
        Image waveformulaimage2 = new Image( "wave2.png" );
        ImageView waveformulaimgView2 = new ImageView( waveformulaimage2 );
        waveformulaimgView2.getTransforms().add(new Translate(10, 630));
        button22.setOnAction((ActionEvent e) -> {
        	//2次元波動方程式のアレンジ解説画面
        	Stage waveformulastage = new Stage();
        	waveformulastage.initModality(Modality.APPLICATION_MODAL);
        	waveformulastage.initOwner(stage);
        	waveformulagc.setFill(Color.rgb(244, 244, 244));
            waveformulagc.fillRect(0, 0, waveformulacanvas.getWidth(), waveformulacanvas.getHeight());
            Group waveformula = new Group();
            waveformula.getChildren().add(waveformulacanvas);
            waveformula.getChildren().add(waveformulalabel1);
            waveformula.getChildren().add(waveformulalabel2);
            waveformula.getChildren().add(waveformulalabel3);
            waveformula.getChildren().add(waveformulaimgView1);
            waveformula.getChildren().add(waveformulaimgView2);
            Scene waveformulascene = new Scene(waveformula);
            waveformulastage.setTitle("数値実験の話題");
            waveformulastage.setScene(waveformulascene);
            waveformulastage.show();
        });
        
        
        button23.setOnAction((ActionEvent e) -> {
        	//2次元波動方程式のアレンジcsv
        	
        	FileInputStream wavefis = null;
        	InputStreamReader waveisr = null;
        	BufferedReader wavebr = null;
        	
        	FileChooser fc = new FileChooser();
        	fc.setTitle("csvファイル選択");
        	
        	fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("csvファイル", "*.csv*"));
        	
        	File file = fc.showOpenDialog(null);
        	String filename = null;
        	
        	if(file != null) {
        		  filename = file.getPath();
        		  try {
              		wavefis = new FileInputStream(filename);
              		waveisr = new InputStreamReader(wavefis);
              		wavebr = new BufferedReader(waveisr);
              		
              		String line;
              		
              		int i = 0;
              		
              		while ((line = wavebr.readLine()) != null) {
              			String[] arr = null;
              			arr = line.split(",");
              			int arrleng = arr.length;
              			for (int arrcounter = 0; arrcounter < arrleng; arrcounter++) {
              				u1[arrcounter][99 - i][0] = Double.parseDouble(arr[arrcounter]);
              				u1[arrcounter][99 - i][1] = Double.parseDouble(arr[arrcounter]);
              			}
              			i++;
              		}
              		
              	} catch (Exception e1) {
              	    e1.printStackTrace();
              	  } finally {
              	    try {
              	      wavebr.close();
              	    } catch (Exception e1) {
              	      e1.printStackTrace();
              	    }
              	  }
        		  waveuzerocheck = true;
        		}
        	
        	
        	
        	
        });
        
        
        button24.setOnAction((ActionEvent e) -> {
        	if (arrange1 == false) {
        		arrange1 = true;
        		button24.setText("アレンジあり");
        	} 
        	else {
        		arrange1 = false;
        		button24.setText("アレンジなし");
        		}
        	
        	
        });
        
        
    }

    public static void main(String[] args) {
        launch();
    }
}


