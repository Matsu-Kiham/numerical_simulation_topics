数値実験の話題

製作者：Matsu-Kiham
バージョン：1.00

有名な数値実験の例をアニメーション付きで紹介するアプリです．
数値実験の例を作成するにあたって，下記の書籍を参考にしました．
デヴィッド・バージェス／モラグ・ボリー著，垣田高夫／大町比佐栄訳，1990，
微分方程式で数学モデルを作ろう，日本評論社

導入方法：
まずは，jdkをインストールしてください．
製作者は次の環境で開発しています．
openjdk version "14" 2020-03-17
OpenJDK Runtime Environment AdoptOpenJDK (build 14+36)
OpenJDK 64-Bit Server VM AdoptOpenJDK (build 14+36, mixed mode, sharing)
このjdkはhttps://jdk.java.net/archive/
からダウンロードできます．
jdkをインストールしたら，環境変数のPathに\jdk-14.0.0.36-hotspot\binを追加してください．

続いて，eclipseをインストールしてください．
製作者はVersion: 2020-03 (4.15.0)で開発しています．
製作者はhttps://www.eclipse.org/downloads/packages/release/2020-03/r
からEclipse IDE for Enterprise Java Developers(includes Incubating componentsを
ダウンロードしてインストールしました．

次に，JavaFX SDKをダウンロードしてください．
製作者はjavafx-sdk-14.0.1で開発しています．
JavaFX SDKはhttps://gluonhq.com/products/javafx/
からダウンロードできます．

最後に，このレポジトリからnumerical_simulation_topicsをダウンロード
してください．

以上を終えたら，eclipseを起動して，ワークスペースにダウンロードした
numerical_simulation_topicsを入れてください．
そして，Project Explorerを確認して，JRE System Libraryにjdkのhotspot
を，sampleに先ほどダウンロードしたJavaFXの以下のjarファイルを対応させてください．
javafx.base.jar
javafx.controls.jar
javafx.fxml
javafx.graphics.jar
javafx.media.jar
javafx.swing.jar
javafx.web.jar
javafx-swt.jar
なお，これらのファイルは，javafx-sdkフォルダの中にあるlibフォルダに
含まれています．

以上を終えたら，eclipseのRunをクリックするとアプリが起動します．

アプリの操作方法：
2次元拡散方程式のアレンジと2次波動方程式のアレンジではcsvファイル
を選択しますが，このファイルは縦横のデータ数がそれぞれ100個の
ファイルを選択してください．numerical_simulation_topics/binフォルダ
の中には，csvファイルのサンプルが入っているので，とりあえず試したい
方はこちらをご利用ください．

作者本人への連絡
matsukiham★gmail.com（★を@に変えてください）

更新履歴
2020/08/06　バージョン1.00公開