/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Model;

import java.util.List;

/**
 *
 * @author ariel
 */
public interface ICRUD {
    
    public List list();
    public String add(User user);
    public String edit(User user);
    public String delete(String dni);
}
