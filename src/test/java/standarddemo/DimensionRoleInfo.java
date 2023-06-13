package standarddemo;

import lombok.Data;

@Data
public class DimensionRoleInfo {

    private String name;
    private CubeInfo cubeInfo;
    private DimensionInfo dimensionInfo;

    public DimensionRoleInfo(String name, CubeInfo cubeInfo, DimensionInfo dimensionInfo) {
        this.name = name;
        this.cubeInfo = cubeInfo;
        this.dimensionInfo = dimensionInfo;
    }
}
