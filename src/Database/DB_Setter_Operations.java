/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class DB_Setter_Operations {

    public static void submitHighscore(DB_Connect dbc, int u_id, int punkte) {
        PreparedStatement stmt = null;
        try {
            stmt = dbc.getTheConnection().prepareStatement("INSERT INTO snakedhbw.highscore (`score`, `user_id`) VALUES (?,?)");
            stmt.setInt(1, punkte);
            stmt.setInt(2, u_id);
        } catch (SQLException ex) {
        }
        dbc.executeSQLInsert(stmt);
    }

}
