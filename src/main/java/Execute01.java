import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName ("org.postgresql.Driver");

        Connection con = DriverManager.getConnection ("jdbc:postgresql://localhost:5432/techproed","postgres","Faik9793.");

        Statement st = con.createStatement();

        System.out.println("Connection Success");

        /*

         */

        //1.Örnek: "workers" adında bir table oluşturup "worker_id,worker_name, worker_salary" sütunlarını ekleyin

        boolean sql1 = st.execute("CREATE TABLE workers(worker_id VARCHAR(20),worker_name VARCHAR(20),worker_salary INT)");

        System.out.println("sql1: " + sql1); // false return etti cunku data cagirmiyoruz.


        //2. Ornek : Table ye worker_adress sutunu ekleyerek alter yapin

        String sql2 = "ALTER TABLE workers ADD worker_address VARCHAR(80)";

        boolean sql2b = st.execute(sql2);

        System.out.println("sql2b : "+ sql2b);

         //3. Ornek workers table i silin

        String sql3 = "DROP TABLE workers";

        st.execute(sql3);


        // Baglanti ve statement i kapat

        con.close();
        st.close();


    }
}
