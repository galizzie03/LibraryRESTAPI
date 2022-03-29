import com.google.gson.Gson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@Path("/loan")
public class GestioneP {
    private final String error = "Server error, contact administrators";
    private boolean checkParams(String idutente,String idlibro, String iniziop, String finep){
        return (idutente == null || idutente.trim().length() == 0) || (idlibro == null || idlibro.trim().length() == 0) || (iniziop == null || iniziop.trim().length() == 0) || (finep == null || finep.trim().length() == 0);
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response read(){
        final String QUERY = "SELECT * FROM Prestiti";
        final List<Loan> loans = new ArrayList<>();
        final String[] data = Database.getData();
        try(

                Connection conn = DriverManager.getConnection(data[0]);
                PreparedStatement pstmt = conn.prepareStatement( QUERY )
        ) {
            ResultSet results =  pstmt.executeQuery();
            while (results.next()){
                Loan loan = new Loan();
                loan.setIDUtente(results.getString("IDUtente"));
                loan.setIDLibro(results.getString("IDLibro"));
                loan.setInizioP(results.getString("InizioP"));
                loan.setFineP(results.getString("FineP"));
                loans.add(loan);

            }
        }catch (SQLException e){
            e.printStackTrace();
            String obj = new Gson().toJson(error);
            return Response.serverError().entity(obj).build();
        }
        String obj = new Gson().toJson(loans);
        return Response.status(200).entity(obj).build();
    }
}
