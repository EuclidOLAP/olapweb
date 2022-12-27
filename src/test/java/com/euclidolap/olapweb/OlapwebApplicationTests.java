package com.euclidolap.olapweb;

import com.euclidolap.sdk.MultiDimResult;
import com.euclidolap.sdk.Terminal;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OlapwebApplicationTests {

    @Test
    void conn() {
        Terminal terminal = new Terminal("192.168.66.235", 8760);

        terminal.connect();

        String mdx = "select " +
                "{ ([Calendar].[2020].[Q1]), ([Calendar].[2020].[Q2]), ([Calendar].[2020].[Q3]), ([Calendar].[2020].[Q4]) } on 0, " +
                "{ ([Payment Method].[Credit Card], measure.[sales amount]), ([Payment Method].[Debit Card], measure.[sales amount]), ([Payment Method].[Account Balance], measure.[sales amount]) } on 1 " +
                "from [Online Store];";

        for (int i = 0; i < 666; i++) {
            MultiDimResult result = (MultiDimResult) terminal.exec(mdx);
            result.show(System.out);
        }

        terminal.close();
    }

    @Test
    void insertMeasureValues() {
        Terminal terminal = new Terminal("192.168.66.235", 8760);
        terminal.connect();
        MultiDimResult result = (MultiDimResult) terminal.exec("insert [Online Store] " +
                "(" +
                "[Store Type].[ALL].[Platform Self-operated Store],[Payment Method].[ALL].[Credit Card],[Goods].[ALL].[Kitchen & Dining].[Bento Boxes],[Calendar].[ALL].[2020].[Q1].[M1] " +
                "measures [sales amount] 10 [sales quantity] 1 " +
                ");");

        if (result != null)
            result.show(System.out);
        terminal.close();
    }

}
