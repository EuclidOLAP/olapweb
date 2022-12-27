package com.euclidolap.olapweb.meaval10000000;

import com.euclidolap.sdk.Terminal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MeaVals10000000 {

    static Dimension calendar;
    static Dimension goods;
    static Dimension storeType;
    static Dimension paymentMethod;
    static Dimension region;
    static Dimension transport;

    static String cubeName = "logistics.test";

    static DimensionRole roleGoods;
    static DimensionRole roleTransport;
    static DimensionRole roleStartingRegion;
    static DimensionRole roleEndingRegion;
    static DimensionRole roleStartingDate;
    static DimensionRole roleCompletionDate;

    static {
        calendar = new Dimension();
        calendar.setName("Calendar");
        calendar.setMemberInfoTxt("[Calendar].[ALL].[2019].[Q1].[M1],\n" +
                "[Calendar].[ALL].[2019].[Q1].[M2],\n" +
                "[Calendar].[ALL].[2019].[Q1].[M3],\n" +
                "[Calendar].[ALL].[2019].[Q2].[M4],\n" +
                "[Calendar].[ALL].[2019].[Q2].[M5],\n" +
                "[Calendar].[ALL].[2019].[Q2].[M6],\n" +
                "[Calendar].[ALL].[2019].[Q3].[M7],\n" +
                "[Calendar].[ALL].[2019].[Q3].[M8],\n" +
                "[Calendar].[ALL].[2019].[Q3].[M9],\n" +
                "[Calendar].[ALL].[2019].[Q4].[M10],\n" +
                "[Calendar].[ALL].[2019].[Q4].[M11],\n" +
                "[Calendar].[ALL].[2019].[Q4].[M12],\n" +
                "[Calendar].[ALL].[2020].[Q1].[M1],\n" +
                "[Calendar].[ALL].[2020].[Q1].[M2],\n" +
                "[Calendar].[ALL].[2020].[Q1].[M3],\n" +
                "[Calendar].[ALL].[2020].[Q2].[M4],\n" +
                "[Calendar].[ALL].[2020].[Q2].[M5],\n" +
                "[Calendar].[ALL].[2020].[Q2].[M6],\n" +
                "[Calendar].[ALL].[2020].[Q3].[M7],\n" +
                "[Calendar].[ALL].[2020].[Q3].[M8],\n" +
                "[Calendar].[ALL].[2020].[Q3].[M9],\n" +
                "[Calendar].[ALL].[2020].[Q4].[M10],\n" +
                "[Calendar].[ALL].[2020].[Q4].[M11],\n" +
                "[Calendar].[ALL].[2020].[Q4].[M12],\n" +
                "[Calendar].[ALL].[2021].[Q1].[M1],\n" +
                "[Calendar].[ALL].[2021].[Q1].[M2],\n" +
                "[Calendar].[ALL].[2021].[Q1].[M3],\n" +
                "[Calendar].[ALL].[2021].[Q2].[M4],\n" +
                "[Calendar].[ALL].[2021].[Q2].[M5],\n" +
                "[Calendar].[ALL].[2021].[Q2].[M6],\n" +
                "[Calendar].[ALL].[2021].[Q3].[M7],\n" +
                "[Calendar].[ALL].[2021].[Q3].[M8],\n" +
                "[Calendar].[ALL].[2021].[Q3].[M9],\n" +
                "[Calendar].[ALL].[2021].[Q4].[M10],\n" +
                "[Calendar].[ALL].[2021].[Q4].[M11],\n" +
                "[Calendar].[ALL].[2021].[Q4].[M12]");

        goods = new Dimension();
        goods.setName("Goods");
        goods.setMemberInfoTxt("[Goods].[ALL].[Kitchen & Dining].[Bento Boxes],\n" +
                "[Goods].[ALL].[Kitchen & Dining].[Food Savers],\n" +
                "[Goods].[ALL].[Kitchen & Dining].[Water Bottles],\n" +
                "[Goods].[ALL].[Kitchen & Dining].[Rice Cookers],\n" +
                "[Goods].[ALL].[Home Electronics].[LCD HDTVs],\n" +
                "[Goods].[ALL].[Home Electronics].[LED HDTVs],\n" +
                "[Goods].[ALL].[Home Electronics].[TV Boxes],\n" +
                "[Goods].[ALL].[Home Electronics].[Video Games],\n" +
                "[Goods].[ALL].[Cell Phones & Accessories].[iPhone],\n" +
                "[Goods].[ALL].[Cell Phones & Accessories].[Android Phones],\n" +
                "[Goods].[ALL].[Cell Phones & Accessories].[Headphones & Earphones],\n" +
                "[Goods].[ALL].[Cell Phones & Accessories].[Cell Phone Parts],\n" +
                "[Goods].[ALL].[Computers & Cameras].[Tablet PCs],\n" +
                "[Goods].[ALL].[Computers & Cameras].[Software],\n" +
                "[Goods].[ALL].[Computers & Cameras].[Desktops],\n" +
                "[Goods].[ALL].[Computers & Cameras].[Laptops & Netbooks],\n" +
                "[Goods].[ALL].[foods].[nut],\n" +
                "[Goods].[ALL].[foods].[wine],\n" +
                "[Goods].[ALL].[foods].[beef],\n" +
                "[Goods].[ALL].[electronic product].[mobile phone],\n" +
                "[Goods].[ALL].[electronic product].[computer],\n" +
                "[Goods].[ALL].[electronic product].[smart watch],\n" +
                "[Goods].[ALL].[household appliances].[washing machine],\n" +
                "[Goods].[ALL].[household appliances].[refrigerator],\n" +
                "[Goods].[ALL].[household appliances].[television]");

        storeType = new Dimension();
        storeType.setName("Store Type");
        storeType.setMemberInfoTxt("[Store Type].[ALL].[Platform Self-operated Store],\n" +
                "[Store Type].[ALL].[Brand Flagship Store],\n" +
                "[Store Type].[ALL].[Other]");

        paymentMethod = new Dimension();
        paymentMethod.setName("Payment Method");
        paymentMethod.setMemberInfoTxt("[Payment Method].[ALL].[Credit Card],\n" +
                "[Payment Method].[ALL].[Debit Card],\n" +
                "[Payment Method].[ALL].[Account Balance],\n" +
                "[Payment Method].[ALL].Other");

        region = new Dimension();
        region.setName("Region");
        region.setMemberInfoTxt("[Region].[ALL].[Asia].[China],\n" +
                "[Region].[ALL].[Asia].[Japan],\n" +
                "[Region].[ALL].[Asia].[South Korea],\n" +
                "[Region].[ALL].[America].[U.S],\n" +
                "[Region].[ALL].[America].[Mexico],\n" +
                "[Region].[ALL].[America].[Chile],\n" +
                "[Region].[ALL].[Europe].[Greece],\n" +
                "[Region].[ALL].[Europe].[Italy],\n" +
                "[Region].[ALL].[Europe].[UK]");

        transport = new Dimension();
        transport.setName("Transport");
        transport.setMemberInfoTxt("[Transport].[ALL].[railway],\n" +
                "[Transport].[ALL].[highway],\n" +
                "[Transport].[ALL].[aviation],\n" +
                "[Transport].[ALL].[ocean freight]");

        roleGoods = new DimensionRole();
        roleGoods.setName("Goods");
        roleGoods.setDimension(goods);

        roleTransport = new DimensionRole();
        roleTransport.setName("Transport");
        roleTransport.setDimension(transport);

        roleStartingRegion = new DimensionRole();
        roleStartingRegion.setName("starting region");
        roleStartingRegion.setDimension(region);

        roleEndingRegion = new DimensionRole();
        roleEndingRegion.setName("ending region");
        roleEndingRegion.setDimension(region);

        roleStartingDate = new DimensionRole();
        roleStartingDate.setName("starting date");
        roleStartingDate.setDimension(calendar);

        roleCompletionDate = new DimensionRole();
        roleCompletionDate.setName("completion date");
        roleCompletionDate.setDimension(calendar);
    }

    public static void main(String[] args) {

        System.out.println(roleGoods.getDimension().getMembersInfo().size()
                * roleTransport.getDimension().getMembersInfo().size()
                * roleStartingRegion.getDimension().getMembersInfo().size()
                * roleEndingRegion.getDimension().getMembersInfo().size()
                * roleStartingDate.getDimension().getMembersInfo().size()
                * roleCompletionDate.getDimension().getMembersInfo().size());

        /*
         * (
         *
         * Goods.        [ALL].[household appliances].[television],
         * Transport.    [ALL].[ocean freight],
         * [starting region].        [ALL].[Europe].[UK],
         * [ending region].          [ALL].[Europe].[Italy],
         * [starting date].              [ALL].[2021].[Q2].[M4],
         * [completion date].        [ALL].[2020].[Q1].[M1]
         *
         * measures quantity 9 income 88.88 cost 1 ),
         * */

        int count = 0;
        //StringBuilder mdxIns = new StringBuilder("insert [" + cubeName + "] ");
        //StringBuilder sep = new StringBuilder();
        //
        //List<String> sep = new ArrayList<>(6);

        String[] sep = new String[6];
        List<String> cells = new ArrayList<>(11000);

        Terminal terminal = new Terminal("192.168.66.235", 8760);
        terminal.connect();


        exit:
        for (String goodsMC : roleGoods.getDimension().getMembersInfo()) {
            sep[0] = "[" + roleGoods.getName() + "]." + goodsMC;
            //sep.append("[" + roleGoods.getName() + "]." + goodsMC);
            //mdxIns.append("([" + roleGoods.getName() + "]." + goodsMC);
            for (String transportMC : roleTransport.getDimension().getMembersInfo()) {
                sep[1] = "[" + roleTransport.getName() + "]." + transportMC;
                //sep.append("[" + roleTransport.getName() + "]." + transportMC);
                //mdxIns.append(", [" + roleTransport.getName() + "]." + transportMC);
                for (String startingRegionMC : roleStartingRegion.getDimension().getMembersInfo()) {
                    sep[2] = "[" + roleStartingRegion.getName() + "]." + startingRegionMC;
                    //sep.append("[" + roleStartingRegion.getName() + "]." + startingRegionMC);
                    //mdxIns.append(", [" + roleStartingRegion.getName() + "]." + startingRegionMC);
                    for (String endingRegionMC : roleEndingRegion.getDimension().getMembersInfo()) {
                        sep[3] = "[" + roleEndingRegion.getName() + "]." + endingRegionMC;
                        //sep.append("[" + roleEndingRegion.getName() + "]." + endingRegionMC);
                        //mdxIns.append(", [" + roleEndingRegion.getName() + "]." + endingRegionMC);
                        for (String startingDateMC : roleStartingDate.getDimension().getMembersInfo()) {
                            sep[4] = "[" + roleStartingDate.getName() + "]." + startingDateMC;
                            //sep.append("[" + roleStartingDate.getName() + "]." + startingDateMC);
                            //mdxIns.append(", [" + roleStartingDate.getName() + "]." + startingDateMC);
                            for (String completionDateMC : roleCompletionDate.getDimension().getMembersInfo()) {
                                sep[5] = "[" + roleCompletionDate.getName() + "]." + completionDateMC;
                                //sep.append("[" + roleCompletionDate.getName() + "]." + completionDateMC);

                                //System.out.println(String.join(", ", sep));
                                cells.add("(" + String.join(", ", sep) + " measures quantity 1 income 10 cost 0.1)");
                                ++count;

                                if (cells.size() >= 10000) {
                                    String insertMDX = "insert [logistics.test]\n" + String.join(",\n", cells) + ";";
                                    //System.out.println(insertMDX);
                                    System.out.println(count);
                                    terminal.exec(insertMDX);
                                    cells.clear();
                                    if (count >= 3000_0000)
                                        break exit;
                                    //System.exit(0);
                                }

                                //mdxIns.append(", [" + roleCompletionDate.getName() + "]." + completionDateMC + ")");
                                //++count;
                            }
                        }
                    }
                }
            }
        }
        if (cells.size() > 0) {
            String insertMDX = "insert [logistics.test]\n" + String.join(",\n", cells) + ";";
            terminal.exec(insertMDX);
        }
        terminal.close();
    }

}

class Dimension {
    private String memberInfoTxt;
    private String name;
    private List<String> membersInfo = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemberInfoTxt() {
        return memberInfoTxt;
    }

    public void setMemberInfoTxt(String memberInfoTxt) {
        this.memberInfoTxt = memberInfoTxt;
        String[] split = memberInfoTxt.replaceAll("\n", "").split(",");
        for (String str : split) {
            String[] split1 = str.split("\\.");
            List<String> mls = new ArrayList<>();
            mls.addAll(Arrays.asList(split1).subList(1, split1.length));
            membersInfo.add(String.join(".", mls));
        }
        //System.out.println(membersInfo);
    }

    public List<String> getMembersInfo() {
        return membersInfo;
    }

    public void setMembersInfo(List<String> membersInfo) {
        this.membersInfo = membersInfo;
    }
}

class DimensionRole {
    private Dimension dimension;
    private String name;

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
