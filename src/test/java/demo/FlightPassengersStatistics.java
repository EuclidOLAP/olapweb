package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FlightPassengersStatistics {

    static List<Member> dateDimension;
    static List<Member> aircraftTypeDimension;
    static List<Member> areaDimension;

    static {
        dateDimension = new ArrayList<>();
        aircraftTypeDimension = new ArrayList<>();
        areaDimension = new ArrayList<>();

        String membersInfoText = "[Date].[ALL].[2020].[Q1].[January]#1.08901034553157\n" +
                "[Date].[ALL].[2020].[Q1].[February]#1.02919029203695\n" +
                "[Date].[ALL].[2020].[Q1].[March]#1.08311315408311\n" +
                "[Date].[ALL].[2020].[Q2].[April]#1.03344610283361\n" +
                "[Date].[ALL].[2020].[Q2].[May]#1.0578471180989\n" +
                "[Date].[ALL].[2020].[Q2].[June]#1.06426320914909\n" +
                "[Date].[ALL].[2020].[Q3].[July]#1.06548671357507\n" +
                "[Date].[ALL].[2020].[Q3].[August]#1.00859445970757\n" +
                "[Date].[ALL].[2020].[Q3].[September]#1.02635883755199\n" +
                "[Date].[ALL].[2020].[Q4].[October]#1.01526308305384\n" +
                "[Date].[ALL].[2020].[Q4].[November]#1.03077386840581\n" +
                "[Date].[ALL].[2020].[Q4].[December]#1.03325873973327\n" +
                "[Date].[ALL].[2021].[Q1].[January]#1.0759446959775\n" +
                "[Date].[ALL].[2021].[Q1].[February]#1.03639651337123\n" +
                "[Date].[ALL].[2021].[Q1].[March]#1.00958670963075\n" +
                "[Date].[ALL].[2021].[Q2].[April]#1.04150001585064\n" +
                "[Date].[ALL].[2021].[Q2].[May]#1.01112810028399\n" +
                "[Date].[ALL].[2021].[Q2].[June]#1.04055630892128\n" +
                "[Date].[ALL].[2021].[Q3].[July]#1.07256756555606\n" +
                "[Date].[ALL].[2021].[Q3].[August]#1.05464677829157\n" +
                "[Date].[ALL].[2021].[Q3].[September]#1.06367953202545\n" +
                "[Date].[ALL].[2021].[Q4].[October]#1.07039392380762\n" +
                "[Date].[ALL].[2021].[Q4].[November]#1.0664015452169\n" +
                "[Date].[ALL].[2021].[Q4].[December]#1.07451774942961\n" +
                "[Date].[ALL].[2022].[Q1].[January]#1.09294160381452\n" +
                "[Date].[ALL].[2022].[Q1].[February]#1.01579483583636\n" +
                "[Date].[ALL].[2022].[Q1].[March]#1.06553756197897\n" +
                "[Date].[ALL].[2022].[Q2].[April]#1.01923147013899\n" +
                "[Date].[ALL].[2022].[Q2].[May]#1.06151422711606\n" +
                "[Date].[ALL].[2022].[Q2].[June]#1.02110640273086\n" +
                "[Date].[ALL].[2022].[Q3].[July]#1.08624192944852\n" +
                "[Date].[ALL].[2022].[Q3].[August]#1.09771187917412\n" +
                "[Date].[ALL].[2022].[Q3].[September]#1.08152641844636\n" +
                "[Date].[ALL].[2022].[Q4].[October]#1.06936032868958\n" +
                "[Date].[ALL].[2022].[Q4].[November]#1.05361517325103\n" +
                "[Date].[ALL].[2022].[Q4].[December]#1.01869450947036\n" +
                "[Aircraft Type].[ALL].[Boeing].[Boeing 747]#1.15\n" +
                "[Aircraft Type].[ALL].[Boeing].[Boeing 777]#1.4\n" +
                "[Aircraft Type].[ALL].[Boeing].[Boeing 787 Dreamliner]#1.6\n" +
                "[Aircraft Type].[ALL].[Airbus].[Airbus A380]#1.59\n" +
                "[Aircraft Type].[ALL].[Airbus].[Airbus A350]#1.41\n" +
                "[Aircraft Type].[ALL].[Airbus].[Airbus A330]#1.14\n" +
                "[Aircraft Type].[ALL].[Embraer].[Embraer E-Jets]#0.7\n" +
                "[Aircraft Type].[ALL].[Bombardier].[Bombardier CRJ]#0.85\n" +
                "[Aircraft Type].[ALL].[Sukhoi].[Sukhoi Superjet]#1.1\n" +
                "[Area].[ALL].[Asia]#1\n" +
                "[Area].[ALL].[Europe]#1\n" +
                "[Area].[ALL].[North America]#1\n" +
                "[Area].[ALL].[South America]#1\n" +
                "[Area].[ALL].[Africa]#1\n" +
                "[Area].[ALL].[Oceania]#1";

        for (String memberInfo : membersInfoText.split("\n")) {
            String[] split = memberInfo.split("#");
            String path = split[0];
            double weight = Double.parseDouble(split[1]);

            Member member = new Member(path, weight);

            if (path.contains("[Date].[ALL].")) {
                dateDimension.add(member);
            } else if (path.contains("[Aircraft Type].[ALL].")) {
                aircraftTypeDimension.add(member);
            } else {
                areaDimension.add(member);
            }
        }
    }

    public static void main(String[] args) {

        Random random = new Random();

        for (Member starting : areaDimension) {
            for (Member dest : areaDimension) {
                for (Member dateMember : dateDimension) {
                    for (Member aircraftMember : aircraftTypeDimension) {
                        double turnover
                                = starting.getWeight()
                                * dest.getWeight()
                                * dateMember.getWeight()
                                * aircraftMember.getWeight()
                                * (20 + random.nextDouble() * 300);
                        String insVal = String.format("(%s, %s, %s, %s measures [Number of Passengers] %d),",
                                starting.getPath().replace("[Area]", "[Starting Point]"), dest.getPath().replace("[Area]", "[Destination]"), dateMember.getPath(), aircraftMember.getPath(), (int) turnover);
                        System.out.println(insVal);
                    }
                }
            }
        }


    }
}
