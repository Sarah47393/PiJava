/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservationvisite.service;

import reservationvisite.entity.Dons;
import reservationvisite.entity.Reservation;
import reservationvisite.entity.Visite;

/**
 *
 * @author Mortadha
 */
public interface MyListener {
    public void onClickListener(Dons d);
    public void onClickListener2(Visite v);
    public void onClickListener3(Reservation r);
}
