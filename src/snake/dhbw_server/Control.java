/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snake.dhbw_server;

/**
 *
 * @author User
 */
public class Control {
    
    private ConnectionManager cmgr;
    
    public Control(){
        cmgr = new ConnectionManager(this, 1234);
    }
    
    
}
