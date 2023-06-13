package standarddemo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DimensionInfo {

    private String name;
    private List<MemberInfo> memberInfoList = new ArrayList<>();

    public static DimensionInfo gen(String dimensionName, String[] membersInfo) {

        DimensionInfo dimension = new DimensionInfo();
        dimension.name = dimensionName;

        for (int i = 0; i < membersInfo.length; i += 2) {
            dimension.memberInfoList.add(new MemberInfo(membersInfo[i], Double.parseDouble(membersInfo[i + 1])));
        }

        return dimension;
    }
}
