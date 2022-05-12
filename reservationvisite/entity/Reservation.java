/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservationvisite.entity;

/**
 *
 * @author Mortadha
 */
public class Reservation {
    private int id;
    private int user_id;
    private int visite_id;

    public Reservation() {
    }

    public Reservation(int user_id, int visite_id) {
        this.user_id = user_id;
        this.visite_id = visite_id;
    }

    public Reservation(int id, int user_id, int visite_id) {
        this.id = id;
        this.user_id = user_id;
        this.visite_id = visite_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getVisite_id() {
        return visite_id;
    }

    public void setVisite_id(int visite_id) {
        this.visite_id = visite_id;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", user_id=" + user_id + ", visite_id=" + visite_id + '}'+"\n";
    }
    
}
