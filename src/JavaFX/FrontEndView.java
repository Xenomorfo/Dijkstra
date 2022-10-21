/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX;

import Graph.Vertex;
import Manager.Local;
import Manager.Statistics;
import Manager.Tickets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Optional;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Gere a visualização do JavaFX
 * @author José Canelas
 */
public class FrontEndView implements FrontEndInterface {

    private static final int PIN = 1234;
    private final Scene scene;
    private StackPane scenario;
    private final String styleOff = "-fx-text-fill:red;-fx-border-color: red; "
            + "-fx-background-color: transparent; -fx-border-radius: 23;";
    private final String styleOn = "-fx-background-color: yellow;-fx-background-radius: 23;";
    private final ButtonType sim;
    private final ButtonType nao;
    private final ButtonType rapido;
    private final ButtonType barato;
    private final ButtonType cancelar;
    private final List<Button> buttons;
    private boolean newPath = false;

    /**
     *
     * Classe ManagerView
     */
    public FrontEndView() {

        scenario = initScenario();
        scene = new Scene(scenario, 1250, 712);
        sim = new ButtonType("SIM");
        nao = new ButtonType("NÃO");
        buttons = new ArrayList<>();
        rapido = new ButtonType("Mais Rápido");
        barato = new ButtonType("Mais Barato");
        cancelar = new ButtonType("Cancelar");

    }

    /**
     *
     * Cria cenário
     */
    private StackPane initScenario() {

        scenario = new StackPane();
        Image image = new Image("file:jardim.jpg");
        ImageView background = new ImageView(image);
        scenario.getChildren().add(background);
        return scenario;
    }

    /**
     *
     * @return scene cena
     */
    @Override
    public Scene getScene() {
        return scene;
    }

    /**
     *
     * Cria Butões
     *
     * @param controller Controlador
     * @param path Caminho
     * @param text Texto
     */
    @Override
    public void buttonInit(FrontEndController controller, List<Integer> path, String text) {

        Button btn = new Button();
        Text legend = createText(text, -500, 250, 15);
        Text info = createText("Escolha os locais a visitar", 400, -300, 25);
        Text choice = createText("Menu de Opções:", 500, 130, 15);
        Text sair = createText("Pressione\n Para Sair", -30, 318, 9);
        sair.setFill(Color.MAGENTA);
        btn.setScaleX(6);
        btn.setScaleY(6);
        btn.setText("Iniciar");
        btn.setOnAction((ActionEvent event) -> {
            btn.setVisible(false);
            setTriggers(controller, path);
            scenario.getChildren().add(legend);
            scenario.getChildren().add(info);
            scenario.getChildren().add(choice);
            scenario.getChildren().add(sair);
        });
        scenario.getChildren().add(btn);

    }

    /*
     *
     * Cria Triggers
     * @param controller Controlador
     * @param path Caminho
     */
    /**
     * Butões de Locais
     *
     * @param controller Controlador
     * @param path Caminho
     */
    @Override
    public void setTriggers(FrontEndController controller, List<Integer> path) {

        Button btn1 = createButtons("1", -30, 280);
        Button btn2 = createButtons("2", -240, 0);
        Button btn3 = createButtons("3", 600, 70);
        Button btn4 = createButtons("4", -150, -150);
        Button btn5 = createButtons("5", 20, 100);
        Button btn6 = createButtons("6", 375, -30);
        Button btn7 = createButtons("7", -50, -50);
        Button btn8 = createButtons("8", 200, 140);
        Button calcDistBtn = createButtons("Calculo Novo Percurso", 500, 170);
        Button calcLastBtn = createButtons("Calcular Percurso Anterior", 500, 220);
        Button viewStats = createButtons("Ver Estatísticas do Parque", 500, 270);
        Button eventLogs = createButtons("Alterar Eventos/Logs", 500, 320);
        btn1.setOnAction((ActionEvent event) -> {
            Alert alert = Alerts(Alert.AlertType.CONFIRMATION, "Abandonar o Parque", "Deseja Sair do Programa?", "");
            alert.getButtonTypes().clear();
            alert.getButtonTypes().addAll(sim, nao);
            Optional<ButtonType> result = alert.showAndWait();
            ButtonType button = result.orElse(nao);
            if (button == (sim)) {
                Platform.exit();
            }
        });

        btn2.setOnAction((ActionEvent event) -> {
            buttonCheck(controller, btn2, path, 2);
        });

        btn3.setOnAction((ActionEvent event) -> {
            buttonCheck(controller, btn3, path, 3);
        });

        btn4.setOnAction((ActionEvent event) -> {
            buttonCheck(controller, btn4, path, 4);
        });

        btn5.setOnAction((ActionEvent event) -> {
            buttonCheck(controller, btn5, path, 5);
        });

        btn6.setOnAction((ActionEvent event) -> {
            buttonCheck(controller, btn6, path, 6);
        });

        btn7.setOnAction((ActionEvent event) -> {
            buttonCheck(controller, btn7, path, 7);
        });

        btn8.setOnAction((ActionEvent event) -> {
            buttonCheck(controller, btn8, path, 8);
        });

        calcDistBtn.setOnAction((ActionEvent event) -> {
            if (calcDistBtn.isManaged()) {
                setState(calcDistBtn, styleOn, false);
                buttons.add(calcDistBtn);
            } else {
                setState(calcDistBtn, styleOff, true);
            }
            if (path.size() == 1) {
                Alert alert = Alerts(Alert.AlertType.WARNING, "INFORMAÇÃO",
                        "Não é possivel satisfazer o seu pedido!!!", "Por favor, escolha pelo menos um local a visitar....");
                alert.showAndWait();
            } else {
                Alert alert = Alerts(Alert.AlertType.CONFIRMATION, "Mensagem", "\t\tModo de Passeio", "");
                alert.getButtonTypes().clear();
                ButtonType bike = new ButtonType("Bicicleta");
                ButtonType walk = new ButtonType("A pé");
                alert.getButtonTypes().addAll(bike, walk, cancelar);
                alert.getDialogPane().setPrefSize(200, 100);
                Optional<ButtonType> result = alert.showAndWait();
                ButtonType button = result.get();
                if (button == bike) {
                    controller.setIsBike(true);
                    controller.calcPath(path);
                    newPath = true;
                } else if (button == walk) {
                    controller.setIsBike(false);
                    controller.calcPath(path);
                    newPath = true;
                } else {
                    alert.close();
                }
            }

            alterButtonState(path);
        });

        calcLastBtn.setOnAction((ActionEvent event) -> {
            if (calcLastBtn.isManaged()) {
                setState(calcLastBtn, styleOn, false);
                buttons.add(calcLastBtn);
            } else {
                setState(calcLastBtn, styleOff, true);
            }

            if (!newPath) {
                Alert alert = Alerts(Alert.AlertType.WARNING, "INFORMAÇÃO",
                        "Não é possivel satisfazer o seu pedido!!!", "Não existem percurso anteriores...");
                alert.showAndWait();
                setState(calcLastBtn, styleOff, true);

            } else {
                controller.calcLast(path);
            }
            alterButtonState(path);

        });

        viewStats.setOnAction((ActionEvent event) -> {
            controller.showStats();

        });
        eventLogs.setOnAction((ActionEvent event) -> {
            Alert alert;
            int password = Dialog("Pin de Administrador");
            if (password != PIN) {
                alert = Alerts(Alert.AlertType.ERROR, "ATENÇÃO", "Password Inválida", "Acesso reservado a Administradores");
                alert.getDialogPane().setPrefSize(250, 170);
                alert.showAndWait();
            } else {
                alert = Alerts(Alert.AlertType.NONE, "Mensagem", "Eventos & Logs", "Escolha o log a guardar");
                ButtonType tickets = new ButtonType("Bilhetes");
                ButtonType paths = new ButtonType("Caminhos");
                ButtonType stats = new ButtonType("Estatisticas");
                alert.getButtonTypes().addAll(tickets, paths, stats);
                alert.getDialogPane().setPrefSize(340, 170);
                Optional<ButtonType> result = alert.showAndWait();
                ButtonType button = result.get();
                if (button == tickets) {
                    controller.setLogChoice(0);
                } else if (button == paths) {
                    controller.setLogChoice(1);
                } else {
                    controller.setLogChoice(2);
                }

            }
        });
    }

    /**
     *
     * Verifica se há acesso aos locais
     */
    private void buttonCheck(FrontEndController controller, Button btn, List<Integer> path, int btnNumber) {
        if (controller.checkVertex(btnNumber) == null) {
            Alert alert = Alerts(Alert.AlertType.WARNING, "INFORMAÇÃO",
                    "Não é possivel visitar o local!!!", "Obras em curso...");
            alert.showAndWait();
            setState(btn, styleOn, false);
        }
        buttonHandle(btn, path, btnNumber);
    }

    /**
     *
     * Altera estado dos botões
     */
    private void buttonHandle(Button btn, List<Integer> path, int destination) {

        if (btn.isManaged()) {
            setState(btn, styleOn, false);
            path.add(destination);
            buttons.add(btn);
        } else {
            setState(btn, styleOff, true);
            path.remove(Integer.valueOf(destination));
        }

    }

    /**
     *
     * Cria Butões
     *
     * @param name Nome
     * @param x coordenadaX
     * @param y coordenadaY
     */
    private Button createButtons(String name, int x, int y) {

        Button btn = new Button(name);
        btn.setTranslateX(x);
        btn.setTranslateY(y);
        btn.setStyle(styleOff);
        btn.setManaged(true);
        scenario.getChildren().add(btn);
        return btn;

    }

    /**
     *
     * Cria Texto
     *
     * @param name Nome
     * @param x coordenadaX
     * @param y coordenadaY
     * @param fontSize Tamanho fonte
     */
    private Text createText(String txt, int x, int y, int fontSize) {

        Text text = new Text(txt);
        text.setFill(Color.YELLOW);
        text.setTranslateX(x);
        text.setTranslateY(y);
        text.setFont(Font.font("Verdana", fontSize));
        return text;

    }

    /**
     *
     * Mostra Informação
     *
     * @param controller Controlador
     * @param destination Destino
     * @param resultsDist Resultados distância
     * @param resultsCost Resultados custo
     * @param Ecost Económico
     * @param RCost Rápido
     * @param pathDestDist Caminho de Destino - Distância
     * @param pathDestCost Caminho de Destino - Custo
     */
    @Override
    public void showInfo(FrontEndController controller, String destination, String resultsDist, String resultsCost, double Ecost,
            double RCost, List<Vertex<Local>> pathDestDist, List<Vertex<Local>> pathDestCost, String bike) {
        boolean footBike = controller.isBike();
        Alert alert = Alerts(Alert.AlertType.CONFIRMATION, "Mensagem", "- Atrações a visitar » " + destination,
                "Percurso a ser feito " + bike + "\n\n" + resultsDist + "\n\n" + resultsCost
                + "\n\nComprar bilhete(s)?");
        alert.getDialogPane().setPrefSize(700, 400);
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(rapido, barato, cancelar);
        Optional<ButtonType> result = alert.showAndWait();
        ButtonType button = result.orElse(cancelar);
        if (button == (rapido)) {
            controller.setIsCheap(false);
            showChoose(controller, resultsDist, alert, RCost, pathDestDist);

        } else if (button == (barato)) {
            controller.setIsCheap(true);
            showChoose(controller, resultsCost, alert, Ecost, pathDestCost);

        }

    }

    /**
     *
     * Informação da escolha de caminho
     */
    private void showChoose(FrontEndController controller, String results, Alert alert, double cost, List<Vertex<Local>> pathDest) {
        String ticks;
        int qty_tickets = Dialog("Quantidade de bilhetes(max. 10 uni)");
        if (qty_tickets == 1) {
            ticks = "Bilhete Comprado";
        } else {
            ticks = "Bilhetes Comprados";
        }
        if (qty_tickets > 0 && qty_tickets < 11) {
            int nif = Dialog("Número de Identificação Fiscal");
            Tickets ticket = controller.createTicket(nif, results, qty_tickets, cost, pathDest);
            alert = Alerts(Alert.AlertType.INFORMATION, "Mensagem", "\t\t\t\t\t\t\t\t" + qty_tickets + " x " + ticks,
                    results + "\n\n" + "Custo total bilhetes - " + qty_tickets * cost + " €\n\n"
                    + ticket.toString());
            alert.getDialogPane().setPrefSize(700, 450);
            alert.showAndWait();
        } else {
            alert = Alerts(Alert.AlertType.ERROR, "Mensagem", "Quantidade inválida de bilhetes", "Máximo 10 unidades...");
            alert.getDialogPane().setPrefSize(250, 150);
            alert.showAndWait();
        }

    }

    /**
     *
     * Janela de Inserção de NIF
     */
    private int Dialog(String question) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Mensagem");
        dialog.setHeaderText(question);
        dialog.setContentText("Insira o número: ");
        dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);
        Optional<String> show = dialog.showAndWait();
        try {
            if (!show.get().isEmpty()) {
                return Integer.parseInt(show.get());
            } else {
                return 0;
            }
        } catch (NoSuchElementException ex) {
            return 0;
        }
    }

    /**
     *
     * Janela de Inserção de NIF
     *
     * @param type Tipo
     * @param title Titulo
     * @param header Cabeçalho
     * @param content Conteudo
     * @return alert Alerta
     */
    private Alert Alerts(Alert.AlertType type, String title, String header, String content) {

        Alert alert = new Alert(type);
        alert.getDialogPane().setPrefSize(400, 150);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert;

    }

    /**
     *
     * Altera estado do butão
     */
    private void setState(Button btn, String style, Boolean status) {
        btn.setStyle(style);
        btn.setManaged(status);

    }

    /**
     *
     * Altera estado dos butões pressionados
     */
    private void alterButtonState(List<Integer> path) {
        buttons.forEach((btns) -> {
            setState(btns, styleOff, true);
        });
        path.clear();
        path.add(1);
    }


    /**
     * Mostra Estatisticas
     *
     * @param controller Controlador
     * @param map Gráfico de Barras
     */
    @Override
    public void showStats(FrontEndController controller, Map<String, Integer> map) {

        Statistics stats = controller.getStatistics();
        Stage stage = new Stage();
        Scene statsScene = new Scene(new Group());
        stage.setTitle("Estatísticas do Parque");
        stage.setWidth(800);
        stage.setHeight(500);
        stage.setResizable(false);
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart bar = new BarChart(xAxis, yAxis);
        bar.setMaxWidth(400);
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("Bicicleta", stats.getBikeTicket()),
                        new PieChart.Data("A pé", stats.getFootTicket()));
        final PieChart chart = new PieChart(pieChartData);
        XYChart.Series series = new XYChart.Series();
        chart.setTitle("Bilhetes Vendidos");
        bar.setTitle("Locais Mais Visitados");
        chart.setLayoutX(-30);
        chart.setLayoutY(0);
        series.setName("Número de visitas");
        bar.setLayoutX(400);
        bar.setLayoutY(0);
        for (Map.Entry<String, Integer> entry : stats.getMap().entrySet()) {
            series.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
        }
        Text midPrice = new Text();
        midPrice.setLayoutX(10);
        midPrice.setLayoutY(420);
        DecimalFormat df2 = new DecimalFormat(".##");
        midPrice.setText("Preço médio bilhetes - " + df2.format(stats.getMidPrice()) + " €"
                + "\nPercentagem Percusos Rápidos: " + stats.getPercentage(stats.getFaster()) + " %"
                + "\nPercentagem Percursos Económicos: " + stats.getPercentage(stats.getCheaper()) + " %");
        bar.getData().add(series);
        ((Group) statsScene.getRoot()).getChildren().addAll(chart, bar, midPrice);
        stage.setScene(statsScene);
        stage.show();
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
