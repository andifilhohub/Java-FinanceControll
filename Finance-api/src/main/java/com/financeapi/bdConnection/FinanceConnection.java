import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FinanceConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/financecontroll";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "postgres";

    public static void main(String[] args) {
        try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            System.out.println("Pong");
	  if (conexao != null) { 
		  try (Statement stm = conexao.createStatement()) {
    System.out.println("Banco Pong");
    //consultData(stm);
}

	  }
	  else {
		  System.out.println ("conexao falhou!");
	  }
        } catch (SQLException e) {
            System.out.println("Not Pong: " + e.getMessage());
        }
    }

    
    // public static void consultData (Statement stm){
	//     String sql = "select isbn, titulo from titulos";
	//  try (ResultSet resultado = stm.executeQuery(sql)){
    //         while(resultado.next()){
    //             System.out.println("ISBN:" + resultado.getString("isbn") + 
    //                                    "Titulo:" + resultado.getString("titulo"));
	//     }}
	//     catch (SQLException e) {
	//     System.out.println("not Pong: " + e.getMessage());
	//     }
    
    // }
}

