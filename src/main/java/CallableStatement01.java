import java.sql.*;

public class CallableStatement01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
         /*

        Javada methodlar return type sahibi olsada olmasada method olarak adlandirilir.
        SQL de ise data return ediyorsa "function" denir. Return yapmiyorsa "procedure" olarak adlandirilir.

         */

        Class.forName ("org.postgresql.Driver");

        Connection con = DriverManager.getConnection ("jdbc:postgresql://localhost:5432/techproed","postgres","Faik9793.");

        Statement st = con.createStatement();

        //CallableStatement ile function cagirmayi parametrelendirecegiz.

        //1.Adim: Function fonksiyonunu yaz:

        String sql1 = "CREATE OR REPLACE FUNCTION toplamaF(x NUMERIC,y NUMERIC) RETURNS NUMERIC LANGUAGE plpgsql \n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "RETURN x+y;\n" +
                "\n" +
                "END\n" +
                "$$";
        //2.Adim: Function'i calistir.

        st.execute(sql1);

        //3.Adim: Fonction'i cagir.

        CallableStatement  cst1 = con.prepareCall("{? = call toplamaF(?, ?)}");

        //4.Adim: Return icin registerOurParameter() methodunu, parametreler icin ise set() ... methotlarini uygula.

       cst1.registerOutParameter(1,Types.NUMERIC);
       cst1.setInt(2,6);
       cst1.setInt(3,4);

        //5.Adim: execute() methodu ile CallableStatement'i calistir.

        cst1.execute();

        //6.Adim: Sonucu cagirmak icin return data type tipine gore

        System.out.println(cst1.getBigDecimal(1));


        // Konunun hacmini hesaplayan Fonction yazin?


        //1.Adim:

        String sql2 = "CREATE OR REPLACE FUNCTION konininHacmi(r NUMERIC,h NUMERIC) RETURNS NUMERIC LANGUAGE plpgsql \n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "RETURN 3.14*r*r*h/3;\n" +
                "\n" +
                "END\n" +
                "$$";

        //2.Adim:

        st.execute(sql2);

        //3.Adim:

        CallableStatement  cst2 = con.prepareCall("{? = call konininHacmi(?, ?)}");

        //4.Adim:

        cst2.registerOutParameter(1,Types.NUMERIC);
        cst2.setInt(2,1);
        cst2.setInt(3,6);

        //5.Adim:

        cst2.execute();

        //6.Adim:

        System.out.printf("%.2f",cst2.getBigDecimal(1));


    }
}
