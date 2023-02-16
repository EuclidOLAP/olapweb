package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AirlineCostOfFlight {

    static List<Member> dateDimension;
    static List<Member> aircraftTypeDimension;

    static {
        dateDimension = new ArrayList<>();
        aircraftTypeDimension = new ArrayList<>();
        String membersInfoText = "[Date].[ALL].[2020].[Q1].[January]#1.04442420598701\n" +
                "[Date].[ALL].[2020].[Q1].[February]#1.0479751942808\n" +
                "[Date].[ALL].[2020].[Q1].[March]#1.04736540563634\n" +
                "[Date].[ALL].[2020].[Q2].[April]#1.02933360580389\n" +
                "[Date].[ALL].[2020].[Q2].[May]#1.05585864986716\n" +
                "[Date].[ALL].[2020].[Q2].[June]#1.03960602106242\n" +
                "[Date].[ALL].[2020].[Q3].[July]#1.07686409109141\n" +
                "[Date].[ALL].[2020].[Q3].[August]#1.05120382895369\n" +
                "[Date].[ALL].[2020].[Q3].[September]#1.0653980937523\n" +
                "[Date].[ALL].[2020].[Q4].[October]#1.01157304666841\n" +
                "[Date].[ALL].[2020].[Q4].[November]#1.06408680226601\n" +
                "[Date].[ALL].[2020].[Q4].[December]#1.04164801644116\n" +
                "[Date].[ALL].[2021].[Q1].[January]#1.03961388909031\n" +
                "[Date].[ALL].[2021].[Q1].[February]#1.00147830605133\n" +
                "[Date].[ALL].[2021].[Q1].[March]#1.01872638814486\n" +
                "[Date].[ALL].[2021].[Q2].[April]#1.00771867131439\n" +
                "[Date].[ALL].[2021].[Q2].[May]#1.06768267515655\n" +
                "[Date].[ALL].[2021].[Q2].[June]#1.0904774313479\n" +
                "[Date].[ALL].[2021].[Q3].[July]#1.04837636364891\n" +
                "[Date].[ALL].[2021].[Q3].[August]#1.01060831900515\n" +
                "[Date].[ALL].[2021].[Q3].[September]#1.05769491761819\n" +
                "[Date].[ALL].[2021].[Q4].[October]#1.0463204985025\n" +
                "[Date].[ALL].[2021].[Q4].[November]#1.00326861066914\n" +
                "[Date].[ALL].[2021].[Q4].[December]#1.01138998808563\n" +
                "[Date].[ALL].[2022].[Q1].[January]#1.0378032975713\n" +
                "[Date].[ALL].[2022].[Q1].[February]#1.00275954570292\n" +
                "[Date].[ALL].[2022].[Q1].[March]#1.09935471140339\n" +
                "[Date].[ALL].[2022].[Q2].[April]#1.04837946423263\n" +
                "[Date].[ALL].[2022].[Q2].[May]#1.08904086209461\n" +
                "[Date].[ALL].[2022].[Q2].[June]#1.00948533624767\n" +
                "[Date].[ALL].[2022].[Q3].[July]#1.09527690790862\n" +
                "[Date].[ALL].[2022].[Q3].[August]#1.08572880936115\n" +
                "[Date].[ALL].[2022].[Q3].[September]#1.04713551181178\n" +
                "[Date].[ALL].[2022].[Q4].[October]#1.02778852214303\n" +
                "[Date].[ALL].[2022].[Q4].[November]#1.01421888965095\n" +
                "[Date].[ALL].[2022].[Q4].[December]#1.00304903522604\n" +
                "[Aircraft Type].[ALL].[Boeing].[Boeing 747]#0.3\n" +
                "[Aircraft Type].[ALL].[Boeing].[Boeing 777]#0.6\n" +
                "[Aircraft Type].[ALL].[Boeing].[Boeing 787 Dreamliner]#1\n" +
                "[Aircraft Type].[ALL].[Airbus].[Airbus A380]#1\n" +
                "[Aircraft Type].[ALL].[Airbus].[Airbus A350]#0.65\n" +
                "[Aircraft Type].[ALL].[Airbus].[Airbus A330]#0.4\n" +
                "[Aircraft Type].[ALL].[Embraer].[Embraer E-Jets]#0.45\n" +
                "[Aircraft Type].[ALL].[Bombardier].[Bombardier CRJ]#0.39\n" +
                "[Aircraft Type].[ALL].[Sukhoi].[Sukhoi Superjet]#0.42";

        for (String memberInfo : membersInfoText.split("\n")) {
            String[] split = memberInfo.split("#");
            String path = split[0];
            double weight = Double.parseDouble(split[1]);

            Member member = new Member(path, weight);

            if (path.contains("[Date].[ALL].")) {
                dateDimension.add(member);
            } else if (path.contains("[Aircraft Type].[ALL].")) {
                aircraftTypeDimension.add(member);
            }
        }

    }

    public static void main(String[] args) {

        Random random = new Random();

        for (Member date : dateDimension) {
            for (Member aircraftType : aircraftTypeDimension) {

                double randomFloat = date.getWeight() * aircraftType.getWeight();
                String insVal = String.format("(%s, %s measures " +
                                "[Fuel costs] %d " +
                                "[Crew costs] %d " +
                                "[Maintenance and repair costs] %d " +
                                "[Airport fees] %d " +
                                "[Insurance costs] %d " +
                                "[Administrative and overhead costs] %d " +
                                "[Taxes] %d " +
                                "[Catering and other in-flight services] %d),",
                        date.getPath(), aircraftType.getPath(),
                        (int) (randomFloat * (1800000 + random.nextDouble() * 180000)),
                        (int) (randomFloat * (1000000 + random.nextDouble() * 100000)),
                        (int) (randomFloat * (800000 + random.nextDouble() * 80000)),
                        (int) (randomFloat * (600000 + random.nextDouble() * 60000)),
                        (int) (randomFloat * (900000 + random.nextDouble() * 90000)),
                        (int) (randomFloat * (300000 + random.nextDouble() * 30000)),
                        (int) (randomFloat * (550000 + random.nextDouble() * 55000)),
                        (int) (randomFloat * (700000 + random.nextDouble() * 70000))
                );
                System.out.println(insVal);
            }
        }


    }

}
