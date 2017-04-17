
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import static java.awt.SystemColor.info;

public class Parser {

    public static void main(String[] args) throws IOException {

        // get 방식으로 홈페이지를 로드
        Document doc = Jsoup.connect("http://score.sports.media.daum.net/record/soccer/epl/trnk.daum").get();

        // 기록 테이블을 가져와서 내용을 리스트에 입력
        Element table = doc.select(".team_rank").get(0);
        Elements infos = table.select("tbody tr");
        Elements topInfo= table.select("thead tr");

        ArrayList<TeamInfo> list = new ArrayList<>();

        for (Element info : infos) {

            Elements tds = info.select("td");

            TeamInfo team = new TeamInfo();
            team.position = Integer.parseInt(tds.get(0).text().trim());
            team.teamName = tds.get(1).text().trim();
            team.played = Integer.parseInt(tds.get(2).text().trim());
            team.win = Integer.parseInt(tds.get(3).text().trim());
            team.draw = Integer.parseInt(tds.get(4).text().trim());
            team.loss = Integer.parseInt(tds.get(5).text().trim());
            team.gf = Integer.parseInt(tds.get(6).text().trim());
            team.ga = Integer.parseInt(tds.get(7).text().trim());
            team.gd = Integer.parseInt(tds.get(8).text().trim());
            team.points = Integer.parseInt(tds.get(9).text().trim());
            team.lastFiveGame = tds.get(10).text().trim();

            list.add(team);
        }
        Elements newInfo = topInfo.select("th");
        TeamInfo sTeam =new TeamInfo();
        sTeam.sPosition=newInfo.get(0).text().trim();
        sTeam.sTeamName=newInfo.get(1).text().trim();
        sTeam.sPlayed=newInfo.get(2).text().trim();
        sTeam.sWin=newInfo.get(3).text().trim();
        sTeam.sDraw=newInfo.get(4).text().trim();
        sTeam.sLoss=newInfo.get(5).text().trim();
        sTeam.sGf=newInfo.get(6).text().trim();
        sTeam.sGa=newInfo.get(7).text().trim();
        sTeam.sGd=newInfo.get(8).text().trim();
        sTeam.sPoint=newInfo.get(9).text().trim();
        sTeam.sLastGame=newInfo.get(10).text().trim();
        list.add(sTeam);



        // 가져온 결과를 출력
        System.out.printf("%2s %12s %3s %3s %3s %3s %3s %3s %3s %3s %s\n", sTeam.sPosition, sTeam.sTeamName,
                sTeam.sPlayed, sTeam.sWin, sTeam.sDraw, sTeam.sLoss, sTeam.sGf, sTeam.sGa, sTeam.sGd, sTeam.sPoint,
                sTeam.sLastGame);

        for (TeamInfo info : list) {
            System.out.printf("%2d %15s %3d %3d %3d %3d %3d %3d %3d %3d %s\n", info.position, info.teamName,
                    info.played, info.win, info.draw, info.loss, info.gf, info.ga, info.gd, info.points,
                    info.lastFiveGame);
        }
    }
}

class TeamInfo {

    public int position; // 순위
    public String teamName; // 팀명
    public int played; // 경기
    public int win; // 승
    public int draw; // 무
    public int loss; // 패
    public int gf; // 득점(Goals For)
    public int ga; // 실점(Goals Against)
    public int gd; // 득실차
    public int points; // 승점
    public String lastFiveGame; // 최근 5경기

    public String sPosition;
    public String sTeamName;
    public String sPlayed;
    public String sWin;
    public String sDraw;
    public String sLoss;
    public String sGf;
    public String sGa;
    public String sGd;
    public String sPoint;
    public String sLastGame;
}