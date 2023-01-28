package course_work;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;


public class GUI extends Application {

     Scene sceneHome,sceneDetails,sceneHistory;
     ObservableList<Object> objectList = FXCollections.observableArrayList();
     ObservableList<Object> matchList = FXCollections.observableArrayList();
     ObservableList<Object> search = FXCollections.observableArrayList();
     PremierLeagueManager premierLeagueManager = new PremierLeagueManager();
     ArrayList<Football_club> clubs;
     ArrayList<DataOfMatch> matchData;
     Image image;
     ImageView imageView,imageView2,imageView3;
     TableView tableHistory;
     TextField textDay,textMonth,textYear;
     int day,month,year;


      public ObservableList<Object> dataOfList(){

        try{
            FileInputStream fileInputStream = new FileInputStream("save.ser");
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            clubs = (ArrayList<Football_club>) inputStream.readObject();
            inputStream.close();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            System.out.println("");
        }catch (NullPointerException e){
            System.out.println("");
        }
        objectList.clear();
        Comparator<Football_club> sortByPoints = Comparator.comparing(Football_club::getPoints).thenComparing(Football_club::getGoalDifference);
        clubs.sort(sortByPoints.reversed());
        objectList.addAll(clubs);
        return objectList;
    }

    public ObservableList<Object> sortByGoalsScored(){
          objectList.clear();
          objectList.addAll(clubs);
          Comparator<Football_club> sortGoalsScored = Comparator.comparing(Football_club::getGoalsScored);
          clubs.sort(sortGoalsScored);
          Collections.reverse(clubs);
          return objectList;
    }

    public ObservableList<Object> sortByWins(){
        objectList.clear();
        objectList.addAll(clubs);
        Comparator<Football_club> sortWins = Comparator.comparing(Football_club::getWins);
        clubs.sort(sortWins);
        Collections.reverse(clubs);
        return objectList;
    }

    public ObservableList<Object> dataOfMatchHistory() {
        try {
            FileInputStream fileInputStreamMatchHistory = new FileInputStream("History.ser");
            ObjectInputStream inputStreamMatchHistory = new ObjectInputStream(fileInputStreamMatchHistory);
            matchData = (ArrayList<DataOfMatch>) inputStreamMatchHistory.readObject();
            inputStreamMatchHistory.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("");
        } catch (NullPointerException e) {
            System.out.println("");
        }
        matchList.clear();
        try{
            matchList.addAll(matchData);
        }catch (NullPointerException e){
            System.out.println("No match data in list");
        }return matchList;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane rootHome = new Pane();

        image = new Image("file:Football.jpg");
        imageView = new ImageView();
        imageView.setImage(image);
        imageView.setPreserveRatio(true);

        Label lblTitle = new Label("Football League");
        lblTitle.setLayoutX(200);
        lblTitle.setLayoutY(0);

        Button btnDetails = new Button("CLUB DETAILS");
        btnDetails.setLayoutX(190);
        btnDetails.setLayoutY(100);
        btnDetails.setStyle("-fx-background-color:#bfff00; -fx-min-width:100; -fx-font: normal bold 15px 'serif'");
        btnDetails.setOnAction(event -> primaryStage.setScene(sceneDetails));

        Button btnHistory = new Button("MATCH HISTORY");
        btnHistory.setLayoutX(180);
        btnHistory.setLayoutY(180);
        btnHistory.setStyle("-fx-background-color:#bfff00; -fx-min-width:100; -fx-font: normal bold 15px 'serif'");
        btnHistory.setOnAction(event -> primaryStage.setScene(sceneHistory));

        Button btnClose = new Button("CLOSE");
        btnClose.setLayoutX(200);
        btnClose.setLayoutY(260);
        btnClose.setStyle("-fx-background-color:#bfff00; -fx-min-width:100; -fx-font: normal bold 15px 'serif'");
        btnClose.setOnAction(event -> primaryStage.close());

        rootHome.getChildren().addAll(imageView,lblTitle,btnDetails,btnHistory,btnClose);

        sceneHome = new Scene(rootHome, 500, 370);
        primaryStage.setScene(sceneHome);
        primaryStage.show();
        primaryStage.setTitle("Football League");


        //Club Details Pane
        Pane paneDetails = new Pane();

        image = new Image("file:Football.jpg");
        imageView2 = new ImageView();
        imageView2.setImage(image);
        imageView2.setFitWidth(1000);
        imageView2.setFitHeight(1000);
        imageView2.setPreserveRatio(true);


        TableView table = new TableView();
        table.setLayoutX(20);
        table.setLayoutY(20);
        table.setPrefWidth(900);

        TableColumn<Object, String> colName = new TableColumn<>("Club Name");
        colName.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        colName.setPrefWidth(250);

        TableColumn<Object, Integer> colPoint = new TableColumn<>("Points");
        colPoint.setCellValueFactory(new PropertyValueFactory<>("points"));
        colPoint.setPrefWidth(87);

        TableColumn<Object, Integer> colWin = new TableColumn<>("Wins");
        colWin.setCellValueFactory(new PropertyValueFactory<>("wins"));
        colWin.setPrefWidth(87);

        TableColumn<Object, Integer> colDraw = new TableColumn<>("Draws");
        colDraw.setCellValueFactory(new PropertyValueFactory<>("draws"));
        colDraw.setPrefWidth(87);

        TableColumn<Object, Integer> colDefeat = new TableColumn<>("Defeats");
        colDefeat.setCellValueFactory(new PropertyValueFactory<>("defeats"));
        colDefeat.setPrefWidth(87);

        TableColumn<Object, Integer> colScore = new TableColumn<>("Goals Scored");
        colScore.setCellValueFactory(new PropertyValueFactory<>("goalsScored"));
        colScore.setPrefWidth(100);

        TableColumn<Object, Integer> colReceive = new TableColumn<>("Goals Received");
        colReceive.setCellValueFactory(new PropertyValueFactory<>("goalsReceived"));
        colReceive.setPrefWidth(100);

        TableColumn<Object, Integer> colNoMatch = new TableColumn<>("No. Of Matches");
        colNoMatch.setCellValueFactory(new PropertyValueFactory<>("noOfMatches"));
        colNoMatch.setPrefWidth(100);


        table.setItems(dataOfList());
        table.getColumns().addAll(colName, colPoint, colWin, colDraw, colDefeat, colScore, colReceive, colNoMatch);

        
        Button btnSortGoals = new Button("Sort by Goals Scored ");
        btnSortGoals.setLayoutX(30);
        btnSortGoals.setLayoutY(450);
        btnSortGoals.setOnAction(event -> sortByGoalsScored());
        btnSortGoals.setStyle("-fx-background-color:#bfff00; -fx-min-width:100; -fx-font: normal bold 15px 'serif'");

        Button btnSortWins = new Button("Sort by wins ");
        btnSortWins.setLayoutX(250);
        btnSortWins.setLayoutY(450);
        btnSortWins.setOnAction(event -> sortByWins());
        btnSortWins.setStyle("-fx-background-color:#bfff00;-fx-min-width:100; -fx-font: normal bold 15px 'serif'");

        Button btnSortPoints = new Button("Sort by Points ");
        btnSortPoints.setLayoutX(450);
        btnSortPoints.setLayoutY(450);
        btnSortPoints.setStyle("-fx-background-color:#bfff00;-fx-min-width:100; -fx-font: normal bold 15px 'serif'");
        btnSortPoints.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                colPoint.setSortType(TableColumn.SortType.DESCENDING);
                table.getSortOrder().addAll(colPoint);
            }
        });

        Button btnBackDetails = new Button("BACK");
        btnBackDetails.setLayoutX(900);
        btnBackDetails.setLayoutY(450);
        btnBackDetails.setStyle("-fx-background-color:#bfff00; -fx-min-width:100; -fx-font: normal bold 15px 'serif'");
        btnBackDetails.setOnAction(event -> primaryStage.setScene(sceneHome));


        paneDetails.getChildren().addAll(imageView2,table,btnSortGoals, btnSortWins, btnSortPoints,btnBackDetails);


        sceneDetails = new Scene(paneDetails, 1000, 500);


        // Match History pane

        Pane paneHistory = new Pane();

        image = new Image("file:Football.jpg");
        imageView3 = new ImageView();
        imageView3.setImage(image);
        imageView3.setFitWidth(900);
        imageView3.setFitHeight(900);
        imageView3.setPreserveRatio(true);

        tableHistory = new TableView();
        tableHistory.setLayoutX(35);
        tableHistory.setLayoutY(20);
        tableHistory.setPrefWidth(700);
        tableHistory.setItems(dataOfMatchHistory());


        TableColumn<Object,String> columnDate = new TableColumn<>("Date");
        columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnDate.setPrefWidth(100);

        TableColumn<Object,String> columnClubOneName = new TableColumn<>("Club One");
        columnClubOneName.setCellValueFactory(new PropertyValueFactory<>("clubOne"));
        columnClubOneName.setPrefWidth(200);

        TableColumn<Object,Integer> columnClubOneGoals = new TableColumn<>("Club One Goals");
        columnClubOneGoals.setCellValueFactory(new PropertyValueFactory<>("clubOneGoals"));
        columnClubOneGoals.setPrefWidth(99);

        TableColumn<Object,String> columnClubTwoName = new TableColumn<>("Club Two");
        columnClubTwoName.setCellValueFactory(new PropertyValueFactory<>("clubTwo"));
        columnClubTwoName.setPrefWidth(200);

        TableColumn<Object,Integer> columnClubTwoGoals = new TableColumn<>("Club Two Goals");
        columnClubTwoGoals.setCellValueFactory(new PropertyValueFactory<>("clubTwoGoals"));
        columnClubTwoGoals.setPrefWidth(99);

        Label labelSearchDate = new Label("Search Date : ");
        labelSearchDate.setLayoutX(10);
        labelSearchDate.setLayoutY(450);
        labelSearchDate.setStyle("-fx-min-width:100; -fx-font: normal bold 15px 'serif'");
        labelSearchDate.setTextFill(Color.WHITE);

        textDay = new TextField();
        textDay.setLayoutX(100);
        textDay.setLayoutY(445);
        textDay.setMaxWidth(40);
        textDay.setPromptText("DD");

        textMonth = new TextField();
        textMonth.setLayoutX(150);
        textMonth.setLayoutY(445);
        textMonth.setMaxWidth(40);
        textMonth.setPromptText("MM");

        textYear = new TextField();
        textYear.setLayoutX(200);
        textYear.setLayoutY(445);
        textYear.setMaxWidth(50);
        textYear.setPromptText("YYYY");

        Button btnSearch = new Button("Search");
        btnSearch.setLayoutX(270);
        btnSearch.setLayoutY(445);
        btnSearch.setStyle("-fx-background-color:#bfff00; -fx-min-width:100; -fx-font: normal bold 15px 'serif'");
        btnSearch.setOnAction(event ->  searchDate());

        Button btnClear = new Button("Clear");
        btnClear.setLayoutX(400);
        btnClear.setLayoutY(445);
        btnClear.setStyle("-fx-background-color:#bfff00; -fx-min-width:100; -fx-font: normal bold 15px 'serif'");
        btnClear.setOnAction(event -> clear());

        Button btnRandomMatch = new Button("Random Match");
        btnRandomMatch.setLayoutX(525);
        btnRandomMatch.setLayoutY(445);
        btnRandomMatch.setStyle("-fx-background-color:#bfff00; -fx-min-width:100; -fx-font: normal bold 15px 'serif'");
        btnRandomMatch.setOnAction(event -> randomMatch());

        Button btnBackHistory = new Button("BACK");
        btnBackHistory.setLayoutX(670);
        btnBackHistory.setLayoutY(445);
        btnBackHistory.setStyle("-fx-background-color:#bfff00; -fx-min-width:100; -fx-font: normal bold 15px 'serif'");
        btnBackHistory.setOnAction(event -> primaryStage.setScene(sceneHome));

        tableHistory.getColumns().addAll(columnDate,columnClubOneName,columnClubOneGoals,columnClubTwoName,columnClubTwoGoals);
        paneHistory.getChildren().addAll(imageView3,tableHistory,labelSearchDate,textDay,textMonth,textYear,btnSearch,btnClear,btnBackHistory,btnRandomMatch);


        sceneHistory = new Scene(paneHistory,780,500);
    }

    public void searchDate(){
          search.clear();
        try{
            day = Integer.parseInt(textDay.getText());
            month = Integer.parseInt(textMonth.getText());
            year = Integer.parseInt(textYear.getText());
        }catch (Exception e){
            System.out.println("Please enter a date.");
        }
        for (DataOfMatch match : matchData ){
            if(day == match.getDate().getDay() && month == match.getDate().getMonth() && year == match.getDate().getYear()){
                search.add(match);
            }
        }
        tableHistory.setItems(search);
    }
    public void clear(){
          textDay.setText("");
          textMonth.setText("");
          textYear.setText("");
          tableHistory.setItems(dataOfMatchHistory());
    }

    void randomMatch(){
          premierLeagueManager.randomMatch();
          dataOfList();
          dataOfMatchHistory();
    }

    public static void main(String args[]){
        launch(args);
    }
}