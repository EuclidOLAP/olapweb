package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AirlineTurnover {

    static List<Member> dateDimension;
    static List<Member> aircraftTypeDimension;
    static List<Member> serviceTypeDimension;

    static {
        dateDimension = new ArrayList<>();
        aircraftTypeDimension = new ArrayList<>();
        serviceTypeDimension = new ArrayList<>();
        String membersInfoText = "[Date].[ALL].[2020].[Q1].[January]#1\n" +
                "[Date].[ALL].[2020].[Q1].[February]#1\n" +
                "[Date].[ALL].[2020].[Q1].[March]#1\n" +
                "[Date].[ALL].[2020].[Q2].[April]#1\n" +
                "[Date].[ALL].[2020].[Q2].[May]#1\n" +
                "[Date].[ALL].[2020].[Q2].[June]#1\n" +
                "[Date].[ALL].[2020].[Q3].[July]#1\n" +
                "[Date].[ALL].[2020].[Q3].[August]#1\n" +
                "[Date].[ALL].[2020].[Q3].[September]#1\n" +
                "[Date].[ALL].[2020].[Q4].[October]#1\n" +
                "[Date].[ALL].[2020].[Q4].[November]#1\n" +
                "[Date].[ALL].[2020].[Q4].[December]#1\n" +
                "[Date].[ALL].[2021].[Q1].[January]#1.2\n" +
                "[Date].[ALL].[2021].[Q1].[February]#1.2\n" +
                "[Date].[ALL].[2021].[Q1].[March]#1.2\n" +
                "[Date].[ALL].[2021].[Q2].[April]#1.2\n" +
                "[Date].[ALL].[2021].[Q2].[May]#1.2\n" +
                "[Date].[ALL].[2021].[Q2].[June]#1.2\n" +
                "[Date].[ALL].[2021].[Q3].[July]#1.2\n" +
                "[Date].[ALL].[2021].[Q3].[August]#1.2\n" +
                "[Date].[ALL].[2021].[Q3].[September]#1.2\n" +
                "[Date].[ALL].[2021].[Q4].[October]#1.2\n" +
                "[Date].[ALL].[2021].[Q4].[November]#1.2\n" +
                "[Date].[ALL].[2021].[Q4].[December]#1.2\n" +
                "[Date].[ALL].[2022].[Q1].[January]#1.37\n" +
                "[Date].[ALL].[2022].[Q1].[February]#1.37\n" +
                "[Date].[ALL].[2022].[Q1].[March]#1.37\n" +
                "[Date].[ALL].[2022].[Q2].[April]#1.37\n" +
                "[Date].[ALL].[2022].[Q2].[May]#1.37\n" +
                "[Date].[ALL].[2022].[Q2].[June]#1.37\n" +
                "[Date].[ALL].[2022].[Q3].[July]#1.37\n" +
                "[Date].[ALL].[2022].[Q3].[August]#1.37\n" +
                "[Date].[ALL].[2022].[Q3].[September]#1.37\n" +
                "[Date].[ALL].[2022].[Q4].[October]#1.37\n" +
                "[Date].[ALL].[2022].[Q4].[November]#1.37\n" +
                "[Date].[ALL].[2022].[Q4].[December]#1.37\n" +
                "[Aircraft Type].[ALL].[Boeing].[Boeing 747]#1\n" +
                "[Aircraft Type].[ALL].[Boeing].[Boeing 777]#1.4\n" +
                "[Aircraft Type].[ALL].[Boeing].[Boeing 787 Dreamliner]#1.7\n" +
                "[Aircraft Type].[ALL].[Airbus].[Airbus A380]#1.9\n" +
                "[Aircraft Type].[ALL].[Airbus].[Airbus A350]#1.3\n" +
                "[Aircraft Type].[ALL].[Airbus].[Airbus A330]#0.85\n" +
                "[Aircraft Type].[ALL].[Embraer].[Embraer E-Jets]#1.2\n" +
                "[Aircraft Type].[ALL].[Bombardier].[Bombardier CRJ]#0.9\n" +
                "[Aircraft Type].[ALL].[Sukhoi].[Sukhoi Superjet]#0.4\n" +
                "[Service Type].[ALL].[Premium Economy Class]#0.7\n" +
                "[Service Type].[ALL].[Basic Economy Class]#1\n" +
                "[Service Type].[ALL].[Economy Plus]#0.9\n" +
                "[Service Type].[ALL].[Business Suite]#0.4\n" +
                "[Service Type].[ALL].[Premium Business]#0.25\n" +
                "[Service Type].[ALL].[First Class Suite]#0.19\n" +
                "[Service Type].[ALL].[Private Jet Charter]#0.1";
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
                serviceTypeDimension.add(member);
            }
        }
    }

    public static void main(String[] args) {

        Random random = new Random();

        for (Member dateMember : dateDimension) {
            for (Member aircraftMember : aircraftTypeDimension) {
                for (Member serviceMember : serviceTypeDimension) {
                    double turnover = dateMember.getWeight() * aircraftMember.getWeight() * serviceMember.getWeight() * (10_000_000 + random.nextDouble() * 4000_000);
                    String insVal = String.format("(%s, %s, %s measures [Turnover] %d),", dateMember.getPath(), aircraftMember.getPath(), serviceMember.getPath(), (int) turnover);

                    System.out.println(insVal);

                }
            }
        }
    }
}

class Member {
    private String path;
    private double weight;

    public Member(String path, double weight) {
        this.path = path;
        this.weight = weight;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}