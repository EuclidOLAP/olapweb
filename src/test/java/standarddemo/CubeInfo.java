package standarddemo;

import com.google.common.io.CharSink;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class CubeInfo {

    private String name;
    private List<DimensionRoleInfo> dimensionRoleInfoList = new ArrayList<>();
    private MemberInfo[] measures;
    private double cubeWeight;
    private double threshold;

    public CubeInfo(String name, MemberInfo[] measures, double weight, double threshold) {
        this.name = name;
        this.measures = measures;
        this.cubeWeight = weight;
        this.threshold = threshold;
    }

    public void addDimensionRole(DimensionRoleInfo dimensionRoleInfo) {
        dimensionRoleInfoList.add(dimensionRoleInfo);
    }

    public void genInsertingStatement() throws IOException {
        String[] members = new String[dimensionRoleInfoList.size()];
        double[] measureWeight = new double[dimensionRoleInfoList.size()];

        CharSink charSink = Files.asCharSink(new File("demo-standard.txt"), StandardCharsets.UTF_8, FileWriteMode.APPEND);

        charSink.write("insert [" + name + "]\n");

        do_(0, members, measureWeight, charSink);
    }

    private void do_(int position, String[] members, double[] measureWeight, CharSink charSink) throws IOException {
        DimensionRoleInfo dimRole = dimensionRoleInfoList.get(position);

        for (MemberInfo memberInfo : dimRole.getDimensionInfo().getMemberInfoList()) {
            members[position] = "[" + dimRole.getName() + "]." + memberInfo.getPath();
            measureWeight[position] = memberInfo.getWeight();

            if (position == dimensionRoleInfoList.size() - 1) {

                if (Math.random() < threshold)
                    continue;

                String string = Arrays.toString(members);
                string = string.substring(1, string.length() - 1);

                string += " measures";
                for (MemberInfo measure : this.measures) {
                    double valueWeight = this.cubeWeight * measure.getWeight();
                    for (double w : measureWeight) {
                        valueWeight *= w;
                    }

                    double value = 100000 * valueWeight * (Math.random() * 0.4 + 0.8);

                    string += " [" + measure.getPath() + "] " + (((long) value) / 1000 * 100);
                }
                charSink.write("( " + string + " ),\n");
                //System.out.println("( " + string + " ),");
            } else {
                do_(position + 1, members, measureWeight, charSink);
            }
        }
    }
}
