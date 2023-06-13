package standarddemo;

import java.io.IOException;

public class BuildStandardDemoMainClass {

    public static void main(String[] args) throws IOException {
        DimensionInfo classesOfService = DimensionInfo.gen("Classes of Service", MemberInfo.classesOfServiceMembersInfo);
        DimensionInfo aircraftModels = DimensionInfo.gen("Aircraft Models", MemberInfo.aircraftModelsMembersInfo);
        DimensionInfo region = DimensionInfo.gen("Region", MemberInfo.regionMembersInfo);
        DimensionInfo date = DimensionInfo.gen("Date", MemberInfo.dateMembersInfo);

        CubeInfo airlineA = new CubeInfo("Airline A",
                new MemberInfo[]{new MemberInfo("Revenue", 1), new MemberInfo("Cost", 0.85)},
                35, 0.15);
        airlineA.addDimensionRole(new DimensionRoleInfo("Classes of Service", airlineA, classesOfService));
        airlineA.addDimensionRole(new DimensionRoleInfo("Aircraft Models", airlineA, aircraftModels));
        airlineA.addDimensionRole(new DimensionRoleInfo("Date", airlineA, date));

        CubeInfo airlineB = new CubeInfo("Airline B",
                new MemberInfo[]{new MemberInfo("Revenue", 1), new MemberInfo("Cost", 0.85)},
                1, 0.2);
        airlineB.addDimensionRole(new DimensionRoleInfo("Classes of Service", airlineB, classesOfService));
        airlineB.addDimensionRole(new DimensionRoleInfo("Aircraft Models", airlineB, aircraftModels));
        airlineB.addDimensionRole(new DimensionRoleInfo("Date", airlineB, date));
        airlineB.addDimensionRole(new DimensionRoleInfo("Starting Point", airlineB, region));
        airlineB.addDimensionRole(new DimensionRoleInfo("Ending Point", airlineB, region));

        airlineA.genInsertingStatement();
        airlineB.genInsertingStatement();

    }

}
